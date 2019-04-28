package it.polimi.isw2019.Controller;
import it.polimi.isw2019.Message.PlayerMove.PlayerMove;
import it.polimi.isw2019.Model.*;
import it.polimi.isw2019.Utilities.Observer;

public class Controller implements Observer<PlayerMove> {

    private Model model;
    private VisitorModel visitorModel;
    private int idPlayer;
    public void powerOn(){

    }

    public void setUpGame(){

    }

    public void createPlayer(PlayerMove playerMove){
        //funziona nel model
       // model.addPlayer(playerMove.getNickname(), playerMove.getPhrase(), idPlayer);

        //eccezione che i giocatori sono troppi
        //model rifiuta di accettare un'altro giocatore e , quindi , viene de-registrato come observer

        //viene invocato quando ci sono almeno 5 giocatori o quando è scaduto il timer nel server
        startGame();

    }

    public void updateColorPlayer(PlayerMove playerMove){
        //model.updateComponentPlayer(playerMove.getIdPlayer(),playerMove.getColor());

    }

    public void startGame(){
            //invocato un metodo start game che notifica tutti i giocatori
            //attiva la schermata del primo giocatore
    }

    public void checkStateGame(){

    }

    public void endTurnPlayer(){

    }

    public void endGame(){

    }

    public void scoreGame(){

    }


    @Override
    public void update(PlayerMove playerMove){
        switch (playerMove.getIdPlayerMove()) {
            case "Starting Message":
                createPlayer(playerMove);
                break;
            case "Color choosen":
                updateColorPlayer(playerMove);
                break;

        }

    }


}
