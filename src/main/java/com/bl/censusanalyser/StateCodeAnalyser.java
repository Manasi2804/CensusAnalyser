package com.bl.censusanalyser;

import com.bl.censusanalyser.exception.CensusAnalyserException;
import com.bl.censusanalyser.model.CSVStateCode;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;

public class StateCodeAnalyser {
    public static int loadCSVFileData(String filePath) throws IOException, CensusAnalyserException {
        int noOfRecords = 0;
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            CSVReader csvReader = new CSVReader(reader);
            CsvToBean<CSVStateCode> csvToBean = new CsvToBeanBuilder<CSVStateCode>(csvReader).
                    withType(CSVStateCode.class).build();
            Iterator<CSVStateCode> csvRecords = csvToBean.iterator();
            while (csvRecords.hasNext()) {
                noOfRecords++;
                csvRecords.next();
            }
            return noOfRecords;
        } catch (NoSuchFileException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.ENTERED_WRONG_FILE_NAME, "FILE NAME IS INCORRECT");
        }
        catch (RuntimeException e){
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.INCORRECT_DELIMITER_OR_HEADER,"FILE DELIMITER OR HEADER IS INCORRECT");
        }
    }
    public static void getFileExtension(File filePath) throws CensusAnalyserException {
        String fileName = filePath.getName();
        String extension = null;
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        if (!(extension.equals("csv"))) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.ENTERED_WRONG_FILE_TYPE, "FILE TYPE IS INCORRECT");
        }
    }
}