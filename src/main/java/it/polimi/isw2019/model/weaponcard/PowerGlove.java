package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.ColorCube;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.ErrorEffectException;
import it.polimi.isw2019.model.exception.NoEffectException;
import it.polimi.isw2019.model.GameBoard;
import it.polimi.isw2019.model.Player;

import java.util.ArrayList;

public class PowerGlove extends AbstractWeaponCard {

    public PowerGlove() {
        super(19, "Power Glove", ColorCube.YELLOW, 1);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC MODE: choose 1 target on any square exactly 1 move away. Move onto that square and give the target 1 damage and 2 marks");
        this.infoEffect.add("IN ROCKET FIST MODE : Choose a square exactly 1 move away. " +
                "Move onto that square you may deal 2 damage to 1 target there if you want, you may move 1 more square in that same direction " +
                "(but only if it is a legal move). You may deal 2 damage to 1 target there, as well");
        this.infoEffect.add("NOTE : In rocket fist mode, you're flying squares in a straight line, punching person per square ");
        this.rechargeCube[0] = 0;
        this.rechargeCube[1] = 1;
        this.rechargeCube[2] = 1;
    }

    /**
     *
     * @param gameBoard is the Gameboard where players play
     * @param attacker is the player who use Weapon card
     * @param defenders are players attacked
     * @param coordinates some coordinates used to move players or to indicate squares to attack players
     * @throws ErrorEffectException there is a problem during effect
     *
     * @æuthor Davide Lista
     */

    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {

        if(gameBoard.isSquareAvailableOnArena(attacker, defenders.get(0).getX(), defenders.get(0).getY())){ //se il defender è la stanza accanto DA CONTROLLARE SE È ESATTAMENTE UN MOVIMENTO O NO
            gameBoard.changePositionPlayer(attacker, defenders.get(0).getX(), defenders.get(0).getY()); //Cambia posizione l'attacconte e va nello stesso quadrato del bersaglio

            try {
                defenders.get(0).sufferDamageOrMark(attacker.getColor(), 1, 2);
            } catch (DamageTrackException e) {
                e.getMessage();
            }

        } else {
            throw new ErrorEffectException();
        }
    }

    /**
     *
     * @param gameBoard is the Gameboard where players play
     * @param attacker is the player who use Weapon card
     * @param defenders are players attacked
     * @param coordinates some coordinates used to move players or to indicate squares to attack players
     * @throws ErrorEffectException there is a problem during effect
     *
     * @æuthor Davide Lista
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
            } catch (DamageTrackException e) {
                e.getMessage();
            }

            if (gameBoard.isSquareAvailableOnArena(attacker, defenders.get(1).getX(), defenders.get(1).getY()) && (dir1 == dir2)) { //se il defenders.get(1) è la stanza accanto
                gameBoard.changePositionPlayer(attacker, defenders.get(1).getX(), defenders.get(1).getY());

                try {
                    defenders.get(1).sufferDamageOrMark(attacker.getColor(), 2, 0);
                } catch (DamageTrackException e) {
                    e.getMessage();
                }

            } else {
                throw new ErrorEffectException();
            }

        }
    }

    /**
     * This effect doesn't exist
     * @throws NoEffectException there isn't this effect
     *
     * @æuthor Davide Lista
     */


    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws NoEffectException {
        throw new NoEffectException();

    }




}
