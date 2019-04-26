package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.StateCard;

import java.util.ArrayList;

public class PowerGlove extends AbstractWeaponCard {

    public PowerGlove(int id, String name, ColorCube color) {
        super(19, "Power Glove", ColorCube.YELLOW);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC MODE: choose 1 target on any square exactly 1 move away. Move onto that square and give the target 1 damage and 2 marks");
        this.infoEffect.add("IN ROCKET FIST MODE : Choose a square exactly 1 move away. " +
                "Move onto that square you may deal 2 damage to 1 target there if you want, you may move 1 more square in that same direction " +
                "(but only if it is a legal move). You may deal 2 damage to 1 target there, as well");
        this.infoEffect.add("NOTE : In rocket fist mode, you're flying squares in a straight line, punching person per square ");
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
        moveOneSquare();
        doOneDamage();
        for (int i = 0; i < 2; i++){
            putOneMark();
        }
        return false;
    }

    @Override
    public boolean secondEffect() {
        for (int i = 0; i < 2; i++){
            moveOneSquare();
            for (int j = 0; j < 2; j++){
                doOneDamage();
            }
        }
        return false;
    }

    @Override
    public boolean thirdEffect() {
        return false;
    }
}
