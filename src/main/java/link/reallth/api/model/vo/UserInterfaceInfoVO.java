package link.reallth.api.model.vo;

import link.reallth.api.constant.enums.BANNED;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * user interface info view object
 *
 * @author ReAllTh
 */
@Data
public class UserInterfaceInfoVO implements Serializable {

    private String id;
    private InterfaceInfoVO interfaceInfo;
    private UserVO user;
    private Integer leftNum;
    private BANNED banned;

    @Serial
    private static final long serialVersionUID = 1L;
}
