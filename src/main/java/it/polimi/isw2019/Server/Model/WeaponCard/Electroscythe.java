package it.polimi.isw2019.Server.Model.WeaponCard;

import it.polimi.isw2019.Server.Model.ColorCube;
import it.polimi.isw2019.Server.Model.Exception.ErrorEffectException;
import it.polimi.isw2019.Server.Model.Exception.KillShotException;
import it.polimi.isw2019.Server.Model.Exception.NoEffectException;
import it.polimi.isw2019.Server.Model.Exception.OverKillException;
import it.polimi.isw2019.Server.Model.GameBoard;
import it.polimi.isw2019.Server.Model.Player;

import java.util.ArrayList;

public class Electroscythe extends AbstractWeaponCard {

    public Electroscythe() {
        super(6, "Electroscythe", ColorCube.BLUE, 1);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT: Deal 1 damage and to every other player on your square.\n");
        this.infoEffect.add("IN REAPER MODE: Deal 2 damage to every other player on your square. You have to pay a BLUE cube and a RED cube.\n");
        this.rechargeCube[0] = 0;
        this.rechargeCube[1] = 0;
        this.rechargeCube[2] = 1;
    }

    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException, KillShotException {


        ArrayList<Player> playersList = gameBoard.playersInOneSquare(attacker.getX(), attacker.getY(), attacker);

        if(!playersList.isEmpty()){

                for (Player aPlayerList:playersList) {
                    try {
                        aPlayerList.sufferDamageOrMark(attacker.getColor(), 1, 0);
                    }catch (KillShotException e){
                        throw new KillShotException(aPlayerList.getColor());
                    } catch (OverKillException e){
                        e.printStackTrace();
                    }
                }

            } else {
                throw new ErrorEffectException();
            }

    }

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException{
        /*AGGIUNGERE PAGARE UN CUBO ROSSO E UNO BLU*/

        ArrayList<Player> playersList = gameBoard.playersInOneSquare(attacker.getX(), attacker.getY(), attacker);

        if(!playersList.isEmpty()){

                for (Player aPlayerList:playersList) {
                    try {
                        aPlayerList.sufferDamageOrMark(attacker.getColor(), 2, 0);
                    }catch (KillShotException | OverKillException e){
                        e.printStackTrace();
                    }
                }

        } else {
            throw new ErrorEffectException();
        }

    }

    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException {
        /*NON ESISTE IL QUESTO EFFETTO*/
        throw new NoEffectException();

    }


}
