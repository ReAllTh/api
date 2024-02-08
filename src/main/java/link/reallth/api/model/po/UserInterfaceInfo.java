package link.reallth.api.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
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

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private String userId;
    private String interfaceId;

    /**
     * remain call
     */
    private Integer leftNum;

    /**
     * status: - 0 working - 1 ban
     */
    @TableField(value = "status")
    private Integer banned;
    private Date createTime;
    private Date updateTime;
    private Integer deleted;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}