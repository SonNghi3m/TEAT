package g45_lexicon.teat.service;

import g45_lexicon.teat.model.dto.AttachmentDto;

import java.util.List;

public interface AttachmentService {
    List<AttachmentDto> getAll();
    AttachmentDto findById(Integer id);
    AttachmentDto create(AttachmentDto obj);
    void update(AttachmentDto obj);
    void delete(Integer id);
}
