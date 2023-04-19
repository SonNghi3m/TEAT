package g45_lexicon.teat.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
public class ConversationDto {
    private Integer id;
    @NotEmpty(message = "chatName should not empty")
    @Size(min = 2, max = 100, message = "chatName length should be between 2-100")
    private String chatName;
    @NotNull
    @Valid
    private List<UserDto> participants;
    @NotNull
    @Valid
    private Set<MessageDto> messages;
    @NotNull
    private LocalDateTime timestamp;

}
