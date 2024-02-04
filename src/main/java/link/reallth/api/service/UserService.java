package link.reallth.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import link.reallth.api.annotation.RequireRole;
import link.reallth.api.constant.enums.ROLES;
import link.reallth.api.model.dto.UserFindDTO;
import link.reallth.api.model.dto.UserSignInDTO;
import link.reallth.api.model.dto.UserSignUpDTO;
import link.reallth.api.model.dto.UserUpdateDTO;
import link.reallth.api.model.po.User;
import link.reallth.api.model.vo.UserVO;

import java.util.List;

/**
 * UserService
 *
 * @author ReAllTh
 */
public interface UserService extends IService<User> {

    /**
     * user sign up
     *
     * @param userSignUpDTO sign up info data transfer object
     * @return new user
     */
    UserVO signUp(@Valid UserSignUpDTO userSignUpDTO);

    /**
     * user sign in
     *
     * @param userSignInDTO sign in data transfer object
     * @param session       session
     * @return new user
     */
    UserVO signIn(@Valid UserSignInDTO userSignInDTO, HttpSession session);

    /**
     * return current user
     *
     * @return current user
     */
    UserVO currentUser(HttpSession session);

    /**
     * user sign out
     *
     * @param session session
     */
    void signOut(HttpSession session);

    /**
     * user delete
     *
     * @param id user delete data transfer object
     * @return result
     */
    @RequireRole(role = ROLES.ADMIN)
    boolean deleteById(@NotBlank String id);

    /**
     * user find
     *
     * @param userFindDTO user find date transfer object
     * @return target users list
     */
    @RequireRole
    List<UserVO> find(@Valid UserFindDTO userFindDTO);

    /**
     * user update
     *
     * @param userUpdateDTO user update data transfer object
     * @param session       session
     * @return target user view object
     */
    @RequireRole
    UserVO update(@Valid UserUpdateDTO userUpdateDTO, HttpSession session);
}
