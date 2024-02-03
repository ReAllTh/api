package link.reallth.api.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * role enum
 *
 * @author ReAllTh
 */
@Getter
@AllArgsConstructor
public enum ROLES implements Convertable {
    DEFAULT(0),
    ADMIN(1);

    private final Integer val;
}
