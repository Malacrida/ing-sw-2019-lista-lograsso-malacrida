package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;
import it.polimi.isw2019.network.rmi.NetworkHandlerVisitorInterface;

public class PowerUpChoice extends PlayerMove{

    private int idPowerUp;
    private int cardChoosen;


    public PowerUpChoice(String player, int idPowerUp) {
        super(player);
        this.idPowerUp = idPowerUp;
    }

    public int getIdPowerUp() {
        return idPowerUp;
    }

    public int getCardChoosen() {
        return cardChoosen;
    }

    public void setCardChoosen(int cardChoosen) {
        this.cardChoosen = cardChoosen;
    }

    @Override
    public void accept(VisitorController visitorController) {
            visitorController.powerUpChoice(this);
    }

    @Override
    public void accept(NetworkHandlerVisitorInterface networkHandler) {

    }
}
