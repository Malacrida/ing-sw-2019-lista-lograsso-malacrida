package it.polimi.isw2019.Server.Model.PowerUpCard;

import it.polimi.isw2019.Server.Model.ColorCube;
import it.polimi.isw2019.Server.Model.Exception.KillShotException;
import it.polimi.isw2019.Server.Model.Exception.OverKillException;
import it.polimi.isw2019.Server.Model.GameBoard;
import it.polimi.isw2019.Server.Model.Player;
import it.polimi.isw2019.Server.Model.StateCard;

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
                } catch (KillShotException | OverKillException e) {
                    e.printStackTrace();
                }
                break;

            case "Newton":
                //change position 2
                break;

            case "Tagback Grenade":
                try {
                    defender.sufferDamageOrMark(attacker.getColor(), 0, 1);
                } catch (KillShotException | OverKillException e) {
                    e.printStackTrace();
                }
                break;

            case "Teleporter":
                if(gameBoard.changePositionPlayer(attacker, x, y, true)){

                    System.out.println("In attesa");

                }
                break;

            default:
                //lancia eccezione
        }
    }

}
