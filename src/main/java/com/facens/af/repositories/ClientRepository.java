package com.facens.af.repositories;

import java.util.ArrayList;
import java.util.List;

import com.facens.af.models.entities.Client;
import com.facens.af.models.payloads.CreateClientPayload;
import com.facens.af.models.payloads.UpdateClientPayload;

public class ClientRepository {

  private static List<Client> listClients = new ArrayList<Client>();

  public static List<Client> listAll() {
    return listClients;
  }

  public static Client save(CreateClientPayload payload) {
    Client client = payload.toEntity().save();

    listClients.add(client);

    return client;
  }

  public static Client getOne(int id) {
    for (Client client : listClients) {
      if (client.getId() != id)
        continue;

      return client;
    }

    return null;
  }

  public static Client update(int id, UpdateClientPayload payload) {
    for (Client client : listClients) {
      if (client.getId() != id)
        continue;

      payload.update(client);

      return client;
    }

    return null;
  }

  public static boolean delete(int id) {
    return listClients.removeIf(client -> client.getId() == id);
  }
}
