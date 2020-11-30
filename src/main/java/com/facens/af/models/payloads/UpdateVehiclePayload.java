package com.facens.af.models.payloads;

import com.facens.af.models.entities.Vehicle;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModelProperty;

public class UpdateVehiclePayload {

  @ApiModelProperty
  @JsonSerialize
  public String model;

  @ApiModelProperty
  @JsonSerialize
  public Double priceDiary;

  public Vehicle update(Vehicle vehicle) {
    if (model != null)
      vehicle.setModel(model);

    if (priceDiary != null)
      vehicle.setPriceDiary(priceDiary);

    return vehicle;
  }

  public UpdateVehiclePayload(String model, Double priceDiary) {
    this.model = model;
    this.priceDiary = priceDiary;
  }
}