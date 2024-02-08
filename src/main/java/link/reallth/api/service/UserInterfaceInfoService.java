package link.reallth.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import link.reallth.api.model.dto.userinterfaceinfo.UserInterfaceInfoAddDTO;
import link.reallth.api.model.dto.userinterfaceinfo.UserInterfaceInfoDeleteDTO;
import link.reallth.api.model.dto.userinterfaceinfo.UserInterfaceInfoFindDTO;
import link.reallth.api.model.dto.userinterfaceinfo.UserInterfaceInfoUpdateDTO;
import link.reallth.api.model.po.UserInterfaceInfo;
import link.reallth.api.model.vo.UserInterfaceInfoVO;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * UserInterfaceInfoService
 *
 * @author ReAllTh
 */
@Validated
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {
    /**
     * user interface add
     *
     * @param userInterfaceInfoAddDTO user interface info add data transfer object
     * @return target user interface
     */
    UserInterfaceInfoVO add(@Valid @NotNull UserInterfaceInfoAddDTO userInterfaceInfoAddDTO);

    /**
     * user interface delete
     *
     * @param userInterfaceInfoDeleteDTO user interface info delete data transfer object
     * @return result
     */
    boolean deleteById(@Valid @NotNull UserInterfaceInfoDeleteDTO userInterfaceInfoDeleteDTO);

    /**
     * user interface find
     *
     * @param userInterfaceInfoFindDTO user interface info find data transfer object
     * @return target user interface list
     */
    List<UserInterfaceInfoVO> find(@Valid @NotNull UserInterfaceInfoFindDTO userInterfaceInfoFindDTO);

    /**
     * user interface update
     *
     * @param userInterfaceInfoUpdateDTO user interface info update data transfer object
     * @return new user interface
     */
    UserInterfaceInfoVO update(@Valid @NotNull UserInterfaceInfoUpdateDTO userInterfaceInfoUpdateDTO);
}
