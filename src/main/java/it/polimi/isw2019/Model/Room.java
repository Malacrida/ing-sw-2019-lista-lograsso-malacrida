package it.polimi.isw2019.Model;

import it.polimi.isw2019.Model.ColorRoom;
import it.polimi.isw2019.Model.Player;
import it.polimi.isw2019.Model.Square;

import java.util.ArrayList;

public class Room {

    private ColorRoom colorRoom;
    private ArrayList<Square> squares;
    private ArrayList<Player> players;

    public Room(ColorRoom colorRoom){
        this.colorRoom=colorRoom;
    }

    public ColorRoom getColorRoom() {
        return colorRoom;
    }

    public void addSquere (Square square){
        squares.add(square);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }


}
