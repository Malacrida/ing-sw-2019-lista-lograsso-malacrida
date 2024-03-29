package it.polimi.isw2019.model;

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

    /**
     * add player in a room
     * @param player must be added
     */

    public void addPlayer (Player player){
        if (!players.contains(player))
        players.add(player);
    }

    /**
     * remove that player from a room
     * @param player
     */

    public void removePlayer (Player player){
        if (players.contains(player)){
            players.remove(player);
        }
    }

    /**
     * getter of players in that room
     * @return list of players
     */

    public ArrayList<Player> getPlayers() {
        return players;
    }




}
