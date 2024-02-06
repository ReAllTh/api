package link.reallth.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import link.reallth.api.annotation.RequireRole;
import link.reallth.api.constant.enums.CODES;
import link.reallth.api.constant.enums.METHOD;
import link.reallth.api.constant.enums.ROLES;
import link.reallth.api.constant.enums.STATUS;
import link.reallth.api.exception.BaseException;
import link.reallth.api.mapper.InterfaceInfoMapper;
import link.reallth.api.model.dto.interfaceinfo.InterfaceInfoAddDTO;
import link.reallth.api.model.po.InterfaceInfo;
import link.reallth.api.model.vo.InterfaceInfoVO;
import link.reallth.api.service.InterfaceInfoService;
import link.reallth.api.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import static link.reallth.api.constant.ValidateConst.ERROR_MSG_DATABASE;

/**
 * InterfaceInfoServiceImpl
 *
 * @author ReAllTh
 */
@Service
@RequireRole(role = ROLES.ADMIN)
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
        implements InterfaceInfoService {

    @Resource
    private UserService userService;
    @Resource
    private ConversionService conversionService;

    public static final String COLUMN_INTERFACE_NAME = "name";
    public static final String INVALID_MSG_INTERFACE_EXIST = "interface already exist";

    @Override
    public InterfaceInfoVO add(InterfaceInfoAddDTO interfaceInfoAddDTO) {
        // check if interface already exist
        String name = interfaceInfoAddDTO.getName();
        if (this.exists(new QueryWrapper<InterfaceInfo>().eq(COLUMN_INTERFACE_NAME, name)))
            throw new BaseException(CODES.ERROR_PARAM, INVALID_MSG_INTERFACE_EXIST);
        // generate new interface
        InterfaceInfo interfaceInfo = new InterfaceInfo();
        BeanUtils.copyProperties(interfaceInfoAddDTO, interfaceInfo);
        interfaceInfo.setStatus(interfaceInfoAddDTO.getStatus().getVal());
        interfaceInfo.setMethod(interfaceInfoAddDTO.getMethod().getName());
        interfaceInfo.setCreatorId(userService.currentUser().getId());
        // save
        if (!this.save(interfaceInfo))
            throw new BaseException(CODES.ERROR_SYSTEM, ERROR_MSG_DATABASE);
        return this.getInterfaceVO(this.getById(interfaceInfo));
    }

    private InterfaceInfoVO getInterfaceVO(InterfaceInfo interfaceInfo) {
        InterfaceInfoVO interfaceInfoVO = new InterfaceInfoVO();
        BeanUtils.copyProperties(interfaceInfo, interfaceInfoVO);
        interfaceInfoVO.setStatus(conversionService.convert(interfaceInfo.getStatus(), STATUS.class));
        interfaceInfoVO.setMethod(conversionService.convert(interfaceInfo.getMethod(), METHOD.class));
        interfaceInfoVO.setCreator(userService.currentUser());
        return interfaceInfoVO;
    }
}
