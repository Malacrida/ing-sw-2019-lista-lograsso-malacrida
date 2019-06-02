package it.polimi.isw2019.network;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ApplicationClientRmi {

    static ServerInterfaceRMI server;
    static int id;
    static ClientRmi client;
    static String name;
    static String insert;

    public static void main (String[] args) throws RemoteException, NotBoundException, MalformedURLException {



        server = (ServerInterfaceRMI) Naming.lookup("rmi://localhost:1234/ServerRmi");

        client = new ClientRmi();


        ClientInterfaceRmi remoteClient = (ClientInterfaceRmi) UnicastRemoteObject.exportObject(client,0);

        server.addToTheServer("nomeClient", remoteClient);


    }

}
