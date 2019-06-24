package it.polimi.isw2019.network.rmi;

import it.polimi.isw2019.network.ClientRmi;
import it.polimi.isw2019.network.network_interface.ClientInterface;

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

        server = (ServerInterfaceRMI) Naming.lookup("rmi://localhost:1234/registryServer");

        client = new ClientRmi();


        ClientInterface remoteClient = (ClientInterface) UnicastRemoteObject.exportObject(client,0);

        //server.addToTheServer("nomeClient", remoteClient);


    }

}
