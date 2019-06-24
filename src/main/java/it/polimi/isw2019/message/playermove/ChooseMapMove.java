package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;
import it.polimi.isw2019.network.rmi.NetworkHandlerVisitorInterface;

public class ChooseMapMove extends PlayerMove{

    private int indexMap;
    private int color;

    public ChooseMapMove(String player, int index, int color) {
        super(player);
        this.indexMap = index;
        this.color = color;
    }

    public int getIndex() {
        return indexMap;
    }

    public int getIndexColor() {
        return color;
    }

    @Override
    public void accept(VisitorController visitorController) {
        visitorController.chooseMap(this);
    }

    @Override
    public void accept(NetworkHandlerVisitorInterface networkHandler) {

    }
}
