package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.model.powerupcard.InterfacePowerUpCard;
import it.polimi.isw2019.model.powerupcard.PowerUpCardInterface;
import it.polimi.isw2019.view.VisitorView;

import java.util.ArrayList;

public class UsePowerUpCardMessage extends MoveMessage {

    private ArrayList<InterfacePowerUpCard> powerUpCard;

    private int[] featuresAvailable;

    public UsePowerUpCardMessage(String nicknamePlayer) {
        super(nicknamePlayer);
    }

    public UsePowerUpCardMessage(String nicknamePlayer, int[] featuresAvailable) {
        super(nicknamePlayer);
        this.featuresAvailable = featuresAvailable;
    }
    public ArrayList<InterfacePowerUpCard> getPowerUpCard() {
        return powerUpCard;
    }

    public void setPowerUpCard(ArrayList<InterfacePowerUpCard> powerUpCard) {
        this.powerUpCard = powerUpCard;
    }

    public int[] getFeaturesAvailable() {
        return featuresAvailable;
    }

    public void setFeaturesAvailable(int[] featuresAvailable) {
        this.featuresAvailable = featuresAvailable;
    }

    @Override
    public void accept(VisitorView visitorview) {
            visitorview.usePowerUpCard(this);
    }
}
