package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.network.rmi.VirtualViewVisitorInterface;
import it.polimi.isw2019.view.VisitorView;

import java.util.ArrayList;

public class ChoicePowerUpCard extends MoveMessage {

    private String[] descriptionPowerUp;
    private int[] idPowerUp;

    public ChoicePowerUpCard(String nicknamePlayer) {
        super(nicknamePlayer);
    }

    public ChoicePowerUpCard(String nicknamePlayer,String[] descriptionPowerUp, int[] idPowerUp) {
        super(nicknamePlayer);
        this.descriptionPowerUp = descriptionPowerUp;
        this.idPowerUp = idPowerUp;
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

    @Override
    public void accept(VisitorView visitorview) {
        visitorview.powerUpChoice(this);
    }

    @Override
    public void accept(VirtualViewVisitorInterface virtualView) {
        virtualView.sendPowerUpChoice(this);
    }
}
