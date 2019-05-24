package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.ColorCube;
import it.polimi.isw2019.model.exception.ErrorEffectException;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.NoEffectException;
import it.polimi.isw2019.model.GameBoard;
import it.polimi.isw2019.model.Player;

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
     * @param defenders
     * @param coordinates
     * @throws ErrorEffectException
     * @throws DamageTrackException
     */

    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {


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
     * @param defenders
     * @param coordinates
     * @throws ErrorEffectException
     */
    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {
        /*AGGIUNGERE PAGARE UN CUBO ROSSO E UNO BLU*/

        ArrayList<Player> playersList = gameBoard.playersInOneSquare(attacker.getX(), attacker.getY(), attacker);

        if(!playersList.isEmpty()){

                for (Player aPlayerList:playersList) {
                    try {
                        aPlayerList.sufferDamageOrMark(attacker.getColor(), 2, 0);
                    }catch (DamageTrackException e){
                        e.getMessage();
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
     * @param defenders
     * @param coordinates
     * @throws NoEffectException
     */

    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws NoEffectException {
        /*NON ESISTE IL QUESTO EFFETTO*/
        throw new NoEffectException();

    }


}
