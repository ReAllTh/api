package link.reallth.api.model.ro.userinterfaceinfo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import static link.reallth.api.constant.ValidateConst.INVALID_MSG_ID;
import static link.reallth.api.constant.ValidateConst.INVALID_MSG_INTERFACE_LEFT_NUM;

/**
 * user interface info add request object
 *
 * @author ReAllTh
 */
@Data
public class UserInterfaceInfoAddRO {
    @NotBlank
    @Length(min = 32, max = 32, message = INVALID_MSG_ID)
    private String userId;

    @NotBlank
    @Length(min = 32, max = 32, message = INVALID_MSG_ID)
    private String interfaceId;

    @NotNull
    @Min(value = 0, message = INVALID_MSG_INTERFACE_LEFT_NUM)
    private Integer leftNum;
}
