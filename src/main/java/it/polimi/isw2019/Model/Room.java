package it.polimi.isw2019.Model;

import it.polimi.isw2019.Model.ColorRoom;
import it.polimi.isw2019.Model.Player;
import it.polimi.isw2019.Model.Square;

import java.util.ArrayList;

public class Room {

    private ColorRoom colorRoom;
    private ArrayList<Square> squares= new ArrayList<>();
    private ArrayList<Player> players= new ArrayList<>();

    public Room(ColorRoom colorRoom){
        this.colorRoom=colorRoom;
    }

    public ColorRoom getColorRoom() {
        return colorRoom;
    }

    public void addSquere (Square square){
        squares.add(square);
    }

    public void addPlayer (Player player){
        players.add(player);
    }

    public void removePlayer (Player player){
        if (players.contains(player)){
            players.remove(player);
        }
        //else inconsistenzza del player
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }


}
