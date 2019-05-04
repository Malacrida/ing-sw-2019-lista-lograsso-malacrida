package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.Exception.ErrorEffect;
import it.polimi.isw2019.Model.Exception.NoEffectException;
import it.polimi.isw2019.Model.Player;

import java.util.ArrayList;

public class Hellion extends AbstractWeaponCard {

    public Hellion() {
        super(11, "Hellion", ColorCube.RED, 1);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC MODE: Deal 1 damage to 1 target you can see at least\n" +
                "1 move away. Then give 1 mark to that target and everyone\n" +
                "else on that square.\n");
        this.infoEffect.add("IN NANO-TRACER MODE: Deal 1 damage to 1 target you can\n" +
                "see at least 1 move away. Then give 2 marks to that target\n" +
                "and everyone else on that square.\n");
    }

    @Override
    public void firstEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffect{

        /* AGGIUNGERE CONTROLLO STANZA DISTANTE ALMENO UNO*/
        /*ArrayList<Player> playerList = firstAttackSquare.getPlayers();

        try {

            firstDefender.sufferDamage(attacker.getColor(), 1, 0);

            for(int i = 0; i < playerList.size(); i++){

                playerList.get(i).sufferDamage(attacker.getColor(), 0, 1);

            }

        } catch (ErrorEffect) {

            throw new ErrorEffect();

        }*/

    }

    @Override
    public void secondEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffect{

        /* AGGIUNGERE CONTROLLO STANZA DISTANTE ALMENO UNO*/

        /*ArrayList<Player> playerList = firstAttackSquare.getPlayers();

        try {

            firstDefender.sufferDamage(attacker.getColor(), 1, 0);

            for (Player aPlayerList : playerList) {
                aPlayerList.sufferDamage(attacker.getColor(), 0, 2);
            }

        } catch (ErrorEffect) {

            throw new ErrorEffect();

        }*/

    }

    @Override
    public void thirdEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException{

        throw new NoEffectException();

    }


}
