package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.StateCard;

import java.util.ArrayList;

public class RocketLauncher extends  AbstractWeaponCard {

    public RocketLauncher(int id, String name, ColorCube color) {
        super(14, "Rocket Launcher", ColorCube.RED);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT : basic effect: Deal 2 damage to 1 target you can see that is not on your" +
                "square. Then you may move the target 1 square.");
        this.infoEffect.add("WITH ROCKET JUMP:Move 1 or 2 squares. This effect can be used either" +
                "before or after the basic effect");
        this.infoEffect.add("WITH FREGMETING WARHEAD: During the basic effect, deal 1 damage to" +
                "every player on your target's original square – including the target," +
                "even if you move it.");
        this.infoEffect.add(" NOTE :If you use the rocket jump before the basic effect, you consider" +
                "only your new square when determining if a target is legal. You can" +
                "even move off a square so you can shoot someone on it. If you use the" +
                "fragmenting warhead, you deal damage to everyone on the target's" +
                "square before you move the target – your target will take 3 damage total. ");
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
        for(int i = 0; i < 2; i++){
            doOneDamage();
        }
        moveOneSquare();
        return false;
    }

    @Override
    public boolean secondEffect() {
        for(int i = 0; i < 2; i++){
            moveOneSquare();
        }
        return false;
    }

    @Override
    public boolean thirdEffect() {
        doOneDamage();
        return false;
    }
}
