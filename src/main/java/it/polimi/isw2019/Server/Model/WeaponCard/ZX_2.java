package it.polimi.isw2019.Server.Model.WeaponCard;

import it.polimi.isw2019.Server.Model.ColorCube;
import it.polimi.isw2019.Server.Model.Exception.ErrorEffectException;
import it.polimi.isw2019.Server.Model.Exception.NoEffectException;
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
    }

    @Override
    public void firstEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException {

        if(firstDefender != null){

          //  firstDefender.sufferDamage(attacker.getColor(), 1, 2);

        } else {

            throw new ErrorEffectException();

        }


    }

    @Override
    public void secondEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException{

        if(firstDefender != null){

         //   firstDefender.sufferDamage(attacker.getColor(), 0, 1);

          //  secondDefender.sufferDamage(attacker.getColor(), 0, 1);

        //    thirdDefender.sufferDamage(attacker.getColor(), 0, 1);

        } else {

            throw new ErrorEffectException();
        }



    }

    @Override
    public void thirdEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException {
        throw new NoEffectException();

    }
}
