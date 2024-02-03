package link.reallth.api.model.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

import static link.reallth.api.constant.ValidateConst.*;

/**
 * sign in data transfer object
 *
 * @author ReAllTh
 */
@Data
public class UserSignInDTO {

    @Pattern(regexp = REGEX_USERNAME, message = INVALID_MSG_USERNAME)
    private String username;
    @Pattern(regexp = REGEX_PASSWORD, message = INVALID_MSG_PASSWORD)
    private String password;

}
