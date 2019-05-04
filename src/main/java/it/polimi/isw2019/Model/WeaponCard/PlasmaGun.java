package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.Exception.ErrorEffect;
import it.polimi.isw2019.Model.Player;

import java.util.ArrayList;

public class PlasmaGun extends AbstractWeaponCard{

    public PlasmaGun() {
        super(4, "Plasma Gun", ColorCube.BLUE, 3);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT: Deal 2 damage to 1 target you can see.\n");
        this.infoEffect.add("WITH WITH PHASE GLIDE: Move 1 or 2 squares. This effect can be used either before or after the basic effect.\n");
        this.infoEffect.add("WITH CHARGED SHOT: Deal 1 additional damage to 1 additional damage to your target. You have to pay a BLUE cube.\n");
        this.infoEffect.add("NOTES: The two moves have no ammo cost. You don't have" +
                " to be able to see your target when you play the card." +
                "For example, you can move 2 squares and shoot a target" +
                "you now see. You cannot use 1 move before shooting and " +
                "1 move after.");
    }

    @Override
    public void firstEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffect {
        /* AGGIUNGERE CONTROLLO CHE VEDE IL GIOCATORE */
        if (firstDefender != null){

            firstDefender.sufferDamage(attacker.getColor(), 2,0);

            firstIsValid = true;

        } else {

            throw new ErrorEffect();

        }
    }

    @Override
    public void secondEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) {
        /* AGGIUNGI MUOVI DI DUE */
    }

    @Override
    public void thirdEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffect {

        if(firstIsValid){

            firstDefender.sufferDamage(attacker.getColor(), 1,0);

        } else {

            throw new ErrorEffect();

        }
    }
}
