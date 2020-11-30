package com.facens.af.models.payloads;

import com.facens.af.models.entities.Client;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModelProperty;

public class UpdateClientPayload {

  @ApiModelProperty
  @JsonSerialize
  public String name;

  @ApiModelProperty
  @JsonSerialize
  public String address;

  @ApiModelProperty
  @JsonSerialize
  public String cpf;

  public Client update(Client client) {
    if (name != null)
      client.setName(name);

    if (address != null)
      client.setAddress(address);

    if (cpf != null)
      client.setCpf(cpf);

    return client;
  }

  public UpdateClientPayload(String name, String address, String cpf) {
    this.name = name;
    this.address = address;
    this.cpf = cpf;
  }

}
