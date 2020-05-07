package com.bl.censusanalyser.model;

import com.opencsv.bean.CsvBindByName;

public class IndiaStateCodeCSV {

    @CsvBindByName(column = "SrNo")
    public int srNo;
    @CsvBindByName(column = "State Name")
    public String stateName;
    @CsvBindByName(column = "TIN")
    public int tin;
    @CsvBindByName(column = "StateCode")
    public String stateCode;

    public IndiaStateCodeCSV() {

    }

    public IndiaStateCodeCSV(int srNo, String stateName, int tin, String stateCode) {
        this.srNo = srNo;
        this.stateName = stateName;
        this.tin = tin;
        this.stateCode = stateCode;
    }

    public String getStateCode() {
        return stateCode;
    }

    @Override
    public String toString() {
        return "IndiaStateCodeCSV{" +
                "SrNo='" + srNo + '\'' +
                ",StateName=" + stateName +
                ",TIN=" + tin +
                ", StateCode=" + stateCode +
                '}';
    }
    public int getDensityPerSqKm() {
        return getDensityPerSqKm();
    }

    public int getAreaInSqKm() {
        return getAreaInSqKm();
    }

    public int getPopulation() {
        return getPopulation();
    }

    public String getState() {
        return getState();
    }
}