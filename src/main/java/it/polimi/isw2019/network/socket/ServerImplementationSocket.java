package it.polimi.isw2019.network.socket;

import it.polimi.isw2019.network.network_interface.ServerInterface;
import sun.plugin2.message.HeartbeatMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;

public class ServerImplementationSocket extends Thread implements ServerInterface<Socket> {

    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private String message;


    public ServerImplementationSocket(Socket socket) throws IOException {
        this.socket = socket;
        System.out.println("this.socket");

            output = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("output");
            input = new ObjectInputStream(socket.getInputStream());
            System.out.println("input");


        }


    @Override
    public Boolean registerNewClient(Socket client, String nickname) throws IOException {
        System.out.println("Si è registrato " + nickname);
        write(nickname);
        return true;
    }

    @Override
    public void write(Object object) throws IOException {
        this.output.writeObject(object);
        output.flush();
        output.reset();
    }

    @Override
    public Object read() throws IOException, ClassNotFoundException {
        return this.input.readObject();

    }

    @Override
    public void sendHeartBeat(HeartbeatMessage heartbeatMessage) throws RemoteException {
        //send(heartbeatMessage);
    }

    @Override
    public Boolean reconnectClient(Socket client, String nickname) throws RemoteException {
        return true;
    }


}