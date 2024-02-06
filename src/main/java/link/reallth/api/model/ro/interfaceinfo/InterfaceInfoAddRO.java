package link.reallth.api.model.ro.interfaceinfo;

import link.reallth.api.model.vo.UserVO;
import lombok.Data;

import java.util.Date;

/**
 * interface info add request object
 *
 * @author ReAllTh
 */
@Data
public class InterfaceInfoAddRO {
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
}
