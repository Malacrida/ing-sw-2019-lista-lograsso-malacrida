package it.polimi.isw2019.Server.Model.WeaponCard;

import it.polimi.isw2019.Server.Model.ColorCube;
import it.polimi.isw2019.Server.Model.Exception.ErrorEffectException;
import it.polimi.isw2019.Server.Model.Exception.KillShotException;
import it.polimi.isw2019.Server.Model.Exception.NoEffectException;
import it.polimi.isw2019.Server.Model.Exception.OverKillException;
import it.polimi.isw2019.Server.Model.GameBoard;
import it.polimi.isw2019.Server.Model.Player;

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
    public void firstEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException {

        /*MUOVI DI DUE*/

        if(firstDefender != null){

            try {
                firstDefender.sufferDamageOrMark(attacker.getColor(), 1, 0);
            } catch (KillShotException | OverKillException e) {
                e.printStackTrace();
            }

        } else {

            throw new ErrorEffectException();

        }

    }

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException {

        /*MUOVI DI DUE*/

        if(secondDefender != null){

            try {
                secondDefender.sufferDamageOrMark(attacker.getColor(), 3, 0);
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
