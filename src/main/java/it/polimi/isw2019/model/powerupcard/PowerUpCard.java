package it.polimi.isw2019.model.powerupcard;

import it.polimi.isw2019.model.ColorCube;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.GameBoard;
import it.polimi.isw2019.model.Player;
import it.polimi.isw2019.model.StateCard;

public class PowerUpCard implements PowerUpCardInterface {

    /* Attributes */

    private int id;

    private String name;

    private String color;

    private String infoEffect;

    private ColorCube colorCard;

    private StateCard checkState = StateCard.DECK;

    private String[][] powerUpDescription;

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

    public StateCard getCheckState() {
        return checkState;
    }

    @Override
    public void effect() {

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

    public void effect(GameBoard gameBoard, String name, Player attacker, Player defender, int x, int y){
        switch (name) {
            case "Targeting Scope":

                //payonecube
                try {
                    defender.sufferDamageOrMark(attacker.getColor(),1,0);
                } catch (DamageTrackException e) {
                    e.printStackTrace();
                }
                break;

            case "Newton":
                //change position 2
                break;

            case "Tagback Grenade":
                try {
                    defender.sufferDamageOrMark(attacker.getColor(), 0, 1);
                } catch (DamageTrackException  e) {
                    e.printStackTrace();
                }
                break;

            case "Teleporter":
                if(gameBoard.isSquareAvailableOnArena(attacker, x, y)){
//
                    System.out.println("In attesa");

                }
                break;

            default:
                //lancia eccezione
        }
    }

}
