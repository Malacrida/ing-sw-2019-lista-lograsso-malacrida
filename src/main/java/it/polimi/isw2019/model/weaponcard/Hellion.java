package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.ColorCube;
import it.polimi.isw2019.model.exception.ErrorEffectException;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.NoEffectException;
import it.polimi.isw2019.model.GameBoard;
import it.polimi.isw2019.model.Player;

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
        this.rechargeCube[0] = 1;
        this.rechargeCube[1] = 1;
        this.rechargeCube[2] = 0;
    }

    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {

        if((defenders.get(0) != null) && (moreThanOneOrTwoDistance(attacker.getX(), attacker.getY(), defenders.get(0).getX(), defenders.get(0).getY(), 1))){ //se ha inserito almeno un difensore e si trova in una cella almeno distante 1
            ArrayList<Player> playerList = gameBoard.playersInOneSquare(defenders.get(0).getX(), defenders.get(0).getY(), null);
            ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

            /*CONTROLLO SE I PLAYERS ALL'INTERNO DELLA STANZA SONO VISIBILI */

            if(visiblePlayers.contains(defenders.get(0))){ // se defenders.get(0) è contenuto nella lista di player visiili dall'attaccante allora procedo
                try {
                    defenders.get(0).sufferDamageOrMark(attacker.getColor(), 1, 0);
                } catch (DamageTrackException e) {
                    e.printStackTrace();
                }

                for (Player aPlayerList : playerList) {
                    try {
                        aPlayerList.sufferDamageOrMark(attacker.getColor(), 0, 1);
                    } catch (DamageTrackException e) {
                        e.printStackTrace();
                    }
                }
            }
            else {
                throw new ErrorEffectException();
            }
        }
        else{
            throw new ErrorEffectException();
        }

    }

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {
        ArrayList<Player> playerList = gameBoard.playersInOneSquare(defenders.get(0).getX(), defenders.get(0).getY(), null);
        ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

        if(moreThanOneOrTwoDistance(attacker.getX(), attacker.getY(), defenders.get(0).getX(), defenders.get(0).getY(), 1)){ //se ha inserito almeno un difensore e si trova in una cella almeno distante 1

        /*CONTROLLO SE I PLAYERS ALL'INTERNO DELLA STANZA SONO VISIBILI */

            if(visiblePlayers.contains(defenders.get(0))){ // se defenders.get(0) è contenuto nella lista di player visiili dall'attaccante allora procedo
                try {
                    defenders.get(0).sufferDamageOrMark(attacker.getColor(), 1, 0);
                } catch (DamageTrackException e) {
                    e.getMessage();
                }

                for (Player aPlayerList : playerList) {
                    try {
                        aPlayerList.sufferDamageOrMark(attacker.getColor(), 0, 2);
                    } catch (DamageTrackException e) {
                        e.getMessage();
                    }
                }
            }
            else {
                throw new ErrorEffectException();
            }
        }
        else{
            throw new ErrorEffectException();
        }

    }

    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws NoEffectException {

        throw new NoEffectException();

    }


}
