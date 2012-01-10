package seks.basic.exceptions;

/**
 * Exception thrown when there is one or more parameters missing from a XML 
 * configuration file.
 * 
 * @author Paulo Figueiras
 */
public class MissingParamException extends Exception {

    /**
     * Class constructor.
     * 
     * @param msg   Error message to be logged
     */
    public MissingParamException(String msg) {
        super(msg) ;
    }

}
