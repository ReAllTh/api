package link.reallth.api.exception;

import jakarta.validation.ConstraintViolationException;
import link.reallth.api.common.BaseResponse;
import link.reallth.api.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * global exception handler
 *
 * @author ReAllTh
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * generate a BaseException response with info
     *
     * @param e   BaseException
     * @param <T> T
     * @return base response with error info
     */
    @ExceptionHandler(BaseException.class)
    public <T> BaseResponse<T> baseExceptionHandler(BaseException e) {
        return ResponseUtils.baseError(e);
    }

    /**
     * generate a NullPointerException response with info
     *
     * @param e   NullPointerException
     * @param <T> T
     * @return base response with error info
     */
    @ExceptionHandler(NullPointerException.class)
    public <T> BaseResponse<T> baseExceptionHandler(NullPointerException e) {
        return ResponseUtils.nullPointerError(e);
    }

    /**
     * generate a BindException response with info
     *
     * @param e   BindException
     * @param <T> T
     * @return base response with error info
     */
    @ExceptionHandler(BindException.class)
    public <T> BaseResponse<T> baseExceptionHandler(BindException e) {
        return ResponseUtils.getObjValidateError(e);
    }

    /**
     * generate a MethodArgumentNotValidException response with info
     *
     * @param e   MethodArgumentNotValidException
     * @param <T> T
     * @return base response with error info
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public <T> BaseResponse<T> baseExceptionHandler(MethodArgumentNotValidException e) {
        return ResponseUtils.postBodyValidateError(e);
    }

    /**
     * generate a ConstraintViolationException response with info
     *
     * @param e   ConstraintViolationException
     * @param <T> T
     * @return base response with error info
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public <T> BaseResponse<T> baseExceptionHandler(ConstraintViolationException e) {
        return ResponseUtils.getParamValidateError(e);
    }

    /**
     * generate an Exception response with info
     *
     * @param e   Exception
     * @param <T> T
     * @return base response with error info
     */
    @ExceptionHandler(Exception.class)
    public <T> BaseResponse<T> baseExceptionHandler(Exception e) {
        return ResponseUtils.error(e);
    }

}