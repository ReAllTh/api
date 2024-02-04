package link.reallth.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import link.reallth.api.constant.enums.CODES;
import link.reallth.api.constant.enums.ROLES;
import link.reallth.api.exception.BaseException;
import link.reallth.api.mapper.UserMapper;
import link.reallth.api.model.dto.UserFindDTO;
import link.reallth.api.model.dto.UserSignInDTO;
import link.reallth.api.model.dto.UserSignUpDTO;
import link.reallth.api.model.dto.UserUpdateDTO;
import link.reallth.api.model.po.User;
import link.reallth.api.model.vo.UserVO;
import link.reallth.api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static link.reallth.api.constant.AttributeConst.ATTR_CURRENT_USER;
import static link.reallth.api.constant.AttributeConst.INVALID_MSG_REQATTR;

/**
 * UserServiceImpl
 *
 * @author ReAllTh
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Resource
    private ConversionService converter;
    public static final String COLUMN_USERNAME = "username";
    public static final String INVALID_MSG_DUP_USERNAME = "username already exist";
    public static final String ERROR_MSG_DATABASE = "failed on database";
    public static final String SALT = "salt";

    /**
     * user sign up
     *
     * @param userSignUpDTO sign up info data transfer object
     * @return new user
     */
    @Override
    public UserVO signUp(UserSignUpDTO userSignUpDTO) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        // check username exist
        String username = userSignUpDTO.getUsername();
        if (this.exists(qw.eq(COLUMN_USERNAME, username)))
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
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null)
            throw new BaseException(CODES.ERROR_SYSTEM, INVALID_MSG_REQATTR);
        UserVO userVO = this.getUserVO(this.getById(newUser));
        requestAttributes.setAttribute(ATTR_CURRENT_USER, userVO, RequestAttributes.SCOPE_SESSION);
        return userVO;
    }

    /**
     * user sign in
     *
     * @param userSignInDTO sign in data transfer object
     * @param session       session
     * @return new user
     */
    @Override
    public UserVO signIn(UserSignInDTO userSignInDTO, HttpSession session) {
        return null;
    }

    /**
     * return current user
     *
     * @param session
     * @return current user
     */
    @Override
    public UserVO currentUser(HttpSession session) {
        return null;
    }

    /**
     * user sign out
     *
     * @param session session
     */
    @Override
    public void signOut(HttpSession session) {

    }

    /**
     * user delete
     *
     * @param id user delete data transfer object
     * @return result
     */
    @Override
    public boolean deleteById(String id) {
        return false;
    }

    /**
     * user find
     *
     * @param userFindDTO user find date transfer object
     * @return target users list
     */
    @Override
    public List<UserVO> find(UserFindDTO userFindDTO) {
        return null;
    }

    /**
     * user update
     *
     * @param userUpdateDTO user update data transfer object
     * @param session       session
     * @return target user view object
     */
    @Override
    public UserVO update(UserUpdateDTO userUpdateDTO, HttpSession session) {
        return null;
    }

    private UserVO getUserVO(User user) {
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO, "role");
        userVO.setRole(converter.convert(user.getRole(), ROLES.class));
        return userVO;
    }
}




