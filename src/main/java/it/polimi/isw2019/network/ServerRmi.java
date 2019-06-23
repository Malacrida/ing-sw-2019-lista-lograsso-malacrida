package it.polimi.isw2019.network;

import it.polimi.isw2019.controller.MainController;
import it.polimi.isw2019.controller.VisitorController;
import it.polimi.isw2019.message.playermove.PlayerMove;
import it.polimi.isw2019.network.network_interface.ClientInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;


public class ServerRmi  extends UnicastRemoteObject implements ServerInterfaceRMI{


    private VisitorController controller= new MainController();
    private HashMap<String, ClientInterface> clientConnected = new HashMap<>();


    public ServerRmi ()throws RemoteException {

    }

    @Override
    public void addToTheServer(String name, ClientInterface clientInterface)throws RemoteException {
        if (!clientConnected.containsKey(name)) {
            clientConnected.put(name, clientInterface);
        }
        System.out.println("Aggiunto: "+name);
    }



    @Override
    public void removeToTheClient(String name) throws RemoteException {
        if (!clientConnected.containsKey(name)) {
            clientConnected.remove(name);
        }

    }

    @Override
    public void addGame() {

    }

    @Override
    public void reciveMove(PlayerMove playerMove) {

    }




}
