package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.Exception.ErrorEffectException;
import it.polimi.isw2019.Model.Exception.NoEffectException;
import it.polimi.isw2019.Model.Player;

import java.util.ArrayList;

public class Electroscythe extends AbstractWeaponCard {

    public Electroscythe() {
        super(6, "Electroscythe", ColorCube.BLUE, 1);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT: Deal 1 damage and to every other player on your square.\n");
        this.infoEffect.add("IN REAPER MODE: Deal 2 damage to every other player on your square. You have to pay a BLUE cube and a RED cube.\n");
    }

    @Override
    public void firstEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException {

        /*Square square = new SquareAmmo(); //chiedere a Sara

        ArrayList<Player> playersList = square[x1][y1].getPlayers(); //player in x1 e y1*/

        /* Se l'attaccante è nella stanza selezionata allora passa tutti gli elementi dell'array e fai un danno*/
        /*if (playersList.contains(attacker)) {

            for (Player aPlayersList : playersList) {

                aPlayersList.sufferDamage(attacker.getColor(), 1, 0);

            }
        }
        else {

            throw new ErrorEffectException();
        }*/
    }

    @Override
    public void secondEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException {
        /*AGGIUNGERE PAGARE UN CUBO ROSSO E UNO BLU*/
        //ArrayList<Player> playersList = square[x1][y1].getPlayers();

        /* Se l'attaccante è nella stanza selezionata allora passa tutti gli elementi dell'array e fai un danno*/

        /*try{

            if (firstAttackSquare.findPlayer(attacker)){

                for (Player aPlayersList : playersList) {

                    aPlayersList.sufferDamage(attacker.getColor(), 2, 0);

                }

            }

        } catch (ErrorEffectException) {
            throw new ErrorEffectException();
        }*/

    }

    @Override
    public void thirdEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException {
        /*NON ESISTE IL QUESTO EFFETTO*/
        throw new NoEffectException();

    }


}
