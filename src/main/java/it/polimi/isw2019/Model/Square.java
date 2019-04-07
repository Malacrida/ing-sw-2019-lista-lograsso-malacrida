package it.polimi.isw2019.Model;

import java.util.ArrayList;

public abstract class Square {
    private boolean sideN;
    private boolean sideE;
    private boolean sideS;
    private boolean sideO;
    private int squareN;
    private int squareE;
    private int squareS;
    private int squareO;
    private int squareID;
    private boolean spownpoint;
    private ArrayList<Player> player;

    Square (int squareID, int squareN, int squareE, int squareO, int squareS, boolean sideN,
            boolean sideE, boolean sideO, boolean sideS){
        this.squareID= squareID;
        this.sideN=sideN;
        this.sideE=sideE;
        this.sideS=sideS;
        this.sideO=sideO;
        this.squareN=squareN;
        this.squareE=squareE;
        this.squareO=squareO;
        this.squareS=squareS;
    }

    public ArrayList<Player> getPlayer() {
        return player;
    }
}
