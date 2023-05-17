package g45_lexicon.teat.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class ConversationDto {
    private Integer id;
    @NotNull(message = "chatName should not empty")
    @Size(min = 2, max = 100, message = "chatName length should be between 2-100")
    private String chatName;
    @Valid
    private List<UserDto> participants;
    @JsonIgnoreProperties("conversation")
    @Valid
    private List<MessageDto> messages;
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

}
