package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.model.powerupcard.InterfacePowerUpCard;
import it.polimi.isw2019.model.powerupcard.PowerUpCardInterface;
import it.polimi.isw2019.view.VisitorView;

import java.util.ArrayList;

public class UsePowerUpCardMessage extends MoveMessage {

    private ArrayList<InterfacePowerUpCard> powerUpCard;

    public UsePowerUpCardMessage(String nicknamePlayer) {
        super(nicknamePlayer);
        this.powerUpCard = powerUpCard;
    }

    public ArrayList<InterfacePowerUpCard> getPowerUpCard() {
        return powerUpCard;
    }

    public void setPowerUpCard(ArrayList<InterfacePowerUpCard> powerUpCard) {
        this.powerUpCard = powerUpCard;
    }

    @Override
    public void accept(VisitorView visitorview) {
            visitorview.usePowerUpCard(this);
    }
}
