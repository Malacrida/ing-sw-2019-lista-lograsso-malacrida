package it.polimi.isw2019.Server.Message.PlayerMove;


public class ReloadMove extends Action {
    private int positionWeaponCardToReload;
    char [][] paymentForRecharge;
    public ReloadMove(String idPlayerMove, String idAction, int positionWeaponCardToReload, char [][] paymentForRecharge){
        super(idPlayerMove,idAction);
        this.positionWeaponCardToReload = positionWeaponCardToReload;
        this.paymentForRecharge= paymentForRecharge;
    }
}
