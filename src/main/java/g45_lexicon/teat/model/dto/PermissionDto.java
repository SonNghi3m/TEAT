package g45_lexicon.teat.model.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
@Data
public class PermissionDto {
    private Integer id;
    @NotEmpty(message = "permissionName should not empty")
    @Size(min = 2, max = 50, message = "permissionName length should be between 2-50")
    private String permissionName;
    @NotEmpty(message = "description should not empty")
    private String description;
//    @Valid
//    private Set<RoleDto> roles;
}
