package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.view.VisitorView;

import java.util.ArrayList;

public class ReloadMessage extends MoveMessage{

    private int[] numCubesForRecharge;
    private int numWeaponCard;

    public int getNumWeaponCard() {
        return numWeaponCard;
    }

    public ReloadMessage(String nicknamePlayer, int[] numCubesForRecharge, int numWeaponCard){
        super(nicknamePlayer);
        this.numCubesForRecharge = numCubesForRecharge;
        this.numWeaponCard = numWeaponCard;
    }

    public ReloadMessage(String nicknamePlayer){
        super(nicknamePlayer);
    }

    public int[] getNumCubesForRecharge() {
        return numCubesForRecharge;
    }

    @Override
    public void accept(VisitorView visitorView) {
        visitorView.visitReload(this);
    }
}
