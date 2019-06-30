package it.polimi.isw2019.network;

import it.polimi.isw2019.network.rmi.ServerRmi;
import it.polimi.isw2019.network.rmi.VirtualView;
import it.polimi.isw2019.network.socket.GathererSocket;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
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

        GathererInterface gathererSocket= new GathererSocket(1111);


        try {
             gathererRmi = new ServerRmi(1235);
        } catch (RemoteException e) {
            e.printStackTrace();
        }



        Server server = new Server();
        server.start(gathererSocket, gathererRmi);

    }

    /*public void startServer(){
        while(isRunning){
            Conn c = servsock.accept();
            Thread t = new Thread(r -> register(c)).start();
        }
    }*/

    private void start(GathererInterface... gatherers){
        this.executor = Executors.newFixedThreadPool(128);
        this.executor.submit(lobby);
        for(GathererInterface aGatherer : gatherers){
            aGatherer.setLobby(lobby);
            executor.submit(aGatherer);
        }

    }


    public static void setVirtualViewOnServer (ArrayList<VirtualView> virtualViews){
        gathererRmi.setVirtualViews(virtualViews);
    }


    //inserire start
    //inserire main
    //inserire stop
    //inserire add client
    //inserire remove client

}
