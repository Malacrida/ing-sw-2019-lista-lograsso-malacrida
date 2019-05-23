package it.polimi.isw2019.message.MoveMessage;


import it.polimi.isw2019.view.VisitorView;

public class ErrorMessage extends MoveMessage {

    private String errorMessage;

    public ErrorMessage(String idMoveMessage, String errorMessage) {
        super(idMoveMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(){
        return this.errorMessage;
    }


    @Override
    public void visit(VisitorView visitorView) {

    }
}
