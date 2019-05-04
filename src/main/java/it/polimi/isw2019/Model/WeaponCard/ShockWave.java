package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.Player;
import it.polimi.isw2019.Model.Square;

import java.util.ArrayList;

public class ShockWave extends AbstractWeaponCard {

    public ShockWave(int id, String name, ColorCube color) {
        super(20, "Shock Wave", ColorCube.YELLOW);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT : choose up to 3 targets on different squares, each exactly 1 move away deal 1 damage to each target.");
        this.infoEffect.add("TSUNAMI MODE : Deal 1 damage to all targets that are exactly 1 move away\n");
    }

    @Override
    public boolean firstEffect(Player attacker, Square firstAttackSquare, Player firstDefender, Square secondAttackSquare, Player secondDefender, Square thirdAttackSquare, Player thirdDefender) {

        /*CONTROLLI SU STANZE 1, 2, 3 */

        firstDefender.sufferDamage(attacker.getColor(), 1, 0);

        secondDefender.sufferDamage(attacker.getColor(), 1, 0);

        thirdDefender.sufferDamage(attacker.getColor(),1,0);


        return false;
    }

    @Override
    public boolean secondEffect(Player attacker, Square firstAttackSquare, Player firstDefender, Square secondAttackSquare, Player secondDefender, Square thirdAttackSquare, Player thirdDefender) {

        ArrayList<Player> firstPlayerList = firstAttackSquare.getPlayers();
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

        }

        return false;
    }

    @Override
    public boolean thirdEffect(Player attacker, Square firstAttackSquare, Player firstDefender, Square secondAttackSquare, Player secondDefender, Square thirdAttackSquare, Player thirdDefender) {
        return false;
    }


}
