package link.reallth.api.exception;

import link.reallth.api.constant.enums.CODES;
import lombok.Getter;

/**
 * global exception
 *
 * @author ReAllTh
 */
@Getter
public class BaseException extends RuntimeException {
    private final int code;
    private final String msg;
    private final String description;

    public BaseException(CODES code, String description) {
        this.code = code.getCode();
        this.msg = code.getMsg();
        this.description = description;
    }
}