package it.polimi.isw2019.message.movemessage;


public abstract class MoveMessage implements VisitableMessageMove{

    //String idMoveMessage is no more used
    protected String idMoveMessage;
    //little model that contains all the structure of the model and updates the view.
    protected int idPlayer;
    protected String nicknamePlayer;


    public MoveMessage(String idMoveMessage, int idPlayer){
        this.idMoveMessage = idMoveMessage;
        this.idPlayer = idPlayer;
    }


    public MoveMessage(String nicknamePlayer){
        this.nicknamePlayer= nicknamePlayer;
    }

    public String getNicknamePlayer(){
        return nicknamePlayer;
    }

}
