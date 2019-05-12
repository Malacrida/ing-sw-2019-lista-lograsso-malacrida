package it.polimi.isw2019.Server.Model;

import it.polimi.isw2019.Server.Model.AmmoTile.AmmoTile;
import it.polimi.isw2019.Server.Model.Exception.AmmoTileUseException;
import it.polimi.isw2019.Server.Model.Exception.OutOfBoundsException;
import it.polimi.isw2019.Server.Model.WeaponCard.AbstractWeaponCard;

import java.util.ArrayList;

public class Arena {

    private static Arena instance; // guardare se fare l'attributo static
    private Square[][] squares= new Square[3][4];

    private ArrayList<Room> rooms= new ArrayList<>();

     Arena(){

    }


    public void chooseArena (int numArena) throws OutOfBoundsException{
        try {
            squares= CreateArena.chooseMap(numArena);
            rooms= CreateArena.chooseRoom(numArena);
        }
        catch (OutOfBoundsException e){
            throw new OutOfBoundsException("Number out of possible bounds");
        }

    }

    //I colori indicano i punti di spawn
    public void setWeaponsCardOnSquareSpawn (ArrayList<AbstractWeaponCard> weaponCardsRed, ArrayList<AbstractWeaponCard> weaponCardsBlue, ArrayList<AbstractWeaponCard> weaponCardsYellow){
            squares[1][0].setWeaponCards(weaponCardsRed);
            squares[0][2].setWeaponCards(weaponCardsBlue);
            squares[2][3].setWeaponCards(weaponCardsYellow);
    }


    public void placeAnotherWeaponCardsOnSquareSpawn (AbstractWeaponCard weaponCard, int x, int y){
        squares[x][y].putNewWeaponCard(weaponCard);
    }

    public boolean containsWeaponOnSpawnSquare (int x, int y, AbstractWeaponCard weaponCard){
        return squares[x][y].containsWeapon(weaponCard);
    }

    public void takeWeaponCardsOnSquareSpawn (AbstractWeaponCard weaponCard, int x, int y){
        squares[x][y].takeWeapon(weaponCard);
    }

    public ArrayList<AbstractWeaponCard> getWeaponCardsOnSquares (int x, int y){
        return squares[x][y].getWeaponCards();
    }

    public void setAmmoTilesOnSquare(ArrayList<AmmoTile> ammoTiles){
        for (int i=0; i<3; i++){
            for (int j=0; j<4; j++){
                if ((!((i==1 && j==0) || (i==0 && j==2)|| (i==2 && j==3)))&& squares[i][j]!=null){
                    squares[i][j].setAmmoTile(ammoTiles.get(0));
                    ammoTiles.remove(0);
                    //cambio di stato
                }
            }

        }
    }

    public void placeAnotherAmmoTileOnSquare (AmmoTile ammoTile, int x, int y){
        squares[x][y].setAmmoTile(ammoTile);
    }
    public AmmoTile getAmmoTileOnSquare (int x, int y){
        return squares[x][y].getAmmoTile();
    }

    public AmmoTile takeAmmoTileOnSquare (int x, int y) throws AmmoTileUseException {
         try{
             return squares[x][y].takeAmmoTile();
         }
         catch (AmmoTileUseException e){
             throw new AmmoTileUseException();
         }
    }
    public boolean useAmmoTileOnSquare (int x, int y){
        return squares[x][y].isCanUseAmmo();
    }

    public ArrayList<Player> playersInOneRoom (ColorRoom colorRoom, Player player){
         ArrayList <Player> playersInRoom= new ArrayList<>();
         for (int i=0 ; i<rooms.size(); i++){
             if (rooms.get(i).getColorRoom()==colorRoom){
                 playersInRoom= rooms.get(i).getPlayers();
             }
         }
         if (playersInRoom.contains(player)){
             playersInRoom.remove(player);
         }
         return playersInRoom;
    }

    public ArrayList<Player> playersInOneSquareOnArena (int x, int y,Player player){
        ArrayList<Player> players = squares[x][y].getPlayers();
        if(players.contains(player) ){
            players.remove(player);
        }
        return players;
    }

