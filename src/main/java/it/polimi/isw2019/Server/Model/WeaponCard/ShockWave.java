package it.polimi.isw2019.Server.Model.WeaponCard;

import it.polimi.isw2019.Server.Model.ColorCube;
import it.polimi.isw2019.Server.Model.Exception.ErrorEffectException;
import it.polimi.isw2019.Server.Model.Exception.NoEffectException;
import it.polimi.isw2019.Server.Model.Player;

import java.util.ArrayList;

public class ShockWave extends AbstractWeaponCard {

    public ShockWave() {
        super(20, "Shock Wave", ColorCube.YELLOW, 1);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT : choose up to 3 targets on different squares, each exactly 1 move away deal 1 damage to each target.");
        this.infoEffect.add("TSUNAMI MODE : Deal 1 damage to all targets that are exactly 1 move away\n");
    }

    @Override
    public void firstEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException {

        if(firstDefender != null){

            /*Mettere controlli su stanze first, second, third*/
         //   firstDefender.sufferDamage(attacker.getColor(), 1, 0);
         //   secondDefender.sufferDamage(attacker.getColor(), 1, 0);
        //    thirdDefender.sufferDamage(attacker.getColor(),1,0);

        } else {

            throw new ErrorEffectException();

        }
    }

    @Override
    public void secondEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException {



        /*ArrayList<Player> firstPlayerList = firstAttackSquare.getPlayers();
        ArrayList<Player> secondPlayerList = secondAttackSquare.getPlayers();
        ArrayList<Player> thirdPlayerList = thirdAttackSquare.getPlayers();

        for(int i = 0; i < firstPlayerList.size(); i++){

            firstPlayerList.get(i).sufferDamage(attacker.getColor(), 1, 0);

        }

        for(int i = 0; i < secondPlayerList.size(); i++){

            secondPlayerList.get(i).sufferDamage(attacker.getColor(), 1, 0);

        }

        for(int i = 0; i < thirdPlayerList.size(); i++){

            thirdPlayerList.get(i).sufferDamage(attacker.getColor(), 1, 0);

        }*/
    }

    @Override
    public void thirdEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException {

        throw new NoEffectException();

    }


}
