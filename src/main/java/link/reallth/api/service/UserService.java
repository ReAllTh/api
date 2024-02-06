package link.reallth.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import link.reallth.api.model.dto.user.*;
import link.reallth.api.model.po.User;
import link.reallth.api.model.vo.UserVO;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * UserService
 *
 * @author ReAllTh
 */
@Validated
public interface UserService extends IService<User> {

    /**
     * user sign up
     *
     * @param userSignUpDTO sign up info data transfer object
     * @return new user
     */
    UserVO signUp(@Valid @NotNull UserSignUpDTO userSignUpDTO);

    /**
     * user sign in
     *
     * @param userSignInDTO sign in data transfer object
     * @return new user
     */
    UserVO signIn(@Valid @NotNull UserSignInDTO userSignInDTO);

    /**
     * return current user
     *
     * @return current user
     */
    UserVO currentUser();

    /**
     * user sign out
     */
    void signOut();

    /**
     * user delete
     *
     * @param userDeleteDTO user delete data transfer object
     * @return result
     */
    boolean deleteById(UserDeleteDTO userDeleteDTO);

    /**
     * user find
     *
     * @param userFindDTO user find date transfer object
     * @return target users list
     */
    List<UserVO> find(@Valid @NotNull UserFindDTO userFindDTO);

    /**
     * user update
     *
     * @param userUpdateDTO user update data transfer object
     * @return target user view object
     */
    UserVO update(@Valid @NotNull UserUpdateDTO userUpdateDTO);
}
