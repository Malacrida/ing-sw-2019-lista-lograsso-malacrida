package it.polimi.isw2019.network.rmi;

import it.polimi.isw2019.controller.MainController;
import it.polimi.isw2019.controller.VisitorController;
import it.polimi.isw2019.message.playermove.PlayerMove;
import it.polimi.isw2019.network.network_interface.ClientInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;


public class ServerRmi  extends UnicastRemoteObject implements ServerInterfaceRMI {


    private VisitorController controller= new MainController();
    private HashMap<String, ClientInterface> clientConnected = new HashMap<>();
    private ArrayList<VirtualView> virtualViews= new ArrayList<>();


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
    public void receiveMove(PlayerMove playerMove) {

    }

    @Override
    public void receiveChooseActionMove(String nickname, String idPlayer, int numAction) {
        for (int i=0; i<virtualViews.size(); i++){
            if(virtualViews.get(i).getNickname().equals(nickname)){
                virtualViews.get(i).createChooseActionMove(idPlayer, numAction);
            }
        }
    }


}
