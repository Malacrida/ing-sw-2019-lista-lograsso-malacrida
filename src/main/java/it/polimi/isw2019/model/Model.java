package it.polimi.isw2019.model;


import it.polimi.isw2019.message.movemessage.*;
import it.polimi.isw2019.model.exception.ColorNotAvailableException;
import it.polimi.isw2019.utilities.Observable;
import it.polimi.isw2019.model.weaponcard.AbstractWeaponCard;

import java.util.ArrayList;
import java.util.Random;

public class Model extends Observable {
    private Player currentPlayer;
    private int turn;
    private GameBoard gameBoard;
    private KillShotTrack killShotTrack;
    //assume that the player are in order!!
    //se un giocatore si disconnette, mettiamo il suo STATO a DISCONNECTED
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<PlayerBoard> playerBoardsAvailable= new ArrayList<>();
    int [][] damageRanking;
    private ArrayList<String> colorAvailable;

    private Player tmpPlayer;

    public ArrayList<String> getColorAvailable() {
        return colorAvailable;
    }
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



    public void chooseFirstPlayer(){
        Random rand = new Random();

        int n = rand.nextInt(players.size());

        players.get(n).setFirstPlayer(true);


    }

    public void setFrenzyPlayers(){
            //guardare il regolamento

    }

    public boolean isSpawnPoint(int x, int y){
       return gameBoard.getGameArena().isSpawnSquare(x,y);
    }


    public void gameSetting (){
        playerBoardsAvailable= SetUpGame.setPlayerBoard();
    }

    public void checkNickname(String name, String actionHeroComment, int numIdPlayer){

        tmpPlayer = new Player(numIdPlayer);

        if(players.size()<5) {
            players.add(tmpPlayer);
            for (int i = 0; i < players.size(); i++) {
                if (players.get(i).getName().equals(name)) {
                    setPlayerWithoutNickname(numIdPlayer);
                    return;
                }
            }
            tmpPlayer.setNicknameAndActionHeroComment(name,actionHeroComment);
        }

        else{
            //send error message
            //deregister observer
        }

    }


    public void setPlayerWithoutNickname(int idPlayer) {
        //notify del nickname!!!!!!! che lo deve reinserire

    }
    //
    public void updateTurnPlayer(){

        if(gameBoard.getKillShotTrack().getNumSkull()<8) {
            if (currentPlayer.getPlayerID() == players.size()) {
                turn = 0;
            } else
                turn = turn + 1;

            currentPlayer = players.get(turn);
        }
        else{
            // controllare frenzy
            // l'ultimo turno!
        }

    }

    public void sendUpdateMessage(){
        //notify mex di update
        //cambiare il player

    }

    public void setCorrectActionChoosenMessages(){

        ActionMessage actionMessage = new ActionMessage(currentPlayer.getName());
        if(!currentPlayer.isFrenzy() && ! currentPlayer.isFrenzy()){
            if(currentPlayer.playerDamage()<=2)
                actionMessage.setNormalAction();
            else if (currentPlayer.playerDamage()<= 4)
                actionMessage.setFirstPoweredAction();
            else
                actionMessage.setFrenzyFirstPlayerAction();
        }
        else if (currentPlayer.isFrenzy() && !currentPlayer.isFirstPlayer()){
            actionMessage.setFrenzyAction();
        }
        else{
            actionMessage.setFrenzyFirstPlayerAction();
        }

        notifyObservers(actionMessage);
    }

    public int numMovementCanBePerformedRun(){
        if(!currentPlayer.isFrenzy())
            return 3;

        else
            return 4;

    }

    public int numMovementCanBePerformedRunGrab(){
        if(!currentPlayer.isFrenzy()){
            if(currentPlayer.playerDamage()<=2)
                return 1;
            else
                return 2;
        }
        else{
            if(!currentPlayer.isFirstPlayer())
                return 2;
            else
                return 3;
        }
    }

    public void setWeaponCard(){

        int[] effectForWeaponCard = new int [3];

        for(AbstractWeaponCard abstractWeaponCard :currentPlayer.getWeaponCards()){
            effectForWeaponCard[0] = abstractWeaponCard.getMaxPossibleEffects();
            //manca num coordinate, num
        }

        notifyObservers(new UseWeaponCardMessage(currentPlayer.getName(), effectForWeaponCard));
    }


