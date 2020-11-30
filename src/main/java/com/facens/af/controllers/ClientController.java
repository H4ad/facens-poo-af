package com.facens.af.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import com.facens.af.models.entities.Client;
import com.facens.af.models.entities.Reservation;
import com.facens.af.models.payloads.CreateClientPayload;
import com.facens.af.models.payloads.CreateReservationPayload;
import com.facens.af.models.payloads.UpdateClientPayload;
import com.facens.af.repositories.ClientRepository;
import com.facens.af.repositories.ReservationRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/clients")
public class ClientController {

  @PostMapping
  public ResponseEntity<Client> create(@RequestBody @Valid CreateClientPayload payload) {
    Client entity = ClientRepository.save(payload);

    return new ResponseEntity<>(entity, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<Client>> getAll() {
    return new ResponseEntity<>(ClientRepository.listAll(), HttpStatus.OK);
  }

  @GetMapping("{id}")
  public ResponseEntity<Client> getOne(@PathVariable int id) {
    Client entity = ClientRepository.getOne(id);

    if (entity != null)
      return new ResponseEntity<>(entity, HttpStatus.OK);

    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O cliente no qual você quer buscar não foi encontrado.");
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Exception> delete(@PathVariable int id) {
    boolean wasRemoved = ClientRepository.delete(id);

    if (wasRemoved)
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O cliente no qual você quer remover não foi encontrado.");
  }

  @PutMapping("{id}")
  public ResponseEntity<Client> put(@PathVariable int id, @RequestBody @Valid UpdateClientPayload payload) {
    Client entityUpdated = ClientRepository.update(id, payload);

    if (entityUpdated != null)
      return new ResponseEntity<>(entityUpdated, HttpStatus.OK);

    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
  }

  @GetMapping("{id}/reservations")
  public ResponseEntity<List<Reservation>> getReservationsByClient(@PathVariable int id) {
    return new ResponseEntity<>(ReservationRepository.listAllByClient(id), HttpStatus.OK);
  }

  @PostMapping("{clientId}/vehicles/{vehicleId}")
  public ResponseEntity<Reservation> create(@PathVariable int clientId, @PathVariable int vehicleId, @RequestBody @Valid CreateReservationPayload payload) {
    Reservation entity = ReservationRepository.save(clientId, vehicleId, payload);

    return new ResponseEntity<>(entity, HttpStatus.CREATED);
  }
}
