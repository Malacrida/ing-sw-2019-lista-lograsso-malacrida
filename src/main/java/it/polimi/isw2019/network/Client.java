package it.polimi.isw2019.network;

import it.polimi.isw2019.network.network_interface.ClientInterface;
import it.polimi.isw2019.network.network_interface.ServerInterface;

import it.polimi.isw2019.network.rmi.NetworkHandlerInterface;
import it.polimi.isw2019.network.rmi.NetworkHandlerRmi;
import it.polimi.isw2019.network.rmi.ServerInterfaceRMI;
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

    public static void main(String[] args) throws UnknownHostException {

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
                CLIView cliView = new CLIView(nickname);

                Socket socket = new Socket("localhost", 1111);
                System.out.println("new Socket");
                serverInterface = new ServerImplementationSocket(socket);

                System.out.println("New serverImplementationSocket");
                serverInterface.registerNewClient(socket, nickname, cliView);

                System.out.println("Sto mandando un messaggio\n");
                String messageOutput = "Messaggio di prova";
                serverInterface.write(messageOutput);

                System.out.println("\nSono in attesa di un messaggio: \n");

                ObjectInputStream messageInput = new ObjectInputStream(socket.getInputStream());


                Object messageInput1 = (String) messageInput.readObject();
                System.out.println(messageInput1);


            } catch (IOException | ClassNotFoundException e) {
                e.getCause();
            }
        }
        if (typeServer==1){
            try {
                serverRmi = (ServerInterface<ClientInterface>) Naming.lookup("rmi://localhost:1234/ServerRmi");
            } catch (RemoteException | NotBoundException | MalformedURLException e) {
                e.getCause();
            }
            startView(nickname);
        }


    }


    private static void startView (String nickname){
        CLIView view = new CLIView(nickname);
        NetworkHandlerRmi networkHandler = new NetworkHandlerRmi(nickname);


        try {
            ClientInterface remoteClient = (ClientInterface) UnicastRemoteObject.exportObject(networkHandler,0);
            serverRmi.registerNewClient(remoteClient, nickname, view);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("registrazione completata");


        view.registerObserver(networkHandler);
        networkHandler.registerObserver(view);

      //  view.startView();
    }

}
