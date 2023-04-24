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
    public AttachmentDto create(AttachmentDto obj) {
        if (obj == null) throw new IllegalArgumentException("Attachment was null");
        if (obj.getId() != 0) throw new IllegalArgumentException("Attachment id should be null or zero");
        if (attachmentRepository.existsById(obj.getId())) throw new DataDuplicateException("Attachment already existed");
        Attachment createdEntity = attachmentRepository.save(modelMapper.map(obj, Attachment.class));
        return modelMapper.map(createdEntity, AttachmentDto.class);
    }

    @Override
    public void update(AttachmentDto obj) {
        if (obj == null) throw new IllegalArgumentException("Attachment data was null");
        if (obj.getId() == 0) throw new IllegalArgumentException("Attachment id should not be zero");
        if (!attachmentRepository.findById(obj.getId()).isPresent())
            throw new DataNotFoundException("data not found error");
        if (attachmentRepository.findByFileName(obj.getFileName()).isPresent())
            throw new DataDuplicateException("duplicate fileName error");
        attachmentRepository.save(modelMapper.map(obj, Attachment.class));
    }

    @Override
    public void delete(Integer id) {
        AttachmentDto obj = findById(id);
        if (obj == null) throw new DataNotFoundException("id was not valid");
        attachmentRepository.deleteById(id);
    }
}
