package com.bl.censusanalyser;

import com.bl.censusanalyser.dao.IndiaCensusDAO;
import com.bl.censusanalyser.exception.CSVBuilderException;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
public class StateCensusAnalyserTest {
    String PATH_OF_CSV_FILE = "./src/test/resources/StateCensusData.csv";
    String PATH_OF_CSV_FILE_FOR_FILE_NOT_FOUND_EXCEPTION = "./src/test/resources/StateCensusData.csv";
    String PATH_OF_CSV_FILE_FOR_INCORRECT_TYPE_EXCEPTION = "./src/test/resources/StateCensusData.csv";
    String PATH_OF_CSV_FILE_FOR_INCORRECT_DELIMITER = "./src/test/resources/StateCensusData.csv";
    String PATH_OF_STATE_CODE_CSV_FILE = "./src/test/resources/StateCode.csv";
    String PATH_OF_STATE_CODE_CSV_FILE_FOR_FILE_NOT_FOUND = "./src/test/resources/StateCode.csv";
    String PATH_OF_STATE_CODE_CSV_FILE_FOR_INCORRECT_TYPE = "./src/test/resources/StateCode.CSV";
    String PATH_OF_STATE_CODE_CSV_FILE_INCORRECT_DELIMITER = "./src/test/resources/StateCode.csv";
    String PATH_OF_STATE_CODE_CSV_FILE_INCORRECT_HEADER = "./src/test/resources/StateCode.csv";
    String PATH_OF_CSV_FILE_FOR_INCORRECT_HEADER = "./src/test/resources/StateCensusDataCopy.csv";

    CensusAnalyser censusAnalyser = new CensusAnalyser();

