package g45_lexicon.teat.model.dto;

import com.fasterxml.jackson.annotation.*;
import g45_lexicon.teat.model.entity.Attachment;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class MessageDto {
    private Integer id;
    @NotNull
    @Valid
    private UserDto sender;
    private String textContent;
    @Valid
    private List<EmojiDto> emojis;
    @Valid
    private List<Attachment> attachments;
    @JsonIgnoreProperties("messages")
    @Valid
    private ConversationDto conversation;

    private boolean editedStatus;
    private boolean deletedStatus;
    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

}
