package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.Exception.ErrorEffectException;
import it.polimi.isw2019.Model.Exception.DamageTrackException;
import it.polimi.isw2019.Model.Exception.NoEffectException;
import it.polimi.isw2019.Model.GameBoard;
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
        this.rechargeCube[0] = 1;
        this.rechargeCube[1] = 1;
        this.rechargeCube[2] = 0;
    }

    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException, DamageTrackException {

        if((firstDefender != null) && (moreThanOneOrTwoDistance(attacker.getX(), attacker.getY(), firstDefender.getX(), firstDefender.getY(), 1))){ //se ha inserito almeno un difensore e si trova in una cella almeno distante 1
            ArrayList<Player> playerList = gameBoard.playersInOneSquare(firstDefender.getX(), firstDefender.getY(), null);
            ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

            /*CONTROLLO SE I PLAYERS ALL'INTERNO DELLA STANZA SONO VISIBILI */

            if( visiblePlayers.contains(firstDefender)){ // se firstDefender è contenuto nella lista di player visiili dall'attaccante allora procedo
                try {
                    firstDefender.sufferDamageOrMark(attacker.getColor(), 1, 0);
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
    public void secondEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2, int x3, int y3) throws ErrorEffectException, DamageTrackException {


        if((firstDefender != null) && (moreThanOneOrTwoDistance(attacker.getX(), attacker.getY(), firstDefender.getX(), firstDefender.getY(), 1))){ //se ha inserito almeno un difensore e si trova in una cella almeno distante 1
            ArrayList<Player> playerList = gameBoard.playersInOneSquare(firstDefender.getX(), firstDefender.getY(), null);
            ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

            /*CONTROLLO SE I PLAYERS ALL'INTERNO DELLA STANZA SONO VISIBILI */

            if( visiblePlayers.contains(firstDefender)){ // se firstDefender è contenuto nella lista di player visiili dall'attaccante allora procedo
                try {
                    firstDefender.sufferDamageOrMark(attacker.getColor(), 1, 0);
                } catch (DamageTrackException e) {
                    e.printStackTrace();
                }

                for (Player aPlayerList : playerList) {
                    try {
                        aPlayerList.sufferDamageOrMark(attacker.getColor(), 0, 2);
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
    public void thirdEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException {

        throw new NoEffectException();

    }


}
