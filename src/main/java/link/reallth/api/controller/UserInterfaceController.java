package link.reallth.api.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import link.reallth.api.common.BaseResponse;
import link.reallth.api.model.dto.userinterfaceinfo.UserInterfaceInfoAddDTO;
import link.reallth.api.model.dto.userinterfaceinfo.UserInterfaceInfoDeleteDTO;
import link.reallth.api.model.dto.userinterfaceinfo.UserInterfaceInfoFindDTO;
import link.reallth.api.model.dto.userinterfaceinfo.UserInterfaceInfoUpdateDTO;
import link.reallth.api.model.ro.userinterfaceinfo.UserInterfaceInfoAddRO;
import link.reallth.api.model.ro.userinterfaceinfo.UserInterfaceInfoDeleteRO;
import link.reallth.api.model.ro.userinterfaceinfo.UserInterfaceInfoFindRO;
import link.reallth.api.model.ro.userinterfaceinfo.UserInterfaceInfoUpdateRO;
import link.reallth.api.model.vo.UserInterfaceInfoVO;
import link.reallth.api.service.UserInterfaceInfoService;
import link.reallth.api.utils.ResponseUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * user interface controller
 *
 * @author ReAllTh
 */
@Validated
@RestController
@RequestMapping("userInterfaceInfo")
public class UserInterfaceController {

    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;

    /**
     * user interface add
     *
     * @param userInterfaceInfoAddRO user interface info add request object
     * @return user interface info view object
     */
    @PostMapping("add")
    public BaseResponse<UserInterfaceInfoVO> add(@Valid UserInterfaceInfoAddRO userInterfaceInfoAddRO) {
        UserInterfaceInfoAddDTO userInterfaceInfoAddDTO = new UserInterfaceInfoAddDTO();
        BeanUtils.copyProperties(userInterfaceInfoAddRO, userInterfaceInfoAddDTO);
        UserInterfaceInfoVO interfaceInfoVO = userInterfaceInfoService.add(userInterfaceInfoAddDTO);
        return ResponseUtils.success(interfaceInfoVO);
    }

    /**
     * user interface delete
     *
     * @param userInterfaceInfoDeleteRO user interface info delete request object
     * @return result
     */
    @PostMapping("delete")
    public BaseResponse<Boolean> delete(@Valid UserInterfaceInfoDeleteRO userInterfaceInfoDeleteRO) {
        UserInterfaceInfoDeleteDTO userInterfaceInfoDeleteDTO = new UserInterfaceInfoDeleteDTO();
        BeanUtils.copyProperties(userInterfaceInfoDeleteRO, userInterfaceInfoDeleteDTO);
        boolean result = userInterfaceInfoService.deleteById(userInterfaceInfoDeleteDTO);
        return ResponseUtils.success(result);
    }

    /**
     * user interface find
     *
     * @param userInterfaceInfoFindRO user interface info find request object
     * @return result list
     */
    @GetMapping("find")
    public BaseResponse<List<UserInterfaceInfoVO>> find(@Valid UserInterfaceInfoFindRO userInterfaceInfoFindRO) {
        UserInterfaceInfoFindDTO userInterfaceInfoFindDTO = new UserInterfaceInfoFindDTO();
        BeanUtils.copyProperties(userInterfaceInfoFindRO, userInterfaceInfoFindDTO);
        List<UserInterfaceInfoVO> interfaceInfoVOS = userInterfaceInfoService.find(userInterfaceInfoFindDTO);
        return ResponseUtils.success(interfaceInfoVOS);
    }

    /**
     * user interface update
     *
     * @param userInterfaceInfoUpdateRO user interface info update request object
     * @return new user interface
     */
    @PostMapping("update")
    public BaseResponse<UserInterfaceInfoVO> update(UserInterfaceInfoUpdateRO userInterfaceInfoUpdateRO) {
        UserInterfaceInfoUpdateDTO userInterfaceInfoUpdateDTO = new UserInterfaceInfoUpdateDTO();
        BeanUtils.copyProperties(userInterfaceInfoUpdateRO, userInterfaceInfoUpdateDTO);
        UserInterfaceInfoVO newInterface = userInterfaceInfoService.update(userInterfaceInfoUpdateDTO);
        return ResponseUtils.success(newInterface);
    }
}
