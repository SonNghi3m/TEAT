package g45_lexicon.teat.model.dto;

import lombok.Data;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class AttachmentDto {
    private Integer id;
    @NotEmpty(message = "fileName should not empty")
    @Size(min = 2, max = 50, message = "fileName length should be between 2-50")
    private String fileName;
    @NotEmpty(message = "filePath should not empty")
    private String filePath;
    @NotNull
    @Valid
    private List<MessageDto> messages;

}
