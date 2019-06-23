package it.polimi.isw2019.model;


import it.polimi.isw2019.message.movemessage.*;
import it.polimi.isw2019.model.ammotile.AmmoTile;
import it.polimi.isw2019.model.exception.*;
import it.polimi.isw2019.model.powerupcard.InterfacePowerUpCard;
import it.polimi.isw2019.model.powerupcard.PowerUpCard;
import it.polimi.isw2019.utilities.Database;
import it.polimi.isw2019.utilities.Observable;
import it.polimi.isw2019.model.weaponcard.AbstractWeaponCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Model extends Observable implements ModelInterface {

    private Player currentPlayer;
    private int turn;
    private GameBoard gameBoard;
    private KillShotTrack killShotTrack;
    private Database db;
    private int shift;

    //assume that the player are in order!!
    //se un giocatore si disconnette, mettiamo il suo STATO a DISCONNECTED

    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<PlayerBoard> playerBoardsAvailable= new ArrayList<>();
    int [][] damageRanking;

    private ArrayList<String> colorAvailable;

    public ArrayList<String> getColorAvailable() {
        return colorAvailable;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public GameBoard getGameBoard(){
        return this.gameBoard;
    }

    @Override
    public ArrayList<PlayerInterface> getPlayersInterface() {
        ArrayList <PlayerInterface> playerInterface = new ArrayList<>();
        for(Player player: players)
            playerInterface.add(player.getPlayerInterface());
        return playerInterface;

    }

    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }
    /**
     *
     * @param mod type of game mod
     */
    public void setKillShotTrack (int mod){
        killShotTrack = new KillShotTrack(mod);
    }

    //vengono attivati con l'update
    public Model(){
        players = new ArrayList<>();

        //gameBoard = new GameBoard();
        //killShotTrack = new KillShotTrack(5);
    }

    public void gameSetting (){
        playerBoardsAvailable= SetUpGame.setPlayerBoard();
    }

    public void addPlayer(String nickName, String actionHeroComment) throws IndexOutOfBoundsException{

        //tmpPlayer.setNicknameAndActionHeroComment(nickName,actionHeroComment);

        if(players.size()<5) {
            players.add(new Player(nickName, actionHeroComment));
            notifyObservers(new EndRegistration(nickName));
        }
        else{
            notifyObservers(new FailRegistration(nickName));
            throw new IndexOutOfBoundsException();
        }
    }

    //used for the tests
    public void addPlayer(Player player){
        players.add(player);
    }

    public void chooseFirstPlayer(){

        Random rand = new Random();

        shift = rand.nextInt(players.size());

        players.get(shift).setFirstPlayer(true);

        for(int i = shift , j = 0; i < players.size() ; i++, j++){
            players.get(i).setIndexPlayer(j);
        }

        for(int i = 0, j = players.size() - shift ; i < shift ; i++ , j ++){
            players.get(i).setIndexPlayer(j);
        }

        currentPlayer = players.get(shift);

    }

    public void firstMessage(){

        FirstMessageFirstPlayer firstMessageFirstPlayer = new FirstMessageFirstPlayer(currentPlayer.getName());
        colorAvailable();
        firstMessageFirstPlayer.setColorAvailable(colorAvailable);
        if(currentPlayer.isFirstPlayer()) {
            //migliorare la map
            String[] arena = {"map1", "map2", "map3", "map4"};
            firstMessageFirstPlayer.setArenaInterfaces(arena);
        }
        notifyObservers(firstMessageFirstPlayer);
    }

    public void associateMapToGameboard(int indexMap){

        try {
            getGameBoard().chooseArena(indexMap);

        } catch (InstanceArenaException e) {

        } catch (OutOfBoundsException e) {

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

    public boolean containsColor (ColorPlayer color) throws ColorNotAvailableException {
        for (int i = 0; i < playerBoardsAvailable.size(); i++) {
            if (playerBoardsAvailable.get(i).getColor() == color) return true;
        }
        throw new ColorNotAvailableException();
    }

    public void colorAvailable(){
        ArrayList <String> colorAvailable = new ArrayList<>();

        for(PlayerBoard playerBoardAvailable : playerBoardsAvailable){
            colorAvailable.add(playerBoardAvailable.getColor().getColorPlayerRepresentation());
        }

        this.colorAvailable = colorAvailable;

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


    public void changePlayer(){

        //notifyObservers(new EndTurn(currentPlayer.getName()));

        int index = players.indexOf(currentPlayer);
        if(index == players.size() - 1){
            currentPlayer = players.get(0);
        }

        else{
            currentPlayer = players.get(index+1);
        }
        /*
        if(currentPlayer.getRealPlayerBoard() == null){
            firstMessage();
        }

        else if(!currentPlayer.isFirstTurn() || ! currentPlayer.isRespawn()){
            notifyObservers(currentPlayer.setCorrectNormalActionChooseMessages(false));
        }
        */
        //null pointer exception per il test
       /* else{
            ChoicePowerUpCard choicePowerUpCard = new ChoicePowerUpCard(currentPlayer.getName());
            choicePowerUpCard.addPowerUpCard(gameBoard.takePowerUpCard());
            if(currentPlayer.isRespawn()) {
                currentPlayer.setRespawned(false);
                notifyObservers(choicePowerUpCard);
            }
            else{
                currentPlayer.setFirstTurn(false);
                choicePowerUpCard.addPowerUpCard(gameBoard.takePowerUpCard());
                notifyObservers(choicePowerUpCard);
            }
        }*/

    }


    public void setFrenzyMood() {
        for(int i = players.indexOf(currentPlayer); i< players.size(); i++) {
                if (players.get(i).getRealPlayerBoard().damageTokens.isEmpty()) {
                    players.get(i).getRealPlayerBoard().setFrenzy(true);

                }
                players.get(i).setCorrectFrenzyActionChooseMessage(true);
            }

        for(int i = 0; i< players.indexOf(currentPlayer); i++) {
            if (players.get(i).getRealPlayerBoard().damageTokens.isEmpty()) {
                players.get(i).getRealPlayerBoard().setFrenzy(true);

            }
            players.get(i).setCorrectFrenzyActionChooseMessage(false);
            }
        }

    public boolean isSpawnPoint(int x, int y){
       return gameBoard.getGameArena().isSpawnSquare(x,y);
    }

    public void sendActionUpdateMessage(){

        try {
            sendUpdateMessage();
            MoveMessage tmpMoveMessage = currentPlayer.updatePlayerMessageStatus();

        } catch(EndTurnException e) {
            changePlayer();
        }
    }



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



     //reload viene invocata se la lunghezza del messaggio e' pari a 1!! oppure con la scelta delle powerUp
     public void sendUpdateMessage(){
            notifyObservers(new UpdateMessage(null,gameBoard.getGameBoardInterface(),getPlayersInterface()));

     }


    //testare

    public void sendCorrectActionMessage(MoveMessage moveMessage) {
        notifyObservers(moveMessage);
    }


    public void assignPlayerBoardToPlayer(Player player, String color){
        try {
            setPlayerWithPlayerBoard(player, translateColorToColorPlayer(color));

        }catch(ColorNotAvailableException c){
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



    public boolean checkVicinity(ArrayList<Square> squares, Square tmpSquare) {
        for (Square square : squares)
            if (square.equals(tmpSquare))
                return true;
        return false;

    }

    public void movePlayerToRespawnSquare(Player player, ColorRoom color){
        gameBoard.getGameArena().movePlayerRespawnSquare(player,color);
    }


    public void run(int[][] movement){
        ArrayList<Square> squares;

        //square adiacenti alla cella iniziale
        squares = gameBoard.getGameArena().squaresAvailable(currentPlayer.getX(),currentPlayer.getY());
        Square tmpSquare = gameBoard.getGameArena().getSquare(currentPlayer.getX(), currentPlayer.getY());

        for(int i=0; i<movement.length; i++) {

            if (!checkVicinity(squares, gameBoard.getGameArena().getSquare(movement[i][0], movement[i][1]))) {
                //throw new InvalidInsert("the cell you've inserted (" + gameBoard.getGameArena().coordinateOfSquare(tmpSquare) + " does not respect the rules of the game");
                //currentPlayer.getSingleMessageToBeSent().setError();
                //sendCorrectActionMessage(currentPlayer.getSingleMessageToBeSent());
                try {
                    notifyObservers(getCurrentPlayer().updatePlayerStatusIncorrectAction());
                    return;
                } catch (EndTurnException e) {
                    changePlayer();
                }
                return;
            }
            squares =  gameBoard.getGameArena().squaresAvailable(movement[i][0], movement[i][1]);
            tmpSquare = gameBoard.getGameArena().getSquare(movement[i][0], movement[i][1]);
        }

            gameBoard.changePositionPlayer(currentPlayer,movement[movement.length-1][0],movement[movement.length-1][1]);

            sendActionUpdateMessage();
        }

    //introduco un flag in cui bypasso i controlli se la viene inserita un'altra weapon card DOPO il warning
    //introdurre un metodo che vede che weapon card il giocatore puo prendere con le munizioni che ha, altrimenti (automaticamente) si conclude la mossa

    public void reload(ArrayList<PowerUpCard> powerUpCards, ArrayList<ColorCube> colorCubes){
    }

    public void grabAmmoCard(int[][] movement){
        if(!isSpawnPoint(movement[0][0], movement[0][1])){
            if(getGameBoard().getAmmoTileOnSquare(movement[0][0], movement[0][1]).getCheckState().equals(StateCard.ON_BOARD)){
                //chiamata al current player
            }

        }
    }

    //verificare che il pagamento venga fatto tramite powerup card oppure tramite cubi
    public void grabWeaponCard(AbstractWeaponCard weaponCard,int index, ColorCube[] payment) throws OutOfBoundsException {

        ColorCube[] paymentCubes = new ColorCube[payment.length];

        if(isSpawnPoint(currentPlayer.getX(),currentPlayer.getY())){
            //controll that there is the card at that position
            //assume that the index is OK
            //AbstractWeaponCard weaponCard1 = getGameBoard().takeWeaponCard()
            //NB : chiedere a sara come accedere alla weapon card
            if(getCurrentPlayer().getWeaponCards().size() == 3){
                //creare un warning in cui chiedi di inserire un'altra weapon card
               // sendCorrectActionMessage(1,"you've got too many card, you cannot grab! ");
               // sendMessage(1);
            }
            else if(getCurrentPlayer().getWeaponCards().size() <3) {
                //chiedere a davi se il metodo è quello corretto
                for (int i = 1, j = 0; i < weaponCard.getRechargeCube().length; i++, j++) {
                    /*if (weaponCard.getRechargeCube()[i].getColorCubeRepresentation().compareTo(payment[j]) != 1) {
                        //aggiornare il messaggio di erroe e notificare il player
                        return;
                    }
                    else {
                        paymentCubes[i] = weaponCard.getRechargeCube()[i];
                    }*/
               // }

                try {
                    currentPlayer.getRealPlayerBoard().updateCubes(paymentCubes);
                    currentPlayer.takeWeaponCards(weaponCard, null);
                }catch (IndexOutOfBoundsException e){
                    // sendErrorMessage(currentPlayer, "you don't have the cubes to pay for this weapon card!");
                }
             }
            }
        }
    }

    public void grabAmmoCard(){
        if(isSpawnPoint(currentPlayer.getX(), currentPlayer.getY())){
            currentPlayer.getSingleMessageToBeSent().setError("Cannot grab : is not a spawn point" + currentPlayer.getX() + currentPlayer. getY());
        }
        else{
            try {
                AmmoTile tmpAmmoTile = getGameBoard().pickUpAmmoTile(currentPlayer.getX(), currentPlayer.getY());
                currentPlayer.getRealPlayerBoard().addCube(tmpAmmoTile.getFirstColor());
                currentPlayer.getRealPlayerBoard().addCube(tmpAmmoTile.getSecondColor());
                if(tmpAmmoTile.isPowerUpCard()){
                    currentPlayer.takePowerUpCard(getGameBoard().takePowerUpCard(),null);
                }
                else{
                    currentPlayer.getRealPlayerBoard().addCube(tmpAmmoTile.getThirdColor());
                }
            } catch (AmmoTileUseException e) {
                try {
                    notifyObservers(currentPlayer.updatePlayerMessageStatus());
                } catch (EndTurnException e1) {
                    changePlayer();
                }
            } catch (TooManyCubes tooManyCubes) {
                //non succede nulla
            } catch (TooManyPowerUpCard tooManyPowerUpCard) {
                //inviare un messaggio di inserimento della carta
            }
        }

    }


    public void handlePayment(ArrayList<ColorCube> colors, ArrayList<PowerUpCard> powerUpCards){

        int[] payment = new int[3];

        try {
            currentPlayer.handlePaymentWithPowerUpCards(powerUpCards);
        } catch (NotPossesPowerUp notPossesPowerUp) {
            notPossesPowerUp.printStackTrace();
        }

        for(ColorCube color: colors){
            if(color.equals(ColorCube.RED)){
                payment[0] ++;
            }
            if(color.equals(ColorCube.YELLOW)){
                payment[1] ++ ;
            }
            if(color.equals(ColorCube.BLUE)) {
                payment[2]++;
            }
        }

        try {
            currentPlayer.payEffect(payment[0],payment[1], payment[2]);
        } catch (OutOfBoundsException e) {
            try {
                currentPlayer.updatePlayerStatusIncorrectAction();
            } catch (EndTurnException e1) {
                //vedere con end turn
                changePlayer();
            }
        }


    }




    public void useWeaponCard(int numEffect, AbstractWeaponCard weaponCard, ArrayList<Player> defenders, int[] coordinates, ArrayList<ColorCube> payment, ArrayList<PowerUpCard> powerUpCards, boolean notify){

        if(numEffect == 1){
            try {
                weaponCard.firstEffect(this.getGameBoard(),this.getCurrentPlayer(),defenders,coordinates);
            } catch (NoEffectException e) {
                e.printStackTrace();
            } catch (ErrorEffectException e) {
                e.printStackTrace();
            } catch (DamageTrackException e) {
                e.printStackTrace();
            }
        }

        if(numEffect == 2){
            try {
                weaponCard.secondEffect(this.getGameBoard(),this.getCurrentPlayer(),defenders,coordinates);
            } catch (NoEffectException e) {
                e.printStackTrace();
            } catch (ErrorEffectException e) {
                e.printStackTrace();
            } catch (DamageTrackException e) {
                e.printStackTrace();
            }
        }

        if(numEffect == 3){
            try {
                weaponCard.thirdEffect(this.getGameBoard(),this.getCurrentPlayer(),defenders,coordinates);
            } catch (NoEffectException e) {
                e.printStackTrace();
            } catch (ErrorEffectException e) {
                e.printStackTrace();
            } catch (DamageTrackException e) {
                e.printStackTrace();
            }
        }

        if(notify == true){
            try {
                currentPlayer.updatePlayerMessageStatus();
            } catch (EndTurnException e) {
                changePlayer();
            }
        }





        //due e per il tre
    }

}