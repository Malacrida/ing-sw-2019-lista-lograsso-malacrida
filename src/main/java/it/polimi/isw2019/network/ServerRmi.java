package it.polimi.isw2019.network;

import it.polimi.isw2019.controller.MainController;
import it.polimi.isw2019.controller.VisitorController;
import it.polimi.isw2019.message.playermove.PlayerMove;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;


public class ServerRmi  extends UnicastRemoteObject implements ServerInterfaceRMI{


    private VisitorController controller= new MainController();
    private HashMap<String,ClientInterfaceRmi> clientConnected = new HashMap<>();


    public ServerRmi ()throws RemoteException {

    }

    @Override
    public void addToTheServer(String name, ClientInterfaceRmi clientInterfaceRmi)throws RemoteException {
        if (!clientConnected.containsKey(name)) {
            clientConnected.put(name, clientInterfaceRmi);
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
