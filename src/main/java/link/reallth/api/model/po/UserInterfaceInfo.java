package link.reallth.api.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * user interface relation
 */
@TableName(value = "user_interface_info")
@Data
public class UserInterfaceInfo implements Serializable {
    /**
     *
     */
    @TableId
    private String id;

    /**
     *
     */
    private String userId;

    /**
     *
     */
    private String interfaceinfoId;

    /**
     * total call
     */
    private Integer totalNum;

    /**
     * remain call
     */
    private Integer leftNum;

    /**
     * status: - 0 working - 1 ban
     */
    private Integer status;

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