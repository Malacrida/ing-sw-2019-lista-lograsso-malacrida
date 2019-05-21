package it.polimi.isw2019.Server.Model;


//import it.polimi.isw2019.Controller.VisitorAction; -> problemi con git
import it.polimi.isw2019.Server.Model.Exception.ColorNotAvailableException;
import it.polimi.isw2019.Server.Model.PowerUpCard.PowerUpCard;
import it.polimi.isw2019.Server.Utilities.Observable;

import java.util.ArrayList;


public class Model extends Observable {
    private Player currentPlayer;
    private int turn;
    private GameBoard gameBoard;
    private KillShotTrack killShotTrack;
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<PlayerBoard> playerBoardsAvailable= new ArrayList<>();
    int [][] damageRanking;


    //manca una MAP per mappare le posizioni dei giocatori all'interno del model

    //rendere questo oggetto clonato in modo che non viene ritornato un riferimento di questo oggetto alla view
    public GameBoard getGameBoard(){
        return this.gameBoard;
    }

    /**
     *
     * @param mod type of game mod
     */
    public void setKillShotTrack (int mod){
        killShotTrack = new KillShotTrack(mod);
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
        //notifyObservers(new SetUpMessage("Choose color", currentPlayer.getIdPlayer(),  //colorAvailable ));
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
    public void reload(Player player,ArrayList<ColorCube> cube){

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

    //Colore scelto dal giocatore è ancora disponibile
    public boolean containsColor (ColorPlayer color) throws ColorNotAvailableException {
        for (int i = 0; i < playerBoardsAvailable.size(); i++) {
            if (playerBoardsAvailable.get(i).getColor() == color) return true;
        }
        throw new ColorNotAvailableException();
    }


    public int positionPlayerBoardAvailable (ColorPlayer color) throws ColorNotAvailableException {
        for (int i=0; i<playerBoardsAvailable.size(); i++){
            if (playerBoardsAvailable.get(i).getColor()==color) return i;
        }
        throw new ColorNotAvailableException();
    }

    //Set del colore del player
    public void setPlayer(String name, String actionHeroComment, int playerID, ColorPlayer green) {
        Player player= new Player(name, actionHeroComment, playerID);
        players.add(player);

    }

    public void setPlayerWithPlayerBoard (Player player, ColorPlayer colorPlayer) throws ColorNotAvailableException {
        try {
            player.setPlayerBoardAndColor(playerBoardsAvailable.get(positionPlayerBoardAvailable(colorPlayer)), colorPlayer);
            playerBoardsAvailable.remove(playerBoardsAvailable.get(positionPlayerBoardAvailable(colorPlayer)));
        }
        catch (ColorNotAvailableException e){
            throw new ColorNotAvailableException();
            //Manda un messaggio di scelta sbagliata -> Update!!
            // al posto di rilanciare l'eccezione
        }

    }


    public int [][] setDamageRanking (Player playerDeath) {

        damageRanking= new int [players.size()-1][2];
        int idApp, damageApp,n=0;

        //Matrice in ordine decrescente
        for (int i=0; i<players.size(); i++){
            if(players.get(i)!= playerDeath && playerDeath.damageDoByAnotherPlayer(players.get(i).getColor())>0){
                damageRanking[n][0]= players.get(i).getPlayerID();
                damageRanking[n][1]= playerDeath.damageDoByAnotherPlayer(players.get(i).getColor());
                for (int j=n; j>0;j--){
                    if(damageRanking[j][1]>damageRanking[j-1][1]){
                        idApp=damageRanking[j][0];
                        damageApp=damageRanking[j][1];
                        damageRanking[j][0]=damageRanking[j-1][0];
                        damageRanking[j][1]=damageRanking[j-1][1];
                        damageRanking[j-1][0]=idApp;
                        damageRanking[j-1][1]=damageApp;
                    }
                }
                n++;
            }
        }
        return damageRanking;
    }


    public void assignPoint (int n1,int n2, int n3, int n4, int[][] playerDamageRanking){
        for (int i=0; i<players.size(); i++){
            if (damageRanking[0][0]==players.get(i).getPlayerID()) players.get(i).addScore(n1);
            if (damageRanking[1][0]==players.get(i).getPlayerID()) players.get(i).addScore(n2);
            if (damageRanking[2][0]==players.get(i).getPlayerID()) players.get(i).addScore(n3);
            if (damageRanking[3][0]==players.get(i).getPlayerID()) players.get(i).addScore(n4);
        }

    }


    public void addScoreAfterDeath (Player playerDeath){

        //Give a point for first blood
        for (int i=0; i<players.size(); i++){
            if (players.get(i).getColor()==playerDeath.firstPlayerDoDamage()){
                players.get(i).addScore(1);
            }
        }

        damageRanking=setDamageRanking(playerDeath);
        if (playerDeath.getNumberOfSkulls()==0) {
            assignPoint(8,6,4,2, damageRanking);
        }

        for (int i=0; i<players.size(); i++){
            if (damageRanking[0][0]==players.get(i).getPlayerID()){
                if (playerDeath.getNumberOfSkulls()==0)players.get(i).addScore(8);
                if (playerDeath.getNumberOfSkulls()==1)players.get(i).addScore(6);
                if (playerDeath.getNumberOfSkulls()==2)players.get(i).addScore(4);
                if (playerDeath.getNumberOfSkulls()==3)players.get(i).addScore(2);
                if (playerDeath.getNumberOfSkulls()==4)players.get(i).addScore(1);
                if (playerDeath.getNumberOfSkulls()==5)players.get(i).addScore(1);
            }
            if (damageRanking[1][0]==players.get(i).getPlayerID()){
                if (playerDeath.getNumberOfSkulls()==0)players.get(i).addScore(6);
                if (playerDeath.getNumberOfSkulls()==1)players.get(i).addScore(4);
                if (playerDeath.getNumberOfSkulls()==2)players.get(i).addScore(2);
                if (playerDeath.getNumberOfSkulls()==3)players.get(i).addScore(1);
                if (playerDeath.getNumberOfSkulls()==4)players.get(i).addScore(1);

            }
            if (players.size()-1>2){
                if (damageRanking[2][0]==players.get(i).getPlayerID()){
                    if (playerDeath.getNumberOfSkulls()==0)players.get(i).addScore(4);
                    if (playerDeath.getNumberOfSkulls()==1)players.get(i).addScore(2);
                    if (playerDeath.getNumberOfSkulls()==2)players.get(i).addScore(1);
                    if (playerDeath.getNumberOfSkulls()==3)players.get(i).addScore(1);
                }
                if (players.size()-1>3){
                    if (damageRanking[3][0]==players.get(i).getPlayerID()){
                        if (playerDeath.getNumberOfSkulls()==0)players.get(i).addScore(2);
                        if (playerDeath.getNumberOfSkulls()==1)players.get(i).addScore(1);
                        if (playerDeath.getNumberOfSkulls()==2)players.get(i).addScore(1);
                    }
                }
            }
        }
    }

    /**
     *
     * @param colorPlayerDoKill color of player do the kill shot
     * @param numOfDamage number of damage that suffer player death
     */
    public void addDamageOnKillShotTrack (ColorPlayer colorPlayerDoKill, int numOfDamage){
        killShotTrack.killPlayer(colorPlayerDoKill,numOfDamage);
    }


    public int [][] killShotRanking (){

        int [][] killShotTable = new int [players.size()][2];
        int idApp, damageApp;

        for (int i=0; i<players.size(); i++){
            killShotTable[i][0]= players.get(i).getPlayerID();
            killShotTable[i][1]= killShotTrack.numOfKillShotByOnePlayer(players.get(i).getColor());

            for (int j=i; j>0; j--){
                if (killShotTable[j][1]>killShotTable[j-1][1]){
                    idApp= killShotTable[j][0];
                    damageApp= killShotTable[j][1];
                    killShotTable[j][0]= killShotTable[j-1][0];
                    killShotTable[j][1]= killShotTable[j-1][1];
                    killShotTable[j-1][0]= idApp;
                    killShotTable[j-1][1]=damageApp;
                }
            }
        }
        return killShotTable;

    }


    public void addScoreToKillShotTrack (){

        int [][] killShotTable= killShotRanking();

        for (int i=0; i<players.size(); i++){
            if (players.get(i).getPlayerID()==killShotTable[0][0] && killShotTable[0][1]!=0)
                players.get(i).addScore(8);
            if (players.get(i).getPlayerID()==killShotTable[1][0] && killShotTable[1][1]!=0)
                players.get(i).addScore(6);
            if (players.get(i).getPlayerID()==killShotTable[2][0] && killShotTable[2][1]!=0)
                players.get(i).addScore(4);
            if (players.get(i).getPlayerID()==killShotTable[3][0] && killShotTable[3][1]!=0)
                players.get(i).addScore(2);
            if (players.get(i).getPlayerID()==killShotTable[4][0] && killShotTable[4][1]!=0)
                players.get(i).addScore(1);

        }

    }



}