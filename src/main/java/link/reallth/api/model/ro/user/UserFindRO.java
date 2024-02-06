package link.reallth.api.model.ro.user;

import jakarta.validation.constraints.*;
import link.reallth.api.constant.enums.ROLES;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

import static link.reallth.api.constant.ValidateConst.*;

/**
 * user find request object
 *
 * @author ReAllTh
 */
@Data
public class UserFindRO {

    @Length(min = 32, max = 32, message = INVALID_MSG_ID)
    private String id;

    @Pattern(regexp = REGEX_USERNAME, message = INVALID_MSG_USERNAME)
    private String username;

    private ROLES role;

    @Past(message = INVALID_MSG_TIME)
    private Date createTimeFrom;

    @Past(message = INVALID_MSG_TIME)
    private Date createTimeTo;

    @NotNull
    @Min(value = 1, message = INVALID_MSG_PAGE)
    private Integer page;

    @NotNull
    @Min(value = 5, message = INVALID_MSG_PAGE_SIZE)
    @Max(value = 100, message = INVALID_MSG_PAGE_SIZE)
    private Integer pageSize;

}