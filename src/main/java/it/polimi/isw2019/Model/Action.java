package it.polimi.isw2019.Model;

import java.util.ArrayList;

public abstract class Action {
    private int idMove ;
    private GameBoard gameboard;
    private Player player;
    //maybe it's not necessary
    private ArrayList<Object> actionDate;

    public int getIdMove(){
        return this.idMove;
    }

    public void run(ArrayList<Object> actionDate){

    }

    public void grab(ArrayList<Object> actionDate){

    }

    public void shoot(ArrayList<Object> actionDate){

    }

    public void reload(ArrayList<Object> actionDate){

    }

    public abstract void execute();


}