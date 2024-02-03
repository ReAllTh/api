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
 * user
 *
 * @author ReAllTh
 */
@TableName(value = "user")
@Data
public class User implements Serializable {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private String username;
    private String nickname;
    private String avatar;

    /**
     * role: - 0 user - 1 admin
     */
    private Integer role;
    private String password;
    private String accessKey;
    private String secretKey;
    private Date createTime;
    private Date updateTime;
    private Integer deleted;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}