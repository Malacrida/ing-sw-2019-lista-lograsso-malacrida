package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.StateCard;

import java.util.ArrayList;

public class TractorBeam extends AbstractWeaponCard {

    public TractorBeam(int id, String name, ColorCube color) {
        super(7, "Tractor Beam", ColorCube.BLUE);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT: Move a target 0,1 or 2 squares to a square you can see, and give it 1 damage.\n");
        this.infoEffect.add("IN PUNISHER MODE: Choose a target 0,1 or 2 moves away from you. Move the target to your square and deal 3 damage to it.\n");
        this.infoEffect.add("NOTES: You can move a target even if you can't see it. The target ends up in a place where you can see and damage it. The moves do not have to be in the same direction.\n");
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
        moveOneSquare(); //muovi un giocatore al più 2 volte
        doOneDamage();
        return false;
    }

    @Override
    public boolean secondEffect() {
        moveOneSquare(); //muovi un giocatore al più 2 volte
        for(int i = 0; i < 3; i++){
            doOneDamage();
        }
        return false;
    }

    @Override
    public boolean thirdEffect() {
        return false;
    }
}
