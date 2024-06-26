package link.reallth.api.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * role enum
 *
 * @author ReAllTh
 */
@Getter
@AllArgsConstructor
public enum ROLES implements ConvertableEnum {
    DEFAULT(0),
    ADMIN(1);

    private final Integer val;

    @NotNull
    @Contract(pure = true)
    public String getName() {
        return this.name();
    }
}
