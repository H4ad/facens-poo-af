package com.facens.af.models.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Client {

  public Client() {}

  public Client(String name, String address, String cpf) {
    this.name = name;
    this.address = address;
    this.cpf = cpf;
  }

  private static int internalIdIndex = 0;

  @JsonSerialize
  private int id;

  @JsonSerialize
  private String name;

  @JsonSerialize
  private String address;

  @JsonSerialize
  private String cpf;

  public Client save() {
    internalIdIndex++;

    id = internalIdIndex;

    return this;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

}