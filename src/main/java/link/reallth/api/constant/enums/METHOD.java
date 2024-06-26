package link.reallth.api.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * interface method
 *
 * @author ReAllTh
 */
@Getter
@AllArgsConstructor
public enum METHOD implements ConvertableEnum {
    GET(0), POST(1), PUT(2), DELETE(3);

    private final Integer val;

    @NotNull
    @Contract(pure = true)
    @Override
    public String getName() {
        return this.name();
    }
}
