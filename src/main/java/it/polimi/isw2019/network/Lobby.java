package it.polimi.isw2019.network;

import it.polimi.isw2019.controller.MainController;
import it.polimi.isw2019.network.network_interface.ClientInterface;
import it.polimi.isw2019.network.rmi.VirtualView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;



public class Lobby implements LobbyInterface {

    private static final Logger LOGGER = Logger.getLogger(Lobby.class.getName());

    private HashMap<String, ClientInterface> clientConnected = new HashMap<>();
    private HashMap<String, ClientInterface> clientDisconnected = new HashMap<>();

    private ArrayList<VirtualView> virtualViews = new ArrayList<>();
    private ArrayList<String> nicknames = new ArrayList<>();
    private ArrayList<ConnetedClient> connetedClients = new ArrayList<>();
    private int countDown = 60;
    boolean lobbyIsRunning = true;

    public Lobby() {

    }

    public HashMap<String, ClientInterface> getClientConnected() {
        return clientConnected;
    }

    public boolean addConnectedClient(String nickname, ClientInterface clientInterface, TypeConnection typeConnection){
        if(!alreadyExistNickname(nickname)){
            ConnetedClient connetedClient = new ConnetedClient(nickname, clientInterface, typeConnection, true);
            return true;
        }

        else return false;
    }

    public boolean alreadyExistNickname(String nickname){
        for (int i = 0; i < connetedClients.size(); i++){
            if (connetedClients.get(i).getNickname().equals(nickname)){
                return true;
            }
        }
        return false;
    }

    public boolean addClientConnected(String nickname, ClientInterface clientInterface) {
        if (!clientConnected.containsKey(nickname)) {
            clientConnected.put(nickname, clientInterface);
            nicknames.add(nickname);

            return true;
        } else return false;
    }


    public void disconnectedClient(String nickname){
        if (clientConnected.containsKey(nickname)) {
            clientDisconnected.put(nickname, clientConnected.get(nickname));
            clientConnected.remove(nickname);
        }
    }


    @Override
    public void run() {
        while (lobbyIsRunning) {
            boolean roomStartable = true;
            System.out.println("Waiting for at least 3 clients.");
            while (clientConnected.size() < 1) { //era 3
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("Three clients or more are waiting.");
            System.out.println("Starting the countdown.");

            System.out.println("Countdown started with " + clientConnected.size() + " players!");


            try {
                for (int i = 0; i < countDown; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    if (clientConnected.size() < 1) {//era 2
                        i = countDown + 1;
                        roomStartable = false;
                        System.out.println("Countdown reset, not enough players to start the room.");

                    } else if (clientConnected.size() == 2) { //era 5
                        i = countDown + 1;
                        roomStartable = true;
                    } else if ((countDown - i) % 5 == 0 && (countDown - i) != 0) {
                        System.out.println("Room will start in " + (countDown - i) + "s with " + clientConnected.size() + " players.");
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
        for (int i=0; i<nicknames.size(); i++ ){
            VirtualView virtualView = new VirtualView(nicknames.get(i), clientConnected.get(nicknames.get(i)));
            virtualViews.add(virtualView);
        }
    }

    public ArrayList<VirtualView> getVirtualViews() {
        return virtualViews;
    }

    public void startGame(){
        MainController controller = new MainController();

        setVirtualViews();
        Server.setVirtualViewOnServer(virtualViews);

        System.out.println("ci sono :" +virtualViews.size());
        for (VirtualView virtualView: virtualViews){
            virtualView.registerObserver(controller);
            System.out.println(virtualView.getNickname());
        }

        for(VirtualView virtualView : virtualViews){
            virtualView.startView();
            System.out.println(virtualView.getNickname());
        }
        controller.startGame();
    }
}