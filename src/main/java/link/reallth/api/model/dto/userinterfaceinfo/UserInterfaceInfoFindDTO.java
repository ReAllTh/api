package link.reallth.api.model.dto.userinterfaceinfo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import static link.reallth.api.constant.ValidateConst.*;

/**
 * user interface info find data transfer object
 *
 * @author ReAllTh
 */
@Data
public class UserInterfaceInfoFindDTO {

    @Length(min = 32, max = 32, message = INVALID_MSG_ID)
    private String id;

    @Length(min = 32, max = 32, message = INVALID_MSG_ID)
    private String userId;

    @Length(min = 32, max = 32, message = INVALID_MSG_ID)
    private String interfaceId;

    @Min(value = 0, message = INVALID_MSG_INTERFACE_LEFT_NUM)
    private Integer leftNumLeast;

    @Min(value = 0, message = INVALID_MSG_INTERFACE_LEFT_NUM)
    private Integer leftNumMax;

    @NotNull
    @Min(value = 1, message = INVALID_MSG_PAGE)
    private Integer page;

    @NotNull
    @Min(value = 5, message = INVALID_MSG_PAGE_SIZE)
    @Max(value = 100, message = INVALID_MSG_PAGE_SIZE)
    private Integer pageSize;
}
