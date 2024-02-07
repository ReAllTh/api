package link.reallth.api.model.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import static link.reallth.api.constant.ValidateConst.*;

/**
 * sign up data transfer object
 *
 * @author ReAllTh
 */
@Data
public class UserSignUpDTO {

    @NotBlank
    @Pattern(regexp = REGEX_USERNAME, message = INVALID_MSG_USERNAME)
    private String username;

    @Length(min = 2, max = 16)
    private String nickname;

    @NotBlank
    @Pattern(regexp = REGEX_PASSWORD, message = INVALID_MSG_USER_PASSWORD)
    private String password;

    @URL(message = INVALID_MSG_URL)
    private String avatar;

}