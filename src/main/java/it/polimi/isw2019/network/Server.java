package it.polimi.isw2019.network;

import it.polimi.isw2019.network.socket.GathererSocket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private ExecutorService executor;

    private static boolean isRunning = true;

    public static void main(String[] args){
        GathererInterface gathererSocket = new GathererSocket(1111);

        Server server = new Server();
        server.start(gathererSocket);

    }

    /*public void startServer(){
        while(isRunning){
            Conn c = servsock.accept();
            Thread t = new Thread(r -> register(c)).start();
        }
    }*/

    private void start(GathererInterface... gathererSocket){
        this.executor = Executors.newFixedThreadPool(128);

        for(GathererInterface aGathererSocket : gathererSocket){
            executor.submit(aGathererSocket);
        }
    }

    //inserire start
    //inserire main
    //inserire stop
    //inserire add client
    //inserire remove client

}
