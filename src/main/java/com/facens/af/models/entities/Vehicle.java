package com.facens.af.models.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Vehicle {
  
  public Vehicle() {}

  public Vehicle(String model, Double priceDiary) {
    this.model = model;
    this.priceDiary = priceDiary;
  }
  
  private static int internalIdIndex = 0;

  @JsonSerialize
  private int id;

  @JsonSerialize
  private String model;

  @JsonSerialize
  private Double priceDiary;

  public Vehicle save() {
    internalIdIndex++;

    id = internalIdIndex;

    return this;
  }

  public int getId() {
    return id;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public Double getPriceDiary() {
    return priceDiary;
  }

  public void setPriceDiary(Double priceDiary) {
    this.priceDiary = priceDiary;
  }
}
