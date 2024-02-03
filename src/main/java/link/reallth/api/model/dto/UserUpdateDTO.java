package link.reallth.api.model.dto;

import lombok.Data;

/**
 * user update data transfer object
 *
 * @author ReAllTh
 */
@Data
public class UserUpdateDTO {

    private String id;
    private String username;
    private String password;
    private String avatar;
    private Integer role;

}