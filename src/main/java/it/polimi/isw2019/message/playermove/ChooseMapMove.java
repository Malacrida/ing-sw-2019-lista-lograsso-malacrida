package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;
import it.polimi.isw2019.network.rmi.NetworkHandlerVisitorInterface;

public class ChooseMapMove extends PlayerMove{

    private int indexMap;
    private int color;
    private int mod;
    private int terminator;

    public ChooseMapMove(String player, int index, int color, int mod, int terminator) {
        super(player);
        this.indexMap = index;
        this.color = color;
        this.mod = mod;
        this.terminator = terminator;
    }

    public int getIndex() {
        return indexMap;
    }

    public int getIndexColor() {
        return color;
    }

    public int getTerminator() {
        return terminator;
    }

    public int getMod() {
        return mod;
    }

    @Override
    public void accept(VisitorController visitorController) {
        visitorController.chooseMap(this);
    }

    @Override
    public void accept(NetworkHandlerVisitorInterface networkHandler) {
        networkHandler.sendChooseMap(this);
    }
}
