package link.reallth.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import link.reallth.api.annotation.RequireRole;
import link.reallth.api.model.dto.UserFindDTO;
import link.reallth.api.model.dto.UserSignInDTO;
import link.reallth.api.model.dto.UserSignUpDTO;
import link.reallth.api.model.dto.UserUpdateDTO;
import link.reallth.api.model.po.User;
import link.reallth.api.model.vo.UserVO;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static link.reallth.api.constant.ValidateConst.INVALID_MSG_ID;

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
     * @param id user delete data transfer object
     * @return result
     */
    boolean deleteById(@NotBlank @Length(min = 32, max = 32, message = INVALID_MSG_ID) String id);

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
    @RequireRole
    UserVO update(@Valid @NotNull UserUpdateDTO userUpdateDTO);
}
