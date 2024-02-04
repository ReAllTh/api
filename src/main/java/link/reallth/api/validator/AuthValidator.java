package link.reallth.api.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import link.reallth.api.annotation.RequireRole;
import link.reallth.api.constant.AttributeConst;
import link.reallth.api.constant.enums.CODES;
import link.reallth.api.constant.enums.ROLES;
import link.reallth.api.exception.BaseException;
import link.reallth.api.model.vo.UserVO;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import static link.reallth.api.constant.AttributeConst.ATTR_CURRENT_USER;

/**
 * authentication validator
 *
 * @author ReAllTh
 */
public class AuthValidator implements ConstraintValidator<RequireRole, UserVO> {
    private ROLES requiredRole;
    private static final String INVALID_MSG_SIGNEDINN = "need to be signed in";
    private static final String INVALID_MSG_ROLE = "need to be admin";
    private static final String INVALID_MSG_REQATTR = "con not get RequestAttributes";

    @Override
    public void initialize(RequireRole requireRole) {
        this.requiredRole = requireRole.role();
    }

    @Override
    public boolean isValid(UserVO userVO, ConstraintValidatorContext constraintValidatorContext) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null)
            throw new BaseException(CODES.ERROR_SYSTEM, INVALID_MSG_REQATTR);
        UserVO currentUser = (UserVO) requestAttributes.getAttribute(ATTR_CURRENT_USER, RequestAttributes.SCOPE_SESSION);
        if (requiredRole == ROLES.DEFAULT && currentUser == null)
            throw new BaseException(CODES.ERROR_PERMISSION, INVALID_MSG_SIGNEDINN);
        if (requiredRole == ROLES.ADMIN && (currentUser == null || currentUser.getRole() != ROLES.ADMIN))
            throw new BaseException(CODES.ERROR_PERMISSION, INVALID_MSG_ROLE);
        return true;
    }
}
