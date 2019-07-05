package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;
import it.polimi.isw2019.network.rmi.NetworkHandlerVisitorInterface;

/**
 * player move of choice power up by player
 */

public class PowerUpChoice extends PlayerMove{

    private int idPowerUpDischarge;
    private int idPowerUpTake;
    private String messageToPrint;

    public PowerUpChoice(String player, int idPowerUp) {
        super(player);
        this.idPowerUpDischarge = idPowerUp;
    }

    /**
     * getter id of power up that player discharge if he has other 3
     * @return id of power up discharge
     */

    public int getIdPowerUpDischarge() {
        return idPowerUpDischarge;
    }

    /**
     * getter id of power up that player taken
     * @return id power up
     */

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

    @Override
    public void accept(NetworkHandlerVisitorInterface networkHandler) {
        networkHandler.sendPowerUpChoice(this);
    }

}
