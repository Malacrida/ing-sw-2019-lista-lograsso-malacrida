package it.polimi.isw2019.model.powerupcard;

import it.polimi.isw2019.model.*;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.InvalidInsert;
import it.polimi.isw2019.model.exception.OutOfBoundsException;

import java.util.ArrayList;

public class PowerUpCard implements PowerUpCardInterface{

    /* Attributes */

    private int id;

    private String name;

    private String infoEffect;

    private ColorCube colorCard;

    private ColorRoom colorRoom;

    private String color;

    private StateCard checkState = StateCard.DECK;

    private String[][] tmp;

    private String powerUpDescription;

    private boolean canBeUsed;


    /* Costruttore */
    public PowerUpCard (int id, String name, String color, String infoEffect/*, ColorCube colorCard*/){

        this.id = id;
        this.name = name;
        if(name.equals("Newton") || name.equals("Teleporter")){
            canBeUsed = true;
        }
        else{
            canBeUsed = false;
        }
        this.color = color;
        setColor(color);
        this.infoEffect = infoEffect;

        setPowerUpDescription();


    }
    /* Methods */

    /**
     * getter of power up card's identifier
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * getter of power up card's name
     * @return name
     */

    public String getName() {
        return name;
    }

    /**
     * getter of power up card's color
     * @return ColorCube color
     */
    public ColorCube getColorCard(){
        return colorCard;
    }

    /**
     * getter of power up card's color (string from db)
     * @return color (String)
     */
    @Override
    public String getColor() {
        return color;
    }

    /**
     * getter of power up card's effect
     * @return info effect
     */
    public String getInfoEffect(){
        return infoEffect;
    }

    /**
     * getter of power up card's state
     * @return state
     */
    public StateCard getCheckState() {
        return checkState;
    }

    /**
     * implementation of power up card's effect
     * @param gameBoard gameboard
     * @param name name of power up
     * @param attacker player who use power up
     * @param defender player who suffer effect of power up
     * @param x first coordinate
     * @param y second coordinate
     */
    @Override
    public void effect(GameBoard gameBoard, String name, Player attacker, Player defender, int x, int y) {
        switch (name) {
            case "Targeting Scope":

                //payonecube
                try {
                    defender.sufferDamageOrMark(attacker.getColor(),1,0);
                } catch (DamageTrackException e) {
                    e.getMessage();
                }
                break;

            case "Newton":
                //change position 2
                break;

            case "Tagback Grenade":
                try {
                    defender.sufferDamageOrMark(attacker.getColor(), 0, 1);
                } catch (DamageTrackException  e) {
                    e.getMessage();
                }
                break;

            case "Teleporter":
                if(gameBoard.isSquareAvailableOnArena(attacker, x, y)){
//
                    gameBoard.changePositionPlayer(attacker, x, y);

                }
                break;

            default:
                //lancia eccezione
        }
    }

    /* setColor assign the color from enumeration ColorCube to card read String color from db.json*/

    /**
     * assign the color from enumeration ColorCube to card read String color from db.json
     * @param color color card of power up
     */

    public void setColor(String color){

       if(color.compareTo("YELLOW")==0) {
           colorCard = ColorCube.YELLOW;
           colorRoom = ColorRoom.YELLOW;
       } else if (color.compareTo("RED")==0) {
           colorCard = ColorCube.RED;
           colorRoom = ColorRoom.RED;
       } else if ( color.compareTo("BLUE")==0) {
           colorCard = ColorCube.BLUE;
           colorRoom = ColorRoom.BLUE;
       }
    }

    /**
     * boolean to check if player can use the power up card
     * @return
     */

    public boolean isCanBeUsed() {
        return canBeUsed;
    }

    /**
     * setter all information of power up cards
     */

    public void setPowerUpDescription(){
        powerUpDescription = name + "\n";
        powerUpDescription += "Id :"+ id + "\n";
        powerUpDescription += infoEffect + "\n";
        powerUpDescription += colorCard.getColorCubeRepresentation();
    }

    public String getPowerUpCardRepresentation(){
        setPowerUpDescription();
        return powerUpDescription;
    }

    public void changeStateCard(StateCard stateCard){
        tmp[4][0] = stateCard.getStateCardRepresentation();
    }


    public ColorRoom getColorRoom() {
        return colorRoom;
    }
}
