package com.bl.censusanalyser;

public class CSVBuilderFactory {
    public ICSVBuilder createCSVBuilder() {
        return new CSVBuilder();
    }
}