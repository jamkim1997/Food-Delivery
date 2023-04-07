package controller;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator implements Serializable {

    // Email must have @
    private String emailPattern = "([a-zA-Z0-9]+)(([._-])([a-zA-Z0-9]+))*(@)([a-z]+)(.)([a-z]{3})((([.])[a-z]{0,2})*)";

    // 1 Uppercase, 1 Number and 1 Special Character needed, At least 8 characters
    // long
    private String passwordPattern = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*#?&^_-]).{8,}";

    // 10 Digits Phone Number
    private String phonePattern = "^[0-9]{9}$";

    // YYYY-MM-DD
    private String datePattern = "^\\d{4}-\\d{2}-\\d{2}$";

    // 6 Digits
    private String bsbPattern = "^[0-9]{6}$";

    // 6 Digits
    private String acctNumPattern = "^[0-9]{6}$";

    // 11 Digits
    private String abnPattern = "^[0-9]{11}$";

    // Only numbers, can be empty
    private String strNumPattern = "^[0-9]*";

    // 3 to 5 uppercase letters
    private String statePattern = "[A-Z]{3,5}";

    // 4 digits
    private String postcodePattern = "^[0-9]{4}$";

    // 100 Characters only - Doesn't include escape character
    private String descPattern = "^[\\s0-9A-Za-z$-\\/:-?{-~!\"^_`\\[\\]@#|]{1,100}$";

    // 30 Characters only - No escape character
    private String resNamePattern = "^[\\s0-9A-Za-z$-\\/:-?{-~!\"^_`\\[\\]@#|]{1,30}$";

    // Name:
    // Name must start with Capital letters
    // Each word in the name must be accompanied by a space before it except for
    // first word
    private String namePattern = "^([A-Z]+[a-zA-Z]*)+([ ][A-Z]+[a-zA-Z]*)*$";

    public Validator() {
    }

    public boolean validate(String pattern, String input) {
        Pattern regEx = Pattern.compile(pattern);
        Matcher match = regEx.matcher(input);

        return match.matches();
    }

    public boolean isBlankOrNull(String input) {
        if (input == null) {
            return true;
        }
        if (input.isEmpty()) {
            return true;
        }
        return false;
    }

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public boolean checkLoginEmpty(String email, String password) {
        return email.isEmpty() || password.isEmpty();
    }

    public boolean validateEmail(String email) {
        return validate(emailPattern, email);
    }

    // Used for both First or Last name validation
    public boolean validateName(String name) {
        return validate(namePattern, name);
    }

    public boolean validatePassword(String password) {
        return validate(passwordPattern, password);
    }

    public boolean validatePhone(String phone) {
        return validate(phonePattern, phone);
    }

    public boolean validateDate(String date) {
        return validate(datePattern, date);
    }

    public boolean validateBSB(String bsb) {
        return validate(bsbPattern, bsb);
    }

    public boolean validateAcctNum(String acctNum) {
        return validate(acctNumPattern, acctNum);
    }

    public boolean validateABN(String abn) {
        return validate(abnPattern, abn);
    }

    public boolean validateState(String state) {
        if (isBlankOrNull(state)) {
            return false;
        }
        return validate(statePattern, state);
    }

    public boolean validatePostCode(String postcode) {
        if (isBlankOrNull(postcode)) {
            return false;
        }
        return validate(postcodePattern, postcode);
    }

    public boolean validateStrNum(String strNum) {
        return validate(strNum, strNum);
    }

    public boolean validateDesc(String desc) {
        return validate(descPattern, desc);
    }

    public boolean validateResName(String resName) {
        return validate(resNamePattern, resName);
    }

}
