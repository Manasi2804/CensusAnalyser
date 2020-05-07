package com.bl.censusanalyser;

import com.bl.censusanalyser.exception.CSVBuilderException;
import com.bl.censusanalyser.model.CSVStateCensus;
import com.bl.censusanalyser.model.CSVStateCode;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

public class CensusAnalyser {
    ICSVBuilder csvBuilder = new CSVBuilderFactory().createCSVBuilder();
    List<CSVStateCensus> StateCensusRecord = null;
    List<CSVStateCode> StateCodeRecord = null;

    public int loadStateCensusCSVFileData(String filePath) throws CSVBuilderException, IOException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            StateCensusRecord = csvBuilder.getCSVFileList(reader, CSVStateCensus.class);
            return StateCensusRecord.size();
        } catch (NoSuchFileException e) {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.ENTERED_WRONG_FILE_NAME,
                    "FILE NAME IS INCORRECT");
        } catch (RuntimeException e) {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.INCORRECT_DELIMITER_OR_HEADER,
                    "FILE DELIMITER OR HEADER IS INCORRECT");
        }
    }

    public int loadStateCodeCSVFileData(String filePath) throws IOException, CSVBuilderException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            StateCodeRecord = csvBuilder.getCSVFileList(reader, CSVStateCode.class);
            return StateCodeRecord.size();
        } catch (NoSuchFileException e) {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.ENTERED_WRONG_FILE_NAME,
                    "FILE NAME IS INCORRECT");
        } catch (RuntimeException e) {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.INCORRECT_DELIMITER_OR_HEADER,
                    "FILE DELIMITER OR HEADER IS INCORRECT");
        }
    }

    public static void getFileExtension(File filePath) throws CSVBuilderException {
        String fileName = filePath.getName();
        String extension = null;
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        if (!(extension.equals("csv"))) {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.ENTERED_WRONG_FILE_TYPE,
                    "FILE TYPE IS INCORRECT");
        }
    }
    public String getStateWiseSortedData() throws CSVBuilderException {
        if (StateCensusRecord == null || StateCensusRecord.size() == 0)
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.NO_CENSUS_DATA, "Data empty");
        {
        }
        Comparator<CSVStateCensus> censusCSVComparator = Comparator.comparing(csvStateCensus ->
                csvStateCensus.getState());
        this.sort(censusCSVComparator,StateCensusRecord);
        String sortedStateCensusJson = new Gson().toJson(StateCensusRecord);
        return sortedStateCensusJson;
    }
    public <E> void sort(Comparator<E> censusCSVComparator, List censusRecords) {
        for (int iterate = 0; iterate < censusRecords.size() - 1; iterate++) {
            for (int Inneriterate = 0; Inneriterate < censusRecords.size() - iterate - 1; Inneriterate++) {
                E census1 = (E) censusRecords.get(Inneriterate);
                E census2 = (E) censusRecords.get(Inneriterate + 1);
                if (censusCSVComparator.compare(census1, census2) > 0) {
                    if (censusCSVComparator.compare(census1, census2) > 0) {
                        censusRecords.set(Inneriterate, census2);
                        censusRecords.set(Inneriterate + 1, census1);
                    }
                }
            }
        }
    }
    public String getStateCodeWiseSortedData() throws CSVBuilderException {
        if (StateCodeRecord == null || StateCodeRecord.size() == 0) {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.NO_CENSUS_DATA, "Data Empty");
        }
        Comparator<CSVStateCode> stateCodeCSVComparator = Comparator.comparing(stateCode ->
                stateCode.stateCode);
        this.sort(stateCodeCSVComparator , StateCodeRecord);
        String sortedStateCodeJson = new Gson().toJson(StateCodeRecord);
        return sortedStateCodeJson;
    }
    }