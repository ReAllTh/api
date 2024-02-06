package link.reallth.api.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * interface status
 *
 * @author ReAllTh
 */
@Getter
@AllArgsConstructor
public enum STATUS implements ConvertableEnum {
    CLOSE(0),
    OPEN(1);

    private final Integer val;

    @NotNull
    @Contract(pure = true)
    public String getName() {
        return this.name();
    }
}
