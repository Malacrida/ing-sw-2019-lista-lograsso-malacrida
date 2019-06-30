package it.polimi.isw2019.network.socket;

import it.polimi.isw2019.message.playermove.FirstMessage;
import it.polimi.isw2019.network.network_interface.ServerInterface;
import it.polimi.isw2019.view.CLIView;
//import sun.plugin2.message.HeartbeatMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.ArrayList;

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
    public void registerNewClient(Socket client, String nickname, CLIView view) throws IOException {
        FirstMessage firstMessage = new FirstMessage(view, nickname, "MANNAIA LA PUTTANA");

        write(firstMessage);

    }

    @Override
    public void write(Object object) throws IOException {
        this.output.writeObject(object);
        output.flush();
        output.reset();
    }

   /* @Override
    public void sendHeartBeat(HeartbeatMessage heartbeatMessage) throws RemoteException {
        //send(heartbeatMessage);
    }*/

    @Override
    public void reconnectClient(Socket client, String nickname) throws RemoteException {

    }

    @Override
    public void removeToTheClient(String name) throws RemoteException {

    }

    @Override
    public void receiveChooseActionMove(String player, int numAction) throws RemoteException {

    }

    @Override
    public void receiveChooseMap(String player, int index, int color) throws RemoteException {

    }

    @Override
    public void receiveRun(String player, int[][] movement) throws RemoteException {

    }

    @Override
    public void receiveGrab(String player) throws RemoteException {

    }

    @Override
    public void receiveRegisterPlayer(String player, String actionHero) throws RemoteException {

    }

    @Override
    public void receiveReload(String player) throws RemoteException {

    }

    @Override
    public void receivePowerUpChoice(String player, int idPowerUp) throws RemoteException {

    }

    @Override
    public void receiveUsePowerUpCard(String player/* InterfacePowerUpCard powerUpCardInterface*/) throws RemoteException {

    }

    @Override
    public void receiveWeaponCardChoice(String player, int indexWeaponCard, String[] payment,/* ArrayList<InterfacePowerUpCard> powerUpCards,*/ boolean grab) throws RemoteException {

    }

    @Override
    public void receiveUseWeaponCard(String player, int weaponCard) throws RemoteException {

    }



}