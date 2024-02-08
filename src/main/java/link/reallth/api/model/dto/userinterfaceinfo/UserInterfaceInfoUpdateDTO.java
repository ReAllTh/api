package link.reallth.api.model.dto.userinterfaceinfo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import link.reallth.api.constant.enums.BANNED;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import static link.reallth.api.constant.ValidateConst.INVALID_MSG_ID;
import static link.reallth.api.constant.ValidateConst.INVALID_MSG_INTERFACE_LEFT_NUM;

/**
 * user interface info update data transfer object
 *
 * @author ReAllTh
 */
@Data
public class UserInterfaceInfoUpdateDTO {

    @NotBlank
    @Length(min = 32, max = 32, message = INVALID_MSG_ID)
    private String id;

    @Min(value = 0, message = INVALID_MSG_INTERFACE_LEFT_NUM)
    private Integer leftNum;

    private BANNED banned;
}
