package link.reallth.api.model.ro;

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

    @Pattern(regexp = REGEX_USERNAME, message = INVALID_MSG_USERNAME)
    private String username;
    @Pattern(regexp = REGEX_PASSWORD, message = INVALID_MSG_PASSWORD)
    private String password;

}