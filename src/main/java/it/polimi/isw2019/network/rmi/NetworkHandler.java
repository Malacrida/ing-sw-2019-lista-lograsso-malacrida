package it.polimi.isw2019.network.rmi;

import it.polimi.isw2019.message.movemessage.ActionMessage;
import it.polimi.isw2019.message.movemessage.MoveMessage;
import it.polimi.isw2019.message.playermove.ChooseActionMove;
import it.polimi.isw2019.message.playermove.PlayerMove;
import it.polimi.isw2019.network.ClientRmi;
import it.polimi.isw2019.utilities.Observable;
import it.polimi.isw2019.utilities.Observer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class NetworkHandler extends Observable<MoveMessage> implements Observer<PlayerMove>, NetworkHandlerInterface {

//forse va qui il riferimento al registro


    static ServerInterfaceRMI server;
    static int id;
    static ClientRmi client;
    static String nameNetworkHandler;
    static String insert;

    NetworkHandler (){
        try {
            server = (ServerInterfaceRMI) Naming.lookup("rmi://localhost:1234/ServerRmi");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }




        @Override
    public void update(PlayerMove message) {

    }

    public void sendChooseActionMove (String nickname, ChooseActionMove actionMove){
        server.receiveChooseActionMove(nickname,actionMove.getPlayer(), actionMove.getNumAction());
    }

    @Override
    public void createActionMessage(String nickname) {
        ActionMessage actionMessage= new ActionMessage(nickname);
        notifyObservers(actionMessage);
    }
}
