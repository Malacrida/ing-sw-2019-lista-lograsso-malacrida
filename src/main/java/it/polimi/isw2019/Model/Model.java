package it.polimi.isw2019.Model;


//import it.polimi.isw2019.Controller.VisitorAction; -> problemi con git
import it.polimi.isw2019.Model.Exception.ColorNotAvailable;
import it.polimi.isw2019.Model.PowerUpCard.PowerUpCard;
import it.polimi.isw2019.Utilities.Observable;

import java.util.ArrayList;

public class Model extends Observable {
    private Player currentPlayer;
    private int turn;
    private GameBoard gameBoard;
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<PlayerBoard> playerBoardsAvailable= new ArrayList<>();

    //manca una MAP per mappare le posizioni dei giocatori all'interno del model

    //rendere questo oggetto clonato in modo che non viene ritornato un riferimento di questo oggetto alla view
    public GameBoard getGameBoard(){
        return this.gameBoard;
    }

    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }

    public void createGamePlay(){
        //metodo che ti permette di creare l'istanza del gioco
    }

    public void setUpGameBoard(){
        //richiamare tutti i metodi che settano la gameBoard
    }


    public void restoreBoardPlayer (PlayerBoard playerBoard){
        //resettarla quando si finisce il turno
    }

    public void addPlayer (Player player){
        //verificare che la modalità non sia quella degli spawn

        if(this.players.size() <5)
                this.players.add(player);

        else
                // il model dovrà fare l'update a quella view o dell'avvenuta aggiunta oppure dell'errore
                System.out.println("Cannot be added");
    }

    public void calculationTemporaryScore(){

        //quando qualcuno muore, alla fine di chi ha ucciso una determinata persona, viene calcolato il punteggio.
        //il modo con cui viene fatto è strano

    }

    public void calculationScore(){
        //termine dell'ultimo turno nella modalità freenzy
    }

    public void chooseFirstPlayer(){
        //scelto in modo randomico
    }

    /*public  void accept(VisitorAction visitorAction, PlayerMove playerMove){
        visitorAction.visitModel(this, playerMove);
    } -> problemi con git
    */

    public void run(Player player, ArrayList movement){

        for(int i = 0; i < movement.size(); i++){
            //check dei movimenti verra' fatto nel controller
            //sposto di tre direttamente o faccio un for
        }

        //update

    }

    //generalizzare le carte in modo tale che poi qui non devo stare ad inserire tre tipi di grab diversi ma solo uno con la carta
    //check viene fatto nel controller

    public void grab(Player player){
        //posizionare la carta dalla Gameboard al player
        //verificare il player quante carte ha di quel tipo
        //se ne ha piu di tre deve scambiare una carta con quella che ha appena preso
        //nel caso che siano ammoCard, spostare i cubi dal ammoBox alla parte delle munizioni
        //oppure prendere una powerup card ed eventualmente fare quello che viene fatto prima

        //fare tre tipo di grab diversi (?)

        //termine update


    }

    //mancherebbe la carta
    public void reload(Player player,ArrayList<Cube> cube){

        //cubi vengono spostati dalle munizioni all'ammo box

        //cambio di stato della carta


    }

    public void useWeaponCard(Player player,ArrayList position){
        //riflettere su come invocare bene gli effetti diversi
        //weaponCard di quel giocatore diventa da carica a scarica

        //update
    }

    public void usePowerUpCard(Player player, PowerUpCard powerUpCard){
        //getEffect della weaponCard
        //posizionata nel mazzo

        //update

    }

    public void gameSetting (){
        playerBoardsAvailable= SetUpGame.setPlayerBoard();
    }

    public boolean containsColor (ColorPlayer color) throws ColorNotAvailable {
        for (int i = 0; i < playerBoardsAvailable.size(); i++) {
            if (playerBoardsAvailable.get(i).getColor() == color) return true;
        }
        throw new ColorNotAvailable();
    }


    public int positionPlayerBoardAviable (ColorPlayer color) throws ColorNotAvailable{
        for (int i=0; i<playerBoardsAvailable.size(); i++){
            if (playerBoardsAvailable.get(i).getColor()==color) return i;
        }
        throw new ColorNotAvailable();
    }

    public void setPlayer (String name, ColorPlayer colorPlayer) throws ColorNotAvailable {
        try {
            if (containsColor(colorPlayer)){
                Player player= new Player(name,colorPlayer,playerBoardsAvailable.get(positionPlayerBoardAviable(colorPlayer)));
                players.add(player);
                playerBoardsAvailable.remove(playerBoardsAvailable.get(positionPlayerBoardAviable(colorPlayer)));
            }
        }
        catch (ColorNotAvailable e){
            throw new ColorNotAvailable();
            //Manda un messaggio di scelta sbagliata -> Update!!
            // al posto di rilanciare l'eccezione
        }
    }

}