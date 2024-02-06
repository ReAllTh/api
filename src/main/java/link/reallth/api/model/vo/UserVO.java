package link.reallth.api.model.vo;

import link.reallth.api.constant.enums.ROLES;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * user view object
 *
 * @author ReAllTh
 */
@Data
public class UserVO implements Serializable {

    private String id;
    private String username;
    private String nickname;
    private String avatar;
    private ROLES role;
    private Date createTime;

    @Serial
    private static final long serialVersionUID = 1L;
}