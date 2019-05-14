package it.polimi.isw2019.Server.Model.WeaponCard;

import it.polimi.isw2019.Server.Model.ColorCube;
import it.polimi.isw2019.Server.Model.Exception.ErrorEffectException;
import it.polimi.isw2019.Server.Model.Exception.KillShotException;
import it.polimi.isw2019.Server.Model.Exception.NoEffectException;
import it.polimi.isw2019.Server.Model.Exception.OverKillException;
import it.polimi.isw2019.Server.Model.GameBoard;
import it.polimi.isw2019.Server.Model.Player;

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
    public void firstEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException{

        /* AGGIUNGERE CONTROLLO STANZA DISTANTE ALMENO UNO*/
        ArrayList<Player> playerList = gameBoard.playersInOneSquare(x1, y1, null);

        if (playerList != null){
            try {
                firstDefender.sufferDamageOrMark(attacker.getColor(), 1, 0);
            } catch (OverKillException | KillShotException e) {
                e.printStackTrace();
            }
            for (Player aPlayerList : playerList) {
                try {
                    aPlayerList.sufferDamageOrMark(attacker.getColor(), 0, 1);
                } catch (OverKillException | KillShotException e) {
                    e.printStackTrace();
                }
            }
        } else{

            throw new ErrorEffectException();

        }

    }

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException{

        /* AGGIUNGERE CONTROLLO STANZA DISTANTE ALMENO UNO*/

        ArrayList<Player> playerList = gameBoard.playersInOneSquare(x1, y1, null);

        if(playerList != null){
            try {
                firstDefender.sufferDamageOrMark(attacker.getColor(), 1, 0);
            } catch (OverKillException | KillShotException e) {
                e.printStackTrace();
            }
            for (Player aPlayerList : playerList) {

                try {
                    aPlayerList.sufferDamageOrMark(attacker.getColor(), 0, 2);
                } catch (OverKillException | KillShotException e) {
                    e.printStackTrace();
                }
            }

        } else {

            throw new ErrorEffectException();

        }

    }

    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException{

        throw new NoEffectException();

    }


}
