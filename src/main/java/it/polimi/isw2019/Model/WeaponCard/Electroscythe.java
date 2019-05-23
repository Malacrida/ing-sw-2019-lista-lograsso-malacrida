package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.Exception.ErrorEffectException;
import it.polimi.isw2019.Model.Exception.DamageTrackException;
import it.polimi.isw2019.Model.Exception.NoEffectException;
import it.polimi.isw2019.Model.GameBoard;
import it.polimi.isw2019.Model.Player;

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

    /**
     *
     * @param gameBoard
     * @param attacker
     * @param firstDefender
     * @param secondDefender
     * @param thirdDefender
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @throws ErrorEffectException
     * @throws DamageTrackException
     */

    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException, ErrorEffectException, DamageTrackException {


        ArrayList<Player> playersList = gameBoard.playersInOneSquare(attacker.getX(), attacker.getY(), attacker);

        if(!playersList.isEmpty()){

                for (Player aPlayerList:playersList) {
                    try {
                        aPlayerList.sufferDamageOrMark(attacker.getColor(), 1, 0);
                    }catch (DamageTrackException e){
                        throw new DamageTrackException(aPlayerList.getColor());
                    }
                }

            } else {
                throw new ErrorEffectException();
            }

    }


    /**
     *
     * @param gameBoard
     * @param attacker
     * @param firstDefender
     * @param secondDefender
     * @param thirdDefender
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param x3
     * @param y3
     * @throws ErrorEffectException
     */
    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2, int x3, int y3) throws ErrorEffectException, DamageTrackException {
        /*AGGIUNGERE PAGARE UN CUBO ROSSO E UNO BLU*/

        ArrayList<Player> playersList = gameBoard.playersInOneSquare(attacker.getX(), attacker.getY(), attacker);

        if(!playersList.isEmpty()){

                for (Player aPlayerList:playersList) {
                    try {
                        aPlayerList.sufferDamageOrMark(attacker.getColor(), 2, 0);
                    }catch (DamageTrackException e){
                        e.printStackTrace();
                    }
                }

        } else {
            throw new ErrorEffectException();
        }

    }

    /**
     *
     * @param gameBoard
     * @param attacker
     * @param firstDefender
     * @param secondDefender
     * @param thirdDefender
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @throws NoEffectException
     */

    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException, ErrorEffectException, DamageTrackException {
        /*NON ESISTE IL QUESTO EFFETTO*/
        throw new NoEffectException();

    }


}
