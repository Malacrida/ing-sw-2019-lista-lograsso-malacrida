package it.polimi.isw2019.network.rmi;

import it.polimi.isw2019.controller.MainController;
import it.polimi.isw2019.controller.VisitorController;
import it.polimi.isw2019.network.GathererInterface;
import it.polimi.isw2019.network.Lobby;
import it.polimi.isw2019.network.network_interface.ClientInterface;
import it.polimi.isw2019.network.network_interface.ServerInterface;
import it.polimi.isw2019.view.CLIView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;


public class ServerRmi  extends UnicastRemoteObject implements ServerInterface<ClientInterface>, Remote, GathererInterface {


    private VisitorController controller= new MainController();
    private HashMap<String, NetworkHandlerInterface> clientConnected = new HashMap<>();
    private ArrayList<VirtualView> virtualViews= new ArrayList<>();
    private MainController mainController;
    private int port;
    private Lobby lobby;

    public ServerRmi(int port) throws RemoteException {
        this.port=port;
    }

    @Override
    public void setLobby(Lobby lobby) {
        this.lobby= lobby;
    }

    @Override
    public void registerNewClient(ClientInterface client, String nickname, CLIView view) throws IOException, RemoteException {
        System.out.println("richiesta di login: "+ nickname);
        System.out.println(client.getNickname());
        if (lobby.addClientConnected(nickname,client))
            client.logInCorrect();
        else client.logInFail();

    }

    public void setVirtualViews(ArrayList<VirtualView> virtualViews) {
        this.virtualViews = virtualViews;
    }

    @Override
    public void reconnectClient(ClientInterface client, String nickname) throws RemoteException {
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
    public void receiveRegisterPlayer(String player, String actionHero) {
        System.out.println("ricevo una registrazione da: "+player);
        for (int i=0; i<virtualViews.size(); i++){
            if(virtualViews.get(i).getNickname().equals(player)){
                virtualViews.get(i).createRegisterPlayer(player,actionHero);
            }
        }

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
    public void receiveUsePowerUpCard(String player/*, InterfacePowerUpCard powerUpCardInterface*/) {
        for (int i=0; i<virtualViews.size(); i++){
            if(virtualViews.get(i).getNickname().equals(player)){
                virtualViews.get(i).createUsePowerUpCard(player/*, powerUpCardInterface*/);
            }
        }
    }

    @Override
    public void receiveWeaponCardChoice(String player, int indexWeaponCard, String[] payment,/* ArrayList<InterfacePowerUpCard> powerUpCards,*/ boolean grab) {
        for (int i=0; i<virtualViews.size(); i++){
            if(virtualViews.get(i).getNickname().equals(player)){
                virtualViews.get(i).createWeaponCardChoice(player, indexWeaponCard,payment,/*powerUpCards,*/grab);
            }
        }
    }


    @Override
    public void receiveUseWeaponCard(String player, int weaponCard) {
        for (int i=0; i<virtualViews.size(); i++){
            if(virtualViews.get(i).getNickname().equals(player)){
                virtualViews.get(i).createUseWeaponCard(player, weaponCard);
            }
        }
    }


    @Override
    public void write(Object object) throws IOException, RemoteException {

    }




    @Override
    public void run() {
        try {
            LocateRegistry.createRegistry(port);
        }
        catch (RemoteException e){
            e.printStackTrace();
        }

        try {
            Naming.rebind("rmi://localhost:"+port+"/ServerRmi", this);
        }
        catch (RemoteException e) {
            System.out.println("Error remote");
            e.printStackTrace();
        }
        catch (MalformedURLException e) {
            System.out.println("Malfunction url");
            e.printStackTrace();
        }
    }
}
