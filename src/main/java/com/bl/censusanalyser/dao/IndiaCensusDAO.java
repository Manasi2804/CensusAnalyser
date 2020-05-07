package com.bl.censusanalyser.dao;

import com.bl.censusanalyser.model.CSVStateCensus;
import com.bl.censusanalyser.model.CSVStateCode;

public class IndiaCensusDAO {
    public String state;
    public long population;
    public long AreaInSqKm;
    public int DensityPerSqkm;
    public String stateCode;
    public int tin;
    public int srNo;

    public IndiaCensusDAO(CSVStateCode stateCode) {
        this.state = stateCode.stateName;
        this.srNo = stateCode.srNo;
        this.tin = stateCode.tin;
        this.stateCode = stateCode.stateCode;
    }

    public IndiaCensusDAO(CSVStateCensus csvStateCensus) {
        this.state = csvStateCensus.State;
        this.population = csvStateCensus.Population;
        this.AreaInSqKm = csvStateCensus.AreaInSqKm;
        this.DensityPerSqkm = csvStateCensus.DensityPerSqkm;
    }
}
