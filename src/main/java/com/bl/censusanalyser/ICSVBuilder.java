package com.bl.censusanalyser;

import com.bl.censusanalyser.exception.CSVBuilderException;

import java.io.Reader;
import java.util.HashMap;

public interface ICSVBuilder {
    public <E> HashMap<E, E> getCSVFileMap(Reader reader, Class csvClass) throws CSVBuilderException;
}

