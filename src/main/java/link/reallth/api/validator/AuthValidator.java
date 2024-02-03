package link.reallth.api.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import link.reallth.api.annotation.RequireRole;
import link.reallth.api.constant.enums.CODES;
import link.reallth.api.constant.enums.ROLES;
import link.reallth.api.exception.BaseException;
import link.reallth.api.model.vo.UserVO;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class AuthValidator implements ConstraintValidator<RequireRole, UserVO> {
    private ROLES requiredRole;

    @Override
    public void initialize(RequireRole requireRole) {
        this.requiredRole = requireRole.role();
    }

    @Override
    public boolean isValid(UserVO userVO, ConstraintValidatorContext constraintValidatorContext) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        UserVO currentUser = (UserVO) requestAttributes.getAttribute("current_user", RequestAttributes.SCOPE_SESSION);
        if (requiredRole == ROLES.DEFAULT && currentUser == null)
            throw new BaseException(CODES.PERMISSION_ERR, "need to be signed in");
        if (requiredRole == ROLES.ADMIN && (currentUser == null || currentUser.getRole() != ROLES.ADMIN))
            throw new BaseException(CODES.PERMISSION_ERR, "need to be admin");
        return true;
    }
}
