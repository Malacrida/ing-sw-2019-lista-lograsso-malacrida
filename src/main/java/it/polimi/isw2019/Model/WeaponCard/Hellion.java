package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.Player;
import it.polimi.isw2019.Model.Square;

import java.util.ArrayList;

public class Hellion extends AbstractWeaponCard {

    public Hellion(int id, String name, ColorCube color) {
        super(11, "Hellion", ColorCube.RED);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC MODE: Deal 1 damage to 1 target you can see at least\n" +
                "1 move away. Then give 1 mark to that target and everyone\n" +
                "else on that square.\n");
        this.infoEffect.add("IN NANO-TRACER MODE: Deal 1 damage to 1 target you can\n" +
                "see at least 1 move away. Then give 2 marks to that target\n" +
                "and everyone else on that square.\n");
    }

    @Override
    public boolean firstEffect(Player attacker, Square firstAttackSquare, Player firstDefender, Square secondAttackSquare, Player secondDefender, Square thirdAttackSquare, Player thirdDefender) {

        /* AGGIUNGERE CONTROLLO STANZA DISTANTE ALMENO UNO*/
        ArrayList<Player> playerList = firstAttackSquare.getPlayers();

        firstDefender.sufferDamage(attacker.getColor(), 1, 0);

        for(int i = 0; i < playerList.size(); i++){
            playerList.get(i).sufferDamage(attacker.getColor(), 0, 1);
        }

        return true;
    }

    @Override
    public boolean secondEffect(Player attacker, Square firstAttackSquare, Player firstDefender, Square secondAttackSquare, Player secondDefender, Square thirdAttackSquare, Player thirdDefender) {

        /* AGGIUNGERE CONTROLLO STANZA DISTANTE ALMENO UNO*/
        ArrayList<Player> playerList = firstAttackSquare.getPlayers();

        firstDefender.sufferDamage(attacker.getColor(), 1, 0);

        for(int i = 0; i < playerList.size(); i++){
            playerList.get(i).sufferDamage(attacker.getColor(), 0, 2);
        }

        return true;
    }

    @Override
    public boolean thirdEffect(Player attacker, Square firstAttackSquare, Player firstDefender, Square secondAttackSquare, Player secondDefender, Square thirdAttackSquare, Player thirdDefender) {
        return false;
    }


}
