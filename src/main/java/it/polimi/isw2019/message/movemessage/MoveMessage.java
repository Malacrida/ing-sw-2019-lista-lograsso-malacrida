package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.view.VisitorView;

public abstract class MoveMessage {

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


    public String getIdMoveMessage(){
        return idMoveMessage;
    }


    public String getNicknamePlayer(){
        return nicknamePlayer;
    }

    public void visitView(VisitorView... visitorView){
        for(VisitorView vv : visitorView){
            visit(vv);
        }
    }

    public abstract void visit(VisitorView visitorView);

}
