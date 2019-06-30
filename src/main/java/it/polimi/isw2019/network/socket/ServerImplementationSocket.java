package it.polimi.isw2019.network.socket;

import com.sun.prism.shader.FillRoundRect_Color_AlphaTest_Loader;
import it.polimi.isw2019.message.movemessage.MoveMessage;
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
    private MoveMessage moveMessage;
    private String message;


    public ServerImplementationSocket(Socket socket) throws IOException {
        this.socket = socket;
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());


        }

       /* @Override
        public void run(){
            try{
                while (null != moveMessage = (MoveMessage) input.readObject())
            }
        }*/

   /* @Override
    public void registerNewClient(Socket client, String nickname, CLIView view) throws IOException {
        String actionHero = "MANNAIA LA PUTTANA";
        FirstMessage firstMessage = new FirstMessage(view, nickname, actionHero);
        write(firstMessage);
    }*/

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

    @Override
    public void registerNewClient(Socket client, String nickname) throws IOException, RemoteException {

    }


}