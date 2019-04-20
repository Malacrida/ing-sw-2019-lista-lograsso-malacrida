package it.polimi.isw2019.Model.AmmoTile;

import it.polimi.isw2019.Model.StateCard;

public class AmmoTile implements AmmoTIleInterface {

    private int id;

    private String firstElement;

    private String secondElement;

    private String thirdElement;

    private StateCard checkState = StateCard.DECK;

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
