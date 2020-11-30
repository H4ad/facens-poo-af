package com.facens.af.controllers;

import java.util.List;

import com.facens.af.models.entities.Reservation;
import com.facens.af.repositories.ReservationRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

  @GetMapping
  public ResponseEntity<List<Reservation>> getAll() {
    return new ResponseEntity<>(ReservationRepository.listAll(), HttpStatus.OK);
  }

  @GetMapping("{id}")
  public ResponseEntity<Reservation> getOne(@PathVariable int id) {
    Reservation entity = ReservationRepository.getOne(id);

    if (entity != null)
      return new ResponseEntity<>(entity, HttpStatus.OK);

    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A reserva no qual você quer buscar não foi encontrado.");
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Exception> delete(@PathVariable int id) {
    boolean wasRemoved = ReservationRepository.delete(id);

    if (wasRemoved)
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A reserva no qual você quer remover não foi encontrado.");
  }
}
