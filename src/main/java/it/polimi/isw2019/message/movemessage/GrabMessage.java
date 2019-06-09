package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.model.ammotile.InterfaceAmmoTile;
import it.polimi.isw2019.model.weaponcard.WeaponCardInterface;
import it.polimi.isw2019.view.VisitorView;

import java.util.ArrayList;

public class GrabMessage extends MoveMessage {

    private ArrayList<WeaponCardInterface> weaponCardAvailable;
    private InterfaceAmmoTile ammoTile;

    public GrabMessage(String nicknamePlayer) {
        super(nicknamePlayer);
    }

    public GrabMessage(String nicknamePlayer, String error) {
        super(nicknamePlayer, error);
    }

    public ArrayList<WeaponCardInterface> getWeaponCardAvailable() {
        return weaponCardAvailable;
    }

    public void setWeaponCardAvailable(ArrayList<WeaponCardInterface> weaponCardAvailable) {
        this.weaponCardAvailable = weaponCardAvailable;
    }

    public InterfaceAmmoTile getAmmoTile() {
        return ammoTile;
    }

    public void setAmmoTile(InterfaceAmmoTile ammoTile) {
        this.ammoTile = ammoTile;
    }

    @Override
    public void accept(VisitorView visitorview) {
                visitorview.visitGrab(this);
    }


}