    public void sendCorrectActionMessage(int actionChoose){
        switch (actionChoose){
            case 0:
                notifyObservers(new RunMessage(currentPlayer.getName(),numMovementCanBePerformedRun()));
                break;
            case 1:
                notifyObservers(new RunGrabMessage(currentPlayer.getName(),numMovementCanBePerformedRunGrab()));
                break;
        }
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


    public ColorPlayer translateColorToColorPlayer(String color){
        if(color.equals("blue"))
            return ColorPlayer.BLUE;
        else if(color.equals("green"))
            return ColorPlayer.GREEN;
        else if(color.equals("yellow"))
            return ColorPlayer.YELLOW;
        else if(color.equals("violet"))
            return ColorPlayer.VIOLET;
        else
            return ColorPlayer.GREY;
    }


    public ArrayList<String> colorAvailable(){
        ArrayList <String> colorAvailable = new ArrayList<>();

        for(PlayerBoard playerBoardAvailable : playerBoardsAvailable){
            colorAvailable.add(playerBoardAvailable.getColor().getColorPlayerRepresentation());
        }

        this.colorAvailable = colorAvailable;

        return colorAvailable;
    }

    public void assignPlayerBoardToPlayer(Player player, String color){
        try {
            setPlayerWithPlayerBoard(player, translateColorToColorPlayer(color));

        }catch(ColorNotAvailableException c){
        }
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


    public void assignPoint (int p1,int p2, int p3, int p4, int[][] playerDamageRanking){
        for (int i=0; i<players.size(); i++){
            if (playerDamageRanking[0][0]==players.get(i).getPlayerID()) players.get(i).addScore(p1);
            if (playerDamageRanking[1][0]==players.get(i).getPlayerID()) players.get(i).addScore(p2);
            if (playerDamageRanking[2][0]==players.get(i).getPlayerID()) players.get(i).addScore(p3);
            if (playerDamageRanking[3][0]==players.get(i).getPlayerID()) players.get(i).addScore(p4);
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

        switch (playerDeath.getNumberOfSkulls()){
            case 0:
                assignPoint(8,6,4,2, damageRanking);
                break;
            case 1:
                assignPoint(6,4,2,1, damageRanking);
                break;
            case 2:
                assignPoint(4,2,1,1, damageRanking);
                break;
            case 3:
                assignPoint(2,1,1,0, damageRanking);
                break;
            case 4:
                assignPoint(1,1,0,0, damageRanking);
                break;
            case 5:
                assignPoint(1,0,0,0, damageRanking);
                break;
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
    //




    public void sendErrorMessage(Player player, String error){
        notifyObservers(new ErrorMessage(player.getName(),error));

    }

    public void grabAmmoCard(int[][] movement){
        if(!isSpawnPoint(movement[0][0], movement[0][1])){
            //model.getGameBoard().getAmmoTileOnSquare(movement[0][0], movement[0][1]){


        }
    }

    public boolean checkVicinity(ArrayList<Square> squares, Square tmpSquare) {
        for (Square square : squares)
            if (square.equals(tmpSquare))
                return true;
        return false;

    }

    public void run(int[][] movement, boolean notify){

        ArrayList<Square> squares = new ArrayList<Square>();

        //square adiacenti alla cella iniziale
        squares = gameBoard.getGameArena().squaresAvailable(currentPlayer.getX(),currentPlayer.getY());
        Square tmpSquare = gameBoard.getGameArena().getSquare(currentPlayer.getX(), currentPlayer.getY());

        for(int i=0; i<movement.length; i++) {
            if (!checkVicinity(squares, gameBoard.getGameArena().getSquare(movement[i][0], movement[i][1]))) {
                int[] index = gameBoard.getGameArena().coordinateOfSquare(tmpSquare);
                sendErrorMessage( currentPlayer,"the cell with coordinates " + movement[i][0] + movement[i][1] + "is NOT near to the cell " +
                        "with coordinates" + index[0]+ index[1]);
                //sendMessage(0);
                return;
            }
            squares =  gameBoard.getGameArena().squaresAvailable(movement[i][0], movement[i][1]);
            tmpSquare = gameBoard.getGameArena().getSquare(movement[i][0], movement[i][1]);
        }

        gameBoard.changePositionPlayer(currentPlayer,movement[movement.length-1][0],movement[movement.length-1][1]);

        if(!notify)
            return;
        else {

            int numPlayerInRoom = gameBoard.playersInOneSquare(movement[movement.length-1][0],movement[movement.length-1][1],currentPlayer).size();
            //gameBoard.getGameArena().updateArenaRepresentation(movement[movement.length-1][0],movement[movement.length-1][1],numPlayerInRoom + 1);
            //migliorare l'update
           //metodo che viene invocato!!!!!
           // notifyObservers(new UpdateMessage(currentPlayer.getName()));
        }
    }

    //introduco un flag in cui bypasso i controlli se la viene inserita un'altra weapon card DOPO il warning
    //introdurre un metodo che vede che weapon card il giocatore puo prendere con le munizioni che ha, altrimenti (automaticamente) si conclude la mossa

    public void grabWeaponCard(AbstractWeaponCard weaponCard, int[][] movement, char color){
        if(isSpawnPoint(movement[0][0], movement[0][1])) {
            //controll that there is the card at that position
            //assume that the index is OK

            if(getCurrentPlayer().getWeaponCards().size() == 3){
                //creare un warning in cui chiedi di inserire un'altra weapon card
                sendErrorMessage(currentPlayer,"you've got too many card, you cannot grab! ");
               // sendMessage(1);
            } else if (getCurrentPlayer().getWeaponCards().size() <3) {
                //chiedere a davi se il metodo è quello corretto
                /*
                if (weaponCard.getRechargecube().equals(convertCharToColorCube(color))) {
                    currentPlayer.takeWeaponCards(weaponCard, null);

                }
                else {
                    //
                    sendErrorMessage(currentPlayer, "you don't have the cubes to pay for this weapon card!");
                }*/
            }
            //assume payment correct
            //model.getCurrentPlayer().
            //else
            //String error ="Payment invalid";
        }
    }

    public void handlePayment(int[] cubes){

    }




}