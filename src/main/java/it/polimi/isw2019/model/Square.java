package it.polimi.isw2019.model;

import it.polimi.isw2019.model.ammotile.AmmoTile;
import it.polimi.isw2019.model.exception.AmmoTileUseException;
import it.polimi.isw2019.model.weaponcard.AbstractWeaponCard;

import java.util.ArrayList;

public abstract class Square{
    private Square squareN;
    private Square squareE;
    private Square squareS;
    private Square squareO;
    private boolean spawnPoint;
    protected ArrayList<Player> players= new ArrayList<>();
    private String[] squareRepresentation;
    Square (boolean spawnPoint){
        this.spawnPoint = spawnPoint;
    }

    public void setSquareAdjacent (Square squareN, Square squareE, Square squareS, Square squareO){
        this.squareN=squareN;
        this.squareE=squareE;
        this.squareO=squareO;
        this.squareS=squareS;

    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

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
        if (squareO!=null){
            squareAvailable.add(squareO);
        }

        return squareAvailable;
    }


    public boolean findPlayer (Player player){
        return players.contains(player);

    }

    public int numPlayers (){
        return players.size();
    }

    public void addPlayer (Player player) throws NullPointerException{
        if (player!= null) {
            players.add(player);
        }
        else throw new NullPointerException();
    }

    public void removePlayers (Player player)throws NullPointerException{
        if (players.contains(player)){
            players.remove(player);
        }
        else throw new NullPointerException();
    }

    public void setWeaponCards(ArrayList<AbstractWeaponCard> weaponCards){}

    public void putNewWeaponCard(AbstractWeaponCard weaponCard){}

    public void takeWeapon (AbstractWeaponCard weaponCard){}

    public boolean containsWeapon (AbstractWeaponCard weaponCard){
        return false;
    }
    public ArrayList<AbstractWeaponCard> getWeaponCards(){ return null;}

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

    public String[] getSquareRepresentation() {
        return squareRepresentation;
    }

    public void setSquareRepresentation(String color) {
        this.squareRepresentation = new String[3];
        squareRepresentation[0] = color;
        squareRepresentation[1] = Character.toString(returnTypeOfCard());
        squareRepresentation[2] = String.valueOf(players.size());

    }

    public char returnTypeOfCard(){
        if(this.spawnPoint)
            return 'W';
        else return 'A';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
