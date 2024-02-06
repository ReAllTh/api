package link.reallth.api.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

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

    @Override
    public String getName() {
        return this.name();
    }
}
