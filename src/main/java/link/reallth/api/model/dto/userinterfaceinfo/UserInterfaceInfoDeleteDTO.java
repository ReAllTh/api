package link.reallth.api.model.dto.userinterfaceinfo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import static link.reallth.api.constant.ValidateConst.INVALID_MSG_ID;

/**
 * user interface info delete data transfer object
 *
 * @author ReAllTh
 */
@Data
public class UserInterfaceInfoDeleteDTO {
    @NotBlank
    @Length(min = 32, max = 32, message = INVALID_MSG_ID)
    private String id;
}
