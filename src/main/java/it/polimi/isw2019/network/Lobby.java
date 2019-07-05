package it.polimi.isw2019.network;

import it.polimi.isw2019.controller.MainController;
import it.polimi.isw2019.network.network_interface.ClientInterface;
import it.polimi.isw2019.network.socket.ClientSocket;
import it.polimi.isw2019.network.socket.VirtualViewSocket;
import it.polimi.isw2019.network.rmi.VirtualViewRmi;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;



public class Lobby implements LobbyInterface {

    private static final Logger LOGGER = Logger.getLogger(Lobby.class.getName());

   // private HashMap<String, ClientInterface> clientConnected = new HashMap<>();
    //private HashMap<String, ClientInterface> clientDisconnected = new HashMap<>();

    private ArrayList<VirtualViewRmi> virtualViewRmis = new ArrayList<>();
    private ArrayList<VirtualViewSocket> virtualViewsSocket = new ArrayList<>();
    private ArrayList<String> nicknames = new ArrayList<>();
    private ArrayList<ConnetedClient> connectedClients = new ArrayList<>();
    private ConfigLoader configLoader = new ConfigLoader();
    private int countDown = configLoader.getTimerLobby();
    boolean lobbyIsRunning = true;

    public boolean addConnectedClient(String nickname, ClientInterface clientInterface, TypeConnection typeConnection){
        if(!alreadyExistNickname(nickname)){
            ConnetedClient connectedClient = new ConnetedClient(nickname, clientInterface, typeConnection, true);
            connectedClients.add(connectedClient);
            LOGGER.info(connectedClient.getNickname() + " is added in Lobby with " + connectedClient.getTypeConnection() + " connection.");
            return true;
        }

        else return false;
    }

    public boolean alreadyExistNickname(String nickname){
        for (int i = 0; i < connectedClients.size(); i++){
            if (connectedClients.get(i).getNickname().equals(nickname)){
                return true;
            }
        }
        return false;
    }


    public void analyzeConnectionClient(String nickname, int connection){
        if (alreadyExistNickname(nickname)){
            for (int i=0; i<connectedClients.size(); i++){
                if (connectedClients.get(i).getNickname().equals(nickname)){
                    if (connection==0){
                        connectedClients.get(i).setActive(false);
                        LOGGER.info(nickname + " is disconnected.");
                    }
                    if (connection==1){
                        connectedClients.get(i).setActive(true);
                        LOGGER.info(nickname + "is reconnected.");
                    }
                }
            }
        }
    }

    public void riconnectedClient (String nickname){
        if (alreadyExistNickname(nickname)){
            for (int i=0; i<connectedClients.size(); i++){

            }
        }
    }


    @Override
    public void run() {


        while (lobbyIsRunning) {
            boolean roomStartable = true;
            LOGGER.severe("---WAITING AT LEAST 3 CLIENTS.---");
            while (connectedClients.size() < 3) { //era 3
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.getCause();
                    Thread.currentThread().interrupt();
                }
            }
            LOGGER.severe("3 clients or more are waiting.");

            LOGGER.severe("---COUNTDOWN STARTS---");

           LOGGER.info("Countdown started with " + connectedClients.size() + " players!");


            try {
                for (int i = 0; i < countDown; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    if (connectedClients.size() < 3) {//era 3
                        i = countDown + 1;
                        roomStartable = false;
                        LOGGER.info("--ATTENTION-- Countdown has been reset, not enough players to start the room.");

                    } else if (connectedClients.size() == 5) { //era 5
                        i = countDown + 1;
                        roomStartable = true;
                    } else if ((countDown - i) % 5 == 0 && (countDown - i) != 0) {
                        LOGGER.info("Game will start in " + (countDown - i) + "s with " + connectedClients.size() + " players.\n");
                    } else if ((countDown - i) <= 10){
                        LOGGER.info("Game will start in " + (countDown - i) + "s with " + connectedClients.size() + " players.\n");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
            if(roomStartable) {
                System.out.println("---COUNTDOWN ENDED---");
                System.out.println("Starting the game.");
                startGame();
                return;

            } else {
                LOGGER.info("---ATTETION--- Countdown interrupted: not enough players.");
            }
        }

    }
    public void setVirtualViews() {
        for (int i=0; i<connectedClients.size(); i++ ){
            VirtualViewRmi virtualViewRmi = new VirtualViewRmi(connectedClients.get(i).getNickname(), connectedClients.get(i).getClientInterface());
            LOGGER.severe("New virtual view for: "+ connectedClients.get(i).getNickname());
            virtualViewRmis.add(virtualViewRmi);
/*
             if (connectedClients.get(i).getTypeConnection() == TypeConnection.SOCKET){
                VirtualViewSocket virtualViewSocket = new VirtualViewSocket(connectedClients.get(i).getNickname(), (ClientSocket) connectedClients.get(i).getClientInterface());
                virtualViewsSocket.add(virtualViewSocket);
            }*/
        }
    }

    public ArrayList<VirtualViewRmi> getVirtualViewRmis() {
        return virtualViewRmis;
    }

    public void startGame(){
        MainController controller = new MainController();

        setVirtualViews();
        Server.setVirtualViewOnServer(virtualViewRmis);

        for (VirtualViewRmi virtualViewRmi : virtualViewRmis){
            virtualViewRmi.registerObserver(controller);
        }
/*
        for (VirtualViewSocket aVirtualViewSocket: virtualViewsSocket){
            aVirtualViewSocket.registerObserver(controller);
            System.out.println(aVirtualViewSocket.getNickname());
        }*/

        for(VirtualViewRmi virtualViewRmi : virtualViewRmis) {
            virtualViewRmi.startView();
        }

/*
        for(VirtualViewSocket aVirtualViewSocket : virtualViewsSocket){
            try {
                aVirtualViewSocket.startView();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            System.out.println(aVirtualViewSocket.getNickname());
        }*/
        controller.startGame();
    }
}