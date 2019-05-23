package it.polimi.isw2019.message.MoveMessage;

import it.polimi.isw2019.view.VisitorView;

public abstract class MoveMessage {

    //String idMoveMessage is no more used
    private String idMoveMessage;
    //little model that contains all the structure of the model and updates the view.
    private int idPlayer;


    public MoveMessage(String idMoveMessage){
        this.idMoveMessage = idMoveMessage;
    }

    public MoveMessage(String idMoveMessage, int idPlayer){
        this.idMoveMessage = idMoveMessage;
        this.idPlayer = idPlayer;
    }


    public String getIdMoveMessage(){
        return idMoveMessage;
    }


    public int getIdPlayer(){
        return idPlayer;
    }

    public void visitView(VisitorView... visitorView){
        for(VisitorView vv : visitorView){
            visit(vv);
        }
    }

    public abstract void visit(VisitorView visitorView);

}
