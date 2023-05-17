package g45_lexicon.teat.repository;

import g45_lexicon.teat.model.entity.Attachment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AttachmentRepository extends CrudRepository<Attachment, Integer> {
    Optional<Attachment> findByFileName(String fileName);
}
