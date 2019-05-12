package it.polimi.isw2019.Server.Model.WeaponCard;

import it.polimi.isw2019.Server.Model.ColorCube;
import it.polimi.isw2019.Server.Model.Exception.ErrorEffectException;
import it.polimi.isw2019.Server.Model.Exception.KillShotException;
import it.polimi.isw2019.Server.Model.Exception.OverKillException;
import it.polimi.isw2019.Server.Model.GameBoard;
import it.polimi.isw2019.Server.Model.Player;

import java.util.ArrayList;

public class CyberBlade extends AbstractWeaponCard {

    public CyberBlade(){
        super(16, "Cyber Blade", ColorCube.YELLOW, 3);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT : Deal 2 damage to 1 target on your square.\n");
        this.infoEffect.add("WITH SHADOWSTEP: move 1 square before or after the basic effect");
        this.infoEffect.add("WITH SLICE AND DICE : to a different target on your square the shadowstep may be used before or after this effect.");
        this.infoEffect.add("NOTE : Combining all effects allows you to move onto a square and\n" +
                "whack 2 people; or whack somebody, move, and whack somebody else;\n" +
                "or whack 2 people and then move.   ");
        this.rechargeCube[0] = 1;
        this.rechargeCube[1] = 1;
        this.rechargeCube[2] = 0;
    }

    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException, KillShotException, OverKillException {

        if (sameSquare(attacker, firstDefender)){

            try{

                firstDefender.sufferDamageOrMark(attacker.getColor(),2,0);

            } catch (KillShotException e){
                throw new KillShotException(firstDefender.getColor());
            }

        }

        else {

            throw new ErrorEffectException();

        }
    }

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException{

        if(gameBoard.changePositionPlayer(attacker, x1, y1, false)){

            System.out.println("IN ATTESA DI CHANGEPOSITIONE");

        }
        else {

            throw new ErrorEffectException();

        }
    }

    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException{


        if (sameSquare(attacker, secondDefender)){
            try {
                secondDefender.sufferDamageOrMark(attacker.getColor(),2,0);
            }catch (OverKillException | KillShotException e){
                e.printStackTrace();
            }

        }

        else {
            throw new ErrorEffectException();
        }
    }

}
