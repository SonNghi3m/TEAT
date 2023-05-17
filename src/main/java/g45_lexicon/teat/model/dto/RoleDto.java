package g45_lexicon.teat.model.dto;

import lombok.Data;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class RoleDto {
    private Integer id;
    @NotEmpty(message = "roleTitle should not empty")
    @Size(min = 2, max = 20, message = "roleTitle length should be between 2-20")
    private String roleTitle;
    @Valid
    private Set<PermissionDto> permissions;

}
