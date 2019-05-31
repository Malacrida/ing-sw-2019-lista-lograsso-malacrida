package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;

public class ColorChoosen extends PlayerMove {

    private String nicknamePlayer;
    private String colorChoosen;

    public ColorChoosen(String nicknamePlayer, String colorChoosen){
        this.nicknamePlayer = nicknamePlayer;
        this.colorChoosen = colorChoosen;
    }
    @Override
    public void accept(VisitorController visitorController) {
        visitorController.visitColorChoosen(this);
    }

    public String getNicknamePlayer() {
        return nicknamePlayer;
    }

    public String getColorChoosen() {
        return colorChoosen;
    }
}
