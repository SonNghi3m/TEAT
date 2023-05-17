package g45_lexicon.teat.model.dto;

import lombok.Data;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
@Data
public class EmojiDto {
    private Integer id;
    @NotEmpty(message = "emojiName should not empty")
    @Size(min = 2, max = 50, message = "emojiName length should be between 2-50")
    private String emojiName;
    @NotEmpty(message = "emojiPath should not empty")
    private String emojiPath;
    @NotNull
    @Valid
    private List<MessageDto> messages;
}
