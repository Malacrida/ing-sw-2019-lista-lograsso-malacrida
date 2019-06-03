package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.view.VisitorView;


public class UseWeaponCardMessage extends MoveMessage{

    private String nickNamePlayer;

    private int[] weaponCard;



    public UseWeaponCardMessage(String nicknamePlayer, int [] weaponCard) {
        super(nicknamePlayer);
        this.weaponCard = weaponCard;
    }

    public String getNickNamePlayer() {
        return nickNamePlayer;
    }

    public int[] getWeaponCard(){
        return weaponCard;
    }

    @Override
    public void accept(VisitorView visitorview) {
            visitorview.weaponCardChoice(this);
    }
}
