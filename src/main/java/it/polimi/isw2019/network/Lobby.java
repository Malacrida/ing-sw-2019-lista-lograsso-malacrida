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
    private int countDown = 10;
    boolean lobbyIsRunning = true;

    public Lobby() {

    }


    public boolean addConnectedClient(String nickname, ClientInterface clientInterface, TypeConnection typeConnection){
        if(!alreadyExistNickname(nickname)){
            ConnetedClient connectedClient = new ConnetedClient(nickname, clientInterface, typeConnection, true);
            connectedClients.add(connectedClient);
            System.out.println("dim client: "+connectedClients.size());
            System.out.println(connectedClient.getNickname()+"tipo con :" +connectedClient.getTypeConnection());
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
                        System.out.println("ho disconnesso :" + nickname);
                    }
                    if (connection==1){
                        connectedClients.get(i).setActive(true);
                        System.out.println("ho riconesso :" + nickname);
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
            System.out.println("Waiting for at least 3 clients.");
            while (connectedClients.size() < 3) { //era 3
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("Three clients or more are waiting.");
            System.out.println("Starting the countdown.");

            System.out.println("Countdown started with " + connectedClients.size() + " players!");


            try {
                for (int i = 0; i < countDown; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    if (connectedClients.size() < 3) {//era 3
                        i = countDown + 1;
                        roomStartable = false;
                        System.out.println("Countdown reset, not enough players to start the room.");

                    } else if (connectedClients.size() == 5) { //era 5
                        i = countDown + 1;
                        roomStartable = true;
                    } else if ((countDown - i) % 5 == 0 && (countDown - i) != 0) {
                        System.out.println("Room will start in " + (countDown - i) + "s with " + connectedClients.size() + " players.");
                        /*currentClientsWaiting.iterator().forEachRemaining(wc -> {
                            try {
                                wc.getClientInterface().update(countdownReset);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        });*/
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
            if(roomStartable) {
                System.out.println("Countdown ended, starting the room.");
                startGame();
                return;
                /*ConnectedClient c;
                Set<ConnectedClient> clients = new HashSet<>();
                int i = 0;
                while ((c = currentClientsWaiting.poll()) != null && i < 4) {
                    if(!c.isInactive()) {
                        i++;
                        clients.add(c);
                    }
                }

                startNewRoom(clients);
                LOGGER.info("Room started with " + clients.size() + " clients.");*/
            } else {
                System.out.println("Countdown interrupted: not enough players.");
            }
        }

    }
    public void setVirtualViews() {
        for (int i=0; i<connectedClients.size(); i++ ){
            VirtualViewRmi virtualViewRmi = new VirtualViewRmi(connectedClients.get(i).getNickname(), connectedClients.get(i).getClientInterface());
            System.out.println("creo Virtual View di "+ connectedClients.get(i).getNickname());
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