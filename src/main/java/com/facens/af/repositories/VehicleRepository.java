package com.facens.af.repositories;

import java.util.ArrayList;
import java.util.List;

import com.facens.af.models.entities.Vehicle;
import com.facens.af.models.payloads.CreateVehiclePayload;
import com.facens.af.models.payloads.UpdateVehiclePayload;

public class VehicleRepository {

  private static List<Vehicle> listVehicles = new ArrayList<Vehicle>();

  public static List<Vehicle> listAll() {
    return listVehicles;
  }

  public static Vehicle save(CreateVehiclePayload payload) {
    Vehicle Vehicle = payload.toEntity().save();

    listVehicles.add(Vehicle);

    return Vehicle;
  }

  public static Vehicle getOne(int id) {
    for (Vehicle Vehicle : listVehicles) {
      if (Vehicle.getId() != id)
        continue;

      return Vehicle;
    }

    return null;
  }

  public static Vehicle update(int id, UpdateVehiclePayload payload) {
    for (Vehicle Vehicle : listVehicles) {
      if (Vehicle.getId() != id)
        continue;

      return payload.update(Vehicle);
    }

    return null;
  }

  public static boolean delete(int id) {
    return listVehicles.removeIf(Vehicle -> Vehicle.getId() == id);
  }

}
