package it.polimi.isw2019.Model.PowerUpCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.StateCard;

public class TargetingScope extends AbstractPowerUpCard{

    public TargetingScope(){
        this.name = "Targeting Scope";
        this.infoEffect = "You may play this card when you are dealing\n" +
                " damage to one or more targets. Pay 1 ammo\n" +
                " cube of any color. Choose 1 of those targets\n" +
                " and give it an extra point of damage. Note: You\n" +
                " cannot use this to do 1 damage to a target that\n" +
                " is receiving only marks.\n";
    }
}
