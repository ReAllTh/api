package link.reallth.api.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * user interface banned enum
 *
 * @author ReAllTh
 */
@Getter
@AllArgsConstructor
public enum BANNED implements ConvertableEnum {
    WORKING(0),
    BAN(1);

    private final Integer val;

    @NotNull
    @Contract(pure = true)
    @Override
    public String getName() {
        return this.name();
    }
}
