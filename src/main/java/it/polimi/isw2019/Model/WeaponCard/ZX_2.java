package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.Exception.ErrorEffectException;
import it.polimi.isw2019.Model.Exception.DamageTrackException;
import it.polimi.isw2019.Model.Exception.NoEffectException;
import it.polimi.isw2019.Model.GameBoard;
import it.polimi.isw2019.Model.Player;

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
    public void firstEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException, ErrorEffectException, DamageTrackException {

        ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

        if((firstDefender != null) && (visiblePlayers.contains(firstDefender))){
            try {
                firstDefender.sufferDamageOrMark(attacker.getColor(), 1, 2);
            } catch (DamageTrackException e) {
                e.printStackTrace();
            }

        } else {

            throw new ErrorEffectException();

        }


    }

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2, int x3, int y3) throws NoEffectException, ErrorEffectException, DamageTrackException {

        ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

        if((firstDefender != null) && (secondDefender != null) && (thirdDefender != null) && (playersAreVisible(visiblePlayers, firstDefender, secondDefender, thirdDefender))){

            try {
                firstDefender.sufferDamageOrMark(attacker.getColor(), 0, 1);
            } catch (DamageTrackException  e) {
                e.printStackTrace();
            }

            try {
                secondDefender.sufferDamageOrMark(attacker.getColor(), 0, 1);
            } catch (DamageTrackException  e) {
                e.printStackTrace();
            }

            try {
                thirdDefender.sufferDamageOrMark(attacker.getColor(), 0, 1);
            } catch (DamageTrackException e) {
                e.printStackTrace();
            }

        } else {
            throw new ErrorEffectException();
        }



    }

    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException, ErrorEffectException, DamageTrackException {
        throw new NoEffectException();

    }
}
