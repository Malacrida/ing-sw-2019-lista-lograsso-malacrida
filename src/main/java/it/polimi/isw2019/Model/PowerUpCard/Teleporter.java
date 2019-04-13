package it.polimi.isw2019.Model.PowerUpCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.PowerUpCard.AbstractPowerUpCard;
import it.polimi.isw2019.Model.StateCard;

public class Teleporter extends AbstractPowerUpCard {

    private int id;
    private String name = "Teleporter";
    private ColorCube color;
    private String infoEffect = "You may play this card on your turn before\n" +
            "or after any action. Pick up your figure and\n" +
            "set it down on any square of the board. (You\n" +
            "can't use this after you see where someone\n" +
            "respawns at the end of your turn. By then it is\n" +
            "too late.)\n";
    private StateCard checkState;
}
