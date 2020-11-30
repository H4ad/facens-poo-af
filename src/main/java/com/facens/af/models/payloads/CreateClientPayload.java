package com.facens.af.models.payloads;

import com.facens.af.models.entities.Client;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class CreateClientPayload {

  @ApiModelProperty
  @JsonSerialize
  @Valid
  @NotNull(message = "O nome do cliente precisa ser enviado!")
  public String name;

  @ApiModelProperty
  @JsonSerialize
  @Valid
  @NotNull(message = "O endereço do cliente precisa ser enviado!")
  public String address;

  @ApiModelProperty
  @JsonSerialize
  @Valid
  @NotNull(message = "O CPF precisa ser enviado!")
  public String cpf;

  public Client toEntity() {
    return new Client(name, address, cpf);
  }

  public CreateClientPayload(String name, String address, String cpf) {
    this.name = name;
    this.address = address;
    this.cpf = cpf;
  }

}
