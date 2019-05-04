package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.Exception.ErrorEffect;
import it.polimi.isw2019.Model.Player;

import java.util.ArrayList;

public class CyberBlade extends AbstractWeaponCard {

    public CyberBlade(){
        super(16, "Cyber Blade", ColorCube.YELLOW, 3);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT : Deal 2 damage to 1 target on your square.\n");
        this.infoEffect.add("WITH SHADOWSTEP: move 1 square before or after the basic effect");
        this.infoEffect.add("WITH SLICE AND DICE : to a different target on your square the shadowstep may be used before or after this effect.");
        this.infoEffect.add("NOTE : Combining all effects allows you to move onto a square and\n" +
                "whack 2 people; or whack somebody, move, and whack somebody else;\n" +
                "or whack 2 people and then move.   ");
    }

    @Override
    public void firstEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffect {

        if (sameSquare(attacker, firstDefender)){

           firstDefender.sufferDamage(attacker.getColor(),2,0);

        }

        else {

            throw new ErrorEffect();

        }
    }

    @Override
    public void secondEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffect{

        if (firstDefender != null){
                System.out.println("DEVO INSERIRE QUALCOSA ALTRIMETNI MI DA ERRORE, È SOLO LO SCHELETRO DEL METODO");
                /*MUOVI DI UNO*/

        }
        else {
            throw new ErrorEffect();
        }
    }

    @Override
    public void thirdEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffect{

        if (sameSquare(attacker, secondDefender)){

            secondDefender.sufferDamage(attacker.getColor(),2,0);

        }

        else {
            throw new ErrorEffect();
        }
    }

}
