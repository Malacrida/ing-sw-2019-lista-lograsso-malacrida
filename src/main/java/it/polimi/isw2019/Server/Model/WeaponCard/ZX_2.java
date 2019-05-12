package it.polimi.isw2019.Server.Model.WeaponCard;

import it.polimi.isw2019.Server.Model.ColorCube;
import it.polimi.isw2019.Server.Model.Exception.ErrorEffectException;
import it.polimi.isw2019.Server.Model.Exception.KillShotException;
import it.polimi.isw2019.Server.Model.Exception.NoEffectException;
import it.polimi.isw2019.Server.Model.Exception.OverKillException;
import it.polimi.isw2019.Server.Model.GameBoard;
import it.polimi.isw2019.Server.Model.Player;

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
    public void firstEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException {

        if(firstDefender != null){

            try {
                firstDefender.sufferDamageOrMark(attacker.getColor(), 1, 2);
            } catch (KillShotException | OverKillException e) {
                e.printStackTrace();
            }

        } else {

            throw new ErrorEffectException();

        }


    }

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException{

        if((firstDefender != null) && (secondDefender != null) && (thirdDefender != null)){

            try {
                firstDefender.sufferDamageOrMark(attacker.getColor(), 0, 1);
            } catch (KillShotException | OverKillException e) {
                e.printStackTrace();
            }

            try {
                secondDefender.sufferDamageOrMark(attacker.getColor(), 0, 1);
            } catch (KillShotException | OverKillException e) {
                e.printStackTrace();
            }

            try {
                thirdDefender.sufferDamageOrMark(attacker.getColor(), 0, 1);
            } catch (KillShotException | OverKillException e) {
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
