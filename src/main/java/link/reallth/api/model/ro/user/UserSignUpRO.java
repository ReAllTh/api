package link.reallth.api.model.ro.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import static link.reallth.api.constant.ValidateConst.*;

/**
 * user sign up request object
 *
 * @author ReAllTh
 */
@Data
public class UserSignUpRO {

    @NotBlank(message = INVALID_MSG_USERNAME_BLANK)
    @Pattern(regexp = REGEX_USERNAME, message = INVALID_MSG_USERNAME)
    private String username;

    @Length(min = 2, max = 16)
    private String nickname;

    @NotBlank(message = INVALID_MSG_PASSWORD_BLANK)
    @Pattern(regexp = REGEX_PASSWORD, message = INVALID_MSG_PASSWORD)
    private String password;

    @URL(message = INVALID_MSG_URL)
    private String avatar;

}
