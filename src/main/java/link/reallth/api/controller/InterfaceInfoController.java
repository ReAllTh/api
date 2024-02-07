package link.reallth.api.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import link.reallth.api.common.BaseResponse;
import link.reallth.api.model.dto.interfaceinfo.InterfaceInfoAddDTO;
import link.reallth.api.model.dto.interfaceinfo.InterfaceInfoDeleteDTO;
import link.reallth.api.model.ro.interfaceinfo.InterfaceInfoAddRO;
import link.reallth.api.model.ro.interfaceinfo.InterfaceInfoDeleteRO;
import link.reallth.api.model.vo.InterfaceInfoVO;
import link.reallth.api.service.InterfaceInfoService;
import link.reallth.api.utils.ResponseUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("interface")
public class InterfaceInfoController {

    @Resource
    private InterfaceInfoService interfaceInfoService;

    /**
     * interface add
     *
     * @param interfaceInfoAddRO interface info add request object
     * @return interface info view object
     */
    @PostMapping("add")
    public BaseResponse<InterfaceInfoVO> add(@Valid InterfaceInfoAddRO interfaceInfoAddRO) {
        InterfaceInfoAddDTO interfaceInfoAddDTO = new InterfaceInfoAddDTO();
        BeanUtils.copyProperties(interfaceInfoAddRO, interfaceInfoAddDTO);
        InterfaceInfoVO interfaceInfoVO = interfaceInfoService.add(interfaceInfoAddDTO);
        return ResponseUtils.success(interfaceInfoVO);
    }

    /**
     * interface delete
     *
     * @param interfaceInfoDeleteRO interface info delete request object
     * @return result
     */
    @PostMapping("delete")
    public BaseResponse<Boolean> delete(@Valid InterfaceInfoDeleteRO interfaceInfoDeleteRO) {
        InterfaceInfoDeleteDTO interfaceInfoDeleteDTO = new InterfaceInfoDeleteDTO();
        BeanUtils.copyProperties(interfaceInfoDeleteRO, interfaceInfoDeleteDTO);
        boolean result = interfaceInfoService.deleteById(interfaceInfoDeleteDTO);
        return ResponseUtils.success(result);
    }
}
