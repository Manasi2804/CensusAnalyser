package com.bl.censusanalyser;

import com.bl.censusanalyser.exception.CensusAnalyserException;
import com.opencsv.exceptions.CsvException;
import org.junit.Assert;
import org.junit.Test;
import java.io.File;
import java.io.IOException;

public class StateCensusAnalyserTest {
    String PATH_OF_CSV_FILE = "./src/test/resources/StateCensusData.csv";
    String PATH_OF_CSV_FILE_FOR_FILE_NOT_FOUND_EXCEPTION = "./src/test/resources/StateCensusData.csv";
    String PATH_OF_CSV_FILE_FOR_INCORRECT_TYPE_EXCEPTION = "/home/Desktop/IndiaCensusAnalyser/src/test/resources/StateCensusData.docx";
    String PATH_OF_CSV_FILE_FOR_INCORRECT_DELIMITER = "./src/test/resources/StateCensusData.csv";
    String PATH_OF_STATE_CODE_CSV_FILE = "./src/test/resources/StateCode.csv";
    String PATH_OF_STATE_CODE_CSV_FILE_FOR_FILE_NOT_FOUND = "./src/test/resources/StateCode.csv";
    String PATH_OF_STATE_CODE_CSV_FILE_FOR_INCORRECT_TYPE = "./src/test/resources/StateCode.docx";
    String PATH_OF_STATE_CODE_CSV_FILE_INCORRECT_DELIMITER = "./src/test/resources/StateCode.csv";
    String PATH_OF_STATE_CODE_CSV_FILE_INCORRECT_HEADER = "./src/test/resources/StateCode.csv";
    String PATH_OF_CSV_FILE_FOR_INCORRECT_HEADER = "./src/test/resources/StateCensusDataCopy.csv";
    @Test
    public void givenTheStateCensusCSVFile_WhenNoOfRecordMatch_ShouldReturnTrue() throws IOException, CsvException, IOException {
        int noOfRecords = CensusAnalyser.loadStateCensusCSVFileData(PATH_OF_CSV_FILE);
        Assert.assertEquals(29, noOfRecords);
    }
    @Test
    public void givenTheStateCensusCSVFile_IfIncorrect_ShouldThrowCustomException()throws IOException {
        try {
            int noOfRecords = CensusAnalyser.loadStateCensusCSVFileData(PATH_OF_CSV_FILE_FOR_FILE_NOT_FOUND_EXCEPTION);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.ENTERED_WRONG_FILE_NAME, e.type);
        }
    }
    @Test
    public void givenTheStateCensusCSVFile_IfTypeIsIncorrect_ShouldThrowCustomException() throws CensusAnalyserException, IOException{
        try{
            CensusAnalyser.getFileExtension(new File(PATH_OF_CSV_FILE_FOR_INCORRECT_TYPE_EXCEPTION));
        } catch (CensusAnalyserException e){
            Assert.assertEquals(CensusAnalyserException.ExceptionType.ENTERED_WRONG_FILE_TYPE,e.type);
        }
    }
    @Test
    public void givenTheStateCensusCSVFile_IfDelimiterIsIncorrect_ShouldThrowCustomException() throws IOException{
        try{
            int noOfRecords = CensusAnalyser.loadStateCensusCSVFileData(PATH_OF_CSV_FILE_FOR_INCORRECT_DELIMITER);
        } catch (CensusAnalyserException e){
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_DELIMITER,e.type);
        }
    }
    @Test
    public void givenTheStateCensusCSVFile_IfHeaderIsIncorrect_ShouldThrowCustomException() throws IOException{
        try{
            int noOfRecords = CensusAnalyser.loadStateCensusCSVFileData(PATH_OF_CSV_FILE_FOR_INCORRECT_HEADER);
            Assert.assertEquals(29, noOfRecords);
        } catch (CensusAnalyserException e){
            Assert.assertEquals(CensusAnalyserException.ExceptionType.ENTERED_WRONG_FILE_NAME, e.type);
        }
    }
    @Test
    public void givenTheStateCodeCSVFile__WhenNoOfRecordMatches_ShouldReturnTrue() throws IOException, CensusAnalyserException {
        int noOfRecords = CensusAnalyser.loadStateCodeCSVFileData(PATH_OF_STATE_CODE_CSV_FILE);
        Assert.assertEquals(37, noOfRecords);
    }
    @Test
    public void givenTheStateCodeCSVFile_IfIncorrect_ShouldThrowCustomException() throws IOException {
        try {
            int noOfRecords = CensusAnalyser.loadStateCodeCSVFileData(PATH_OF_STATE_CODE_CSV_FILE_FOR_FILE_NOT_FOUND);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.ENTERED_WRONG_FILE_NAME, e.type);
        }
    }
    @Test
    public void givenTheStateCodeCSVFile_IfTypeIsIncorrect_ShouldThrowCustomException() throws CensusAnalyserException, IOException {
        try {
            CensusAnalyser.getFileExtension(new File(PATH_OF_STATE_CODE_CSV_FILE_FOR_INCORRECT_TYPE));
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.ENTERED_WRONG_FILE_TYPE, e.type);
        }
    }
    @Test
    public void givenTheStateCodeCSVFile_IfDelimiterIsIncorrect_ShouldThrowCustomException() throws IOException{
        try{
            int noOfRecords = CensusAnalyser.loadStateCodeCSVFileData(PATH_OF_STATE_CODE_CSV_FILE_INCORRECT_DELIMITER);
            Assert.assertEquals(37,noOfRecords);
        } catch (CensusAnalyserException e){
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_DELIMITER_OR_HEADER,e.type);
        }
    }
    @Test
    public void givenTheStateCodeCSVFile_IfHeaderIsIncorrect_ShouldThrowCustomException() throws IOException{
        try{
            int noOfRecords = CensusAnalyser.loadStateCodeCSVFileData(PATH_OF_STATE_CODE_CSV_FILE_INCORRECT_HEADER);
            Assert.assertEquals(37,noOfRecords);
        } catch (CensusAnalyserException e){
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_DELIMITER_OR_HEADER,e.type);
        }
    }
}
