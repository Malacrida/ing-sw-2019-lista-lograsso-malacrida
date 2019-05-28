package it.polimi.isw2019.message.movemessage;


import it.polimi.isw2019.view.VisitorView;

public class ErrorMessage extends MoveMessage {

    private String errorMessage;

    public ErrorMessage(String idPlayer, String errorMessage) {
        super(idPlayer);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(){
        return this.errorMessage;
    }


    @Override
    public void visit(VisitorView visitorView) {
        //invocare la schermata di errore
    }
}
