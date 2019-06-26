package it.polimi.isw2019.message.movemessage;


public abstract class MoveMessage implements VisitableMessageMove{

    //String idMoveMessage is no more used
    //little model that contains all the structure of the model and updates the view.
    private int idPlayer;
    private String nicknamePlayer;
    private String error;
    private boolean notifyAll = false;


    public MoveMessage(String nicknamePlayer){
        this.nicknamePlayer= nicknamePlayer;
    }

    public MoveMessage(String nicknamePlayer,boolean notifyAll) {
        this.nicknamePlayer= nicknamePlayer;
        this.notifyAll = notifyAll;
    }
    public MoveMessage(String nicknamePlayer, String error){
        this.nicknamePlayer= nicknamePlayer;
        this.error = error;
        notifyAll = false;
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

    public boolean isNotifyAll() {
        return notifyAll;
    }

    public void setNotifyAll(boolean notifyAll) {
        this.notifyAll = notifyAll;
    }
}
