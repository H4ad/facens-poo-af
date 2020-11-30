package com.facens.af.controllers;

import java.util.List;

import javax.validation.Valid;

import com.facens.af.models.entities.Reservation;
import com.facens.af.models.entities.Vehicle;
import com.facens.af.models.payloads.CreateVehiclePayload;
import com.facens.af.models.payloads.UpdateVehiclePayload;
import com.facens.af.repositories.ReservationRepository;
import com.facens.af.repositories.VehicleRepository;

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
@RequestMapping("/vehicles")
public class VehicleController {

  @PostMapping
  public ResponseEntity<Vehicle> create(@RequestBody @Valid CreateVehiclePayload payload) {
    Vehicle entity = VehicleRepository.save(payload);

    return new ResponseEntity<>(entity, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<Vehicle>> getAll() {
    return new ResponseEntity<>(VehicleRepository.listAll(), HttpStatus.OK);
  }

  @GetMapping("{id}")
  public ResponseEntity<Vehicle> getOne(@PathVariable int id) {
    Vehicle entity = VehicleRepository.getOne(id);

    if (entity != null)
      return new ResponseEntity<>(entity, HttpStatus.OK);

    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O veiculo no qual você quer buscar não foi encontrado.");
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Exception> delete(@PathVariable int id) {
    boolean wasRemoved = VehicleRepository.delete(id);

    if (wasRemoved)
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O veiculo no qual você quer remover não foi encontrado.");
  }

  @PutMapping("{id}")
  public ResponseEntity<Vehicle> put(@PathVariable int id, @RequestBody @Valid UpdateVehiclePayload payload) {
    Vehicle entityUpdated = VehicleRepository.update(id, payload);

    if (entityUpdated != null)
      return new ResponseEntity<>(entityUpdated, HttpStatus.OK);

    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
  }

  @GetMapping("{id}/reservations")
  public ResponseEntity<List<Reservation>> getReservationsByVehicle(@PathVariable int id) {
    return new ResponseEntity<>(ReservationRepository.listAllByVehicle(id), HttpStatus.OK);
  }
}
