package g45_lexicon.teat.service;

import g45_lexicon.teat.exception.DataDuplicateException;
import g45_lexicon.teat.exception.DataNotFoundException;
import g45_lexicon.teat.model.dto.AttachmentDto;

import java.util.List;

public interface AttachmentService {
    List<AttachmentDto> getAll();
    AttachmentDto findById(Integer id) throws DataNotFoundException;
    AttachmentDto findByName(String name) throws DataNotFoundException;
    AttachmentDto create(AttachmentDto obj) throws DataDuplicateException;
    AttachmentDto update(AttachmentDto obj) throws DataNotFoundException, DataDuplicateException;
    void delete(Integer id) throws DataNotFoundException;
}
