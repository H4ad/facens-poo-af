package com.facens.af.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.facens.af.models.entities.Client;
import com.facens.af.models.entities.Reservation;
import com.facens.af.models.entities.Vehicle;
import com.facens.af.models.payloads.CreateReservationPayload;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ReservationRepository {

  private static List<Reservation> listReservations = new ArrayList<Reservation>();

  public static List<Reservation> listAll() {
    return listReservations;
  }

  public static List<Reservation> listAllByClient(int clientId) {
    List<Reservation> list = new ArrayList<Reservation>();

    for (Reservation reservation : listReservations) {
      if (reservation.getClientId() != clientId)
        continue;

      list.add(reservation);
    }

    return list;
  }

  public static List<Reservation> listAllByVehicle(int vehicleId) {
    List<Reservation> list = new ArrayList<Reservation>();

    for (Reservation reservation : listReservations) {
      if (reservation.getVehicleId() != vehicleId)
        continue;

      list.add(reservation);
    }

    return list;
  }

  public static Reservation save(int clientId, int vehicleId, CreateReservationPayload payload) {
    Vehicle vehicle = VehicleRepository.getOne(vehicleId);

    if (vehicle == null)
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,
          "O veiculo informado não foi encontrado dentro da base de dados.");

    Client client = ClientRepository.getOne(clientId);

    if (client == null)
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,
          "O cliente informado não foi encontrado dentro da base de dados.");

    if (payload.startDate.before(new Date()))
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "A data de início não pode ser anterior ao momento atual.");

    if (payload.startDate.getDay() == 0)
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A data de início não pode ser em um domingo.");

    if (payload.startDate.after(payload.endDate))
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "A data de início não pode ser maior que a data final.");

    if (payload.endDate.getDay() == 0)
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A data final não pode ser em um domingo.");

    List<Reservation> allReservations = ReservationRepository.listAll();

    for (Reservation reservation : allReservations) {
      if (reservation.getVehicleId() != vehicleId)
        continue;

      if (!ReservationRepository.isBetween(payload.startDate, reservation.getStartDate(), reservation.getEndDate()))
        continue;

      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe uma reserva para essa determinada data.");
    }

    long totalDays = Math.max(1, ReservationRepository.getDifferenceDays(payload.startDate, payload.endDate));
    double totalPrice = totalDays * vehicle.getPriceDiary();

    Reservation Reservation = payload.toEntity(clientId, vehicleId, totalPrice).save();

    listReservations.add(Reservation);

    return Reservation;
  }

  public static Reservation getOne(int id) {
    for (Reservation Reservation : listReservations) {
      if (Reservation.getId() != id)
        continue;

      return Reservation;
    }

    return null;
  }

  public static boolean delete(int id) {
    return listReservations.removeIf(Reservation -> Reservation.getId() == id);
  }

  public static long getDifferenceDays(Date d1, Date d2) {
    long diff = d2.getTime() - d1.getTime();

    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
  }

  public static boolean isBetween(Date target, Date start, Date end) {
    return start.compareTo(target) * target.compareTo(end) >= 0;
  }
}
