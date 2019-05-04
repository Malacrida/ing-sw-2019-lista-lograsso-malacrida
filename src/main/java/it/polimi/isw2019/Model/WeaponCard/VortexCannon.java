package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.Exception.ErrorEffect;
import it.polimi.isw2019.Model.Exception.NoEffectException;
import it.polimi.isw2019.Model.Player;

import java.util.ArrayList;

public class VortexCannon extends AbstractWeaponCard {

    public VortexCannon() {
        super(8, "Vortex Cannon", ColorCube.RED, 1);
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
    public void firstEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffect {

        if(firstDefender != null){
            /*MUOVI DI UNO*/
            firstDefender.sufferDamage(attacker.getColor(), 2, 0);

        } else {

            throw new ErrorEffect();
        }


    }

    @Override
    public void secondEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffect {

        if(secondDefender != null){

            /*MUOVI DI UNO IL SECONDO E IL TERZO GIOCATORE*/
            secondDefender.sufferDamage(attacker.getColor(), 1, 0);
            thirdDefender.sufferDamage(attacker.getColor(), 1, 0);

        } else {

            throw new ErrorEffect();
        }
    }

    @Override
    public void thirdEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws  NoEffectException {
        throw new NoEffectException();

    }

}
