package it.polimi.isw2019.network;

import it.polimi.isw2019.network.network_interface.ServerInterface;
import it.polimi.isw2019.network.rmi.NetworkHandler;
import it.polimi.isw2019.network.rmi.NetworkHandlerInterface;
import it.polimi.isw2019.network.rmi.ServerInterfaceRMI;
import it.polimi.isw2019.network.socket.ServerImplementationSocket;

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

    private static ServerInterfaceRMI serverRmi;

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
                Socket socket = new Socket("localhost", 1111);
                System.out.println("new Socket");
                serverInterface = new ServerImplementationSocket(socket);

                System.out.println("New serverImplementationSocket");
                serverInterface.registerNewClient(socket, nickname);

                System.out.println("Sto mandando un messaggio\n");
                String messageOutput = "Messaggio di prova";
                serverInterface.write(messageOutput);

                System.out.println("\nSono in attesa di un messaggio: \n");

                ObjectInputStream messageInput = new ObjectInputStream(socket.getInputStream());


                Object messageInput1 = (String) messageInput.readObject();
                System.out.println(messageInput1);


            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (typeServer==1){
            try {
                serverRmi = (ServerInterfaceRMI) Naming.lookup("rmi://localhost:1234/ServerRmi");
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            startViewProvv(nickname);
        }



    }

    private static void startViewProvv (String nickname){
        ViewProvvisioria viewProv = new ViewProvvisioria (nickname);
        NetworkHandler networkHandler = new NetworkHandler(nickname);


        try {
            NetworkHandlerInterface remoteClient = (NetworkHandlerInterface) UnicastRemoteObject.exportObject(networkHandler,0);
            serverRmi.addToTheServer(nickname, remoteClient);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        System.out.println("registrazione completata");

        viewProv.registerObserver(networkHandler);
        networkHandler.registerObserver(viewProv);

        viewProv.createChooseAction();

    }
}
