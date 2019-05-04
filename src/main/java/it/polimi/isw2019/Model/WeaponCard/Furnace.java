package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.Player;
import it.polimi.isw2019.Model.Square;

import java.util.ArrayList;

public class Furnace extends AbstractWeaponCard {

    public Furnace(/*int id, String name, ColorCube color*/) {
        super(9, "Furnace", ColorCube.RED);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT: Choose a room you can see, but not the room\n" +
                "you are in. Deal 1 damage to everyone in that room.\n");
        this.infoEffect.add("IN COZY FIRE MODE: Choose a square exactly one move\n" +
                "away. Deal 1 damage and 1 mark to everyone on that\n" +
                "square.\n");
    }

    @Override
    public boolean firstEffect(Player attacker, Square firstAttackSquare, Player firstDefender, Square secondAttackSquare, Player secondDefender, Square thirdAttackSquare, Player thirdDefender) {
        /*AGGIUNGERE CONTROLLO SE LA STANZA È VISTA DALL'ATTACCANTE*/

        ArrayList<Player> playerList = firstAttackSquare.getPlayers();

        for (int i = 0; i < playerList.size(); i++){

            playerList.get(i).sufferDamage(attacker.getColor(), 1, 0);

        }
        return true;
    }

    @Override
    public boolean secondEffect(Player attacker, Square firstAttackSquare, Player firstDefender, Square secondAttackSquare, Player secondDefender, Square thirdAttackSquare, Player thirdDefender) {

        /*AGGIUNGERE CONTROLLO SE LA STANZA È ADIACENTE*/

        ArrayList<Player> playerList = firstAttackSquare.getPlayers();

        for (int i = 0; i < playerList.size(); i++){

            playerList.get(i).sufferDamage(attacker.getColor(), 1, 1);

        }
        return true;
    }

    @Override
    public boolean thirdEffect(Player attacker, Square firstAttackSquare, Player firstDefender, Square secondAttackSquare, Player secondDefender, Square thirdAttackSquare, Player thirdDefender) {
        return true;
    }


}
