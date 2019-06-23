package it.polimi.isw2019.model.ammotile;

import it.polimi.isw2019.model.ColorCube;
import it.polimi.isw2019.model.StateCard;

public class AmmoTile implements AmmoTIleInterface, InterfaceAmmoTile {

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
        //thirdColor = setColor(thirdElement);
        this.secondElement = secondElement;
        //secondColor = setColor(secondElement);
        this.firstElement = firstElement;
       /* if(firstElement.equals("powerup"))
            this.powerUpCard = true;
        else{
            this.powerUpCard = false;
            firstColor = setColor(firstElement);
        }*/

    }

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
    public int getId() {
        return id;
    }

    public String getFirstElement() {
        return firstElement;
    }

    public String getSecondElement() {
        return secondElement;
    }

    public String getThirdElement() {
        return thirdElement;
    }

    public ColorCube getFirstColor() {
        return firstColor;
    }

    public ColorCube getSecondColor() {
        return secondColor;
    }

    public ColorCube getThirdColor() {
        return thirdColor;
    }

    public boolean isPowerUpCard(){
        return this.powerUpCard;
    }

    public StateCard getCheckState(){
        return checkState;
    }


    @Override
    public InterfaceAmmoTile getAmmoTileInterface() {
        return this;
    }

    @Override
    public String[] getAmmoTileDescription() {
        return ammoCardDescription;
    }
}
