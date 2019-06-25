package it.polimi.isw2019.model.powerupcard;

import it.polimi.isw2019.model.*;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.InvalidInsert;
import it.polimi.isw2019.model.exception.OutOfBoundsException;

import java.util.ArrayList;

public class PowerUpCard implements PowerUpCardInterface, InterfacePowerUpCard{

    /* Attributes */

    private int id;

    private String name;

    private String infoEffect;

    private ColorCube colorCard;

    private String color;

    private StateCard checkState = StateCard.DECK;

    private String[][] powerUpDescription;

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
        powerUpDescription = new String[5][];

        powerUpDescription[0][0] = String.valueOf(id);
        powerUpDescription[1][0] = name;
        powerUpDescription[2][0] = colorCard.getColorCubeRepresentation();
        powerUpDescription[3][0] = infoEffect;
        powerUpDescription[4][0] = checkState.getStateCardRepresentation();
    }

    /**
     * change the state of the card
     * @param stateCard state of the card
     */

    public void changeStateCard(StateCard stateCard){
        powerUpDescription[4][0] = stateCard.getStateCardRepresentation();
    }
    /**
     * getter of power up info
     */

    public String[][] getPowerUpDescription() {
        return powerUpDescription;
    }

    @Override
    public String[][] getPowerUpCardRepresentation() {
        return powerUpDescription;
    }

    @Override
    public int getNumberColorPayment() {
        return 0;
    }

    @Override
    public int getIdPowerUpCard() {
        return id;
    }

    @Override
    public InterfacePowerUpCard getPowerUpCard() {
        return this;
    }

    @Override
    public ColorRoom getPowerUpColor() {
        return null;
    }

    @Override
    public String infoEffect() {
        return infoEffect;
    }
}
