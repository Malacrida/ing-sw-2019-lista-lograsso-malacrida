package it.polimi.isw2019.message.movemessage;


public abstract class MoveMessage implements VisitableMessageMove{

    //String idMoveMessage is no more used
    private String idMoveMessage;
    //little model that contains all the structure of the model and updates the view.
    private int idPlayer;
    private String nicknamePlayer;

    private String error;


    public MoveMessage(String idMoveMessage, int idPlayer){
        this.idMoveMessage = idMoveMessage;
        this.idPlayer = idPlayer;
    }


    public MoveMessage(String nicknamePlayer){
        this.nicknamePlayer= nicknamePlayer;
    }

    public MoveMessage(String nicknamePlayer, String error){
        this.nicknamePlayer= nicknamePlayer;
        this.error = error;
    }
    public String getNicknamePlayer(){
        return nicknamePlayer;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
