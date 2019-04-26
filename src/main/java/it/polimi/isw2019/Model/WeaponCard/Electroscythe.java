package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.StateCard;

import java.util.ArrayList;

public class Electroscythe extends AbstractWeaponCard {

    public Electroscythe(int id, String name, ColorCube color) {
        super(6, "Electroscythe", ColorCube.BLUE);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT: Deal 1 damage and to every other player on your square.\n");
        this.infoEffect.add("IN REAPER MODE: Deal 2 damage to every other player on your square. You have to pay a BLUE cube and a RED cube.\n");
    }


    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ArrayList<String> getInfoEffect() {
        return infoEffect;
    }

    @Override
    public ArrayList<ColorCube> getRechargecube() {
        return rechargeCube;
    }

    @Override
    public ColorCube getColor() {
        return color;
    }

    @Override
    public StateCard checkState() {
        return stateCard;
    }

    @Override
    public boolean firstEffect() {
        doOneDamage(); //ad ogni giocatore dentro una stanza, quindi serve magari una lista giocatori prenseti in quella stanza e fare un for each player
        return false;
    }

    @Override
    public boolean secondEffect() {
        for (int i = 0; i < 2; i++){ //anche qui ad ogni giocatore dentro una stanza
            doOneDamage();
        }
        return false;
    }

    @Override
    public boolean thirdEffect() {
        return false;
    }
}
