package link.reallth.api.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * response codes enums
 *
 * @author ReAllTh
 */
@Getter
@AllArgsConstructor
public enum CODES {
    // base code
    SUCCESS(0, "ok"),
    ERROR(-1, "undefined error"),

    // permission err - 10000
    ERROR_PERMISSION(10001, "permission error"),

    // param err - 20000
    ERROR_PARAM(20001, "parameters error"),

    // business err - 40000
    ERROR_BUSINESS(40001, "business error"),

    // system err - 50000
    ERROR_SYSTEM(50001, "system error");

    private final int code;
    private final String msg;
}