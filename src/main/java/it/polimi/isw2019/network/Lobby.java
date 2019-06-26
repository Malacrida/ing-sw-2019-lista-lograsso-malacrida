package it.polimi.isw2019.network;

import it.polimi.isw2019.network.network_interface.ClientInterface;
import it.polimi.isw2019.network.rmi.NetworkHandlerInterface;
import it.polimi.isw2019.network.rmi.VirtualView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Lobby {
    private Set<ClientInterface> connectedClients = new HashSet<>();
    private  HashMap<String, ClientInterface> clientConnected = new HashMap<>();
    private ArrayList<VirtualView> virtualViews = new ArrayList<>();
    private ArrayList<String> nicknames = new ArrayList<>();

    public void addClient(ClientInterface client){
        connectedClients.add(client);
    }

    public Set<ClientInterface> getConnectedClients(){
        return connectedClients;
    }

    public boolean addClientConnected (String nickname, ClientInterface clientInterface){
        if (!clientConnected.containsKey(nickname)) {
            clientConnected.put(nickname, clientInterface);
            nicknames.add(nickname);

            return true;
        }
        else return false;


    }

    public void setVirtualViews() {
        for (int i=0; i<nicknames.size(); i++ ){
            VirtualView virtualView = new VirtualView(nicknames.get(i), clientConnected.get(nicknames.get(i)));
            virtualViews.add(virtualView);
        }
    }

    public ArrayList<VirtualView> getVirtualViews() {
        return virtualViews;
    }

    //crea VV
    //get VV


}
