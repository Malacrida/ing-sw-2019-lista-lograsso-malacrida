package it.polimi.isw2019.Model;

import java.util.ArrayList;

public abstract class Square {
    private Square squareN;
    private Square squareE;
    private Square squareS;
    private Square squareO;
    private boolean spownpoint;
    private ArrayList<Player> players;

    Square ( Square squareN, Square squareE, Square squareO, Square squareS, boolean spownpoint){
        this.squareN=squareN;
        this.squareE=squareE;
        this.squareO=squareO;
        this.squareS=squareS;
        this.spownpoint=spownpoint;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Square> squaresAvailable (){
        ArrayList<Square> squareAvailable = new ArrayList<Square>();
        if (squareN!=null){
            squareAvailable.add(squareN);
        }
        if (squareE!=null){
            squareAvailable.add(squareE);
        }
        if (squareS!=null){
            squareAvailable.add(squareS);
        }
        if (squareO!=null){
            squareAvailable.add(squareO);
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
