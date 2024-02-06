package link.reallth.api.model.ro.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import static link.reallth.api.constant.ValidateConst.INVALID_MSG_ID;

/**
 * user delete request object
 *
 * @author ReAllTh
 */
@Data
public class UserDeleteRO {
    @NotBlank
    @Length(min = 32, max = 32, message = INVALID_MSG_ID)
    private String id;
}
