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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttachmentServiceImpl implements AttachmentService{
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public List<AttachmentDto> getAll() {
        List<Attachment> list = new ArrayList<>();
        attachmentRepository.findAll().iterator().forEachRemaining(list::add);
        return list.stream().map(category -> modelMapper.map(category, AttachmentDto.class)).collect(Collectors.toList());
    }

    @Override
    public AttachmentDto findById(Integer id) throws DataNotFoundException {
        if (id == null) throw new IllegalArgumentException("Attachment id was null");
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (!optionalAttachment.isPresent()) throw new DataNotFoundException("Attachment id was not valid");
        Attachment attachment = optionalAttachment.get();
        return modelMapper.map(attachment, AttachmentDto.class);
    }

    @Override
    public AttachmentDto findByName(String name) throws DataNotFoundException {
        if (name == null) throw new IllegalArgumentException("Attachment name was null");
        Optional<Attachment> optionalAttachment = attachmentRepository.findByFileName(name);
        if (!optionalAttachment.isPresent()) throw new DataNotFoundException("Attachment name was not valid");
        Attachment attachment = optionalAttachment.get();
        return modelMapper.map(attachment, AttachmentDto.class);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public AttachmentDto create(AttachmentDto attachmentDto) throws DataDuplicateException {
        if (attachmentDto == null) throw new IllegalArgumentException("Attachment was null");
        if (attachmentDto.getId() != 0) throw new IllegalArgumentException("Attachment id should be null or zero");
        if (attachmentRepository.existsById(attachmentDto.getId())) throw new DataDuplicateException("Attachment already existed");
        Attachment createdEntity = attachmentRepository.save(modelMapper.map(attachmentDto, Attachment.class));
        return modelMapper.map(createdEntity, AttachmentDto.class);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public AttachmentDto update(AttachmentDto attachmentDto) throws DataNotFoundException, DataDuplicateException {
        if (attachmentDto == null) throw new IllegalArgumentException("Attachment data was null");
        if (attachmentDto.getId() == 0) throw new IllegalArgumentException("Attachment id should not be zero");
        if (!attachmentRepository.findById(attachmentDto.getId()).isPresent())
            throw new DataNotFoundException("Data not found error");
        if (attachmentRepository.findByFileName(attachmentDto.getFileName()).isPresent())
            throw new DataDuplicateException("Duplicate fileName error");
        Attachment result = attachmentRepository.save(modelMapper.map(attachmentDto, Attachment.class));
        return modelMapper.map(result, AttachmentDto.class);
    }

    @Override
    public void delete(Integer id) throws DataNotFoundException {
        AttachmentDto attachmentDto = findById(id);
        if (attachmentDto == null) throw new DataNotFoundException("Id was not valid");
        attachmentRepository.deleteById(id);
    }
}
