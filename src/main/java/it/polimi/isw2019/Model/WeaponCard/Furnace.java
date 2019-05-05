package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.Exception.NoEffectException;
import it.polimi.isw2019.Model.Player;

import java.util.ArrayList;

public class Furnace extends AbstractWeaponCard {

    public Furnace() {
        super(9, "Furnace", ColorCube.RED,1);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT: Choose a room you can see, but not the room\n" +
                "you are in. Deal 1 damage to everyone in that room.\n");
        this.infoEffect.add("IN COZY FIRE MODE: Choose a square exactly one move\n" +
                "away. Deal 1 damage and 1 mark to everyone on that\n" +
                "square.\n");
    }

    @Override
    public void firstEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) {


        /*AGGIUNGERE CONTROLLO SE LA STANZA È VISTA DALL'ATTACCANTE*/
/*
        ArrayList<Player> playerList = firstAttackSquare.getPlayers();

        try {

            for (int i = 0; i < playerList.size(); i++){

                playerList.get(i).sufferDamage(attacker.getColor(), 1, 0);

            }

        } catch (ErrorEffectException) { // se la stanza è vuota allora errore

            throw new ErrorEffectException();

        }*/

    }

    @Override
    public void secondEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) {

        /*AGGIUNGERE CONTROLLO SE LA STANZA È ADIACENTE*/
/*
        ArrayList<Player> playerList = firstAttackSquare.getPlayers();

        try {

            if (oneDistanceX(attacker, playerList.get(0))){ //se sono esattamente distante 1 su asse X

                for (Player aPlayerList : playerList) {

                    aPlayerList.sufferDamage(attacker.getColor(), 1, 0);

                }

            }

            else if (oneDistanceY(attacker, playerList.get(0))){ //se sono esattamente distante 1 su asse Y

                for (Player aPlayerList : playerList) {

                    aPlayerList.sufferDamage(attacker.getColor(), 1, 0);

                }

            }

        } catch (ErrorEffectException) {

            throw new ErrorEffectException();

        }*/
    }

    @Override
    public void thirdEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException {

        /* NON ESISTE L'EFFETTO */

        throw new NoEffectException();
    }


}
