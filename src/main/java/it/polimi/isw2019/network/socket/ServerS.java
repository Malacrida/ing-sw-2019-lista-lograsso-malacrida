package it.polimi.isw2019.network.socket;

import it.polimi.isw2019.message.playermove.PlayerMove;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerS {

    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    Socket socket;
    ServerSocket serverSocket;
    VirtualViewSocket virtualViewSocket;
    MiniController miniController;

    ServerS (){
        virtualViewSocket = new VirtualViewSocket(null);
        miniController = new MiniController();
        virtualViewSocket.registerObserver(miniController);

        runServer();
    }

    public void runServer(){

        try {

            socket = new Socket();
            serverSocket = new ServerSocket(1112);
            socket= serverSocket.accept();
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            PlayerMove playerMove= (PlayerMove) objectInputStream.readObject();
            virtualViewSocket.receivePlayerMove(playerMove);

        } catch (IOException |ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public static void main (String[] args){
        ServerS s = new ServerS();
    }
}
