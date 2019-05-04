package it.polimi.isw2019.Model.PowerUpCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.StateCard;

public class PowerUpCard implements PowerUpCardInterface {

    /* Attributes */

    private int id;

    private String name;

    private String color;

    private String infoEffect;

    private ColorCube colorCard;

    private StateCard checkState = StateCard.DECK;


    /* Costruttore */
    public PowerUpCard (int id, String name, String color, String infoEffect, ColorCube colorCard){

        this.id = id;
        this.name = name;
        this.color = color;
        this.infoEffect = infoEffect;
        this.colorCard = colorCard;

    }

    public PowerUpCard(int id, String name, String color, String infoEffect) {
    }

    /* Methods */

    /* GET*/
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getInfoEffect(){
        return infoEffect;
    }

    public ColorCube getColorCard(){
        return colorCard;
    }

    public StateCard getCheckState() {
        return checkState;
    }

    /* setColor assign the color from enumeration ColorCube to card read String color from db.json*/

    public void setColor(String color){
        switch (color) {

            case "YELLOW":
                colorCard = ColorCube.YELLOW;
                break;

            case "RED":
                colorCard = ColorCube.RED;
                break;

            case "BLUE":
                colorCard = ColorCube.BLUE;
                break;
            default:
                //lancio eccezione
        }
    }

    /*public void effect(String name){
        switch (name) {
            case "Targeting Scope":
                AbstractWeaponCard.doOneDamage();
                break;

            case "Newton":
                AbstractWeaponCard.moveOneSquare();
                break;

            case "Tagback Grenade":
                AbstractWeaponCard.putOneMark();
                break;

            case "Teleporter":
                AbstractWeaponCard.moveOneSquare();
                break;

            default:
                //lancia eccezione
        }
    }*/

}
