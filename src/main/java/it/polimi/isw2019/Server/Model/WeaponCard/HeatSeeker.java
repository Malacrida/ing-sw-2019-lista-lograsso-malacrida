package it.polimi.isw2019.Server.Model.WeaponCard;

import it.polimi.isw2019.Server.Model.ColorCube;
import it.polimi.isw2019.Server.Model.Exception.ErrorEffectException;
import it.polimi.isw2019.Server.Model.Exception.KillShotException;
import it.polimi.isw2019.Server.Model.Exception.NoEffectException;
import it.polimi.isw2019.Server.Model.Exception.OverKillException;
import it.polimi.isw2019.Server.Model.GameBoard;
import it.polimi.isw2019.Server.Model.Player;

import java.util.ArrayList;

public class HeatSeeker extends AbstractWeaponCard {

    public HeatSeeker() {
        super(10, "HeatSeeker", ColorCube.RED, 1);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("EFFECT: Choose 1 target you cannot see and deal 3 damage " +
                "to it.");
        this.infoEffect.add("NOTE : Yes, this can only hit targets you cannot see. ");
        this.rechargeCube[0] = 2;
        this.rechargeCube[1] = 1;
        this.rechargeCube[2] = 0;
    }

    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException {

        /* AGGIUNGERE CONTROLLO BERSAGLIO NON VISIBILE*/

        ArrayList<Player> playerList = gameBoard.playersWhoCanSee(attacker.getX(), attacker.getY(), attacker);

        if (firstDefender != null){
            for (Player aPlayerList:playerList) {

                if (aPlayerList == firstDefender){
                    throw new ErrorEffectException();
                }

            }
            try {
                firstDefender.sufferDamageOrMark(attacker.getColor(), 3, 0);
            } catch (OverKillException | KillShotException e) {
                e.printStackTrace();
            }
        } else {

            throw new ErrorEffectException();

        }

    }

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException{
        /* NON ESISTE L'EFFETTO */
        throw new NoEffectException();
    }

    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException{
        /* NON ESISTE L'EFFETTO */
        throw new NoEffectException();
    }

}
