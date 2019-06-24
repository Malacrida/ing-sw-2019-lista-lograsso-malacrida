package it.polimi.isw2019.network.rmi;

import it.polimi.isw2019.controller.MainController;
import it.polimi.isw2019.controller.VisitorController;
import it.polimi.isw2019.message.playermove.ChooseActionMove;
import it.polimi.isw2019.message.playermove.FirstMessage;
import it.polimi.isw2019.message.playermove.PlayerMove;
import it.polimi.isw2019.message.playermove.WeaponCardChoice;
import it.polimi.isw2019.model.powerupcard.InterfacePowerUpCard;
import it.polimi.isw2019.model.weaponcard.WeaponCardInterface;
import it.polimi.isw2019.network.network_interface.ClientInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;


public class ServerRmi  extends UnicastRemoteObject implements ServerInterfaceRMI, Remote {


    private VisitorController controller= new MainController();
    private HashMap<String, NetworkHandlerInterface> clientConnected = new HashMap<>();
    private ArrayList<VirtualView> virtualViews= new ArrayList<>();


    public ServerRmi ()throws RemoteException {

    }

    @Override
    public void addToTheServer(String name, NetworkHandlerInterface networkHandler)throws RemoteException {
        if (!clientConnected.containsKey(name)) {
            clientConnected.put(name, networkHandler);
        }
        System.out.println("Aggiunto: "+name);
        VirtualView virtualView = new VirtualView(name, networkHandler);
        virtualViews.add(virtualView);
    }



    @Override
    public void removeToTheClient(String name) throws RemoteException {
        if (!clientConnected.containsKey(name)) {
            clientConnected.remove(name);
        }

    }

    @Override
    public void receiveChooseActionMove(String player, int numAction) {
        System.out.println("ricevuto una invocazione");
        for (int i=0; i<virtualViews.size(); i++){
            if(virtualViews.get(i).getNickname().equals(player)){
                virtualViews.get(i).createChooseActionMove(player, numAction);
            }
        }
    }

    @Override
    public void receiveChooseMap(String player, int index, int color) {
        for (int i=0; i<virtualViews.size(); i++){
            if(virtualViews.get(i).getNickname().equals(player)){
                virtualViews.get(i).createChooseMap(player, index, color);
            }
        }

    }

    @Override
    public void receiveRun(String player, int[][] movement) {
        for (int i=0; i<virtualViews.size(); i++){
            if(virtualViews.get(i).getNickname().equals(player)){
                virtualViews.get(i).createRun(player, movement);
            }
        }
    }

    @Override
    public void receiveGrab(String player) {
        for (int i=0; i<virtualViews.size(); i++){
            if(virtualViews.get(i).getNickname().equals(player)){
                virtualViews.get(i).createGrab(player);
            }
        }
    }


    @Override
    public void receiveRegisterPlayer(FirstMessage firstMessage) {

    }

    @Override
    public void receiveReload(String player) {
        for (int i=0; i<virtualViews.size(); i++){
            if(virtualViews.get(i).getNickname().equals(player)){
                virtualViews.get(i).createReload(player);
            }
        }
    }

    @Override
    public void receivePowerUpChoice(String player, int idPowerUp) {
        for (int i=0; i<virtualViews.size(); i++){
            if(virtualViews.get(i).getNickname().equals(player)){
                virtualViews.get(i).createPowerUpChoice(player, idPowerUp);
            }
        }
    }

    @Override
    public void receiveUsePowerUpCard(String player, InterfacePowerUpCard powerUpCardInterface) {
        for (int i=0; i<virtualViews.size(); i++){
            if(virtualViews.get(i).getNickname().equals(player)){
                virtualViews.get(i).createUsePowerUpCard(player, powerUpCardInterface);
            }
        }
    }

    @Override
    public void receiveWeaponCardChoice(String player, int indexWeaponCard, String[] payment, ArrayList<InterfacePowerUpCard> powerUpCards, boolean grab) {
        for (int i=0; i<virtualViews.size(); i++){
            if(virtualViews.get(i).getNickname().equals(player)){
                virtualViews.get(i).createWeaponCardChoice(player, indexWeaponCard,payment,powerUpCards,grab);
            }
        }
    }

    @Override
    public void receiveUseWeaponCard(String player, WeaponCardInterface weaponCard) {
        for (int i=0; i<virtualViews.size(); i++){
            if(virtualViews.get(i).getNickname().equals(player)){
                virtualViews.get(i).createUseWeaponCard(player, weaponCard);
            }
        }
    }




}
