package link.reallth.api.model.vo;

import link.reallth.api.constant.enums.METHOD;
import link.reallth.api.constant.enums.STATUS;
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
    private STATUS status;
    private METHOD method;
    private UserVO creator;
    private Date createTime;

    @Serial
    private static final long serialVersionUID = 1L;
}
