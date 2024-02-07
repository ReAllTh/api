package link.reallth.api.model.dto.interfaceinfo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import link.reallth.api.constant.enums.METHOD;
import link.reallth.api.constant.enums.STATUS;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import static link.reallth.api.constant.ValidateConst.*;

/**
 * interface info update data transfer object
 *
 * @author ReAllTh
 */
@Data
public class InterfaceInfoUpdateDTO {

    @NotBlank
    @Length(min = 32, max = 32, message = INVALID_MSG_ID)
    private String id;

    @Pattern(regexp = REGEX_INTERFACE_NAME, message = INVALID_MSG_INTERFACE_NAME)
    private String name;

    @Length(max = 1024, message = INVALID_MSF_INTERFACE_DESC)
    private String description;

    @URL
    private String url;

    @Length(max = 1024, message = INVALID_MSG_INTERFACE_REQ_PARAM)
    private String requestParams;

    @Length(max = 1024, message = INVALID_MSG_INTERFACE_REQ_HDR)
    private String requestHeader;

    @Length(max = 1024, message = INVALID_MSG_INTERFACE_RESP_HDR)
    private String responseHeader;

    private STATUS status;

    private METHOD method;

}