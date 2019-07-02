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
        super(6, "Electroscythe", ColorCube.BLUE, 1, 0, 0);
        this.infoEffect[0] = "FIRST EFFECT : Deal 1 damage and to every other player on your square.\n";
        this.infoEffect[1] = "SECOND EFFECT: Deal 2 damage to every other player on your square. You have to pay a BLUE cube and a RED cube.\n";
        this.infoEffect[2] = "THIRD EFFECT : This effect doesn't exist;\n";
        this.infoEffect[3] = "NOTE : You can choose only one effect";
        this.rechargeCube = new ColorCube[1];
        this.rechargeCube[0] = ColorCube.BLUE;

        this.paySecondEffect = new ColorCube[2];
        this.paySecondEffect[0] = ColorCube.BLUE;
        this.paySecondEffect[1] = ColorCube.RED;
        setWeaponCardDescription();
    }

    /**
     * Deal 1 damage and to every other player on your square.
     * @param gameBoard is the Gameboard where players play
     * @param attacker is the player who use Weapon card
     * @param defenders are players attacked
     * @param coordinates some coordinates used to move players or to indicate squares to attack players
     * @throws ErrorEffectException there is a problem during effect
     */
    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {

        ArrayList<Player> playersList = gameBoard.playersInOneSquare(attacker.getX(), attacker.getY(), attacker); //PLAYERS IN UN QUADRATO;
        if(!playersList.isEmpty()){
                for (Player aPlayerList:playersList) {
                    try {
                        System.out.println("ok");
                        aPlayerList.sufferDamageOrMark(attacker.getColor(), 1, 0);
                    } catch (DamageTrackException e) {
                        e.getMessage();
                    }
                }

            } else {
                throw new ErrorEffectException();
            }

    }


    /**
     * Deal 2 damage to every other player on your square.
     * @param gameBoard is the Gameboard where players play
     * @param attacker is the player who use Weapon card
     * @param defenders are players attacked
     * @param coordinates some coordinates used to move players or to indicate squares to attack players
     * @throws ErrorEffectException there is a problem during effect
     */
    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {

        /*AGGIUNGERE PAGARE UN CUBO ROSSO E UNO BLU*/

        ArrayList<Player> playersList = gameBoard.playersInOneSquare(attacker.getX(), attacker.getY(), attacker); //PLAYERS NELLA CELLA

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
     * This effect doesn't exist
     * @throws NoEffectException there isn't this effect
     */
    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws NoEffectException {
        /*NON ESISTE IL QUESTO EFFETTO*/
        throw new NoEffectException();

    }


}
