package it.polimi.isw2019.Server.Model;

import it.polimi.isw2019.Server.Model.AmmoTile.AmmoTile;
import it.polimi.isw2019.Server.Model.Exception.AmmoTileUseException;
import it.polimi.isw2019.Server.Model.Exception.OutOfRangeException;
import it.polimi.isw2019.Server.Model.WeaponCard.AbstractWeaponCard;

import java.util.ArrayList;

public class Arena {

    private static Arena instance; // guardare se fare l'attributo static
    private Square[][] squares= new Square[3][4];

    private ArrayList<Room> rooms= new ArrayList<>();

     Arena(){

    }

    /*public static Arena instanceArena () throws InstanceArenaException {
        if (instance==null) {
            instance = new Arena();
            return instance;
        }
        else throw new InstanceArenaException ();
    }
*/

    public void chooseArena (int numArena) throws OutOfRangeException{
        try {
            squares= CreateArena.chooseMap(numArena);
            rooms= CreateArena.chooseRoom(numArena);
        }
        catch (OutOfRangeException e){
            throw new OutOfRangeException();
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

    public ArrayList<Player> playersInOneSquareOnArena (int x, int y,Player player){
        ArrayList<Player> players = squares[x][y].getPlayers();
        if(players.contains(player)){
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

    }

    //per spostare di posizione il giocaotre
    public boolean movePlayer (Player player, int x, int y){
        ArrayList<Square> squaresAvailable = squares[player.getX()][player.getY()].squaresAvailable();
        if (squaresAvailable.contains(squares[x][y])){
            squares[player.getX()][player.getY()].removePlayers(player);
            squares[x][y].addPlayer(player);
            return true;
        }
        else return false;
    }

    //metodo dove dato uno squere ritorno il colore della stanza dov'è




}
