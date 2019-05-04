package it.polimi.isw2019.Model;

import it.polimi.isw2019.Model.AmmoTile.AmmoTile;
import it.polimi.isw2019.Model.Exception.AmmoTileUseException;

import java.util.ArrayList;

public class SquareAmmo extends Square {

    private AmmoTile ammoTile;
    private boolean useAmmo;

    SquareAmmo() {
        super( false);
        useAmmo= false;
    }

    public AmmoTile getAmmoTile() {
        return ammoTile;
    }

    public boolean isUseAmmo (){
        return useAmmo;
    }

    public void setAmmoTile (AmmoTile ammoTile){
        this.ammoTile= ammoTile;
        useAmmo=false;
    }

    public AmmoTile takeAmmoTile () throws AmmoTileUseException {
        if (!useAmmo){
            useAmmo=true;
            return ammoTile;
        }
        throw new AmmoTileUseException();
    }


}
