package link.reallth.api.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import link.reallth.api.common.BaseResponse;
import link.reallth.api.model.dto.user.*;
import link.reallth.api.model.ro.user.*;
import link.reallth.api.model.vo.UserVO;
import link.reallth.api.service.UserService;
import link.reallth.api.utils.ResponseUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * user controller
 *
 * @author ReAllTh
 */
@Validated
@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * user sign up
     *
     * @param userSignUpRO user sign up request object
     * @return new user
     */
    @PostMapping("signUp")
    public BaseResponse<UserVO> signUp(@Valid UserSignUpRO userSignUpRO) {
        UserSignUpDTO userSignUpDTO = new UserSignUpDTO();
        BeanUtils.copyProperties(userSignUpRO, userSignUpDTO);
        UserVO userVO = userService.signUp(userSignUpDTO);
        return ResponseUtils.success(userVO);
    }

    /**
     * user sign in
     *
     * @param userSignInRO user sign in request object
     * @return target user
     */
    @PostMapping("signIn")
    public BaseResponse<UserVO> signIn(@Valid UserSignInRO userSignInRO) {
        UserSignInDTO userSignInDTO = new UserSignInDTO();
        BeanUtils.copyProperties(userSignInRO, userSignInDTO);
        UserVO userVO = userService.signIn(userSignInDTO);
        return ResponseUtils.success(userVO);
    }

    /**
     * return current user
     *
     * @return current
     */
    @GetMapping("current")
    public BaseResponse<UserVO> current() {
        UserVO currentUser = userService.currentUser();
        return ResponseUtils.success(currentUser);
    }

    /**
     * user sign out
     *
     * @return null
     */
    @PostMapping("signOut")
    public BaseResponse<Void> signOut() {
        userService.signOut();
        return ResponseUtils.success();
    }

    /**
     * user delete
     *
     * @param id target user id
     * @return result
     */
    @PostMapping("delete")
    public BaseResponse<Boolean> delete(@Valid UserDeleteRO userDeleteRO) {
        UserDeleteDTO userDeleteDTO = new UserDeleteDTO();
        BeanUtils.copyProperties(userDeleteRO, userDeleteDTO);
        boolean result = userService.deleteById(userDeleteDTO);
        return ResponseUtils.success(result);
    }

    /**
     * user find
     *
     * @param userFindRO user find request object
     * @return result user list
     */
    @GetMapping("find")
    public BaseResponse<List<UserVO>> find(@Valid UserFindRO userFindRO) {
        UserFindDTO userFindDTO = new UserFindDTO();
        BeanUtils.copyProperties(userFindRO, userFindDTO);
        List<UserVO> userVOS = userService.find(userFindDTO);
        return ResponseUtils.success(userVOS);
    }

    /**
     * user update
     *
     * @param userUpdateRO user update request object
     * @return user view object
     */
    @PostMapping("update")
    public BaseResponse<UserVO> update(@Valid UserUpdateRO userUpdateRO) {
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        BeanUtils.copyProperties(userUpdateRO, userUpdateDTO);
        UserVO newUser = userService.update(userUpdateDTO);
        return ResponseUtils.success(newUser);
    }
}