package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;
import it.polimi.isw2019.network.rmi.NetworkHandlerVisitorInterface;
import it.polimi.isw2019.network.rmi.VirtualViewRmi;

import it.polimi.isw2019.network.socket.VirtualViewSocket;
import it.polimi.isw2019.view.CLIView;

import java.io.Serializable;

public class FirstMessage extends PlayerMove implements Serializable{

    private String actionHero;
    private CLIView CLIView;
    private VirtualViewRmi virtualViewRmi= null;
    private VirtualViewSocket virtualViewSocket= null;

    public FirstMessage(CLIView CLIView, String nickname, String actionHero){
        super(nickname);
        this.CLIView = CLIView;
        this.actionHero = actionHero;
    }

    public FirstMessage(VirtualViewRmi virtualViewRmi, String nickname, String actionHero){
        super(nickname);
        this.virtualViewRmi = virtualViewRmi;
        this.actionHero = actionHero;
        virtualViewSocket = null;
    }

    public FirstMessage(VirtualViewSocket virtualViewSocket, String nickname, String actionHero){
        super(nickname);
        virtualViewRmi = null;
        this.actionHero = actionHero;
        this.virtualViewSocket = virtualViewSocket;
    }


    @Override
    public void accept(VisitorController visitorController) {
        visitorController.visitControllerRegisterPlayer(this);
    }

    @Override
    public void accept(NetworkHandlerVisitorInterface networkHandler) {
        networkHandler.sendRegisterPlayer(this);
    }


    public String getActionHero() {
        return actionHero;
    }

    public CLIView getCLIView() {
        return CLIView;
    }

    public VirtualViewRmi getVirtualViewRmi() {
        return virtualViewRmi;
    }

    public VirtualViewSocket getVirtualViewSocket () {
        return virtualViewSocket;
    }
}
