package it.polimi.isw2019.Server.Model.WeaponCard;

import it.polimi.isw2019.Server.Model.ColorCube;
import it.polimi.isw2019.Server.Model.Exception.ErrorEffectException;
import it.polimi.isw2019.Server.Model.Exception.KillShotException;
import it.polimi.isw2019.Server.Model.Exception.OverKillException;
import it.polimi.isw2019.Server.Model.GameBoard;
import it.polimi.isw2019.Server.Model.Player;

import java.util.ArrayList;

public class Thor extends AbstractWeaponCard{

    public Thor() {
        super(3, "T.H.O.R.", ColorCube.BLUE,3);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT: Deal 2 damage to 1 target you can see.\n");
        this.infoEffect.add("WITH CHAIN REACTION: Deal 1 damage to a second target that your first target can see. You have to pay a BLUE cube\n");
        this.infoEffect.add("WITH HIGH REACTION: Deal 2 damage to a third target that your second target can see. You have to pay a BLUE cube\n" +
                "You cannot use this effect unless you first use the chain reaction");
        this.infoEffect.add("NOTES: This card constrains the order in which you can use its effects." +
                "(Most cards don't.)" +
                "Also note that each target must be a different player\n");
        this.rechargeCube[0] = 1;
        this.rechargeCube[1] = 0;
        this.rechargeCube[2] = 1;
    }

    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException {

        if (firstDefender != null) {

            try {
                firstDefender.sufferDamageOrMark(attacker.getColor(), 2, 0);
            } catch (KillShotException | OverKillException e) {
                e.printStackTrace();
            }
            this.firstIsValid = true;

        } else {

            throw new ErrorEffectException();
        }
    }

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException{

        if (this.firstIsValid){

            try {
                secondDefender.sufferDamageOrMark(attacker.getColor(), 1, 0);
            } catch (KillShotException | OverKillException e) {
                e.printStackTrace();
            }
            this.secondIsValid = true;

        } else {

            throw new ErrorEffectException();

        }
    }

    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException {

        if(this.secondIsValid) {

            try {
                thirdDefender.sufferDamageOrMark(attacker.getColor(), 2, 0);
            } catch (KillShotException | OverKillException e) {
                e.printStackTrace();
            }

        } else {

            throw new ErrorEffectException();

        }
    }

}
