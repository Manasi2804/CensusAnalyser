package com.bl.censusanalyser;

import com.bl.censusanalyser.exception.CSVBuilderException;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder {
    <E> Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CSVBuilderException;
    <T> Iterator<T> getFileIterator(BufferedReader reader, Class<T> csvClass);
}


