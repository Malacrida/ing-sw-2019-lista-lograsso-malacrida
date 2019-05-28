package it.polimi.isw2019.network;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerRmi {

    //aprire connessione

   // ActionMove actionMove = new ActionMove();
    // ChooseActionMove chooseActionMove = new ChooseActionMove();


    public void start () throws RemoteException, AlreadyBoundException{
        try {
            Registry registry = LocateRegistry.getRegistry();
         //   registry.bind("action_move", actionMove);
        }
        catch (RemoteException e){
            throw new RemoteException(e.getMessage());
        }
        /*
        catch (AlreadyBoundException f){
            throw new AlreadyBoundException(f.getMessage());
        }*/
    }
}
