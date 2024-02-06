package link.reallth.api.advice;

import link.reallth.api.annotation.RequireRole;
import link.reallth.api.constant.enums.ROLES;
import link.reallth.api.exception.BaseException;
import link.reallth.api.model.vo.UserVO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import static link.reallth.api.constant.AttributeConst.ATTR_CURRENT_USER;
import static link.reallth.api.constant.enums.CODES.ERROR_PERMISSION;

@Aspect
@Component
public class AuthAdvice {

    public static final String INVALID_MSG_AUTH_SIGN_IN = "need to be signed in";
    public static final String INVALID_MSG_AUTH_ROLE = "need to be admin";

    @Around("@annotation(requireRole)")
    public Object permissionCheck(ProceedingJoinPoint joinPoint, RequireRole requireRole) throws Throwable {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        UserVO currentUser = (UserVO) requestAttributes.getAttribute(ATTR_CURRENT_USER, RequestAttributes.SCOPE_SESSION);
        // check if signed in
        if (currentUser == null)
            throw new BaseException(ERROR_PERMISSION, INVALID_MSG_AUTH_SIGN_IN);
        // check if admin
        if (currentUser.getRole() == ROLES.DEFAULT && requireRole.role() == ROLES.ADMIN)
            throw new BaseException(ERROR_PERMISSION, INVALID_MSG_AUTH_ROLE);
        return joinPoint.proceed();
    }
}
