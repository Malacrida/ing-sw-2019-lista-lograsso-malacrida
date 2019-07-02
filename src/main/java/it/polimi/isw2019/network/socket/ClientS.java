package it.polimi.isw2019.network.socket;

import it.polimi.isw2019.message.playermove.FirstMessage;
import it.polimi.isw2019.message.playermove.RunMove;
import it.polimi.isw2019.network.Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientS {

    /*Socket socket;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;
    int [][] run = new int[3][2];

    ClientS (){
        run [0][0]=1;
        run [0][1]=2;
        run [1][0]=3;
        run [1][1]=4;
        run [2][0]=5;
        run [2][1]=6;

        run();
    }

    private void run (){
        try {
            socket = new Socket("localhost", 1112);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            RunMove runMove = new RunMove("nick", run);
            objectOutputStream.writeObject(runMove);
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main (String[] args){
        ClientS clientS= new ClientS();
    }*/
}
