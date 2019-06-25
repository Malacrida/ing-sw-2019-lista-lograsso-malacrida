package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.model.weaponcard.WeaponCardInterface;
import it.polimi.isw2019.network.rmi.VirtualViewVisitorInterface;
import it.polimi.isw2019.view.VisitorView;

import java.util.ArrayList;

public class ReloadMessage extends MoveMessage{

    //armi scariche
    private ArrayList<WeaponCardInterface> weaponCardInterfaces;


    public ReloadMessage(String nicknamePlayer, ArrayList<WeaponCardInterface> weaponCardInterfaces){
        super(nicknamePlayer);
        this.weaponCardInterfaces = weaponCardInterfaces;
    }

    public ReloadMessage(String nicknamePlayer){
        super(nicknamePlayer);
    }


    public ArrayList<WeaponCardInterface> getWeaponCardInterfaces() {
        return weaponCardInterfaces;
    }

    @Override
    public void accept(VisitorView visitorView) {
        visitorView.visitReload(this);
    }

    @Override
    public void accept(VirtualViewVisitorInterface virtualView) {
        virtualView.sendReload(this);
    }
}
