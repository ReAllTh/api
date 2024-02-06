package link.reallth.api.model.dto.interfaceinfo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import link.reallth.api.constant.enums.METHOD;
import link.reallth.api.constant.enums.STATUS;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import static link.reallth.api.constant.ValidateConst.*;

@Data
public class InterfaceInfoAddDTO {

    @NotBlank
    @Pattern(regexp = REGEX_INTERFACE_NAME, message = INVALID_MSG_INTERFACE_NAME)
    private String name;

    @NotBlank
    @Max(value = 1024, message = INVALID_MSF_INTERFACE_DESC)
    private String description;

    @NotBlank
    @URL
    private String url;

    @Max(value = 1024, message = INVALID_MSG_INTERFACE_REQPARAM)
    private String requestParams;

    @Max(value = 1024, message = INVALID_MSG_INTERFACE_REQHDR)
    private String requestHeader;

    @Max(value = 1024, message = INVALID_MSG_INTERFACE_RESPHDR)
    private String responseHeader;

    @NotNull
    private STATUS status;

    @NotNull
    private METHOD method;

}