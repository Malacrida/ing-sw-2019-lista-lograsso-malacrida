package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.ColorCube;
import it.polimi.isw2019.model.exception.ErrorEffectException;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.NoEffectException;
import it.polimi.isw2019.model.GameBoard;
import it.polimi.isw2019.model.Player;

import java.util.ArrayList;

public class ZX_2 extends AbstractWeaponCard {

    public ZX_2() {
        super(17, "ZX_2", ColorCube.YELLOW, 1);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT :Deal 1 damage and 2 marks to\n" +
                "1 target you can see");
        this.infoEffect.add("IN SCANNER MODE : Choose up to 3 targets you "+
                "can see and deal 1 mark to each.");
        this.infoEffect.add("NOTE : Remember that the 3 targets can be\n" +
                "in 3 different rooms.   ");
        this.rechargeCube[0] = 1;
        this.rechargeCube[1] = 1;
        this.rechargeCube[2] = 0;
    }

    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {

        ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

        if((defenders.get(0) != null) && (visiblePlayers.contains(defenders.get(0)))){
            try {
                defenders.get(0).sufferDamageOrMark(attacker.getColor(), 1, 2);
            } catch (DamageTrackException e) {
                e.getMessage();
            }

        } else {

            throw new ErrorEffectException();

        }


    }

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {

        ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

        if((defenders.get(0) != null) && (defenders.get(1) != null) && (defenders.get(2) != null) && (playersAreVisible(visiblePlayers, defenders.get(0), defenders.get(1), defenders.get(2)))){

            try {
                defenders.get(0).sufferDamageOrMark(attacker.getColor(), 0, 1);
            } catch (DamageTrackException  e) {
                e.getMessage();
            }

            try {
                defenders.get(1).sufferDamageOrMark(attacker.getColor(), 0, 1);
            } catch (DamageTrackException  e) {
                e.getMessage();
            }

            try {
                defenders.get(2).sufferDamageOrMark(attacker.getColor(), 0, 1);
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
