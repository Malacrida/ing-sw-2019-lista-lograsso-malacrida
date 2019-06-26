package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;

public class PowerUpChoice extends PlayerMove{

    private int idPowerUpDischarge;
    private int idPowerUpTake;
    private String messageToPrint;

    public PowerUpChoice(String player, int idPowerUp) {
        super(player);
        this.idPowerUpDischarge = idPowerUp;
    }

    public int getIdPowerUpDischarge() {
        return idPowerUpDischarge;
    }

    public int getIdPowerUpTake() {
        return idPowerUpTake;
    }

    public void setIdPowerUpTake(int idPowerUpTake) {
        this.idPowerUpTake = idPowerUpTake;
    }

    public String getMessageToPrint() {
        return messageToPrint;
    }

    public void setMessageToPrint(String messageToPrint) {
        this.messageToPrint = messageToPrint;
    }

    @Override
    public void accept(VisitorController visitorController) {
            visitorController.powerUpChoice(this);
    }
}
