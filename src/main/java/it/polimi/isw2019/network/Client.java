package it.polimi.isw2019.network;

import it.polimi.isw2019.network.network_interface.ServerInterface;
import it.polimi.isw2019.network.socket.ServerImplementationSocket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Client {
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

        while(!chooseOk){
            System.out.println("\nInsert 0 to use Console, 1 to use GUI");
            typeView = input.nextLine();

            if (typeView.equals("0") || typeView.equals("1"))

            chooseOk = true;
        }

        chooseOk = false;
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
                serverInterface = new ServerImplementationSocket();
                Socket socket = new Socket("localhost", 1111);
                serverInterface.registerNewClient(socket, nickname);
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
