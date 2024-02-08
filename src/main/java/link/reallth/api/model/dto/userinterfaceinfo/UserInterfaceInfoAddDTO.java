package link.reallth.api.model.dto.userinterfaceinfo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import static link.reallth.api.constant.ValidateConst.INVALID_MSG_ID;
import static link.reallth.api.constant.ValidateConst.INVALID_MSG_INTERFACE_LEFT_NUM;

/**
 * user interface info add data transfer object
 *
 * @author ReAllTh
 */
@Data
public class UserInterfaceInfoAddDTO {

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
