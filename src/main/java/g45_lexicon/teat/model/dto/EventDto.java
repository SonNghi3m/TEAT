package g45_lexicon.teat.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class EventDto {
    private Integer id;
    @NotEmpty(message = "eventName should not empty")
    @Size(min = 2, max = 100, message = "eventName length should be between 2-100")
    private String eventName;
    @NotEmpty(message = "description should not empty")
    private String description;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss a")
    private LocalDateTime startTime;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss a")
    private LocalDateTime endTime;
    @NotNull
    @Valid
    private UserDto organizer;
//    @Valid
//    private Set<UserDto> attendees;

}
