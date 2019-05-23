package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.Exception.ErrorEffectException;
import it.polimi.isw2019.Model.Exception.DamageTrackException;
import it.polimi.isw2019.Model.Exception.NoEffectException;
import it.polimi.isw2019.Model.GameBoard;
import it.polimi.isw2019.Model.Player;

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
    public void firstEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException, DamageTrackException {

        char direction = direction(attacker, firstDefender);

        if(direction != 'n'){

            try {
                firstDefender.sufferDamageOrMark(attacker.getColor(), 3, 0);
            } catch (DamageTrackException e) {
                e.printStackTrace();
            }

        } else {

            throw new ErrorEffectException();

        }

    }

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2, int x3, int y3) throws ErrorEffectException, DamageTrackException {

        char firstDirection = direction(attacker, firstDefender);
        char secondDirection = direction(attacker, firstDefender);

        if ((firstDirection != 'n') && (firstDirection == secondDirection)){

            try {
                firstDefender.sufferDamageOrMark(attacker.getColor(), 2, 0);
            } catch (DamageTrackException  e) {
                e.printStackTrace();
            }
            try {
                secondDefender.sufferDamageOrMark(attacker.getColor(), 2, 0);
            } catch (DamageTrackException e) {
                e.printStackTrace();
            }

        } else {

            throw new ErrorEffectException();

        }
    }

    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException {
        throw new NoEffectException();
    }

}
