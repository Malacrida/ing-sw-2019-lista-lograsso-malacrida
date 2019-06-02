package it.polimi.isw2019.model.ammotile;

import it.polimi.isw2019.model.StateCard;

public class AmmoTile implements AmmoTIleInterface {

    private int id;

    private String firstElement;

    private String secondElement;

    private String thirdElement;

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
        this.firstElement = firstElement;
        this.secondElement = secondElement;
        this.thirdElement = thirdElement;

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

    public StateCard getCheckState(){
        return checkState;
    }

}
