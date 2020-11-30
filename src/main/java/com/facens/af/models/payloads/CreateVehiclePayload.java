package com.facens.af.models.payloads;

import javax.validation.constraints.NotNull;

import com.facens.af.models.entities.Vehicle;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModelProperty;

public class CreateVehiclePayload {

  @ApiModelProperty
  @JsonSerialize
  @NotNull(message = "O modelo do veiculo precisa ser enviado!")
  public String model;

  @ApiModelProperty
  @JsonSerialize
  @NotNull(message = "O preço da diária do veiculo precisa ser enviado!")
  public Double priceDiary;

  public Vehicle toEntity() {
    return new Vehicle(model, priceDiary);
  }

  public CreateVehiclePayload(String model, Double priceDiary) {
    this.model = model;
    this.priceDiary = priceDiary;
  }
}