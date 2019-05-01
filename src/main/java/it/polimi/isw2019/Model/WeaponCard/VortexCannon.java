package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.Player;
import it.polimi.isw2019.Model.Square;

import java.util.ArrayList;

public class VortexCannon extends AbstractWeaponCard {

    public VortexCannon(int id, String name, ColorCube color) {
        super(8, "Vortex Cannon", ColorCube.RED);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT: basic effect: Choose a square you can see, but not your" +
                "square. Call it the vortex. Choose a target on the vortex" +
                "or 1 move away from it. Move it onto the vortex and give it" +
                "2 damage.");
        this.infoEffect.add("WITH BLACK HOLE :Choose up to 2 other targets on the" +
                "vortex or 1 move away from it. Move them onto the vortex" +
                "and give them each 1 damage.");
        this.infoEffect.add("NOTE :The 3 targets must be different, but some might" +
                "start on the same square. It is legal to choose targets on" +
                "your square, on the vortex, or even on squares you can't" +
                "see. They all end up on the vortex. ");
    }

    @Override
    public boolean firstEffect(Player attacker, Square firstAttackSquare, Player firstDefender, Square secondAttackSquare, Player secondDefender, Square thirdAttackSquare, Player thirdDefender) {

        /*MUOVI DI UNO*/

        firstDefender.sufferDamage(attacker.getColor(), 2, 0);

        return false;
    }

    @Override
    public boolean secondEffect(Player attacker, Square firstAttackSquare, Player firstDefender, Square secondAttackSquare, Player secondDefender, Square thirdAttackSquare, Player thirdDefender) {

        /*MUOVI DI UNO IL SECONDO E IL TERZO GIOCATORE*/

        secondDefender.sufferDamage(attacker.getColor(), 1, 0);

        thirdDefender.sufferDamage(attacker.getColor(), 1, 0);

        return false;
    }

    @Override
    public boolean thirdEffect(Player attacker, Square firstAttackSquare, Player firstDefender, Square secondAttackSquare, Player secondDefender, Square thirdAttackSquare, Player thirdDefender) {
        return false;
    }

}
