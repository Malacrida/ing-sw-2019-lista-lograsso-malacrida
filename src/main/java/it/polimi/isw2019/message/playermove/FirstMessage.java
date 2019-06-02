package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;
import it.polimi.isw2019.view.MainView;

public class FirstMessage extends PlayerMove{
    private String nickname;
    private String actionHero;
    private MainView mainView;

    public FirstMessage(MainView mainView,String nickname, String actionHero){
        this.mainView = mainView;
        this.nickname= nickname;
        this.actionHero = actionHero;
    }

    @Override
    public void accept(VisitorController visitorController) {
        visitorController.visitControllerRegisterPlayer(this);
    }

    public String getNickname() {
        return nickname;
    }

    public String getActionHero() {
        return actionHero;
    }

    public MainView getMainView() {
        return mainView;
    }
}
