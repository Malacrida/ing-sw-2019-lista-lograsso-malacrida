package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.ColorCube;
import it.polimi.isw2019.model.exception.ErrorEffectException;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.NoEffectException;
import it.polimi.isw2019.model.GameBoard;
import it.polimi.isw2019.model.Player;

import java.util.ArrayList;

public class VortexCannon extends AbstractWeaponCard {

    public VortexCannon() {
        super(8, "Vortex Cannon", ColorCube.RED, 1, 6, 3);
        this.infoEffect[0] = "FIRST EFFECT: Choose a square you can see, but not your square. Call it the vortex. Choose a target on the vortex or 1 move away from it. Move it onto the vortex and give it 2 damage";
        this.infoEffect[1] = "SECOND EFFECT: Choose up to 2 other targets on the vortex or 1 move away from it. Move them onto the vortex and give them each 1 damage.";
        this.infoEffect[2] = "THIRD EFFECT: This effect doesn't exist";
        this.infoEffect[3] = "NOTES: The 3 targets must be different, but some might start on the same square. It is legal to choose targets on your square, on the vortex, or even on squares you can't see. They all end up on the vortex.";
        this.rechargeCube = new ColorCube[2];
        this.rechargeCube[0] = ColorCube.RED;
        this.rechargeCube[1] = ColorCube.BLUE;
        this.paySecondEffect = new ColorCube[1];
        this.paySecondEffect[0] = ColorCube.RED;
        setWeaponCardDescription();
    }

    /**
     * Choose a square you can see, but not your square. Call it the vortex. Choose a target on the vortex or 1 move away from it. Move it onto the vortex and give it 2 damage
     * @param gameBoard is the Gameboard where players play
     * @param attacker is the player who use Weapon card
     * @param defenders are players attacked
     * @param coordinates some coordinates used to move players or to indicate squares to attack players
     * @throws ErrorEffectException there is a problem during effect
     */

    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {

        ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

        if((!defenders.isEmpty()) && (visiblePlayers.contains(defenders.get(0))) && (!sameSquare(attacker.getX(), attacker.getY(), coordinates[0], coordinates[1]))){
            if (oneDistance(defenders.get(0).getX(), defenders.get(0).getY(), coordinates[0], coordinates[1]) || sameSquare(defenders.get(0).getX(), defenders.get(0).getY(), coordinates[0], coordinates[1])){
                gameBoard.changePositionPlayer(defenders.get(0), coordinates[0], coordinates[1]);
                try {
                    defenders.get(0).sufferDamageOrMark(attacker.getColor(), 2, 0);
                    controlPlayersDamages(gameBoard, defenders.get(0));
                } catch (DamageTrackException e) {
                    e.getMessage();
                }
            } else {
                throw new ErrorEffectException();
            }

            firstIsValid = true;

        } else {

            throw new ErrorEffectException();
        }


    }

    /**
     * Choose up to 2 other targets on the vortex or 1 move away from it. Move them onto the vortex and give them each 1 damage.
     * @param gameBoard is the Gameboard where players play
     * @param attacker is the player who use Weapon card
     * @param defenders are players attacked
     * @param coordinates some coordinates used to move players or to indicate squares to attack players
     * @throws ErrorEffectException there is a problem during effect
     */

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {

        ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

        if (firstIsValid){
            if((visiblePlayers.contains(defenders.get(1))) && (oneDistance(defenders.get(0).getX(), defenders.get(0).getY(), coordinates[0], coordinates[1]) || sameSquare(defenders.get(0).getX(), defenders.get(0).getY(), coordinates[0], coordinates[1]))){

                gameBoard.changePositionPlayer(defenders.get(1), coordinates[0], coordinates[1]);

                try {
                    defenders.get(1).sufferDamageOrMark(attacker.getColor(), 1, 0);
                    controlPlayersDamages(gameBoard, defenders.get(1));
                } catch (DamageTrackException e) {
                    e.getMessage();
                }

                if((visiblePlayers.contains(defenders.get(2))) && (oneDistance(defenders.get(0).getX(), defenders.get(0).getY(), coordinates[0], coordinates[1]) || sameSquare(defenders.get(0).getX(), defenders.get(0).getY(), coordinates[0], coordinates[1]))){

                    gameBoard.changePositionPlayer(defenders.get(2), coordinates[0], coordinates[1]);

                    try {
                        defenders.get(2).sufferDamageOrMark(attacker.getColor(), 1, 0);
                        controlPlayersDamages(gameBoard, defenders.get(2));
                    } catch (DamageTrackException e) {
                        e.getMessage();
                    }
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
        throw new NoEffectException();
    }

}
