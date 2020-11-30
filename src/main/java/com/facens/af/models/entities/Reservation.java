package com.facens.af.models.entities;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Reservation {

  public Reservation(int clientId, int vehicleId, Date startDate, Date endDate, Double totalPrice) {
    this.clientId = clientId;
    this.vehicleId = vehicleId;
    this.startDate = startDate;
    this.endDate = endDate;
    this.totalPrice = totalPrice;
  }
  
  private static int internalIdIndex = 0;

  @JsonSerialize
  private int id;

  @JsonSerialize
  private int clientId;

  @JsonSerialize
  private int vehicleId;

  @JsonSerialize
  private Date startDate;

  @JsonSerialize
  private Date endDate;

  @JsonSerialize
  private Double totalPrice;

  public Reservation save() {
    internalIdIndex++;

    id = internalIdIndex;

    return this;
  }

  public int getId() {
    return id;
  }

  public int getClientId() {
    return clientId;
  }

  public void setClientId(int clientId) {
    this.clientId = clientId;
  }

  public int getVehicleId() {
    return vehicleId;
  }

  public void setVehicleId(int vehicleId) {
    this.vehicleId = vehicleId;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public Double getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(Double totalPrice) {
    this.totalPrice = totalPrice;
  }

}