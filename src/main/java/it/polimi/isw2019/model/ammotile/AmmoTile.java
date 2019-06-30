package it.polimi.isw2019.model.ammotile;

import it.polimi.isw2019.model.ColorCube;
import it.polimi.isw2019.model.StateCard;

public class AmmoTile{

    private int id;

    private String firstElement;

    private String secondElement;

    private String thirdElement;

    private ColorCube firstColor;

    private ColorCube secondColor;

    private ColorCube thirdColor;

    private boolean powerUpCard;

    private StateCard checkState = StateCard.DECK;

    private String ammoCardDescription[];

    public void setAmmoCardDescription() {

        ammoCardDescription = new String[5];
        ammoCardDescription[0] = String.valueOf(id);
        ammoCardDescription[1] = firstElement;
        ammoCardDescription[2] = secondElement;
        ammoCardDescription[3] = thirdElement;
        ammoCardDescription[4] = checkState.getStateCardRepresentation();
    }

    public String[] getAmmoCardDescription() {
        return ammoCardDescription;
    }

    public AmmoTile(int id, String firstElement, String secondElement, String thirdElement){

        this.id = id;
        thirdColor = setColor(thirdElement);
        this.secondElement = secondElement;
        secondColor = setColor(secondElement);
        this.firstElement = firstElement;
        if(firstElement.equals("powerup"))
            this.powerUpCard = true;
        else{
            this.powerUpCard = false;
            firstColor = setColor(firstElement);
        }

        setAmmoCardDescription();

    }

    /**
     * Convert String in ColorCube
     * @param color String with name of color
     * @return Color Cube
     */

    public ColorCube setColor(String color){
        switch (color) {

            case "YELLOW":
                return ColorCube.YELLOW;

            case "RED":
                return ColorCube.RED;

            case "BLUE":
                return ColorCube.BLUE;

            default:
                //lancio eccezione
                return null;
        }
    }

    /* Methods */

    /**
     * getter id of ammo tile
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Get first element of ammo tile
     * @return first element
     */

    public String getFirstElement() {
        return firstElement;
    }

    /**
     * Get second element of ammo tile
     * @return second element
     */

    public String getSecondElement() {
        return secondElement;
    }

    /**
     * Get third element of ammo tile
     * @return third element
     */

    public String getThirdElement() {
        return thirdElement;
    }


    /**
     * Get first color of ammo tile
     * @return first color
     */

    public ColorCube getFirstColor() {
        return firstColor;
    }

    /**
     * Get Second color of ammo tile
     * @return second color
     */
    public ColorCube getSecondColor() {
        return secondColor;
    }

    /**
     * Get third color of ammo tile
     * @return third color
     */

    public ColorCube getThirdColor() {
        return thirdColor;
    }

    /**
     * it is a boolean to check if an element is a power up card
     * @return boolean
     */

    public boolean isPowerUpCard(){
        return this.powerUpCard;
    }

    /**
     * state of card
     * @return state
     */

    public StateCard getCheckState(){
        return checkState;
    }

    public void setCheckState(StateCard checkState) {
        this.checkState = checkState;
    }

    public String toString(){
        String rep = " ";
        setAmmoCardDescription();
        for(int i = 0 ; i < ammoCardDescription.length; i ++) {
            rep += ammoCardDescription[i];
            rep += "\n";
        }
        return rep;
    }
}
