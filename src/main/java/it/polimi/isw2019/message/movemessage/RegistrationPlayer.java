package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.view.VisitorView;

import java.util.ArrayList;

public class RegistrationPlayer extends MoveMessage{
    private String nicknamePlayer;
    private String actionHero;
    private ArrayList<String> colors;

    public ArrayList<String> getColors() {
        return colors;
    }

    public String getActionHero() {
        return actionHero;
    }

    @Override
    public String getNicknamePlayer() {
        return nicknamePlayer;
    }

    public RegistrationPlayer(String nicknamePlayer, String actionHero, ArrayList<String> colors) {
        super(nicknamePlayer);
        this.actionHero = actionHero;
        this.colors = colors;
    }

    @Override
    public void accept(VisitorView visitorview) {
        visitorview.visitOkRegistration(this);
    }
}
