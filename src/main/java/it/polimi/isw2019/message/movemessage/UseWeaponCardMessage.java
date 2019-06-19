package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.model.PlayerInterface;
import it.polimi.isw2019.model.weaponcard.WeaponCardInterface;
import it.polimi.isw2019.view.VisitorView;

import java.util.ArrayList;


public class UseWeaponCardMessage extends MoveMessage{

    private WeaponCardInterface weaponCard;

    private ArrayList<PlayerInterface> playersToAttack;


    public UseWeaponCardMessage(String nicknamePlayer) {
        super(nicknamePlayer);
    }

    public WeaponCardInterface getWeaponCard() {
        return weaponCard;
    }

    public void setWeaponCard(WeaponCardInterface weaponCard) {
        this.weaponCard = weaponCard;
    }

    public ArrayList<PlayerInterface> getPlayersToAttack(){
        return playersToAttack;
    }
    @Override
    public void accept(VisitorView visitorview) {
            visitorview.useWeaponCard(this);
    }


}
