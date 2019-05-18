package it.polimi.isw2019.Server.Model.WeaponCard;

import it.polimi.isw2019.Server.Model.ColorCube;
import it.polimi.isw2019.Server.Model.Exception.ErrorEffectException;
import it.polimi.isw2019.Server.Model.Exception.DamageTrackException;
import it.polimi.isw2019.Server.Model.Exception.NoEffectException;
import it.polimi.isw2019.Server.Model.GameBoard;
import it.polimi.isw2019.Server.Model.Player;

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
        this.rechargeCube[0] = 1;
        this.rechargeCube[1] = 0;
        this.rechargeCube[2] = 1;
    }

    private void damageFurnace(GameBoard gameBoard, Player attacker, int x1, int y1){

        ArrayList<Player> playerList = gameBoard.playersInOneSquare(x1,y1, null);

        for (Player aPlayerList : playerList){

            try {

                aPlayerList.sufferDamageOrMark(attacker.getColor(), 1, 0);
            } catch ( DamageTrackException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException, ErrorEffectException, DamageTrackException {


        /*AGGIUNGERE CONTROLLO SE LA STANZA È VISTA DALL'ATTACCANTE*/

        ArrayList<Player> playerList = gameBoard.playersInOneSquare(x1, y1, null);

        if (playerList != null){

            for (Player aPlayerList : playerList) {

                damageFurnace(gameBoard, attacker, x1, y1);
            }

        } else { // se la stanza è vuota allora errore

            throw new ErrorEffectException();

        }

    }

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException, ErrorEffectException, DamageTrackException {

        /*AGGIUNGERE CONTROLLO SE LA STANZA È ADIACENTE*/
        ArrayList<Player> playerList = gameBoard.playersInOneSquare(x1,y1, null);

        if (oneDistanceX(attacker, playerList.get(0))){ //se sono esattamente distante 1 su asse X

            damageFurnace(gameBoard, attacker, x1, y1);

        }

        else if (oneDistanceY(attacker, playerList.get(0))){ //se sono esattamente distante 1 su asse Y

            damageFurnace(gameBoard, attacker, x1, y1);

        }

        else {

            throw new ErrorEffectException();

        }
    }

    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException, ErrorEffectException, DamageTrackException {

        /* NON ESISTE L'EFFETTO */

        throw new NoEffectException();
    }


}
