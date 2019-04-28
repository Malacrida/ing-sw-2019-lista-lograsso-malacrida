package it.polimi.isw2019.Model;

import it.polimi.isw2019.Model.AmmoTile.AmmoTile;
import it.polimi.isw2019.Model.Exception.AmmoTileUseException;

import java.util.ArrayList;

public class SquareAmmo extends Square {

    private AmmoTile ammoTile;
    private boolean useAmmo;

    SquareAmmo( Square squareN, Square squareE, Square squareS, Square squareO) {
        super( squareN, squareE, squareO, squareS, false);
        useAmmo= false;
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
