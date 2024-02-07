package link.reallth.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import link.reallth.api.model.dto.interfaceinfo.InterfaceInfoAddDTO;
import link.reallth.api.model.dto.interfaceinfo.InterfaceInfoDeleteDTO;
import link.reallth.api.model.dto.interfaceinfo.InterfaceInfoFindDTO;
import link.reallth.api.model.dto.interfaceinfo.InterfaceInfoUpdateDTO;
import link.reallth.api.model.po.InterfaceInfo;
import link.reallth.api.model.vo.InterfaceInfoVO;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * InterfaceInfoService
 *
 * @author ReAllTh
 */
@Validated
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    /**
     * interface add
     *
     * @param interfaceInfoAddDTO interface info add data transfer object
     * @return interface info view object
     */
    InterfaceInfoVO add(@Valid @NotNull InterfaceInfoAddDTO interfaceInfoAddDTO);

    /**
     * interface delete
     *
     * @param interfaceInfoDeleteDTO interface info delete data transfer object
     * @return result
     */
    boolean deleteById(@Valid @NotNull InterfaceInfoDeleteDTO interfaceInfoDeleteDTO);

    /**
     * interface find
     *
     * @param interfaceInfoFindDTO interface info find data transfer object
     * @return target interface
     */
    List<InterfaceInfoVO> find(@Valid @NotNull InterfaceInfoFindDTO interfaceInfoFindDTO);

    /**
     * interface update
     *
     * @param interfaceInfoUpdateDTO interface info update data transfer object
     * @return new interface info view object
     */
    InterfaceInfoVO update(@Valid @NotNull InterfaceInfoUpdateDTO interfaceInfoUpdateDTO);
}
