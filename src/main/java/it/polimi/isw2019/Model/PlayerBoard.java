package it.polimi.isw2019.Model;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerBoard extends AbstractPlayerBoard{

    private HashMap<Integer, ColorPlayer> damagePlayer= new HashMap<>();

    PlayerBoard(ColorPlayer color) {
        super(color,false);
    }

    @Override
    public ColorPlayer getColor() {
        return super.getColor();
    }

    @Override
    public void scorePlayer() {
        ArrayList<ColorPlayer> colorDamage = listOfColorDamage();

        for (int i=0; i<colorDamage.size(); i++){
            damagePlayer.put(numOfDamagesOfOneColor(colorDamage.get(i)), colorDamage.get(i));
        }
    }







}
