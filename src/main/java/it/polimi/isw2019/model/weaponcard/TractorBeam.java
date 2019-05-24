package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.ColorCube;
import it.polimi.isw2019.model.exception.ErrorEffectException;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.NoEffectException;
import it.polimi.isw2019.model.GameBoard;
import it.polimi.isw2019.model.Player;

import java.util.ArrayList;

public class TractorBeam extends AbstractWeaponCard {

    public TractorBeam() {
        super(7, "Tractor Beam", ColorCube.BLUE, 1);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT: Move a target 0,1 or 2 squares to a square you can see, and give it 1 damage.\n");
        this.infoEffect.add("IN PUNISHER MODE: Choose a target 0,1 or 2 moves away from you. Move the target to your square and deal 3 damage to it.\n");
        this.infoEffect.add("NOTES: You can move a target even if you can't see it. The target ends up in a place where you can see and damage it. The moves do not have to be in the same direction.\n");
        this.rechargeCube[0] = 0;
        this.rechargeCube[1] = 0;
        this.rechargeCube[2] = 1;
    }

    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {

        /*MUOVI DI DUE*/

        if(defenders.get(0) != null){

            try {
                defenders.get(0).sufferDamageOrMark(attacker.getColor(), 1, 0);
            } catch (DamageTrackException e) {
                e.getMessage();
            }

        } else {

            throw new ErrorEffectException();

        }

    }

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {

        /*MUOVI DI DUE*/

        if((defenders.get(1) != null) && (sameSquare(attacker.getX(), attacker.getY(), defenders.get(1).getX(), defenders.get(1).getY()))){

            try {
                defenders.get(1).sufferDamageOrMark(attacker.getColor(), 3, 0);
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
