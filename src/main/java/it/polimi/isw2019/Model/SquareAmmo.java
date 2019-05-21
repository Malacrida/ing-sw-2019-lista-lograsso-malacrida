package it.polimi.isw2019.Model;

import it.polimi.isw2019.Model.AmmoTile.AmmoTile;
import it.polimi.isw2019.Model.Exception.AmmoTileUseException;

public class SquareAmmo extends Square {

    private AmmoTile ammoTile;
    private boolean canUseAmmo;

    SquareAmmo() {
        super( false);
        canUseAmmo = true;
    }

    @Override
    public AmmoTile getAmmoTile() {
        return ammoTile;
    }

    @Override
    public boolean isCanUseAmmo(){
        return canUseAmmo;
    }

    @Override
    public void setAmmoTile (AmmoTile ammoTile){
        this.ammoTile= ammoTile;
        canUseAmmo =true;
    }

    @Override
    public AmmoTile takeAmmoTile () throws AmmoTileUseException {
        if (canUseAmmo){
            canUseAmmo =false;
            return ammoTile;
        }
        else throw new AmmoTileUseException();
    }


}
