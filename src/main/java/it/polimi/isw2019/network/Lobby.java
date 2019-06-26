package it.polimi.isw2019.network;

import it.polimi.isw2019.network.network_interface.ClientInterface;
import it.polimi.isw2019.network.rmi.NetworkHandlerInterface;
import it.polimi.isw2019.network.rmi.VirtualView;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Lobby {
    private Set<ClientInterface> connectedClients = new HashSet<>();
    private HashMap<String, ClientInterface> clientConnected = new HashMap<>();

    public void addClient(ClientInterface client){
        connectedClients.add(client);
    }

    public Set<ClientInterface> getConnectedClients(){
        return connectedClients;
    }

    public boolean addClientConnected (String nickname, ClientInterface clientInterface){
        if (!clientConnected.containsKey(nickname)) {
            clientConnected.put(nickname, clientInterface);
            return true;
        }
        else return false;
    }

    //crea VV
    //get VV
}
