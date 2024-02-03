package link.reallth.api.model.vo;

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
    private Integer role;
    private String accessKey;
    private String secretKey;
    private Date createTime;

    @Serial
    private static final long serialVersionUID = 1L;
}