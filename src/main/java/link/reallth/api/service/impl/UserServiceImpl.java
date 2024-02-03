package link.reallth.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpSession;
import link.reallth.api.mapper.UserMapper;
import link.reallth.api.model.dto.UserFindDTO;
import link.reallth.api.model.dto.UserSignInDTO;
import link.reallth.api.model.dto.UserSignUpDTO;
import link.reallth.api.model.dto.UserUpdateDTO;
import link.reallth.api.model.po.User;
import link.reallth.api.model.vo.UserVO;
import link.reallth.api.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserServiceImpl
 *
 * @author ReAllTh
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    /**
     * user sign up
     *
     * @param userSignUpDTO sign up info data transfer object
     * @param session       session
     * @return new user
     */
    @Override
    public UserVO signUp(UserSignUpDTO userSignUpDTO, HttpSession session) {
        return null;
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
     * @param id      user delete data transfer object
     * @param session session
     * @return result
     */
    @Override
    public boolean deleteById(String id, HttpSession session) {
        return false;
    }

    /**
     * user find
     *
     * @param userFindDTO user find date transfer object
     * @param session     session
     * @return target users list
     */
    @Override
    public List<UserVO> find(UserFindDTO userFindDTO, HttpSession session) {
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
}




