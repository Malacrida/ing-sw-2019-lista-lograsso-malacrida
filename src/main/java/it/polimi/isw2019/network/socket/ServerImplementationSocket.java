package it.polimi.isw2019.network.socket;

import com.sun.prism.shader.FillRoundRect_Color_AlphaTest_Loader;
import it.polimi.isw2019.message.movemessage.MoveMessage;
import it.polimi.isw2019.message.movemessage.RunMessage;
import it.polimi.isw2019.message.playermove.FirstMessage;
import it.polimi.isw2019.message.playermove.PlayerMove;
import it.polimi.isw2019.message.playermove.RunMove;
import it.polimi.isw2019.model.Player;
import it.polimi.isw2019.network.network_interface.ServerInterface;
import it.polimi.isw2019.network.rmi.NetworkHandlerVisitorInterface;
import it.polimi.isw2019.network.rmi.VirtualViewVisitorInterface;
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
    private String nickname;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private MoveMessage moveMessage;
    private PlayerMove playerMove;
    private String message;
    private CLIView view;
    private NetworkHandlerSocket networkHandlerSocket;

    public ServerImplementationSocket(Socket socket, String nickname) throws IOException {
        this.socket = socket;
        this.nickname = nickname;
        System.out.println("SOCKET");
        output = new ObjectOutputStream(socket.getOutputStream());
        System.out.println("OUTPUT");

        input = new ObjectInputStream(socket.getInputStream());
        System.out.println("INPUT");

        networkHandlerSocket = new NetworkHandlerSocket(this);
        view = new CLIView(nickname);
        view.registerObserver(networkHandlerSocket);
        networkHandlerSocket.registerObserver(view);
        }


    @Override
    public void run(){
        try{

            while (null != (moveMessage = (MoveMessage) input.readObject())){
                    System.out.println("---SIS--- HO RICEVUTO LA MOVE MESSAGE: " + moveMessage);
                    networkHandlerSocket.receiveMoveMessage(moveMessage);
        }
        }catch (IOException e){
            e.getCause();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void registerNewClient(Socket client, String nickname) throws IOException {

        System.out.println("TI STAI REGISTRANDO COME: " + nickname);
            try {
                this.output.writeObject(nickname);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
            message = (String) this.input.readObject();
            System.out.println(message);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
            }
            this.start();
    }


    public void setPlayerMove(PlayerMove playerMove){
        this.playerMove = playerMove;
        try {
            write(playerMove);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public void receiveChooseActionMove(String player, int numAction) throws RemoteException {

    }

    @Override
    public void receiveChooseMap(String player, int index, int color, int mod, int terminator) throws RemoteException {

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
    public void receiveReload(String player, int[] weaponCard, int[][] payment) throws RemoteException {

    }

    @Override
    public void receivePowerUpChoice(String player, int idPowerUp) throws RemoteException {

    }

    @Override
    public void receiveUsePowerUpCard(String player/* InterfacePowerUpCard powerUpCardInterface*/) throws RemoteException {

    }

    @Override
    public void receiveWeaponCardChoice(String player, int indexWeaponCard, int[] payment) throws RemoteException {

    }

    @Override
    public void receiveUseWeaponCard(String player, int weaponCard) throws RemoteException {

    }

    @Override
    public void receiveConnectionMove(String player, int connection) throws RemoteException {

    }




}