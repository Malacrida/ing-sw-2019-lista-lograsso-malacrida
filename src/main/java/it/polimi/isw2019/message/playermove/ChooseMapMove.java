package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;

public class ChooseMapMove extends PlayerMove{

    private int indexMap;
    private String color;


    public ChooseMapMove(String player, int index, String color) {
        super(player);
        this.indexMap = index;
        this.color = color;
    }

    public int getIndex() {
        return indexMap;
    }

    public String getIndexColor() {
        return color;
    }

    @Override
    public void accept(VisitorController visitorController) {
        visitorController.chooseMap(this);
    }
}
