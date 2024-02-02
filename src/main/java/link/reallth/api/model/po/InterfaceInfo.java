package link.reallth.api.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * interface info
 *
 * @author ReAllTh
 */
@TableName(value = "interface_info")
@Data
public class InterfaceInfo implements Serializable {
    /**
     *
     */
    @TableId
    private String id;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private String description;

    /**
     *
     */
    private String url;

    /**
     *
     */
    private String requestParams;

    /**
     *
     */
    private String requestHeader;

    /**
     *
     */
    private String responseHeader;

    /**
     * status: - 0 close - 1 open
     */
    private Integer status;

    /**
     *
     */
    private String method;

    /**
     * creator id
     */
    private String creatorId;

    /**
     *
     */
    private Date createTime;

    /**
     *
     */
    private Date updateTime;

    /**
     *
     */
    private Integer deleted;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}