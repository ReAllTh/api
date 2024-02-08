package link.reallth.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import link.reallth.api.annotation.RequireRole;
import link.reallth.api.constant.enums.BANNED;
import link.reallth.api.constant.enums.CODES;
import link.reallth.api.constant.enums.ROLES;
import link.reallth.api.exception.BaseException;
import link.reallth.api.mapper.UserInterfaceInfoMapper;
import link.reallth.api.model.dto.userinterfaceinfo.UserInterfaceInfoAddDTO;
import link.reallth.api.model.dto.userinterfaceinfo.UserInterfaceInfoDeleteDTO;
import link.reallth.api.model.dto.userinterfaceinfo.UserInterfaceInfoFindDTO;
import link.reallth.api.model.dto.userinterfaceinfo.UserInterfaceInfoUpdateDTO;
import link.reallth.api.model.po.UserInterfaceInfo;
import link.reallth.api.model.vo.UserInterfaceInfoVO;
import link.reallth.api.model.vo.UserVO;
import link.reallth.api.service.InterfaceInfoService;
import link.reallth.api.service.UserInterfaceInfoService;
import link.reallth.api.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static link.reallth.api.constant.ValidateConst.ERROR_MSG_DATABASE;

/**
 * UserInterfaceInfoServiceImpl
 *
 * @author ReAllTh
 */
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
        implements UserInterfaceInfoService {

    public static final String COLUMN_USER_INTERFACE_USER_ID = "user_id";
    public static final String COLUMN_USER_INTERFACE_INTERFACE_ID = "interface_id";
    public static final String INVALID_MSG_USER_INTERFACE_EXIST = "user interface already exist";
    public static final String INVALID_MSG_NO_USER_INTERFACE = "no such user interface";
    public static final String COLUMN_USER_INTERFACE_ID = "id";
    public static final String COLUMN_USER_INTERFACE_LEFT_NUM = "left_num";
    @Resource
    private InterfaceInfoService interfaceInfoService;
    @Resource
    private UserService userService;
    @Resource
    private ConversionService conversionService;

    /**
     * user interface add
     *
     * @param userInterfaceInfoAddDTO user interface info add data transfer object
     * @return target user interface
     */
    @RequireRole(role = ROLES.ADMIN)
    @Override
    public UserInterfaceInfoVO add(@NotNull UserInterfaceInfoAddDTO userInterfaceInfoAddDTO) {
        // check if already exist
        QueryWrapper<UserInterfaceInfo> qw = new QueryWrapper<UserInterfaceInfo>()
                .eq(COLUMN_USER_INTERFACE_USER_ID, userInterfaceInfoAddDTO.getUserId())
                .eq(COLUMN_USER_INTERFACE_INTERFACE_ID, userInterfaceInfoAddDTO.getInterfaceId());
        if (this.exists(qw))
            throw new BaseException(CODES.ERROR_PARAM, INVALID_MSG_USER_INTERFACE_EXIST);
        // generate new user interface
        UserInterfaceInfo userInterfaceInfo = new UserInterfaceInfo();
        BeanUtils.copyProperties(userInterfaceInfoAddDTO, userInterfaceInfo);
        if (!this.save(userInterfaceInfo))
            throw new BaseException(CODES.ERROR_SYSTEM, ERROR_MSG_DATABASE);
        UserInterfaceInfo newUserInterface = this.getById(userInterfaceInfo);
        return this.getUserInterfaceInfoVO(newUserInterface);
    }

    /**
     * user interface delete
     *
     * @param userInterfaceInfoDeleteDTO user interface info delete data transfer object
     * @return result
     */
    @RequireRole(role = ROLES.ADMIN)
    @Override
    public boolean deleteById(@NotNull UserInterfaceInfoDeleteDTO userInterfaceInfoDeleteDTO) {
        String id = userInterfaceInfoDeleteDTO.getId();
        QueryWrapper<UserInterfaceInfo> qw = new QueryWrapper<>();
        // check if exist
        if (this.exists(qw.eq(COLUMN_USER_INTERFACE_ID, id)))
            throw new BaseException(CODES.ERROR_PARAM, INVALID_MSG_NO_USER_INTERFACE);
        // delete
        if (!this.removeById(id))
            throw new BaseException(CODES.ERROR_SYSTEM, ERROR_MSG_DATABASE);
        return true;
    }

    /**
     * user interface find
     *
     * @param userInterfaceInfoFindDTO user interface info find data transfer object
     * @return target user interface list
     */
    @RequireRole(role = ROLES.ADMIN)
    @Override
    public List<UserInterfaceInfoVO> find(@NotNull UserInterfaceInfoFindDTO userInterfaceInfoFindDTO) {
        // if find by id
        String id = userInterfaceInfoFindDTO.getId();
        if (StringUtils.isNotBlank(id)) {
            UserInterfaceInfo targetUserInterface = this.getById(id);
            if (targetUserInterface == null)
                return Collections.emptyList();
            return List.of(this.getUserInterfaceInfoVO(targetUserInterface));
        }
        // combination find
        QueryWrapper<UserInterfaceInfo> qw = new QueryWrapper<>();
        String interfaceId = userInterfaceInfoFindDTO.getInterfaceId();
        if (StringUtils.isNotBlank(interfaceId))
            qw.eq(COLUMN_USER_INTERFACE_INTERFACE_ID, interfaceId);
        String userId = userInterfaceInfoFindDTO.getUserId();
        if (StringUtils.isNotBlank(userId))
            qw.eq(COLUMN_USER_INTERFACE_USER_ID, userId);
        Integer leftNumLeast = userInterfaceInfoFindDTO.getLeftNumLeast();
        if (leftNumLeast != null)
            qw.ge(COLUMN_USER_INTERFACE_LEFT_NUM, leftNumLeast);
        Integer leftNumMax = userInterfaceInfoFindDTO.getLeftNumMax();
        if (leftNumMax != null)
            qw.le(COLUMN_USER_INTERFACE_LEFT_NUM, leftNumMax);
        // paging
        IPage<UserInterfaceInfo> page = new Page<>(userInterfaceInfoFindDTO.getPage(), userInterfaceInfoFindDTO.getPageSize());
        return this.list(page, qw).stream().map(this::getUserInterfaceInfoVO).toList();
    }

    /**
     * user interface update
     *
     * @param userInterfaceInfoUpdateDTO user interface info update data transfer object
     * @return new user interface
     */
    @RequireRole(role = ROLES.ADMIN)
    @Override
    public UserInterfaceInfoVO update(UserInterfaceInfoUpdateDTO userInterfaceInfoUpdateDTO) {
        // check if exist
        String id = userInterfaceInfoUpdateDTO.getId();
        QueryWrapper<UserInterfaceInfo> qw = new QueryWrapper<>();
        if (!this.exists(qw.eq(COLUMN_USER_INTERFACE_ID, id)))
            throw new BaseException(CODES.ERROR_PARAM, INVALID_MSG_NO_USER_INTERFACE);
        // update
        UserInterfaceInfo userInterfaceInfo = new UserInterfaceInfo();
        BeanUtils.copyProperties(userInterfaceInfoUpdateDTO, userInterfaceInfo);
        BANNED banned = userInterfaceInfoUpdateDTO.getBanned();
        if (banned != null)
            userInterfaceInfo.setBanned(banned.getVal());
        if (!this.updateById(userInterfaceInfo))
            throw new BaseException(CODES.ERROR_SYSTEM, ERROR_MSG_DATABASE);
        UserInterfaceInfo newUserInterface = this.getById(userInterfaceInfo);
        return this.getUserInterfaceInfoVO(newUserInterface);
    }

    /**
     * get user interface info view object
     *
     * @param userInterfaceInfo source user interface info
     * @return user interface info view object
     */
    private UserInterfaceInfoVO getUserInterfaceInfoVO(UserInterfaceInfo userInterfaceInfo) {
        UserInterfaceInfoVO userInterfaceInfoVO = new UserInterfaceInfoVO();
        BeanUtils.copyProperties(userInterfaceInfo, userInterfaceInfoVO);
        userInterfaceInfoVO.setInterfaceInfo(interfaceInfoService.getInterfaceVO(interfaceInfoService.getById(userInterfaceInfo.getInterfaceId())));
        UserVO userVO = userService.getUserVO(userService.getById(userInterfaceInfo.getUserId()));
        userInterfaceInfoVO.setUser(userVO);
        userInterfaceInfoVO.setBanned(conversionService.convert(userInterfaceInfo.getBanned(), BANNED.class));
        return userInterfaceInfoVO;
    }
}




