package it.polimi.isw2019.Server.Model;

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

    public void addSquare(Square square){
        squares.add(square);
    }


    public int sizeSquare (){
        return squares.size();
    }

    public boolean containsSquare (Square square){
        return squares.contains(square);
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
