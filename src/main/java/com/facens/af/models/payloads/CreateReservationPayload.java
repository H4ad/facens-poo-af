package com.facens.af.models.payloads;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.facens.af.models.entities.Reservation;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModelProperty;

public class CreateReservationPayload {
  
  @ApiModelProperty
  @JsonSerialize
  @Valid
  @NotNull(message = "A data final precisa ser enviada!")
  public Date endDate;

  @ApiModelProperty
  @JsonSerialize
  @Valid
  @NotNull(message = "A data de inicio precisa ser enviada!")
  public Date startDate;

  public Reservation toEntity(int clientId, int vehicleId, Double total) {
    return new Reservation(clientId, vehicleId, startDate, endDate, total);
  }

  public CreateReservationPayload(Date startDate, Date endDate) {
    this.startDate = startDate;
    this.endDate = endDate;
  }
}
