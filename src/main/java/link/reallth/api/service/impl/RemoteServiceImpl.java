package link.reallth.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import jakarta.annotation.Resource;
import link.reallth.api.constant.enums.CODES;
import link.reallth.api.exception.BaseException;
import link.reallth.api.model.po.InterfaceInfo;
import link.reallth.api.model.po.User;
import link.reallth.api.model.po.UserInterfaceInfo;
import link.reallth.api.service.InterfaceInfoService;
import link.reallth.api.service.RemoteService;
import link.reallth.api.service.UserInterfaceInfoService;
import link.reallth.api.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.util.DigestUtils;

import static link.reallth.api.constant.ColumnConst.COLUMN_INTERFACE_ID;
import static link.reallth.api.constant.ColumnConst.COLUMN_USER_ID;
import static link.reallth.api.constant.ValidateConst.ERROR_MSG_DATABASE;
import static link.reallth.api.constant.ValidateConst.INVALID_MSG_NO_USER;

/**
 * remote service
 *
 * @author ReAllTh
 */
@DubboService
public class RemoteServiceImpl implements RemoteService {

    @Resource
    private UserService userService;
    @Resource
    private InterfaceInfoService interfaceInfoService;
    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;
    public static final String SQL_COUNT = "left_num = left_num - 1";
    public static final String COLUMN_METHOD = "method";
    public static final String COLUMN_USER_ACCESS_KEY = "access_key";
    public static final String INVALID_MSG_REMOTE_PERMISSION = "user dose not has permission to access this interface";

    /**
     * count interface invoke
     *
     * @param interfaceId target interface id
     * @param userId      user id
     * @return result
     */
    @Override
    public boolean count(String interfaceId, String userId) {
        QueryWrapper<UserInterfaceInfo> qw = new QueryWrapper<>();
        // check if has permission
        if (!userInterfaceInfoService.exists(qw.eq(COLUMN_INTERFACE_ID, interfaceId).eq(COLUMN_USER_ID, userId)))
            throw new BaseException(CODES.ERROR_PERMISSION, INVALID_MSG_REMOTE_PERMISSION);
        UpdateWrapper<UserInterfaceInfo> uw = new UpdateWrapper<>();
        uw.eq(COLUMN_INTERFACE_ID, interfaceId)
                .eq(COLUMN_USER_ID, userId)
                .setSql(SQL_COUNT);
        if (!userInterfaceInfoService.update(uw))
            throw new BaseException(CODES.ERROR_SYSTEM, ERROR_MSG_DATABASE);
        return true;
    }

    /**
     * return signed secret key
     *
     * @param accessKsy user access ksy
     * @param nonce     nonce
     * @return signed secret key
     */
    @Override
    public String getSign(String accessKsy, String nonce) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq(COLUMN_USER_ACCESS_KEY, accessKsy);
        User user = userService.getOne(qw);
        if (user == null)
            throw new BaseException(CODES.ERROR_PARAM, INVALID_MSG_NO_USER);
        String secretKey = user.getSecretKey();
        return DigestUtils.md5DigestAsHex((secretKey + nonce).getBytes());
    }

    /**
     * check interface
     *
     * @param interfaceId interface id
     * @param method      method
     * @return result
     */
    @Override
    public boolean checkInterface(String interfaceId, String method) {
        QueryWrapper<InterfaceInfo> qw = new QueryWrapper<>();
        qw.eq(COLUMN_INTERFACE_ID, interfaceId).eq(COLUMN_METHOD, method);
        return interfaceInfoService.exists(qw);
    }
}
