package g45_lexicon.teat.model.dto;

import lombok.Data;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class RoleDto {
    private Integer id;
    @NotEmpty(message = "roleTitle should not empty")
    @Size(min = 2, max = 20, message = "roleTitle length should be between 2-20")
    private String roleTitle;
    @NotNull
    @Valid
    private Set<UserDto> users;
    @NotNull
    @Valid
    private Set<PermissionDto> permissions;
}
