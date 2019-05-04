package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.Player;
import it.polimi.isw2019.Model.Square;

import java.util.ArrayList;

public class Electroscythe extends AbstractWeaponCard {

    public Electroscythe(int id, String name, ColorCube color) {
        super(6, "Electroscythe", ColorCube.BLUE);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT: Deal 1 damage and to every other player on your square.\n");
        this.infoEffect.add("IN REAPER MODE: Deal 2 damage to every other player on your square. You have to pay a BLUE cube and a RED cube.\n");
    }

    @Override
    public boolean firstEffect(Player attacker, Square firstAttackSquare, Player firstDefender, Square secondAttackSquare, Player secondDefender, Square thirdAttackSquare, Player thirdDefender) {

        ArrayList<Player> playersList = firstAttackSquare.getPlayers();

        /* Se l'attaccante è nella stanza selezionata allora passa tutti gli elementi dell'array e fai un danno*/

        if (firstAttackSquare.findPlayer(attacker)){

            for (int i = 0; i < playersList.size(); i++){

                playersList.get(i).sufferDamage(attacker.getColor(), 1, 0);

            }

            return true;
        }

        else return false;
    }

    @Override
    public boolean secondEffect(Player attacker, Square firstAttackSquare, Player firstDefender, Square secondAttackSquare, Player secondDefender, Square thirdAttackSquare, Player thirdDefender) {
        /*AGGIUNGERE PAGARE UN CUBO ROSSO E UNO BLU*/

        ArrayList<Player> playersList = firstAttackSquare.getPlayers();

        /* Se l'attaccante è nella stanza selezionata allora passa tutti gli elementi dell'array e fai un danno*/

        if (firstAttackSquare.findPlayer(attacker)){

            for (int i = 0; i < playersList.size(); i++){

                playersList.get(i).sufferDamage(attacker.getColor(), 2, 0);

            }

            return true;
        }

        else return false;
    }

    @Override
    public boolean thirdEffect(Player attacker, Square firstAttackSquare, Player firstDefender, Square secondAttackSquare, Player secondDefender, Square thirdAttackSquare, Player thirdDefender) {
        /*NON ESISTE IL QUESTO EFFETTO*/

        return false;
    }


}