    //1.1
    @Test
    public void givenTheStateCensusCSVFile_WhenNoOfRecordMatch_ShouldReturnTrue() throws IOException, CSVBuilderException {
        int noOfRecords = censusAnalyser.loadStateCensusData(PATH_OF_CSV_FILE);
        Assert.assertEquals(29, noOfRecords);
    }
    //1.2
    @Test
    public void givenTheStateCensusCSVFile_IfIncorrect_ShouldThrowCustomException() throws IOException {
        try {
            int noOfRecords = censusAnalyser.loadStateCensusData(PATH_OF_CSV_FILE_FOR_FILE_NOT_FOUND_EXCEPTION);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.ENTERED_WRONG_FILE_NAME, e.type);
        }
    }
    //1.3
    @Test
    public void givenTheStateCensusCSVFile_IfTypeIsIncorrect_ShouldThrowCustomException() throws CSVBuilderException, IOException {
        try {
            censusAnalyser.getFileExtension(new File(PATH_OF_CSV_FILE_FOR_INCORRECT_TYPE_EXCEPTION));
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.ENTERED_WRONG_FILE_TYPE, e.type);
        }
    }
    //1.4
    @Test
    public void givenTheStateCensusCSVFile_IfDelimiterIsIncorrect_ShouldThrowCustomException() throws IOException, CSVBuilderException {
        try {
            int noOfRecords = censusAnalyser.loadStateCensusData(PATH_OF_CSV_FILE_FOR_INCORRECT_DELIMITER);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.INCORRECT_DELIMITER, e.type);
        }
    }
    //1.5
    @Test
    public void givenTheStateCensusCSVFile_IfHeaderIsIncorrect_ShouldThrowCustomException() throws IOException {
        try {
            int noOfRecords = censusAnalyser.loadStateCensusData(PATH_OF_CSV_FILE_FOR_INCORRECT_HEADER);
            Assert.assertEquals(28, noOfRecords);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.ENTERED_WRONG_FILE_NAME, e.type);
        }
    }
    //2.1
    @Test
    public void givenTheStateCodeCSVFile__WhenNoOfRecordMatches_ShouldReturnTrue() throws IOException, CSVBuilderException {
        int noOfRecords = censusAnalyser.loadStateCodeData(PATH_OF_STATE_CODE_CSV_FILE);
        Assert.assertEquals(37, noOfRecords);
    }
    //2.2
    @Test
    public void givenTheStateCodeCSVFile_IfIncorrect_ShouldThrowCustomException() throws IOException {
        try {
            int noOfRecords = censusAnalyser.loadStateCodeData(PATH_OF_STATE_CODE_CSV_FILE_FOR_FILE_NOT_FOUND);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.ENTERED_WRONG_FILE_NAME, e.type);
        }
    }
    //2.3
    @Test
    public void givenTheStateCodeCSVFile_IfTypeIsIncorrect_ShouldThrowCustomException() throws CSVBuilderException, IOException {
        try {
            censusAnalyser.getFileExtension(new File(PATH_OF_STATE_CODE_CSV_FILE_FOR_INCORRECT_TYPE));
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.ENTERED_WRONG_FILE_TYPE, e.type);
        }
    }
    //2.4
    @Test
    public void givenTheStateCodeCSVFile_IfDelimiterIsIncorrect_ShouldThrowCustomException() throws IOException {
        try {
            int noOfRecords = censusAnalyser.loadStateCodeData(PATH_OF_STATE_CODE_CSV_FILE_INCORRECT_DELIMITER);
            Assert.assertEquals(37, noOfRecords);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.INCORRECT_DELIMITER_OR_HEADER, e.type);
        }
    }
    //2.5
    @Test
    public void givenTheStateCodeCSVFile_IfHeaderIsIncorrect_ShouldThrowCustomException() throws IOException {
        try {
            int noOfRecords = censusAnalyser.loadStateCodeData(PATH_OF_STATE_CODE_CSV_FILE_INCORRECT_HEADER);
            Assert.assertEquals(37, noOfRecords);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.INCORRECT_DELIMITER_OR_HEADER, e.type);
        }
    }
    //3.1
    @Test
    public void givenTheStateCensusCSVFile_WhenSortedOnState_ShouldReturnSortedList() throws IOException,
            CSVBuilderException{
        censusAnalyser.loadStateCensusData(PATH_OF_CSV_FILE);
        String sortedCensusData = censusAnalyser.getStateWiseSortedData();
        IndiaCensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusDAO[].class);
        Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
        Assert.assertEquals("West Bengal", censusCSV[28].state);
    }
    //4.1
    @Test
    public void givenTheStateCodeCSVFile_WhenSortedOnStateCode_ShouldReturnSortedList() throws IOException, CSVBuilderException {
        censusAnalyser.loadStateCodeData(PATH_OF_STATE_CODE_CSV_FILE);
        String sortedStateCodeData = censusAnalyser.getStateCodeWiseSortedData();
        IndiaCensusDAO[] censusCSV = new Gson().fromJson(sortedStateCodeData, IndiaCensusDAO[].class);
        Assert.assertEquals("AD", censusCSV[0].stateCode);
        Assert.assertEquals("WB", censusCSV[36].stateCode);
    }
    //5.1
    @Test
    public void givenTheStateCensusCSVFile_WhenSortedOnPopulation_ShouldReturnSortedList() throws IOException {
        try {
            censusAnalyser.loadStateCensusData(PATH_OF_CSV_FILE);
            String sortedStatePopulationData = censusAnalyser.getStatePopulationWiseSortedData();
            IndiaCensusDAO[] censusCSV = new Gson().fromJson(sortedStatePopulationData, IndiaCensusDAO[].class);
            Assert.assertEquals("Uttar Pradesh", censusCSV[0].state);
            Assert.assertEquals("Sikkim", censusCSV[28].state);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }
    //6.1
    @Test
    public void givenTheStateCensusCSVFile_WhenSortedOnPopulationDensity_ShouldReturnSortedList() throws IOException {
        try {
            censusAnalyser.loadStateCensusData(PATH_OF_CSV_FILE);
            String sortedStatePopulationData = censusAnalyser.getStatePopulationDensityWiseSortedData();
            IndiaCensusDAO[] censusCSV = new Gson().fromJson(sortedStatePopulationData, IndiaCensusDAO[].class);
            Assert.assertEquals("Bihar", censusCSV[0].state);
            Assert.assertEquals("Arunachal Pradesh", censusCSV[28].state);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }
}