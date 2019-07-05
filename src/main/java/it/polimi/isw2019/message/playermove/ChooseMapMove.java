package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;
import it.polimi.isw2019.network.rmi.NetworkHandlerVisitorInterface;

/**
 * player move of map choosen by the player
 */

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

    /**
     * getter of index map
     * @return index map
     */

    public int getIndex() {
        return indexMap;
    }

    /**
     * getter of index color
     * @return index color
     */

    public int getIndexColor() {
        return color;
    }

    /**
     * getter of terminator
     * @return terminator
     */

    public int getTerminator() {
        return terminator;
    }

    /**
     * getter of game mode
     * @return game mode
     */

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
