package link.reallth.api.model.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
public class InterfaceInfoVO implements Serializable {
    private String id;
    private String name;
    private String description;
    private String url;
    private String requestParams;
    private String requestHeader;
    private String responseHeader;

    /**
     * status: - 0 close - 1 open
     */
    private Integer status;
    private String method;
    private UserVO creator;
    private Date createTime;

    @Serial
    private static final long serialVersionUID = 1L;
}
