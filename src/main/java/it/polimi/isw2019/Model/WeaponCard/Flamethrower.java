package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.Player;
import it.polimi.isw2019.Model.Square;

import java.util.ArrayList;

public class Flamethrower extends AbstractWeaponCard {


    public Flamethrower(int id, String name, ColorCube color) {
        super(12, "Flamethrower", ColorCube.RED);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC MODE: Choose a square 1 move away and possibly a second square\n" +
                "1 more move away in the same direction. On each square, you may\n" +
                "choose 1 target and give it 1 damage.\n");
        this.infoEffect.add("IN BARBECUE MODE: Choose 2 squares as above. Deal 2 damage to\n" +
                "everyone on the first square and 1 damage to everyone on the second\n" +
                "square.\n");
        this.infoEffect.add("NOTES: This weapon cannot damage anyone in your square. However,\n" +
                "it can sometimes damage a target you can't see – the flame won't go\n" +
                "through walls, but it will go through doors. Think of it as a straight-line\n" +
                "blast of flame that can travel 2 squares in a cardinal direction.\n");
    }

    @Override
    public boolean firstEffect(Player attacker, Square firstAttackSquare, Player firstDefender, Square secondAttackSquare, Player secondDefender, Square thirdAttackSquare, Player thirdDefender) {

        /*AGGIUNGERE CONTROLLO SE PLAYER VISIBILE*/

        if ((!firstAttackSquare.findPlayer(firstDefender)) || (!secondAttackSquare.findPlayer(secondDefender))){
            return false;
        }

        /* Se dista uno sull'asse delle X e ha uguale la Y*/

        if (oneDistanceX(attacker, firstDefender)){

            firstDefender.sufferDamage(attacker.getColor(), 1, 0);

            if ((secondDefender != null) && (oneDistanceX(firstDefender, secondDefender))){
                secondDefender.sufferDamage(attacker.getColor(), 1, 0);
            }

            return true;

        }

        else if (oneDistanceY(attacker, firstDefender)){

            firstDefender.sufferDamage(attacker.getColor(), 1, 0);

            if ((secondDefender != null) && (oneDistanceY(firstDefender, secondDefender))){
                secondDefender.sufferDamage(attacker.getColor(), 1, 0);
            }

            return true;

        }
        return false;
    }

    @Override
    public boolean secondEffect(Player attacker, Square firstAttackSquare, Player firstDefender, Square secondAttackSquare, Player secondDefender, Square thirdAttackSquare, Player thirdDefender) {

        /*AGGIUNGERE CONTROLLO SE LA STANZA È ADIACENTE*/

        ArrayList<Player> playerList = firstAttackSquare.getPlayers();

        for (int i = 0; i < playerList.size(); i++){

            playerList.get(i).sufferDamage(attacker.getColor(), 2, 0);

        }

        /* AGGIUNGERE CONTROLLO SE LA STANZA È ADIACENTE A firstAttackSquare E DIVERSA DALLA STANZA DI attacker*/

        playerList = secondAttackSquare.getPlayers();

        for (int i = 0; i < playerList.size(); i++){

            playerList.get(i).sufferDamage(attacker.getColor(), 1, 0);

        }
        return true;

    }

    @Override
    public boolean thirdEffect(Player attacker, Square firstAttackSquare, Player firstDefender, Square secondAttackSquare, Player secondDefender, Square thirdAttackSquare, Player thirdDefender) {

        /*NON C'È L'EFFETTO */
        return false;
    }


}
