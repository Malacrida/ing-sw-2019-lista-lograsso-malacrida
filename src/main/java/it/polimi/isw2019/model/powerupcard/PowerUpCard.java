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

    /* GET*/
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ColorCube getColorCard(){
        return colorCard;
    }

    @Override
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

    public boolean isCanBeUsed() {
        return canBeUsed;
    }

    public void setPowerUpDescription(){
        powerUpDescription = new String[5][];

        powerUpDescription[0][0] = String.valueOf(id);
        powerUpDescription[1][0] = name;
        powerUpDescription[2][0] = colorCard.getColorCubeRepresentation();
        powerUpDescription[3][0] = infoEffect;
        powerUpDescription[4][0] = checkState.getStateCardRepresentation();
    }

    public void changeStateCard(StateCard stateCard){
        powerUpDescription[4][0] = stateCard.getStateCardRepresentation();
    }

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