    public ArrayList<Player> playerWhoSeeOnArena (int x, int y, Player player){
        ArrayList <Player> players= new ArrayList<>();
        players.clear();
        players=squares[x][y].getPlayers();

        //Orizzontale verso destra
        for (int i=0; i<2; i++){
            if(y+i+1<4) {
                if (squares[x][y + i].squaresAvailable().contains(squares[x][y + i + 1]))
                    players.addAll(squares[x][y + i + 1].getPlayers());
            }
        }
        //Orizzontale verso sinistra
        for (int i=0; i<3; i++){
            if(y-i-1>=0) {
                if (squares[x][y - i].squaresAvailable().contains(squares[x][y - i - 1]))
                    players.addAll(squares[x][y - i - 1].getPlayers());
            }
            else break;
        }
        //Verticale verso l'alto
        for (int i=0; i<2; i++){
            if(x-i>0) {
                if (squares[x - i][y].squaresAvailable().contains(squares[x - i - 1][y]))
                    players.addAll(squares[x - i - 1][y].getPlayers());
            }
            else break;
        }
        //Verticale verso il basso
        for (int i=0; i<2; i++){
            if(x+i+1<3) {
                if (squares[x + i][y].squaresAvailable().contains(squares[x + i + 1][y]))
                    players.addAll(squares[x + i + 1][y].getPlayers());
            }
        }

        if(player.getColorRoom()==ColorRoom.YELLOW){
            ArrayList <Player> playersInRoom = playersInOneRoom(ColorRoom.YELLOW,player);
            for (int i=0; i<playersInRoom.size(); i++){
                if(!players.contains(playersInRoom.get(i))){
                    players.add(playersInRoom.get(i));
                }
            }
        }

        if(players.contains(player) ){
            players.remove(player);
        }
       return players;
    }



    //Controllo sul colore scelto dal giocatore dove spownare
    public void spawnPlayer (ColorRoom colorRoomToSpawn, Player player){
        switch (colorRoomToSpawn){
            case RED:
                squares[1][0].addPlayer(player);
                break;
            case BLUE:
                squares[0][2].addPlayer(player);
                break;
            case YELLOW:
                squares[2][3].addPlayer(player);
                break;
        }
        for (int i=0; i<rooms.size();i++){
            if(rooms.get(i).getColorRoom()==colorRoomToSpawn){
                rooms.get(i).addPlayer(player);
            }
        }

    }

    //per spostare di posizione il giocaotre
    public boolean movePlayer (Player player, int x, int y, boolean teleporter){

        if(isPlayerChangeRoom(player, x,y)){
            playerChangeRoom(player,x,y);
        }

        if (!teleporter) {
            ArrayList<Square> squaresAvailable = squares[player.getX()][player.getY()].squaresAvailable();
            if (squaresAvailable.contains(squares[x][y])) {
                squares[player.getX()][player.getY()].removePlayers(player);
                squares[x][y].addPlayer(player);
                return true;
            } else return false;
        }
        else{
            squares[player.getX()][player.getY()].removePlayers(player);
            squares[x][y].addPlayer(player);
            return true;
        }
    }

    //per vedere se il player cambia stanza
    public boolean isPlayerChangeRoom (Player player, int x, int y){
         for(int i=0; i<rooms.size(); i++){
             if(rooms.get(i).getColorRoom()== player.getColorRoom() && rooms.get(i).containsSquare(squares[x][y])) return false;
         }
         return true;
    }

    public void playerChangeRoom (Player player, int x, int y){
        for(int i=0; i<rooms.size(); i++){
            if(rooms.get(i).getColorRoom()== player.getColorRoom()){
                rooms.get(i).removePlayer(player);
            }
            if(rooms.get(i).containsSquare(squares[x][y])){
                rooms.get(i).addPlayer(player);
            }
        }
    }

    //metodo dove dato uno squere ritorno il colore della stanza dov'è




}
