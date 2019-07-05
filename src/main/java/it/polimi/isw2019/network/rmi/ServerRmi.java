package it.polimi.isw2019.network.rmi;

import it.polimi.isw2019.controller.MainController;
import it.polimi.isw2019.controller.VisitorController;
import it.polimi.isw2019.network.ConfigLoader;
import it.polimi.isw2019.network.GathererInterface;
import it.polimi.isw2019.network.Lobby;
import it.polimi.isw2019.network.TypeConnection;
import it.polimi.isw2019.network.network_interface.ClientInterface;
import it.polimi.isw2019.network.network_interface.ServerInterface;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


public class ServerRmi  extends UnicastRemoteObject implements ServerInterface<ClientInterface>, Remote, GathererInterface {


    private VisitorController controller= new MainController();
    private ArrayList<VirtualViewRmi> virtualViewRmis = new ArrayList<>();
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
    public void registerNewClient(ClientInterface client, String nickname) throws IOException, RemoteException {
        System.out.println("richiesta di login: "+ nickname);
        System.out.println(client.getNickname());
        if (containAlreadyClient(nickname, client) && !virtualViewRmis.isEmpty()){
            client.reconnectionClient();

        }
        else {
            if (lobby.addConnectedClient(nickname, client, TypeConnection.RMI)) {
                System.out.println("Ho inserito il player all'interno della lobby");
                client.logInCorrect();
                System.out.println("gli ho mandato il messaggio di login corretto");
            }
            else client.logInFail();
        }

    }

    /**
     * search the client
     * @param nickname nickname of client
     * @param client interface client
     * @return true if client is already in the game false otherwise
     */
    public boolean containAlreadyClient (String nickname, ClientInterface client){
        for (int i=0; i<virtualViewRmis.size(); i++){
            if (virtualViewRmis.get(i).getNickname().equals(nickname)){
                virtualViewRmis.get(i).setNetworkHandler(client);
                virtualViewRmis.get(i).setNickname(nickname);
                virtualViewRmis.get(i).setActive(true);
                virtualViewRmis.get(i).createConnectionPlayer(nickname,1);
                return true;
            }
        }
        return false;
    }

    @Override
    public void receiveConnectionMove(String player, int connection) throws RemoteException {
        for (int i = 0; i< virtualViewRmis.size(); i++){
            if(virtualViewRmis.get(i).getNickname().equals(player)){
                virtualViewRmis.get(i).createConnectionPlayer(player, connection);
            }
        }
        lobby.analyzeConnectionClient(player, connection);

    }

    @Override
    public void receiveTerminatorMove(String player, int[] coordinates, boolean shootPeople, int colorSpawn, int[] idPlayerToShoot) throws RemoteException {
        for (int i = 0; i< virtualViewRmis.size(); i++){
            if(virtualViewRmis.get(i).getNickname().equals(player)){
                virtualViewRmis.get(i).createTerminatorMove(player,coordinates,shootPeople,colorSpawn, idPlayerToShoot);
            }
        }
    }

    public void setVirtualViewRmis(ArrayList<VirtualViewRmi> virtualViewRmis) {
        this.virtualViewRmis = virtualViewRmis;
    }


    @Override
    public void receiveChooseActionMove(String player, int numAction) {
        for (int i = 0; i< virtualViewRmis.size(); i++){
            if(virtualViewRmis.get(i).getNickname().equals(player)){
                virtualViewRmis.get(i).createChooseActionMove(player, numAction);
            }
        }
    }

    @Override
    public void receiveChooseMap(String player, int index, int color,int mod, int terminator) {
         for (int i = 0; i< virtualViewRmis.size(); i++) {
             if (virtualViewRmis.get(i).getNickname().equals(player)) {
                 virtualViewRmis.get(i).createChooseMap(player, index, color, mod, terminator);
             }
         }
    }

    @Override
    public void receiveRun(String player, int[][] movement) {
        for (int i = 0; i< virtualViewRmis.size(); i++){
            if(virtualViewRmis.get(i).getNickname().equals(player)){
                virtualViewRmis.get(i).createRun(player, movement);
            }
        }
    }

    @Override
    public void receiveGrab(String player, int positionWeaponCard, int[] paymen) {
        for (int i = 0; i< virtualViewRmis.size(); i++){
            if(virtualViewRmis.get(i).getNickname().equals(player)){
                virtualViewRmis.get(i).createGrab(player, positionWeaponCard, paymen);
            }
        }
    }


    @Override
    public void receiveRegisterPlayer(String player, String actionHero) {
        for (int i = 0; i< virtualViewRmis.size(); i++){
            if(virtualViewRmis.get(i).getNickname().equals(player)){
                virtualViewRmis.get(i).createRegisterPlayer(player,actionHero);
            }
        }

    }

    @Override
    public void receiveReload(String player, int[] weaponCard, int[][] payment) {
        for (int i = 0; i< virtualViewRmis.size(); i++){
            if(virtualViewRmis.get(i).getNickname().equals(player)){

                virtualViewRmis.get(i).createReload(player, weaponCard, payment);
            }
        }
    }

    @Override
    public void receivePowerUpChoice(String player, int idPowerUp) {
        for (int i = 0; i< virtualViewRmis.size(); i++){
            if(virtualViewRmis.get(i).getNickname().equals(player)){
                virtualViewRmis.get(i).createPowerUpChoice(player, idPowerUp);
            }
        }
    }

    @Override
    public void receiveUsePowerUpCard(String player,int[][] coordinates, int idPlayer, boolean defend, int positionPowerUp) {
        for (int i = 0; i< virtualViewRmis.size(); i++){
            if(virtualViewRmis.get(i).getNickname().equals(player)){
                virtualViewRmis.get(i).createUsePowerUpCard(player,coordinates,idPlayer,defend,positionPowerUp);
            }
        }
    }

    @Override
    public void receiveWeaponCardChoice(String player, int indexWeaponCard, int[] payment) {
        for (int i = 0; i< virtualViewRmis.size(); i++){
            if(virtualViewRmis.get(i).getNickname().equals(player)){
                virtualViewRmis.get(i).createWeaponCardChoice(player, indexWeaponCard,payment);
            }
        }
    }


    @Override
    public void receiveUseWeaponCard(String player, int weaponCard, int[] effectUsed, int[][] handleEffectCoordinates, int[][] peopleToBeShoot) {
        for (int i = 0; i< virtualViewRmis.size(); i++){
            if(virtualViewRmis.get(i).getNickname().equals(player)){
                virtualViewRmis.get(i).createUseWeaponCard(player, weaponCard, effectUsed, handleEffectCoordinates, peopleToBeShoot);
            }
        }
    }




    @Override
    public void write(Object object) throws IOException, RemoteException {

    }


    /**
     * set register
     */
    @Override
    public void run() {
        ConfigLoader cl = new ConfigLoader();

        try {
            LocateRegistry.createRegistry(port);
        }
        catch (RemoteException e){
            e.printStackTrace();
        }

        try {

            //localhost

            //Naming.rebind("rmi://192.168.43.154:"+port+"/ServerRmi", this);
            Naming.rebind("rmi://"+ cl.getHostIp() + ":"+port+"/ServerRmi", this);
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
