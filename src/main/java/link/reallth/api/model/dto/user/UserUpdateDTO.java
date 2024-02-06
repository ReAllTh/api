package link.reallth.api.model.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import link.reallth.api.constant.enums.ROLES;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import static link.reallth.api.constant.ValidateConst.*;

/**
 * user update data transfer object
 *
 * @author ReAllTh
 */
@Data
public class UserUpdateDTO {

    @NotBlank
    @Length(min = 32, max = 32, message = INVALID_MSG_ID)
    private String id;

    @Pattern(regexp = REGEX_USERNAME, message = INVALID_MSG_USERNAME)
    private String username;

    @Length(min = 2, max = 16)
    private String nickname;

    @Pattern(regexp = REGEX_PASSWORD, message = INVALID_MSG_PASSWORD)
    private String password;

    @URL(message = INVALID_MSG_URL)
    private String avatar;

    private ROLES role;

}