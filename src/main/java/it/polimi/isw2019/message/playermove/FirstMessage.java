package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;
import it.polimi.isw2019.network.rmi.NetworkHandlerVisitorInterface;
import it.polimi.isw2019.network.rmi.VirtualViewVisitorInterface;
import it.polimi.isw2019.view.CLIView;

public class FirstMessage extends PlayerMove{
    private String actionHero;
    private CLIView CLIView;
    private VirtualViewVisitorInterface virtualView;

    public FirstMessage(CLIView CLIView, String nickname, String actionHero){
        super(nickname);
        this.CLIView = CLIView;
        this.actionHero = actionHero;
    }

    public FirstMessage(VirtualViewVisitorInterface virtualView, String nickname, String actionHero){
        super(nickname);
        this.virtualView=virtualView;
        this.actionHero = actionHero;
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
}
