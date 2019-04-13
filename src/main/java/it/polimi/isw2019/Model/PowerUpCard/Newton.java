package it.polimi.isw2019.Model.PowerUpCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.StateCard;

public class Newton extends AbstractPowerUpCard {

    protected int id;
    protected String name = "Newton";
    protected ColorCube color;
    protected String infoEffect = " You may play this card on your turn before or\n" +
            "  after any action. Choose any other player's\n" +
            "  figure and move it 1 or 2 squares in one\n" +
            "  direction. (You can't use this to move a figure\n" +
            "  after it respawns at the end of your turn. That\n" +
            "  would be too late.)\n";
    protected StateCard checkState;
}