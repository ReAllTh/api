package link.reallth.api.model.ro;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import static link.reallth.api.constant.ValidateConst.*;

/**
 * user sign up request object
 *
 * @author ReAllTh
 */
@Data
public class UserSignUpRO {

    @Pattern(regexp = REGEX_USERNAME, message = INVALID_MSG_USERNAME)
    private String username;
    @Pattern(regexp = REGEX_PASSWORD, message = INVALID_MSG_PASSWORD)
    private String password;
    @URL(message = INVALID_MSG_URL)
    private String avatar;

}
