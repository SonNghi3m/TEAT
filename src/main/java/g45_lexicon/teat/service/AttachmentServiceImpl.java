package g45_lexicon.teat.service;

import g45_lexicon.teat.exception.DataDuplicateException;
import g45_lexicon.teat.exception.DataNotFoundException;
import g45_lexicon.teat.model.dto.AttachmentDto;
import g45_lexicon.teat.model.entity.Attachment;
import g45_lexicon.teat.repository.AttachmentRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AttachmentServiceImpl implements AttachmentService{
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public List<AttachmentDto> getAll() {
        List<Attachment> attachmentList = attachmentRepository.findAllByOrOrderByIdDesc();
        return modelMapper.map(attachmentList, new TypeToken<List<AttachmentDto>>(){}.getType());
    }

    @Override
    public AttachmentDto findById(Integer id) {
        if (id == null) throw new IllegalArgumentException("Attachment id was null");
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (!optionalAttachment.isPresent()) throw new DataNotFoundException("Attachment id was not valid");
        Attachment attachment = optionalAttachment.get();
        return modelMapper.map(attachment, AttachmentDto.class);
    }

    @Override
    public AttachmentDto create(AttachmentDto attachmentDto) {
        if (attachmentDto == null) throw new IllegalArgumentException("Attachment was null");
        if (attachmentDto.getId() != 0) throw new IllegalArgumentException("Attachment id should be null or zero");
        if (attachmentRepository.existsById(attachmentDto.getId())) throw new DataDuplicateException("Attachment already existed");
        Attachment createdEntity = attachmentRepository.save(modelMapper.map(attachmentDto, Attachment.class));
        return modelMapper.map(createdEntity, AttachmentDto.class);
    }

    @Override
    public void update(AttachmentDto attachmentDto) {
        if (attachmentDto == null) throw new IllegalArgumentException("Attachment data was null");
        if (attachmentDto.getId() == 0) throw new IllegalArgumentException("Attachment id should not be zero");
        if (!attachmentRepository.findById(attachmentDto.getId()).isPresent())
            throw new DataNotFoundException("Data not found error");
        if (attachmentRepository.findByFileName(attachmentDto.getFileName()).isPresent())
            throw new DataDuplicateException("Duplicate fileName error");
        attachmentRepository.save(modelMapper.map(attachmentDto, Attachment.class));
    }

    @Override
    public void delete(Integer id) {
        AttachmentDto attachmentDto = findById(id);
        if (attachmentDto == null) throw new DataNotFoundException("Id was not valid");
        attachmentRepository.deleteById(id);
    }
}
