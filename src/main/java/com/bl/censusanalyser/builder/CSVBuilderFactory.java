package com.bl.censusanalyser.builder;

public class CSVBuilderFactory {
    public static ICSVBuilder ISCVBuilder() {
        return new CSVBuilder();
    }
}