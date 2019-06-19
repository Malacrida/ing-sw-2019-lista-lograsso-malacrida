package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.model.powerupcard.InterfacePowerUpCard;
import it.polimi.isw2019.view.VisitorView;

public class UsePowerUpCardMessage extends MoveMessage {
    private InterfacePowerUpCard powerUpCard;

    public UsePowerUpCardMessage(String nicknamePlayer) {
        super(nicknamePlayer);
    }

    public InterfacePowerUpCard getPowerUpCard() {
        return powerUpCard;
    }

    public void setPowerUpCard(InterfacePowerUpCard powerUpCard) {
        this.powerUpCard = powerUpCard;
    }

    @Override
    public void accept(VisitorView visitorview) {

    }
}
