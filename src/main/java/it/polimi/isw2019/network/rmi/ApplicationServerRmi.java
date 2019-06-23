package it.polimi.isw2019.network.rmi;

import it.polimi.isw2019.network.Client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ApplicationServerRmi {

    private static int port = 1234;
    private static Client client;
    private static ServerRmi server;
    private static int num=0;

    public static void main(String[] args) throws RemoteException {

        runServer ();


    }


    public static void runServer ()throws RemoteException {

        //creazione del server
        server = new ServerRmi();

        System.out.println("Register: ");

        try {
            LocateRegistry.createRegistry(port);
        }
        catch (RemoteException e){
            //e.printStackTrace();
        }

        try {
            Naming.rebind("rmi://localhost:"+port+"/ServerRmi", server);
        }
        catch (RemoteException e) {
            System.out.println("Error remote");
            //e.printStackTrace();
        }
        catch (MalformedURLException e) {
            System.out.println("Malfunction url");
            //e.printStackTrace();
        }

        System.out.println("server START");

    }
}
