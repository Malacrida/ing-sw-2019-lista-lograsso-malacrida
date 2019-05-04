package it.polimi.isw2019.Model;

import it.polimi.isw2019.Model.AmmoTile.AmmoTile;
import it.polimi.isw2019.Model.Exception.AmmoTileUseException;
import it.polimi.isw2019.Model.Exception.OutOfRangeException;
import it.polimi.isw2019.Model.WeaponCard.AbstractWeaponCard;
import java.util.ArrayList;
import static it.polimi.isw2019.Model.ColorRoom.*;

public class Arena {

    private static Arena instance; // guardare se fare l'attributo static
    private Square[][] squares= new Square[3][4];

    private ArrayList<Room> rooms= new ArrayList<>();

    Arena(){

    }

    public static Arena instanceArena (){
        if (instance==null) {
            instance = new Arena();
        }
        return instance;
    }

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
        squares[0][2].setWeaponCards(weaponCardsRed);
        squares[1][0].setWeaponCards(weaponCardsBlue);
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

    public void setAmmoTilesOnSquare(ArrayList<AmmoTile> ammoTiles){
        for (int i=0; i<3; i++){
            for (int j=0; j<4; j++){
                if (((i!=0 && j!=2)|| (i!=1 && j!=3)||(i!=2 && j!=3))&& squares[i][j]!=null ){
                    //non capisco perchè mi sbagliata la condizione i!=2
                    // fare un metodo che contiene la condizione
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

    public AmmoTile takeAmmoTileOnSquare (int x, int y) throws AmmoTileUseException {
         try{
             return squares[x][y].takeAmmoTile();
         }
         catch (AmmoTileUseException e){
             throw new AmmoTileUseException();
         }
    }

    //Controllo sul colore scelto dal giocatore dove spownare
    public void spawnPlayer (ColorRoom colorRoomToSpawn, Player player){
        switch (colorRoomToSpawn){
            case RED:
                squares[0][2].addPlayer(player);

                break;
            case BLUE:
                squares[1][0].addPlayer(player);
                break;
            case YELLOW:
                squares[2][3].addPlayer(player);
                break;
        }

    }

    //per spostare di posizione il giocaotre
    public void movePlayer (Player player, int x, int y){
        ArrayList<Square> squaresAviable = squares[player.getX()][player.getY()].squaresAvailable();
        if (squaresAviable.contains(squares[x][y])){
            squares[player.getX()][player.getY()].removePlayers(player);
            squares[x][y].addPlayer(player);


        }
        //else errore di scelta
    }

    //metodo dove dato uno squere ritorno il colore della stanza dov'è




}
