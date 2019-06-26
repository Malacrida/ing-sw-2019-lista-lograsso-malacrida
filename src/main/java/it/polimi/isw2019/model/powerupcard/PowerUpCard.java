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

    public boolean isCanBeUsed() {
        return canBeUsed;
    }

    public void setPowerUpDescription(){
        powerUpDescription = name + "\n";
        powerUpDescription += "Id :"+ id + "\n";
        powerUpDescription += infoEffect + "\n";
    }

    public String getPowerUpCardRepresentation(){
        return powerUpDescription;
    }

    public void changeStateCard(StateCard stateCard){
        tmp[4][0] = stateCard.getStateCardRepresentation();
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

    public ColorRoom getColorRoom() {
        return colorRoom;
    }
}
