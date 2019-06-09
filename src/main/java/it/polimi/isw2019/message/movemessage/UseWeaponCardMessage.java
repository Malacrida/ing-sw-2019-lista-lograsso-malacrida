package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.model.weaponcard.WeaponCardInterface;
import it.polimi.isw2019.view.VisitorView;


public class UseWeaponCardMessage extends MoveMessage{

    private WeaponCardInterface weaponCard;

    public UseWeaponCardMessage(String nicknamePlayer) {
        super(nicknamePlayer);
    }

    public WeaponCardInterface getWeaponCard() {
        return weaponCard;
    }

    public void setWeaponCard(WeaponCardInterface weaponCard) {
        this.weaponCard = weaponCard;
    }

    @Override
    public void accept(VisitorView visitorview) {
            //visitorview.weaponCardChoice(this);
    }


}
