package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.model.weaponcard.WeaponCardInterface;
import it.polimi.isw2019.view.VisitorView;

import java.util.ArrayList;

public class ChoiceWeaponCard extends MoveMessage {

    private ArrayList<WeaponCardInterface> weaponCards;

    public ChoiceWeaponCard(String nicknamePlayer, String error, ArrayList<WeaponCardInterface> weaponCards) {
        super(nicknamePlayer, error);
        this.weaponCards = weaponCards;
    }

    public ArrayList<WeaponCardInterface> getWeaponCards() {
        return weaponCards;
    }

    @Override
    public void accept(VisitorView visitorview) {
            visitorview.weaponCardChoice(this);
    }

}
