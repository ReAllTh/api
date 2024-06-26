package link.reallth.api.model.ro.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import static link.reallth.api.constant.ValidateConst.*;

/**
 * user sign in request object
 *
 * @author ReAllTh
 */
@Data
public class UserSignInRO {

    @NotBlank(message = INVALID_MSG_USERNAME_BLANK)
    @Pattern(regexp = REGEX_USERNAME, message = INVALID_MSG_USERNAME)
    private String username;

    @NotBlank(message = INVALID_MSG_PASSWORD_BLANK)
    @Pattern(regexp = REGEX_PASSWORD, message = INVALID_MSG_USER_PASSWORD)
    private String password;

}