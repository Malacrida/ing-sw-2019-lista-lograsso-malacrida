package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.ColorCube;
import it.polimi.isw2019.model.exception.ErrorEffectException;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.NoEffectException;
import it.polimi.isw2019.model.GameBoard;
import it.polimi.isw2019.model.Player;

import java.util.ArrayList;

public class RailGun extends AbstractWeaponCard {

    public RailGun() {
        super(15, "Rail Gun", ColorCube.YELLOW, 1);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC MODE : Choose a cardinal direction and 1 target in that direction deal 3 damage to it");
        this.infoEffect.add("IN PIERCING MODE:Choose a cardinal direction and 1 or 2 targets in that direction deal 2 damage to each");
        this.infoEffect.add(" NOTE : Basically, you're shooting in a straight line and ignoring walls." +
                "You don't have to pick a target on the other side of a wall – it could even" +
                "be someone on your own square – but shooting through walls sure is" +
                "fun. There are only 4 cardinal directions. You imagine facing one wall or" +
                "door, square-on, and firing in that direction. Anyone on a square in that" +
                "direction (including yours) is a valid target. In piercing mode," +
                "the 2 targets can be on the same square or on different squares. ");
        this.rechargeCube[0] = 0;
        this.rechargeCube[1] = 2;
        this.rechargeCube[2] = 1;
    }


    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {

        char direction = direction(attacker, defenders.get(0));

        if(direction != 'n'){

            try {
                defenders.get(0).sufferDamageOrMark(attacker.getColor(), 3, 0);
            } catch (DamageTrackException e) {
                e.printStackTrace();
            }

        } else {

            throw new ErrorEffectException();

        }

    }

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {

        char firstDirection = direction(attacker, defenders.get(0));
        char secondDirection = direction(attacker, defenders.get(0));

        if ((firstDirection != 'n') && (firstDirection == secondDirection)){

            try {
                defenders.get(0).sufferDamageOrMark(attacker.getColor(), 2, 0);
            } catch (DamageTrackException  e) {
                e.getMessage();
            }
            try {
                defenders.get(1).sufferDamageOrMark(attacker.getColor(), 2, 0);
            } catch (DamageTrackException e) {
                e.getMessage();
            }

        } else {

            throw new ErrorEffectException();

        }
    }

    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws NoEffectException {
        throw new NoEffectException();
    }

}
