package it.polimi.isw2019.network;

import it.polimi.isw2019.network.network_interface.ClientInterface;

import java.util.HashSet;
import java.util.Set;

public class Lobby {
    private Set<ClientInterface> connectedClients = new HashSet<>();

    public void addClient(ClientInterface client){
        connectedClients.add(client);
    }

    public Set<ClientInterface> getConnectedClients(){
        return connectedClients;
    }


}
