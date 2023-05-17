package g45_lexicon.teat.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data

public class UserDto {
    private Integer id;
    @NotEmpty(message = "FirstName should not empty")
    @Size(min = 2, max = 40, message = "FirstName length should be between 2-40")
    private String firstName;
    @NotEmpty(message = "LastName should not be empty")
    @Size(min = 2, max = 40, message = "LastName length should be between 2-40")
    private String lastName;
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;
    @NotEmpty(message = "Username should not be empty")
    @Size(min = 4, max = 50, message = "Username length should be between 4-50")
    private String username;
    @NotEmpty(message = "Password should not be empty")
    @Size(min = 6, max = 20, message = "Password length should be between 6-20")
    private String password;
    @Valid
    private RoleDto role;
    @JsonIgnore
    @Valid
    private List<MessageDto> messages;
    @JsonIgnore
    @Valid
    private List<ConversationDto> conversations;
//    @JsonIgnore
//    @Valid
//    private List<EventDto> events;
}

