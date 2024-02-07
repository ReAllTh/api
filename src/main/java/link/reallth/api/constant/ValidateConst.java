package link.reallth.api.constant;

/**
 * constants of validation
 *
 * @author ReAllTh
 */
public class ValidateConst {
    public static final String REGEX_USERNAME = "^[a-zA-Z0-9_-]{4,16}$";
    public static final String REGEX_INTERFACE_NAME = "^[a-zA-Z0-9_-]{4,32}$";
    public static final String REGEX_PASSWORD = "^.*(?=.{6,24})(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*?.]).*$";
    public static final String INVALID_MSG_USERNAME = "username must be a string of 4 to 16 characters consisting of letters, numbers, underscores, and minus signs";
    public static final String INVALID_MSG_USER_NICKNAME = "invalid nickname";
    public static final String INVALID_MSG_USER_PASSWORD = "password must include at least 1 uppercase letter, 1 lowercase letter, 1 number, 1 special character, at least 6 characters, and at most 24 characters";
    public static final String INVALID_MSG_URL = "invalid url";
    public static final String INVALID_MSG_ROLE = "invalid role";
    public static final String INVALID_MSG_USERNAME_BLANK = "username can not be blank";
    public static final String INVALID_MSG_PASSWORD_BLANK = "password can not be blank";
    public static final String INVALID_MSG_ID = "invalid id";
    public static final String INVALID_MSG_TIME = "invalid time";
    public static final String INVALID_MSG_PAGE = "invalid page";
    public static final String INVALID_MSG_PAGE_SIZE = "invalid page size";
    public static final String INVALID_MSG_INTERFACE_NAME = "invalid interface name";
    public static final String INVALID_MSF_INTERFACE_DESC = "description too lang";
    public static final String INVALID_MSG_INTERFACE_REQ_PARAM = "requestParams too lang";
    public static final String INVALID_MSG_INTERFACE_REQ_HDR = "requestHeader too lang";
    public static final String INVALID_MSG_INTERFACE_RESP_HDR = "responseHeader too lang";
    public static final String ERROR_MSG_DATABASE = "failed on database";

    private ValidateConst() {
    }

}
