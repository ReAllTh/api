package link.reallth.api.model.dto.interfaceinfo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import link.reallth.api.constant.enums.METHOD;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import static link.reallth.api.constant.ValidateConst.*;

/**
 * interface find data transfer object
 *
 * @author ReAllTh
 */
@Data
public class InterfaceInfoFindDTO {

    @Length(min = 32, max = 32, message = INVALID_MSG_ID)
    private String id;

    @Length(max = 32, message = INVALID_MSG_INTERFACE_SEARCH_TEXT)
    private String searchText;

    private METHOD method;

    @NotNull
    @Min(value = 1, message = INVALID_MSG_PAGE)
    private Integer page;

    @NotNull
    @Min(value = 5, message = INVALID_MSG_PAGE_SIZE)
    @Max(value = 100, message = INVALID_MSG_PAGE_SIZE)
    private Integer pageSize;
}
