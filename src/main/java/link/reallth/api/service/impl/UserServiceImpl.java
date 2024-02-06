package link.reallth.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import link.reallth.api.annotation.RequireRole;
import link.reallth.api.constant.enums.CODES;
import link.reallth.api.constant.enums.ROLES;
import link.reallth.api.exception.BaseException;
import link.reallth.api.mapper.UserMapper;
import link.reallth.api.model.dto.user.*;
import link.reallth.api.model.po.User;
import link.reallth.api.model.vo.UserVO;
import link.reallth.api.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import static link.reallth.api.constant.AttributeConst.ATTR_CURRENT_USER;
import static link.reallth.api.constant.AttributeConst.INVALID_MSG_REQ_ATTR;

/**
 * UserServiceImpl
 *
 * @author ReAllTh
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    public static final String COLUMN_CREATE_TIME = "create_time";
    @Resource
    private ConversionService converter;
    public static final String SALT = "salt";
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_USER_ROLE = "role";
    public static final String COLUMN_USER_USERNAME = "username";
    public static final String ERROR_MSG_DATABASE = "failed on database";
    public static final String INVALID_MSG_DUP_USERNAME = "username already exist";
    public static final String INVALID_MSG_SIGNED_IN = "already signed in";
    public static final String INVALID_MSG_NO_USER = "no such user";
    public static final String INVALID_MSG_PASSWORD = "username or password mismatched";

    /**
     * user sign up
     *
     * @param userSignUpDTO sign up info data transfer object
     * @return new user
     */
    @Override
    public UserVO signUp(@NotNull UserSignUpDTO userSignUpDTO) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        // check username exist
        String username = userSignUpDTO.getUsername();
        if (this.exists(qw.eq(COLUMN_USER_USERNAME, username)))
            throw new BaseException(CODES.ERROR_PARAM, INVALID_MSG_DUP_USERNAME);
        // generate new user
        User newUser = new User();
        BeanUtils.copyProperties(userSignUpDTO, newUser);
        // digest password
        String toBeDigest = newUser.getPassword() + SALT;
        newUser.setPassword(DigestUtils.md5DigestAsHex(toBeDigest.getBytes(StandardCharsets.UTF_8)));            // 3. 分配 accessKey, secretKey
        // dispatch keys
        String accessStr = SALT + username + RandomStringUtils.randomAlphanumeric(8);
        newUser.setAccessKey(DigestUtils.md5DigestAsHex(accessStr.getBytes()));
        String secretStr = SALT + username + RandomStringUtils.randomAlphanumeric(10);
        newUser.setSecretKey(DigestUtils.md5DigestAsHex(secretStr.getBytes()));
        // save
        if (!this.save(newUser))
            throw new BaseException(CODES.ERROR_SYSTEM, ERROR_MSG_DATABASE);
        // keep signed in
        UserVO userVO = this.getUserVO(this.getById(newUser));
        this.getRequestAttributes().setAttribute(ATTR_CURRENT_USER, userVO, RequestAttributes.SCOPE_SESSION);
        return userVO;
    }

    /**
     * user sign in
     *
     * @param userSignInDTO sign in data transfer object
     * @return new user
     */
    @Override
    public UserVO signIn(UserSignInDTO userSignInDTO) {
        // check if already signed in
        if (this.currentUser() != null)
            throw new BaseException(CODES.ERROR_BUSINESS, INVALID_MSG_SIGNED_IN);
        // get target user
        QueryWrapper<User> qw = new QueryWrapper<>();
        User targetUser = this.getOne(qw.eq(COLUMN_USER_USERNAME, userSignInDTO.getUsername()));
        if (targetUser == null)
            throw new BaseException(CODES.ERROR_PARAM, INVALID_MSG_NO_USER);
        // check password
        String toBeDigest = userSignInDTO.getPassword() + SALT;
        String digested = DigestUtils.md5DigestAsHex(toBeDigest.getBytes(StandardCharsets.UTF_8));
        if (!digested.equals(targetUser.getPassword()))
            throw new BaseException(CODES.ERROR_PARAM, INVALID_MSG_PASSWORD);
        // keep signed in
        UserVO userVO = this.getUserVO(targetUser);
        this.getRequestAttributes().setAttribute(ATTR_CURRENT_USER, userVO, RequestAttributes.SCOPE_SESSION);
        return userVO;
    }

    /**
     * return current user
     *
     * @return current user
     */
    @Override
    @RequireRole
    public UserVO currentUser() {
        return (UserVO) this.getRequestAttributes().getAttribute(ATTR_CURRENT_USER, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * user sign out
     */

    @Override
    @RequireRole
    public void signOut() {
        this.getRequestAttributes().removeAttribute(ATTR_CURRENT_USER, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * user delete
     *
     * @param userDeleteDTO user delete data transfer object
     * @return result
     */
    @Override
    @RequireRole(role = ROLES.ADMIN)
    public boolean deleteById(@NotNull UserDeleteDTO userDeleteDTO) {
        String id = userDeleteDTO.getId();
        QueryWrapper<User> qw = new QueryWrapper<>();
        // check user exist
        if (!this.exists(qw.eq(COLUMN_USER_ID, id)))
            throw new BaseException(CODES.ERROR_PARAM, INVALID_MSG_NO_USER);
        // delete
        if (!this.removeById(id))
            throw new BaseException(CODES.ERROR_SYSTEM, ERROR_MSG_DATABASE);
        return true;
    }

    /**
     * user find
     *
     * @param userFindDTO user find date transfer object
     * @return target users list
     */
    @Override
    @RequireRole
    public List<UserVO> find(@NotNull UserFindDTO userFindDTO) {
        // check if id supplied
        String id = userFindDTO.getId();
        if (StringUtils.isNotBlank(id)) {
            User targetUser = this.getById(id);
            if (targetUser == null)
                throw new BaseException(CODES.ERROR_PARAM, INVALID_MSG_NO_USER);
            return List.of(this.getUserVO(targetUser));
        }
        QueryWrapper<User> qw = new QueryWrapper<>();
        // like username
        String username = userFindDTO.getUsername();
        if (StringUtils.isNotBlank(username))
            qw.like(COLUMN_USER_USERNAME, username);
        // role
        ROLES role = userFindDTO.getRole();
        if (role != null)
            qw.eq(COLUMN_USER_ROLE, role.getVal());
        // create time from
        Date createTimeFrom = userFindDTO.getCreateTimeFrom();
        if (createTimeFrom != null)
            qw.ge(COLUMN_CREATE_TIME, createTimeFrom);
        // create time to
        Date createTimeTo = userFindDTO.getCreateTimeTo();
        if (createTimeTo != null)
            qw.le(COLUMN_CREATE_TIME, createTimeTo);
        // paging
        IPage<User> userIPage = new Page<>(userFindDTO.getPage(), userFindDTO.getPageSize());
        return this.list(userIPage, qw).stream().map(this::getUserVO).toList();
    }

    /**
     * user update
     *
     * @param userUpdateDTO user update data transfer object
     * @return target user view object
     */
    @Override
    @RequireRole
    public UserVO update(@NotNull UserUpdateDTO userUpdateDTO) {
        // if target user exist
        if (this.getById(userUpdateDTO.getId()) == null)
            throw new BaseException(CODES.ERROR_PARAM, INVALID_MSG_NO_USER);
        User user = new User();
        BeanUtils.copyProperties(userUpdateDTO, user);
        if (!this.update(user, new QueryWrapper<User>().eq(COLUMN_USER_ID, user.getId())))
            throw new BaseException(CODES.ERROR_SYSTEM, ERROR_MSG_DATABASE);
        UserVO newUserVO = this.getUserVO(this.getById(user.getId()));
        // update current if update self
        if (this.currentUser().getId().equals(newUserVO.getId()))
            this.getRequestAttributes().setAttribute(ATTR_CURRENT_USER, newUserVO, RequestAttributes.SCOPE_SESSION);
        return newUserVO;
    }

    /**
     * get user vo
     *
     * @param user source user
     * @return target user vo
     */
    @NotNull
    private UserVO getUserVO(User user) {
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        userVO.setRole(converter.convert(user.getRole(), ROLES.class));
        return userVO;
    }

    /**
     * return requestAttributes
     *
     * @return requestAttributes
     */
    @NotNull
    private RequestAttributes getRequestAttributes() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null)
            throw new BaseException(CODES.ERROR_SYSTEM, INVALID_MSG_REQ_ATTR);
        return requestAttributes;
    }
}




