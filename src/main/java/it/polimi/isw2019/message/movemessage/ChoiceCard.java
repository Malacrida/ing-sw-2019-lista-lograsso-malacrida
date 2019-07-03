package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.network.rmi.VirtualViewVisitorInterface;
import it.polimi.isw2019.view.VisitorView;

public class ChoiceCard extends MoveMessage {

    private String[] descriptionPowerUp;
    private int[] idPowerUp;
    private boolean discardOne;
    private boolean isPowerUp;

    public ChoiceCard(String nicknamePlayer) {
        super(nicknamePlayer);
    }

    public ChoiceCard(String nicknamePlayer, String[] descriptionPowerUp, int[] idPowerUp, String error, boolean discardOne, boolean isPowerUp) {
        super(nicknamePlayer,error);
        this.descriptionPowerUp = descriptionPowerUp;
        this.idPowerUp = idPowerUp;
        this.discardOne = discardOne;
        this.isPowerUp = isPowerUp;
    }

    public String[] getDescriptionPowerUp() {
        return descriptionPowerUp;
    }

    public void setDescriptionPowerUp(String[] descriptionPowerUp) {
        this.descriptionPowerUp = descriptionPowerUp;
    }

    public int[] getIdPowerUp() {
        return idPowerUp;
    }

    public void setIdPowerUp(int[] idPowerUp) {
        this.idPowerUp = idPowerUp;
    }

    public boolean isDiscardOne() {
        return discardOne;
    }

    public void setDiscardOne(boolean discardOne) {
        this.discardOne = discardOne;
    }

    public boolean isPowerUp() {
        return isPowerUp;
    }

    public void setPowerUp(boolean powerUp) {
        isPowerUp = powerUp;
    }

    @Override
    public void accept(VisitorView visitorview) {
        visitorview.visitCardChoice(this);
    }

    @Override
    public void accept(VirtualViewVisitorInterface virtualView) {
        System.out.println("Update della choice");
        virtualView.sendPowerUpChoice(this);
    }
}
