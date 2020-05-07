package com.bl.censusanalyser.dao;

import com.bl.censusanalyser.model.CSVStateCensus;
import com.bl.censusanalyser.model.CSVStateCode;
import com.bl.censusanalyser.model.USCensus;

public class CensusDAO {
    public String state;
    public long population;
    public long AreaInSqKm;
    public int DensityPerSqkm;
    public String stateCode;
    public int tin;
    public int srNo;
    public double area;
    public float density;

    public CensusDAO(CSVStateCode stateCode) {
        this.state = stateCode.stateName;
        this.srNo = stateCode.srNo;
        this.tin = stateCode.tin;
        this.stateCode = stateCode.stateCode;
    }

    public CensusDAO(CSVStateCensus csvStateCensus) {
        this.state = csvStateCensus.State;
        this.population = csvStateCensus.Population;
        this.AreaInSqKm = csvStateCensus.AreaInSqKm;
        this.DensityPerSqkm = csvStateCensus.DensityPerSqkm;
    }
    public CensusDAO(USCensus usCensus) {
        this.state = usCensus.state;
        this.population = usCensus.population;
        this.area = usCensus.area;
        this.density = usCensus.populationDensity;;
        this.stateCode = usCensus.stateID;
    }
}
