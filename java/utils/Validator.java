package utils;

import java.util.regex.*;

/**
 * Validation
 * @version 1.0
 */
public class Validator {
    private final static String privilegePattern = "^\\d{1,2}$";


    public static boolean validatePrivilege(String privilege){
        return validate(privilegePattern, privilege);
    }

    public static boolean validate(String pattern, String input){
        return Pattern.compile(pattern).matcher(input).matches();
    }
}
