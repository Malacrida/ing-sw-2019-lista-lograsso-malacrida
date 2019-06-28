package it.polimi.isw2019.network;

import it.polimi.isw2019.network.network_interface.ClientInterface;
import it.polimi.isw2019.network.rmi.NetworkHandlerInterface;
import it.polimi.isw2019.network.rmi.VirtualView;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class Lobby implements LobbyInterface {

    private static final Logger LOGGER = Logger.getLogger(Lobby.class.getName());

    private HashMap<String, ClientInterface> clientConnected = new HashMap<>();

    private ArrayList<VirtualView> virtualViews = new ArrayList<>();
    private ArrayList<String> nicknames = new ArrayList<>();
    private int countDown = 60;

    public Lobby() {

    }

    public HashMap<String, ClientInterface> getClientConnected() {
        return clientConnected;
    }

    public boolean addClientConnected(String nickname, ClientInterface clientInterface) {
        if (!clientConnected.containsKey(nickname)) {
            clientConnected.put(nickname, clientInterface);
            nicknames.add(nickname);

            return true;
        } else return false;
    }

    boolean lobbyIsRunning = true;


    @Override
    public void run() {
        while (lobbyIsRunning) {
            boolean roomStartable = true;
            System.out.println("Waiting for at least 3 clients.");
            while (clientConnected.size() < 3) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Three clients or more are waiting.");
            System.out.println("Starting the countdown.");

            System.out.println("Countdown started with " + clientConnected.size() + " players!");


            try {
                for (int i = 0; i < countDown; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    if (clientConnected.size() < 2) {
                        i = countDown + 1;
                        roomStartable = false;
                        System.out.println("Countdown reset, not enough players to start the room.");

                    } else if (clientConnected.size() == 5) {
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
            }
            if(roomStartable) {
                System.out.println("Countdown ended, starting the room.");

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
}