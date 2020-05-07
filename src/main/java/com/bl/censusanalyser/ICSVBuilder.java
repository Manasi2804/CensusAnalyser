package com.bl.censusanalyser;

import com.bl.censusanalyser.exception.CensusAnalyserException;

import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder {
    public <E> Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CensusAnalyserException;
    public <E> int getCount(Iterator<E> csvRecords);
}
