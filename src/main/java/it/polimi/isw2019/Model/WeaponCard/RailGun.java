package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.Player;
import it.polimi.isw2019.Model.Square;

import java.util.ArrayList;

public class RailGun extends AbstractWeaponCard {

    public RailGun(int id, String name, ColorCube color) {
        super(15, "Rail Gun", ColorCube.RED);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC MODE : Choose a cardinal direction and 1 target in that direction deal 3 damage to it");
        this.infoEffect.add("IN PIERCING MODE:Choose a cardinal direction and 1 or 2 targets in that direction deal 2 damage to each");
        this.infoEffect.add(" NOTE : Basically, you're shooting in a straight line and ignoring walls." +
                "You don't have to pick a target on the other side of a wall – it could even" +
                "be someone on your own square – but shooting through walls sure is" +
                "fun. There are only 4 cardinal directions. You imagine facing one wall or" +
                "door, square-on, and firing in that direction. Anyone on a square in that" +
                "direction (including yours) is a valid target. In piercing mode," +
                "the 2 targets can be on the same square or on different squares. ");
    }

    @Override
    public boolean firstEffect(Player attacker, Square firstAttackSquare, Player firstDefender, Square secondAttackSquare, Player secondDefender, Square thirdAttackSquare, Player thirdDefender) {
        /* controllo in una direzione */
        firstDefender.sufferDamage(attacker.getColor(), 3, 0);

        return false;
    }

    @Override
    public boolean secondEffect(Player attacker, Square firstAttackSquare, Player firstDefender, Square secondAttackSquare, Player secondDefender, Square thirdAttackSquare, Player thirdDefender) {

        firstDefender.sufferDamage(attacker.getColor(), 2, 0);
        secondDefender.sufferDamage(attacker.getColor(), 2, 0);
        return false;
    }

    @Override
    public boolean thirdEffect(Player attacker, Square firstAttackSquare, Player firstDefender, Square secondAttackSquare, Player secondDefender, Square thirdAttackSquare, Player thirdDefender) {
        return false;
    }


}
