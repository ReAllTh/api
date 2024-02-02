package link.reallth.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import link.reallth.api.mapper.UserMapper;
import link.reallth.api.model.po.User;
import link.reallth.api.service.UserService;
import org.springframework.stereotype.Service;

/**
 * UserServiceImpl
 *
 * @author ReAllTh
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

}




