package link.reallth.api.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import link.reallth.api.common.BaseResponse;
import link.reallth.api.constant.enums.CODES;
import link.reallth.api.exception.BaseException;
import link.reallth.api.model.dto.UserSignUpDTO;
import link.reallth.api.model.ro.UserSignUpRO;
import link.reallth.api.model.vo.UserVO;
import link.reallth.api.service.UserService;
import link.reallth.api.utils.ResponseUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * user controller
 *
 * @author ReAllTh
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;
    public static final String INVALID_MSG_NULL_POST_BODY = "null request body";

    /**
     * user sign up
     *
     * @param userSignUpRO user sign up request object
     * @return new user
     */
    @PostMapping("signUp")
    public BaseResponse<UserVO> signUp(@Valid UserSignUpRO userSignUpRO) {
        if (userSignUpRO == null)
            throw new BaseException(CODES.ERROR_PARAM, INVALID_MSG_NULL_POST_BODY);
        UserSignUpDTO userSignUpDTO = new UserSignUpDTO();
        BeanUtils.copyProperties(userSignUpRO, userSignUpDTO);
        UserVO userVO = userService.signUp(userSignUpDTO);
        return ResponseUtils.success(userVO);
    }
}