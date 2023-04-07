package exceptions;

/**
 * Invalid Privilege Exception class
 * @author Hao Zeng
 * @version 1.0
 */
public class InvalidPrivilegeNumException extends Exception{
    public InvalidPrivilegeNumException() {
    }

    public InvalidPrivilegeNumException(String message) {
        super(message);
    }
}
