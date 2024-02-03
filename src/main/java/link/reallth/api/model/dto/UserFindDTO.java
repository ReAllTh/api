package link.reallth.api.model.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.util.Date;

import static link.reallth.api.constant.ValidateConst.INVALID_MSG_USERNAME;
import static link.reallth.api.constant.ValidateConst.REGEX_USERNAME;

/**
 * user find data transfer object
 *
 * @author ReAllTh
 */
@Data
public class UserFindDTO {

    private String id;
    @Pattern(regexp = REGEX_USERNAME, message = INVALID_MSG_USERNAME)
    private String username;

    private Integer role;
    private Date createTimeFrom;
    private Date createTimeTo;

    private Integer page;
    private Integer pageSize;

}
