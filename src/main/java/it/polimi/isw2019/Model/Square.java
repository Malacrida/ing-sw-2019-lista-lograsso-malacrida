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
    private ArrayList<Player> players;

    Square (int squareID, int squareN, int squareE, int squareO, int squareS, boolean sideN,
            boolean sideE, boolean sideO, boolean sideS, boolean spownpoint){
        this.squareID= squareID;
        this.sideN=sideN;
        this.sideE=sideE;
        this.sideS=sideS;
        this.sideO=sideO;
        this.squareN=squareN;
        this.squareE=squareE;
        this.squareO=squareO;
        this.squareS=squareS;
        this.spownpoint=spownpoint;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int[] squaresAvailable (){
        int i=0;
        int [] squareAvailable = new int[4];
        if (sideN){
            squareAvailable[i]= squareN;
            i++;
        }
        if (sideO){
            squareAvailable[i]= squareO;
            i++;
        }
        if (sideE){
            squareAvailable[i]= squareE;
            i++;
        }
        if (sideS){
            squareAvailable[i]= squareS;
        }
        return squareAvailable;
    }

    public boolean findPlayer (Player player){
        if (players.contains(player)) return true;
        else return false;
    }

    public int numPlayers (){
        return players.size();
    }

    public void insertPlayers (Player player){
        players.add(player);
    }

    public void removePlayers (Player player){
        players.remove(player);
    }







}
