package it.polimi.isw2019.Model;

import it.polimi.isw2019.Model.AmmoTile.AmmoTile;
import it.polimi.isw2019.Model.Exception.AmmoTileUseException;
import it.polimi.isw2019.Model.WeaponCard.AbstractWeaponCard;

import java.util.ArrayList;

public abstract class Square{
    private Square squareN;
    private Square squareE;
    private Square squareS;
    private Square squareO;
    private boolean spownpoint;
    protected ArrayList<Player> players= new ArrayList<>();


    Square ( Square squareN, Square squareE, Square squareO, Square squareS, boolean spownpoint){
        this.squareN=squareN;
        this.squareE=squareE;
        this.squareO=squareO;
        this.squareS=squareS;
        this.spownpoint=spownpoint;
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

    public void insertPlayers (Player player){
        players.add(player);
    }

    public void removePlayers (Player player){
        players.remove(player);
    }

    public void setWeaponCards(ArrayList<AbstractWeaponCard> weaponCards){}
    public void putNewWeaponCard(AbstractWeaponCard weaponCard){}
    public void takeWeapon (AbstractWeaponCard weaponCard){}
    public boolean containsWeapon (AbstractWeaponCard weaponCard){
        return false;
    }

    public void setAmmoTile (AmmoTile ammoTile){}

    public AmmoTile takeAmmoTile () throws AmmoTileUseException {
        throw new AmmoTileUseException();
    }




}
