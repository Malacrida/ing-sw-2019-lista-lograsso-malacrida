package it.polimi.isw2019.Controller;
import it.polimi.isw2019.Message.PlayerMove.*;
import it.polimi.isw2019.Model.*;
import it.polimi.isw2019.Utilities.Observer;
import it.polimi.isw2019.View.*;

public class Controller implements Observer<PlayerMove> {

    private Model model;
    private StrategyAction visitorModel;
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

    public void performAction(Action actionMove) {

        //switch case in base allo status del player
        //passato il controllo alla corretta classe del pattern STRATEGY
        //


    }

    public void performNormalAction(Action actionMove){
        //switch case in base all'id dell'azione

        //se l'azione e' run,viene invocato il metodo RUN se le caselle sono adiacenti (metodo nel model, //se NON sono adiacenti , viene generato un mex di errore)

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
            case "STARTING MESSAGE":
                createPlayer((SetUpMove)(playerMove));
                break;
            case "COLOR CHOOSEN":
                updateColorPlayer(playerMove);
                break;
            case "ACTION":
                performAction((Action)(playerMove));
                break;

            case "POWERUP CARD" :
                break;


        }

    }


}
