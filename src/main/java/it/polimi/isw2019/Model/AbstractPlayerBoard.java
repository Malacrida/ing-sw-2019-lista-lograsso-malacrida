package it.polimi.isw2019.Model;

import java.util.ArrayList;

public abstract class AbstractPlayerBoard {

    protected ColorPlayer color;
    private int playerSkulls=0;
    private int playerBoardID; // no caratterizzazione con il colore
    private boolean usePlayerBoard;
    private boolean frenzy;
    private ArrayList<DamageToken> damageTokens;
    private ArrayList<DamageToken> markTokens;


    AbstractPlayerBoard(ColorPlayer color, boolean frenzy){
        this.color=color;
       // this.playerBoardID=playerBoardID;
        this.frenzy=frenzy;
    }

    public ColorPlayer getColor() {
        return color;
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
