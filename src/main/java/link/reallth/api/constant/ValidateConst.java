package link.reallth.api.constant;

/**
 * constants of validation
 *
 * @author ReAllTh
 */
public class ValidateConst {
    public static final String REGEX_USERNAME = "^[a-zA-Z0-9_-]{4,16}$";
    public static final String REGEX_PASSWORD = "^.*(?=.{6,24})(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*?.]).*$";
    public static final String INVALID_MSG_USERNAME = "username must be a string of 4 to 16 characters consisting of letters, numbers, underscores, and minus signs";
    public static final String INVALID_MSG_PASSWORD = "password must include at least 1 uppercase letter, 1 lowercase letter, 1 number, 1 special character, at least 6 characters, and at most 24 characters";
    public static final String INVALID_MSG_URL = "invalid url";
}
