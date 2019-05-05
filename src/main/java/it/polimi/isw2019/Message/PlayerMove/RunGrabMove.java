package it.polimi.isw2019.Message.PlayerMove;

public class RunGrabMove extends RunMove {
    private char typeCard;
    private int weaponCardPosition;

    public RunGrabMove(String idPlayerMove, String idAction, int[][] movement, char typeCard, int weaponCardPosition){
        super(idPlayerMove,idAction,movement);
        this.typeCard = typeCard;
        this.weaponCardPosition = weaponCardPosition;
    }
}
