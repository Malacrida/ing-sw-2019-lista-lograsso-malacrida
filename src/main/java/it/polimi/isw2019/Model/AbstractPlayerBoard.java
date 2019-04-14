package it.polimi.isw2019.Model;

import java.util.ArrayList;

public class AbstractPlayerBoard {

    private ColorPlayer color;
    private int playerSkulls=0;
    private int playerBoardID;
    private boolean usePlayerBoard;
    private boolean frenzy;
    private ArrayList<DamageToken> damageTokens;
    private ArrayList<DamageToken> markTokens;

    AbstractPlayerBoard(ColorPlayer color, int playerBoardID, boolean frenzy){
        this.color=color;
        this.playerBoardID=playerBoardID;
        this.frenzy=frenzy;
    }

    public void takeDamangeToken (DamageToken damageToken){
        //sistemare il controllo sul numero di danni!!
        if (damageTokens.size()<12) {
            damageTokens.add(damageToken);
        }
    }

    public ArrayList<DamageToken> getDamageTokens() {
        return damageTokens;
    }

    public int getPlayerSkulls() {
        return playerSkulls;
    }

    public void addPlayerSkulls(){
        playerSkulls++;
    }

}
