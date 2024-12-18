package com.bigdata.rabbitredis.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

public class SquareData implements Serializable {
    private int originalNumber;
    private int squaredNumber;
    private Date insertionDate;

    // Constructeur par défaut
    public SquareData() {
    }

    @JsonCreator
    public SquareData(@JsonProperty("originalNumber") int originalNumber,
                      @JsonProperty("squaredNumber") int squaredNumber,
                      @JsonProperty("insertionDate") Date insertionDate) {
        this.squaredNumber = squaredNumber;
        this.insertionDate = insertionDate;
        this.originalNumber = originalNumber;
    }

    public int getSquaredNumber() {
        return squaredNumber;
    }

    public void setSquaredNumber(int squaredNumber) {
        this.squaredNumber = squaredNumber;
    }

    public Date getInsertionDate() {
        return insertionDate;
    }

    public void setInsertionDate(Date insertionDate) {
        this.insertionDate = insertionDate;
    }

    public int getOriginalNumber() { return originalNumber;}

    public void setOriginalNumber(int originalNumber) { this.originalNumber = originalNumber;}
}