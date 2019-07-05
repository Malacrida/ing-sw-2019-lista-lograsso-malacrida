package it.polimi.isw2019.network;

import it.polimi.isw2019.network.network_interface.ClientInterface;
import it.polimi.isw2019.network.network_interface.ServerInterface;

import it.polimi.isw2019.network.rmi.NetworkHandlerRmi;
import it.polimi.isw2019.network.socket.NetworkHandlerSocket;
import it.polimi.isw2019.network.socket.ServerImplementationSocket;
import it.polimi.isw2019.view.CLIView;

import javax.swing.text.View;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Client {

    private static ServerInterface serverRmi;

    public static void main(String[] args) throws UnknownHostException, RemoteException {

        InetAddress ip;
        if (args.length == 0) ip = InetAddress.getByName(null);
        else ip = InetAddress.getByName(args[0]);

        boolean chooseOk = false;
        Scanner input = new Scanner(System.in);

        String nickname = null;
        //String connectionType = null;

        while(!chooseOk){
            System.out.println("Insert your nickname: ");
            nickname = input.nextLine();

            chooseOk = true;
        }

            startView(nickname);
        }


        private static void startView (String nickname) throws RemoteException {
            ConfigLoader cl = new ConfigLoader();
            CLIView view = new CLIView(nickname);
            NetworkHandlerRmi networkHandler = new NetworkHandlerRmi(nickname);

            //192.168.43.154
            try {
                serverRmi = (ServerInterface<ClientInterface>) Naming.lookup("rmi://"+cl.getHostIp()+":"+cl.getRmiPort()+"/ServerRmi");
            } catch (RemoteException | NotBoundException | MalformedURLException e) {
                e.getCause();
            }

            try {
                ClientInterface remoteClient = (ClientInterface) UnicastRemoteObject.exportObject(networkHandler,0);
                networkHandler.setRemoteClient(remoteClient);
                serverRmi.registerNewClient(remoteClient, nickname);


            } catch (IOException e) {
                e.getCause();
            }
            System.out.println("Registration completed!");


            view.registerObserver(networkHandler);
            networkHandler.registerObserver(view);
            //view.startView();
    }

}
