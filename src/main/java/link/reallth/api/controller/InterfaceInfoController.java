package link.reallth.api.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import link.reallth.api.common.BaseResponse;
import link.reallth.api.model.dto.interfaceinfo.InterfaceInfoAddDTO;
import link.reallth.api.model.dto.interfaceinfo.InterfaceInfoDeleteDTO;
import link.reallth.api.model.dto.interfaceinfo.InterfaceInfoFindDTO;
import link.reallth.api.model.dto.interfaceinfo.InterfaceInfoUpdateDTO;
import link.reallth.api.model.ro.interfaceinfo.InterfaceInfoAddRO;
import link.reallth.api.model.ro.interfaceinfo.InterfaceInfoDeleteRO;
import link.reallth.api.model.ro.interfaceinfo.InterfaceInfoFindRO;
import link.reallth.api.model.ro.interfaceinfo.InterfaceInfoUpdateRO;
import link.reallth.api.model.vo.InterfaceInfoVO;
import link.reallth.api.service.InterfaceInfoService;
import link.reallth.api.utils.ResponseUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    /**
     * interface find
     *
     * @param interfaceInfoFindRO interface info find request object
     * @return result list
     */
    @GetMapping("find")
    public BaseResponse<List<InterfaceInfoVO>> find(@Valid InterfaceInfoFindRO interfaceInfoFindRO) {
        InterfaceInfoFindDTO interfaceInfoFindDTO = new InterfaceInfoFindDTO();
        BeanUtils.copyProperties(interfaceInfoFindRO, interfaceInfoFindDTO);
        List<InterfaceInfoVO> interfaceInfoVOS = interfaceInfoService.find(interfaceInfoFindDTO);
        return ResponseUtils.success(interfaceInfoVOS);
    }


    /**
     * interface update
     *
     * @param interfaceInfoUpdateRO interface info update request object
     * @return new interface
     */
    @PostMapping("update")
    public BaseResponse<InterfaceInfoVO> update(InterfaceInfoUpdateRO interfaceInfoUpdateRO) {
        InterfaceInfoUpdateDTO interfaceInfoUpdateDTO = new InterfaceInfoUpdateDTO();
        BeanUtils.copyProperties(interfaceInfoUpdateRO, interfaceInfoUpdateDTO);
        InterfaceInfoVO newInterface = interfaceInfoService.update(interfaceInfoUpdateDTO);
        return ResponseUtils.success(newInterface);
    }
}
