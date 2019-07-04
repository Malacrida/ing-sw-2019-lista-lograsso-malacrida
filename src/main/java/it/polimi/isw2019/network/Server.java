package it.polimi.isw2019.network;

import it.polimi.isw2019.network.rmi.ServerRmi;
import it.polimi.isw2019.network.rmi.VirtualViewRmi;
import it.polimi.isw2019.network.socket.GathererSocket;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private ExecutorService executor;
    private static Lobby lobby = new Lobby();
    private ServerRmi serverRmi;
    private static GathererInterface gathererRmi= null;

    private static boolean isRunning = true;

    public static void main(String[] args){

        ConfigLoader cl = new ConfigLoader();
        GathererInterface gathererSocket= new GathererSocket(1111);

        System.setProperty("java.rmi.server.hostname", cl.getHostIp());

        try {
            gathererRmi = new ServerRmi(cl.getRmiPort());
        } catch (RemoteException e) {
            e.printStackTrace();
        }



        Server server = new Server();
        server.start(gathererSocket, gathererRmi);

    }

    private void start(GathererInterface... gatherers){
        this.executor = Executors.newFixedThreadPool(128);
        this.executor.submit(lobby);
        for(GathererInterface aGatherer : gatherers){
            aGatherer.setLobby(lobby);
            executor.submit(aGatherer);
        }

    }


    public static void setVirtualViewOnServer (ArrayList<VirtualViewRmi> virtualViewRmis){
        gathererRmi.setVirtualViewRmis(virtualViewRmis);
    }
}