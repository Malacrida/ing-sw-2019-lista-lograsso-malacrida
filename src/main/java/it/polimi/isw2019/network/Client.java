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
        String typeView;
        String connectionType = null;

        while(!chooseOk){
            System.out.println("\nInsert your nickname: \n");
            nickname = input.nextLine();

            chooseOk = true;
        }

        chooseOk = false;

        /*while(!chooseOk){
            System.out.println("\nInsert 0 to use Console, 1 to use GUI");
            typeView = input.nextLine();

            if (typeView.equals("0") || typeView.equals("1"))

            chooseOk = true;
        }*/

        while(!chooseOk){

            System.out.println("\nInsert 0 to use SOCKET, 1 to use RMI");
            connectionType = input.nextLine();

            if(connectionType.equals("0") || connectionType.equals("1"))
                chooseOk = true;
        }

        int typeServer = Integer.parseInt(connectionType);

        ServerInterface serverInterface = null;

        if (typeServer == 0){
            System.out.println("Starting SOCKET");

            try {

                Socket socket = new Socket("localhost", 1111);

                serverInterface = new ServerImplementationSocket(socket, nickname);
                //ok
                serverInterface.registerNewClient(socket, nickname);
                //ok

            } catch (IOException e) {
                e.getCause();
            }
        }
        if (typeServer==1){
            /*
            //192.168.43.154
            try {
                serverRmi = (ServerInterface<ClientInterface>) Naming.lookup("rmi://localhost:8080/ServerRmi");
            } catch (RemoteException | NotBoundException | MalformedURLException e) {
                e.getCause();
            }*/
            startView(nickname);
        }


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


        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("registrazione completata");


        view.registerObserver(networkHandler);
        networkHandler.registerObserver(view);
        //view.startView();
    }

}
