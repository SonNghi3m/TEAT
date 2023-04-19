package g45_lexicon.teat.model.dto;

import g45_lexicon.teat.model.entity.Attachment;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class MessageDto {
    private Integer id;
    @NotNull
    @Valid
    private UserDto sender;
    private String textContent;
    @NotNull
    @Valid
    private List<EmojiDto> emojis;
    @NotNull
    @Valid
    private List<Attachment> attachments;
    @NotNull
    @Valid
    private ConversationDto conversation;
    @NotNull
    private boolean editedStatus;
    @NotNull
    private boolean deletedStatus;
    @NotNull
    private LocalDateTime timestamp;

}
