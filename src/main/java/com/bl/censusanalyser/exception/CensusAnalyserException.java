package com.bl.censusanalyser.exception;

public class CensusAnalyserException  extends RuntimeException {
    public enum ExceptionType
    {
        ENTERED_WRONG_FILE_NAME, ENTERED_WRONG_FILE_TYPE, INCORRECT_DELIMITER_OR_HEADER, INCORRECT_DELIMITER
    }
    public ExceptionType type;
    // Constructor
    public CensusAnalyserException(ExceptionType type, String message)
    {
        super(message);
        this.type = type;
    }
}
