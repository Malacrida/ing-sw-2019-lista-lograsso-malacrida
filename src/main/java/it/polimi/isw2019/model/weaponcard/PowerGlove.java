package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.ColorCube;
import it.polimi.isw2019.model.GameBoard;
import it.polimi.isw2019.model.Player;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.ErrorEffectException;
import it.polimi.isw2019.model.exception.NoEffectException;

import java.util.ArrayList;

public class PowerGlove extends AbstractWeaponCard {

    public PowerGlove() {
        super(19, "Power Glove", ColorCube.YELLOW, 1, 4, 2);
        this.infoEffect[0] = "FIRST EFFECT: choose 1 target on any square exactly 1 move away. Move onto that square and give the target 1 damage and 2 marks.";
        this.infoEffect[1] = "SECOND EFFECT: Choose a square exactly 1 move away.";
        this.infoEffect[2] = "THIRD EFFECT: This effect doesn't exist";
        this.infoEffect[3] = "NOTES: In rocket fist mode, you're flying squares in a straight line, punching person per square";
        this.rechargeCube = new ColorCube[2];
        this.rechargeCube[0] = ColorCube.YELLOW;
        this.rechargeCube[1] = ColorCube.BLUE;
        setWeaponCardDescription();
        this.paySecondEffect = new ColorCube[1];
        this.paySecondEffect[0] = ColorCube.BLUE;
    }

    /**
     * choose 1 target on any square exactly 1 move away. Move onto that square and give the target 1 damage and 2 marks
     * @param gameBoard is the Gameboard where players play
     * @param attacker is the player who use Weapon card
     * @param defenders are players attacked
     * @param coordinates some coordinates used to move players or to indicate squares to attack players
     * @throws ErrorEffectException there is a problem during effect
     */

    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {

        if(gameBoard.isSquareAvailableOnArena(attacker, defenders.get(0).getX(), defenders.get(0).getY())){ //se il defender è la stanza accanto DA CONTROLLARE SE È ESATTAMENTE UN MOVIMENTO O NO
            gameBoard.changePositionPlayer(attacker, defenders.get(0).getX(), defenders.get(0).getY()); //Cambia posizione l'attacconte e va nello stesso quadrato del bersaglio

            try {
                defenders.get(0).sufferDamageOrMark(attacker.getColor(), 1, 2);
                controlPlayersDamages(gameBoard, defenders.get(0));
            } catch (DamageTrackException e) {
                e.getMessage();
            }

        } else {
            throw new ErrorEffectException();
        }
    }

    /**
     * Choose a square exactly 1 move away.
     * @param gameBoard is the Gameboard where players play
     * @param attacker is the player who use Weapon card
     * @param defenders are players attacked
     * @param coordinates some coordinates used to move players or to indicate squares to attack players
     * @throws ErrorEffectException there is a problem during effect
     */

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException {

        /* PAGA UN BLU */

        char dir1 = direction(attacker, defenders.get(0));
        char dir2 = direction(attacker, defenders.get(1));

        if (gameBoard.isSquareAvailableOnArena(attacker, defenders.get(0).getX(), defenders.get(0).getY())) { //se il defenders.get(0) è la stanza accanto

            gameBoard.changePositionPlayer(attacker, defenders.get(0).getX(), defenders.get(0).getY());

            try {
                defenders.get(0).sufferDamageOrMark(attacker.getColor(), 2, 0);
                controlPlayersDamages(gameBoard, defenders.get(0));
            } catch (DamageTrackException e) {
                e.getMessage();
            }

            if (gameBoard.isSquareAvailableOnArena(attacker, defenders.get(1).getX(), defenders.get(1).getY()) && (dir1 == dir2)) { //se il defenders.get(1) è la stanza accanto
                gameBoard.changePositionPlayer(attacker, defenders.get(1).getX(), defenders.get(1).getY());

                try {
                    defenders.get(1).sufferDamageOrMark(attacker.getColor(), 2, 0);
                    controlPlayersDamages(gameBoard, defenders.get(1));
                } catch (DamageTrackException e) {
                    e.getMessage();
                }

            } else {
                throw new ErrorEffectException();
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
        throw new NoEffectException();

    }




}
