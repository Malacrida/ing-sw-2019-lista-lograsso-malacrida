package it.polimi.isw2019.model;

import it.polimi.isw2019.model.ammotile.AmmoTile;
import it.polimi.isw2019.model.exception.AmmoTileUseException;
import it.polimi.isw2019.model.weaponcard.AbstractWeaponCard;

import java.util.ArrayList;


public abstract class Square{
    private Square squareN;
    private Square squareE;
    private Square squareS;
    private Square squareW;
    private boolean spawnPoint;
    protected ArrayList<Player> players= new ArrayList<>();
    private String squareRepresentation;

    /**
     *
     * @param spawnPoint set the type of squares
     */
    Square (boolean spawnPoint){
        this.spawnPoint = spawnPoint;
    }


    /**
     *
     * @param squareN reference to the north cell
     * @param squareE reference to the east cell
     * @param squareS reference to the south cell
     * @param squareW reference to the west cell
     */
    public void setSquareAdjacent (Square squareN, Square squareE, Square squareS, Square squareW){
        this.squareN=squareN;
        this.squareE=squareE;
        this.squareW =squareW;
        this.squareS=squareS;

    }

    /**
     *
     * @return players who are in this squares
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     *
     * @return squares where is possible to go
     */
    public ArrayList<Square> squaresAvailable (){
        ArrayList<Square> squareAvailable = new ArrayList<>();
        if (squareN!=null){
            squareAvailable.add(squareN);
        }
        if (squareE!=null){
            squareAvailable.add(squareE);
        }
        if (squareS!=null){
            squareAvailable.add(squareS);
        }
        if (squareW !=null){
            squareAvailable.add(squareW);
        }

        return squareAvailable;
    }

    /**
     *
     * @param player that you can find in the squares
     * @return true if player is in the squares else false
     */
    public boolean findPlayer (Player player){
        return players.contains(player);

    }

    /**
     *
     * @return number of player in the squares
     */
    public int numPlayers (){
        return players.size();
    }

    /**
     *
     * @param player who enters in the squares
     * @throws NullPointerException when the value of player is null
     */
    public void addPlayer (Player player) throws NullPointerException{
        if (player!= null) {
            players.add(player);
        }
        else throw new NullPointerException();
    }

    /**
     *
     * @param player who coming out of the room
     * @throws NullPointerException when the value of player is null
     */
    public void removePlayers (Player player)throws NullPointerException{
        if (players.contains(player)){
            players.remove(player);
        }
        else throw new NullPointerException();
    }

    public void setWeaponCards(AbstractWeaponCard[] weaponCards){}
    public void putNewWeaponCard(AbstractWeaponCard weaponCard){}
    public void takeWeapon (AbstractWeaponCard weaponCard){}
    public boolean containsWeapon (AbstractWeaponCard weaponCard){
        return false;
    }
    public AbstractWeaponCard[] getWeaponCards(){ return null;}

    public void setAmmoTile (AmmoTile ammoTile){
    }

    public AmmoTile takeAmmoTile () throws AmmoTileUseException {
        throw new AmmoTileUseException();
    }

    public AmmoTile getAmmoTile(){
        return null;
    }

    public boolean isCanUseAmmo(){
        return false;
    }

    public boolean isSpawnPoint(){
        return spawnPoint;
    }

    public String getSquareRepresentation() {
        setSquareRepresentation();
        return squareRepresentation;
    }

    public void setSquareRepresentation() {
        squareRepresentation = " "+returnTypeOfCard() + " " + players.size() + " ";

    }
    public String toString(){
        setSquareRepresentation();
        return getSquareRepresentation();
    }

    public char returnTypeOfCard(){
        if(this.spawnPoint)
            return 'W';
        else return 'A';
    }

}
