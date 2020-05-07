package com.bl.censusanalyser;

public class CSVBuilderFactory {
    public static ICSVBuilder ISCVBuilder() {
        return new CSVBuilder();
    }
}