package it.polimi.isw2019.Model.Events;

import it.polimi.isw2019.Model.Player;
import it.polimi.isw2019.Model.GameBoard;
public class MoveMessage {

    private Player player;
    private GameBoard gameboard;

    public MoveMessage(Player player, GameBoard gameboard){
        this.player = player;
        this.gameboard = gameboard;
    }

    public Player getPlayer(){
        return this.player.getPlayer();
    }

    /*
    public GameBoard getGameBoard(){
       restituisce l'immagine della gameboard
    }*/
}
