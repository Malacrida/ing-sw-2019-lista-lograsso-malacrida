package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;

public class ColorChoosen extends PlayerMove {

    private String colorChoosen;

    public ColorChoosen(String nicknamePlayer, String colorChoosen){
        super(nicknamePlayer);
        this.colorChoosen = colorChoosen;
    }

    @Override
    public void accept(VisitorController visitorController) {
        visitorController.visitColorChoosen(this);
    }

    public String getColorChoosen() {
        return colorChoosen;
    }
}
