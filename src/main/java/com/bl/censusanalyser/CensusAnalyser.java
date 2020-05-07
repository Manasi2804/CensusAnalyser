package com.bl.censusanalyser;

import com.bl.censusanalyser.dao.CensusDAO;
import com.bl.censusanalyser.exception.CSVBuilderException;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class CensusAnalyser {
    ICSVBuilder csvBuilder = new CSVBuilderFactory().createCSVBuilder();
    Collection<CensusDAO> censusRecords = null;
    HashMap<Integer, CensusDAO> censusHashMap = new HashMap<>();
    public enum COUNTRY {INDIA, US}
    public int loadCensusData(COUNTRY country, String... filePath) throws IOException, CSVBuilderException {
        CensusAdapter censusDataLoader = CensusAdapterFactory.getCensusData(country);
        censusHashMap = censusDataLoader.loadCensusData(filePath);
        return censusHashMap.size();
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
        if (censusHashMap == null || censusHashMap.size() == 0)
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.NO_CENSUS_DATA, "Data empty");
        Comparator<Map.Entry<Integer, CensusDAO>> censusComparator = Comparator.comparing(census -> census.getValue().state);
        LinkedHashMap<Integer, CensusDAO> sortedByValue = this.sort(censusComparator);
        censusRecords = sortedByValue.values();
        String sortedStateCensusJson = new Gson().toJson(censusRecords);
        return sortedStateCensusJson;
    }

    public String getStateCodeWiseSortedData() throws CSVBuilderException {
        if (censusHashMap == null || censusHashMap.size() == 0)
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.NO_CENSUS_DATA, "Data empty");
        Comparator<Map.Entry<Integer, CensusDAO>> censusComparator = Comparator.comparing(census -> census.getValue().stateCode);
        LinkedHashMap<Integer, CensusDAO> sortedByValue = this.sort(censusComparator);
        censusRecords = sortedByValue.values();
        String sortedStateCodeJson = new Gson().toJson(censusRecords);
        return sortedStateCodeJson;
    }

    public LinkedHashMap<Integer, CensusDAO> sort(Comparator censusCSVComparator) {
        Set<Map.Entry<Integer, CensusDAO>> entries = censusHashMap.entrySet();
        List<Map.Entry<Integer, CensusDAO>> listOfEntries = new ArrayList<Map.Entry<Integer, CensusDAO>>(entries);
        Collections.sort(listOfEntries, censusCSVComparator);
        LinkedHashMap<Integer, CensusDAO> sortedByValue = new LinkedHashMap<Integer, CensusDAO>(listOfEntries.size());
        // copying entries from List to Map
        for (Map.Entry<Integer, CensusDAO> entry : listOfEntries) {
            sortedByValue.put(entry.getKey(), entry.getValue());
        }
        return sortedByValue;
    }

    public String getStatePopulationWiseSortedData() throws CSVBuilderException {
        if (censusHashMap == null || censusHashMap.size() == 0)
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.NO_CENSUS_DATA, "Data empty");
        Comparator<Map.Entry<Integer, CensusDAO>> censusComparator = Comparator.comparing(census -> census.getValue().population);
        LinkedHashMap<Integer, CensusDAO> sortedByValue = this.sort(censusComparator);
        List<CensusDAO> sortedList = new ArrayList<CensusDAO>(sortedByValue.values());
        Collections.reverse(sortedList);
        String sortedStatePopulationJson = new Gson().toJson(sortedList);
        return sortedStatePopulationJson;
    }

    public String getStatePopulationDensityWiseSortedData() throws CSVBuilderException {
        if (censusHashMap == null || censusHashMap.size() == 0)
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.NO_CENSUS_DATA, "Data empty");
        Comparator<Map.Entry<Integer, CensusDAO>> censusComparator = Comparator.comparing(census -> census.getValue().DensityPerSqkm);
        LinkedHashMap<Integer, CensusDAO> sortedByValue = this.sort(censusComparator);
        List<CensusDAO> sortedList = new ArrayList<CensusDAO>(sortedByValue.values());
        Collections.reverse(sortedList);
        String sortedStatePopulationDensityJson = new Gson().toJson(sortedList);
        return sortedStatePopulationDensityJson;
    }

    public String getStateAreaWiseSortedData() throws CSVBuilderException {
        if (censusHashMap == null || censusHashMap.size() == 0)
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.NO_CENSUS_DATA, "Data empty");
        Comparator<Map.Entry<Integer, CensusDAO>> censusComparator = Comparator.comparing(census -> census.getValue().AreaInSqKm);
        LinkedHashMap<Integer, CensusDAO> sortedByValue = this.sort(censusComparator);
        List<CensusDAO> sortedList = new ArrayList<CensusDAO>(sortedByValue.values());
        Collections.reverse(sortedList);
        String sortedStateAreaJson = new Gson().toJson(sortedList);
        return sortedStateAreaJson;
    }
}