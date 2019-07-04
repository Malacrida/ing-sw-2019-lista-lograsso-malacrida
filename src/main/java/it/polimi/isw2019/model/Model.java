package it.polimi.isw2019.model;


import it.polimi.isw2019.message.movemessage.*;
import it.polimi.isw2019.model.ammotile.AmmoTile;
import it.polimi.isw2019.model.exception.*;
import it.polimi.isw2019.model.powerupcard.PowerUpCard;
import it.polimi.isw2019.model.weaponcard.*;
import it.polimi.isw2019.utilities.Database;
import it.polimi.isw2019.utilities.Observable;

import java.util.ArrayList;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class Model extends Observable<MoveMessage> {

    private Player currentPlayer;
    private int turn;
    private GameBoard gameBoard;
    private KillShotTrack killShotTrack;
    private int shift;
    private ArrayList<PowerUpCard> tmp;
    private AbstractWeaponCard tmpWeaponCard;
    private int mod;
    private int frenzyPlayer;
    private boolean isFrenzy = false;

    //assume that the player are in order!!
    //se un giocatore si disconnette, mettiamo il suo STATO a DISCONNECTED

    private ArrayList<Player> players ;
    private ArrayList<PlayerBoard> playerBoards;
    private ArrayList<PlayerBoard> playerBoardsAvailable= new ArrayList<>();
    int [][] damageRanking;

    private int[][] playerRepresentation ;
    private String[][] weaponCardDescription ;
    private String[][] powerUpCardDescription;
    private int[][] featuresAvailable ;

    private ArrayList<String> colorAvailable = new ArrayList<>();

    private ArrayList<AbstractWeaponCard> weaponCards;

    private ArrayList<PowerUpCard> tmpPowerUpCard = new ArrayList<>();

    private String entireGameDescription;

    private ArrayList<Player> deadPlayer = new ArrayList<>();
    private ArrayList<Player> shootPlayer = new ArrayList<>();

    private TimerPlayer timer;

    private Player terminator;



    public ArrayList<String> getColorAvailable() {
        return colorAvailable;
    }

    public ArrayList<PowerUpCard> getTmpPowerUpCard() {
        return tmpPowerUpCard;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public GameBoard getGameBoard(){
        return this.gameBoard;
    }

    public Player getCurrentPlayer(){
        return currentPlayer;
    }
    /**
     * @param mod type of game mode
     */
    public void setKillShotTrack (int mod){
        killShotTrack = new KillShotTrack(mod);
    }




    //vengono attivati con l'update
    public Model(){

        players = new ArrayList<>();
        playerBoardsAvailable = new ArrayList<>();

        playerBoardsAvailable.add(new PlayerBoard(ColorPlayer.YELLOW));
        playerBoardsAvailable.add(new PlayerBoard(ColorPlayer.GREEN));
        playerBoardsAvailable.add(new PlayerBoard(ColorPlayer.GREY));
        playerBoardsAvailable.add(new PlayerBoard(ColorPlayer.VIOLET));
        playerBoardsAvailable.add(new PlayerBoard(ColorPlayer.BLUE));

        killShotTrack = new KillShotTrack(5);

        timer = new TimerPlayer(60);
        timer.setModel(this);
    }

    public ArrayList<PlayerBoard> getPlayerBoards() {
        return playerBoards;
    }

    public ArrayList<PlayerBoard> getPlayerBoardsAvailable() {
        return playerBoardsAvailable;
    }

    public ArrayList<AbstractWeaponCard> getWeaponCards() {
        return weaponCards;
    }
    public void gameSetting (){
        playerBoardsAvailable= SetUpGame.setPlayerBoard();
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setPlayerBoards(ArrayList<PlayerBoard> playerBoards) {
        this.playerBoards = playerBoards;
    }

    public void setPlayerBoardsAvailable(ArrayList<PlayerBoard> playerBoardsAvailable) {
        this.playerBoardsAvailable = playerBoardsAvailable;
    }

    public void setColorAvailable(ArrayList<String> colorAvailable) {
        this.colorAvailable = colorAvailable;
    }

    public void setWeaponCards(ArrayList<AbstractWeaponCard> weaponCards) {
        this.weaponCards = weaponCards;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }


    /**
     * add player in model
     *
     * @param nickName          of player
     * @param actionHeroComment action phrase of player
     * @throws IndexOutOfBoundsException
     */

    public void addPlayer(String nickName, String actionHeroComment) throws IndexOutOfBoundsException {

        if(players.size()<5) {
            players.add(new Player(nickName, actionHeroComment));
            notifyObservers(new EndRegistration(nickName));
        } else {
            notifyObservers(new FailRegistration(nickName));
            throw new IndexOutOfBoundsException();
        }
    }

    //used for the tests
    public void addPlayer(Player player) {
        players.add(player);
    }

    public void chooseFirstPlayer(int firstPlayer){

        shift = firstPlayer;

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

            if(players.size() == 5)
                firstMessageFirstPlayer.setError("not terminator");
        }

        firstMessageFirstPlayer.setIdPlayer(players.indexOf(currentPlayer));

        firstMessageFirstPlayer.setNotifyAll(false);

        notifyObservers(firstMessageFirstPlayer);


    }

    /**
     * join player and player board
     *
     * @param player
     * @param colorPlayer
     * @throws ColorNotAvailableException
     */

    public void setPlayerWithPlayerBoard(Player player, ColorPlayer colorPlayer) throws ColorNotAvailableException {
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

    /**
     * method to see if a color is already choosen
     *
     * @param color
     * @return boolean
     * @throws ColorNotAvailableException
     */

    public boolean containsColor(ColorPlayer color) throws ColorNotAvailableException {
        for (int i = 0; i < playerBoardsAvailable.size(); i++) {
            if (playerBoardsAvailable.get(i).getColor() == color) return true;
        }
        throw new ColorNotAvailableException();
    }

    public void colorAvailable(){

        colorAvailable.clear();

        for(PlayerBoard playerBoard1 : playerBoardsAvailable){
            colorAvailable.add(playerBoard1.getColor().getColorPlayerRepresentation());
        }

    }

    public int positionPlayerBoardAvailable (ColorPlayer color) throws ColorNotAvailableException {
        for (int i=0; i<playerBoardsAvailable.size(); i++){
            if (playerBoardsAvailable.get(i).getColor()==color) return i;
        }
        throw new ColorNotAvailableException();
    }

    /**
     * method to change turn
     */

    public void changePlayer(){
         //end turn !!
        //updateEndTurn();

        int index = players.indexOf(currentPlayer);

        if (index == players.size() - 1) {
            currentPlayer = players.get(0);
        } else {
            currentPlayer = players.get(index + 1);
        }

        if(!currentPlayer.isActive())
             changePlayer();

        if(sizeActivePlayer() < 3) {
            System.out.println("number of player less 3");
            // end game
        }

        else{
            if (!killShotTrack.isFinalFrenzy()) {

                if (deadPlayer.contains(currentPlayer)) {
                    updatePlayerDeath();
                }

                sendUpdateMessage();
                handleNormalTurn();
                return;
            }

            if (killShotTrack.isFinalFrenzy() && frenzyPlayer != 0) {
                frenzyPlayer--;
                handleNormalTurn();
                return;
            } else if (killShotTrack.isFinalFrenzy() && frenzyPlayer == 0) {
                //endgame
            }
        }


    }

    public int sizeActivePlayer(){
        int numActivePlayer = 0;

        for(Player player: players)
            if(player.isActive())
                numActivePlayer++;

        return numActivePlayer;
    }
    public void handleNormalTurn(){
        if(currentPlayer.getRealPlayerBoard() == null){
            firstMessage();
            return;
        }
        else {
            if (currentPlayer.isRespawn() && currentPlayer.isFirstTurn()) {
                notifyObservers(currentPlayer.setCorrectNormalActionChooseMessages(false));
                return;
            } else if (!currentPlayer.isRespawn() && currentPlayer.isFirstTurn()) {
                tmpPowerUpCard.clear();
                ChoiceCard choiceCard = new ChoiceCard(currentPlayer.getName());

                tmpPowerUpCard.add(gameBoard.takePowerUpCard());
                tmpPowerUpCard.add(gameBoard.takePowerUpCard());

                choiceCard.setDescriptionPowerUp(setDescriptionPowerUp());
                choiceCard.setPowerUp(true);
                currentPlayer.setFirstTurn(true);
                //currentPlayer.setRespawned(true);
                notifyObservers(choiceCard);
                return;
            } else if (!currentPlayer.isRespawn() && !currentPlayer.isFirstTurn()) {
                tmpPowerUpCard.clear();
                ChoiceCard choiceCard = new ChoiceCard(currentPlayer.getName());
                PowerUpCard powerUpCard1 = gameBoard.takePowerUpCard();
                tmpPowerUpCard.add(powerUpCard1);
                currentPlayer.setRespawned(true);
                choiceCard.setPowerUp(true);
                notifyObservers(choiceCard);
                return;
            }
        }
    }

    /**
     * setter of power up's description
     *
     * @return
     */

    public String[] setDescriptionPowerUp() {
        String[] cardRepresentation = new String[tmpPowerUpCard.size()];
        for (int i = 0; i < tmpPowerUpCard.size(); i++) {
            cardRepresentation[i] = tmpPowerUpCard.get(i).getPowerUpCardRepresentation();
        }
        return cardRepresentation;
    }

    public String[] setDescriptionWeaponCard(){
        String[] cardRepresentation = new String[9];
        return cardRepresentation;
    }

    /**
     * setter of frenzy mode
     */

    public void setFrenzyMood() {

        if(shift < players.indexOf(currentPlayer)){
            for ( int i = players.indexOf(currentPlayer); i < players.size(); i++) {

                if (players.get(i).getRealPlayerBoard().damageTokens.isEmpty()) {
                    players.get(i).getRealPlayerBoard().setFrenzy(true);
                }

                players.get(i).setCorrectFrenzyActionChooseMessage(true);
             }

            for ( int i = 0 ; i < shift ; i++){
                if (players.get(i).getRealPlayerBoard().damageTokens.isEmpty()) {
                    players.get(i).getRealPlayerBoard().setFrenzy(true);
                }
                players.get(i).setCorrectFrenzyActionChooseMessage(true);
            }

            for ( int i = shift ; i < players.indexOf(currentPlayer)






                    ; i ++ ){
                if (players.get(i).getRealPlayerBoard().damageTokens.isEmpty()) {
                    players.get(i).getRealPlayerBoard().setFrenzy(true);
                }
                players.get(i).setCorrectFrenzyActionChooseMessage(false);
            }
            }

        else if (shift > players.indexOf(currentPlayer)){
            for ( int i = players.indexOf(currentPlayer); i < shift; i++) {
                if (players.get(i).getRealPlayerBoard().damageTokens.isEmpty()) {
                    players.get(i).getRealPlayerBoard().setFrenzy(true);
                }players.get(i).setCorrectFrenzyActionChooseMessage(true);
            }
            for ( int i = shift ; i < players.size(); i ++ ){
                if (players.get(i).getRealPlayerBoard().damageTokens.isEmpty()) {
                    players.get(i).getRealPlayerBoard().setFrenzy(true);
                }
                players.get(i).setCorrectFrenzyActionChooseMessage(false);
            }
            for ( int i =0; i <  players.indexOf(currentPlayer); i++) {
                if (players.get(i).getRealPlayerBoard().damageTokens.isEmpty()) {
                    players.get(i).getRealPlayerBoard().setFrenzy(true);
                }players.get(i).setCorrectFrenzyActionChooseMessage(false);
            }
         }

        else if(shift == players.indexOf(currentPlayer)){
            for ( int i = shift ; i < players.size(); i ++ ){
                if (players.get(i).getRealPlayerBoard().damageTokens.isEmpty()) {
                    players.get(i).getRealPlayerBoard().setFrenzy(true);
                }
                players.get(i).setCorrectFrenzyActionChooseMessage(false);
            }
            for ( int i =0; i <  shift; i++) {
                if (players.get(i).getRealPlayerBoard().damageTokens.isEmpty()) {
                    players.get(i).getRealPlayerBoard().setFrenzy(true);
                }
                players.get(i).setCorrectFrenzyActionChooseMessage(false);
            }
         }
        }

    /**
     * boolean to see if is a spawn Point
     * @param x first coordinate
     * @param y second cooridnate
     * @return boolean
     */
    public boolean isSpawnPoint(int x, int y){
       return gameBoard.getGameArena().isSpawnSquare(x,y);
    }

    public void sendActionUpdateMessage(){

            currentPlayer.removeMessageToBeSend();
            sendUpdateMessage();

            if(currentPlayer.updatePlayerMessageStatus())
                sendMessage();
               // notifyObservers(currentPlayer.getSingleMessageToBeSent());
            else
                changePlayer();

    }

    /**
     * send update of game state
     */

    public void updateGameStatus(){

        //entireGameDescription = "GameBoard";
        //gameBoard.setGameBoardDescription();
        //entireGameDescription += gameBoard.getGameBoardDescription();
        getGameBoard().getGameArena().setArenaRepresentation();
        entireGameDescription += gameBoard.getGameArena().getArenaRepresentation();
        for(Player player : players) {
            if(player.getRealPlayerBoard()!= null)
                entireGameDescription += player.getRealPlayerBoard().getPlayerBoardRepresentation();
            entireGameDescription += player.getName() + " ";
            entireGameDescription += player.getX() + " ";
            entireGameDescription += player.getY() + " ";
            entireGameDescription += player.getColorRoom().getColorRoomRepresentation() + "\n";
        }

    }
    /**
     * send update of the message, if lenght of the message is 1 start reload
     */
     //reload viene invocata se la lunghezza del messaggio e' pari a 1!! oppure con la scelta delle powerUp
     public void sendUpdateMessage(){
        setGameRepresentation();
        notifyObservers(new UpdateMessage(currentPlayer.getName(),gameBoard.getGameBoardDescription(),playerRepresentation,featuresAvailable,weaponCardDescription,powerUpCardDescription,true));
     }


    /**
     * send message of the acction
     */

    public void sendActionMessage(){

       if (currentPlayer.getSingleMessageToBeSent() instanceof GrabMessage) {
            updateGrabMessage();
            return;
        }
       else if(currentPlayer.getSingleMessageToBeSent() instanceof UseWeaponCardMessage) {
           currentPlayer.setPlayerInUseWeaponCardMessage(returnCoordinatesOfPlayerInGame());
       }
       else if(currentPlayer.getSingleMessageToBeSent() instanceof UsePowerUpCardMessage) {
            currentPlayer.setPlayerInUsePowerUpMessage(returnCoordinatesOfPlayerInGame());
        }
        sendMessage();

      // notifyObservers(currentPlayer.getSingleMessageToBeSent());
    }

    /**
     * update for grab action
     */

    public void updateGrabMessage() {

        tmpPowerUpCard.clear();
        tmpWeaponCard = null;

        if (!isSpawnPoint(currentPlayer.getX(), currentPlayer.getY())) {
            try {
                AmmoTile ammo = getGameBoard().pickUpAmmoTile(currentPlayer.getX(), currentPlayer.getY());
                currentPlayer.takenAmmoTileColor(ammo);
                gameBoard.addAmmoTileDiscarded(ammo);
                gameBoard.placeAmmoTile(currentPlayer.getX(), currentPlayer.getY());
                gameBoard.getGameArena().getAmmoTileOnSquare(currentPlayer.getX(), currentPlayer.getY()).setCheckState(StateCard.DECK);
                if (ammo.isPowerUpCard()) {
                    tmpPowerUpCard.add(gameBoard.takePowerUpCard());
                    currentPlayer.takePowerUpCard(tmp.get(0), null);
                }

                updateCorrectAction();

            } catch (AmmoTileUseException a) {
                //"there's nothing to grab"
                //end action
            } catch (TooManyPowerUpCard tooManyPowerUpCard) {
                //visitCardChoice -> 4 -> change, altrimenti shalla
                currentPlayer.setTmpPowerUpChoice(tmpPowerUpCard.get(0));
                String[] repPowerUp = {tmpPowerUpCard.get(0).getPowerUpCardRepresentation()};
                int[] idPowerUp = {tmpPowerUpCard.get(0).getId()};
                ((ChoiceCard) currentPlayer.getSingleMessageToBeSent()).setDescriptionPowerUp(repPowerUp);
                ((ChoiceCard) currentPlayer.getSingleMessageToBeSent()).setIdPowerUp(idPowerUp);
                ((ChoiceCard) currentPlayer.getSingleMessageToBeSent()).setPowerUp(true);
                sendMessage();
                //notifyObservers(currentPlayer.getSingleMessageToBeSent());
            }
        } else {
            if (currentPlayer.getWeaponCards().size() < 3) {
                ((GrabMessage) currentPlayer.getSingleMessageToBeSent()).setWeaponCardAvailable(gameBoard.getWeaponCardDescription(currentPlayer.getColorRoom()));
                ((GrabMessage) currentPlayer.getSingleMessageToBeSent()).setGrabWeapon(true);
                sendMessage();
               // notifyObservers(currentPlayer.getSingleMessageToBeSent());

            } else {
                ChoiceCard choiceCard = new ChoiceCard(currentPlayer.getName());
                tmpWeaponCard = gameBoard.getWeaponCardFromGameboard();
                String[] repWeapon = {tmpWeaponCard.getWeaponCardDescription()};
                int[] idWeapon = {tmpWeaponCard.getID()};
                ((ChoiceCard) currentPlayer.getSingleMessageToBeSent()).setDescriptionPowerUp(repWeapon);
                ((ChoiceCard) currentPlayer.getSingleMessageToBeSent()).setIdPowerUp(idWeapon);
                ((ChoiceCard) currentPlayer.getSingleMessageToBeSent()).setPowerUp(false);
                sendMessage();
               // notifyObservers(currentPlayer.getSingleMessageToBeSent());

            }
        }
    }
    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    /**
     * setter of the game
     * @param indexMap map choosen by the player
     * @param terminatorVariable
     */

    public void setGame(int indexMap, int mod, int terminatorVariable){

        try {
            Database db = new Database();
            gameBoard = new GameBoard();
            getGameBoard().chooseArena(indexMap);
            getGameBoard().setPowerUpCards(db.loadPowerUpCards());
            getGameBoard().setAmmoTiles(db.loadAmmoTiles());
            gameBoard.setWeaponCardsOnBoard();
            gameBoard.setUpAmmoTileOnArena(indexMap);
            playerRepresentation = new int[players.size()][];
            weaponCardDescription = new String[players.size()][];
            powerUpCardDescription = new String[players.size()][];
            featuresAvailable = new int[players.size()][];
            killShotTrack = new KillShotTrack(mod);
            frenzyPlayer = players.size();
            if(terminatorVariable == 1) {
                terminator = new Player("terminator", "Sterminerò tutti voi comuni mortali!");
                try {
                    setPlayerWithPlayerBoard(terminator,playerBoardsAvailable.get(0).getColor());
                } catch (ColorNotAvailableException e) {

                }

                for(Player player : players)
                    player.setTerminatorPlayer(terminator);
            }


        } catch (OutOfBoundsException e) {

        } catch(InstanceArenaException e){

        }
    }

    /**
     * Set matrix of point player
     * @param playerDeath
     * @return
     */
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

    /**
     * Assign player's point according to skull
     * @param p1 first player
     * @param p2 second player
     * @param p3 third player
     * @param p4 four player
     * @param playerDamageRanking possible points
     */

    public void assignPoint (int p1,int p2, int p3, int p4, int[][] playerDamageRanking){
        for (int i=0; i<players.size(); i++){
            if (playerDamageRanking[0][0]==players.get(i).getPlayerID()) players.get(i).addScore(p1);
            if (playerDamageRanking[1][0]==players.get(i).getPlayerID()) players.get(i).addScore(p2);
            if (playerDamageRanking[2][0]==players.get(i).getPlayerID()) players.get(i).addScore(p3);
            if (playerDamageRanking[3][0]==players.get(i).getPlayerID()) players.get(i).addScore(p4);
        }

    }

    /**
     * add score when one player is dead
     * @param playerDeath player who is dead
     */

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
     * add damages on kill shot track
     * @param colorPlayerDoKill color of player do the kill shot
     * @param numOfDamage number of damage that suffer player death
     */
    public void addDamageOnKillShotTrack (ColorPlayer colorPlayerDoKill, int numOfDamage){
        killShotTrack.killPlayer(colorPlayerDoKill,numOfDamage);
    }


    /**
     * Assign point after end game
     * @return
     */
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


    /**
     * Set matrix of point kill shot track
     */
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

    public void endScore (){
        for (int i=0; i<players.size(); i++){

        }
    }


    /**
     * respawn action
     * @param positionInTmpCardChoosen
     */

    public void movePlayerToRespawnSquare(int positionInTmpCardChoosen){

        gameBoard.getGameArena().movePlayerRespawnSquare(currentPlayer,tmpPowerUpCard.get(positionInTmpCardChoosen).getColorRoom());


        try {
            currentPlayer.takePowerUpCard(tmpPowerUpCard.get(positionInTmpCardChoosen),null);

        } catch (TooManyPowerUpCard tooManyPowerUpCard) {

        }

        tmpPowerUpCard.remove(positionInTmpCardChoosen);
        //riguardare bene questa cosa
        //stessa cosa che nel player
        gameBoard.addPowerUpCardDiscarded(tmpPowerUpCard.get(0));

        tmpPowerUpCard.clear();
        if(!currentPlayer.isRespawn())
            currentPlayer.setRespawned(true);

        sendUpdateMessage();

        notifyObservers(currentPlayer.setCorrectNormalActionChooseMessages(false));
        //updateCorrectAction();

    }

    /**
     * send un update if it isn't a correct action
     * @param error
     */

    public void updateNotCorrectAction(String error){

        if(currentPlayer.updatePlayerStatusIncorrectAction(error))
            notifyObservers(getCurrentPlayer().getSingleMessageToBeSent());
        else
            changePlayer();
    }

    /**
     * send an update if action is correct
     */
    public void updateCorrectAction(){

        sendUpdateMessage();

        currentPlayer.removeMessageToBeSend();

        if(currentPlayer.updatePlayerMessageStatus()){
            if(getCurrentPlayer().getSingleMessageToBeSent() instanceof GrabMessage){
                updateGrabMessage();
            }

            notifyObservers(getCurrentPlayer().getSingleMessageToBeSent());
        }
        else
            changePlayer();
    }

    /**
     * method to implements action run
     * @param movement matrix of cooridinates (x,y) where x represents row and y represents column
     */



    public void run(int[][] movement){

        int x = currentPlayer.getX();
        int y = currentPlayer.getY();
        boolean endRun = false;
        int i = 0;
       do{

           if(movement[i][0] == -1)
               endRun = true;

           else if(movement[i][1] == -1)
               endRun = true;

           else if (!getGameBoard().isSquareAvailableOnArena(currentPlayer,movement[i][0], movement[i][1])) {
                gameBoard.changePositionPlayer(currentPlayer,x,y);
                updateNotCorrectAction("unreachable square, insert again");
            }
            else {
                gameBoard.changePositionPlayer(currentPlayer, movement[i][0], movement[i][1]);
                i++;
            }

            if(i == movement.length)
                endRun = true;

        }while(!endRun);
;
        updateCorrectAction();

        }

    public void handlePayment(ColorCube[] colorCubes){

        int[] cubes = fromCubesToInt(colorCubes);

        try {
            currentPlayer.payEffect(cubes[1],cubes[2],cubes[0]);
        } catch (OutOfBoundsException e) {

        }

    }


    public void reload(int[][] payment, int[] weaponToRecharge){

        for(int i = 0; i < weaponToRecharge.length; i++){
            if(weaponToRecharge[i]==1){
                handlePayment(currentPlayer.getWeaponCards().get(i).getRechargeCube());
                currentPlayer.getWeaponCards().get(i).changeState(StateCard.HOLDING);
            }
        }

        updateCorrectAction();

    }

    public void grabWeaponCard(AbstractWeaponCard weaponCard) {

        handlePayment(weaponCard.getRechargeCube());

        try {
            tmpWeaponCard = gameBoard.takeWeaponCard(weaponCard,getCurrentPlayer().getX(),getCurrentPlayer().getY());
        } catch (OutOfBoundsException e) {

        }
        try {
            currentPlayer.takeWeaponCards(tmpWeaponCard,null);
        } catch (TooManyWeaponCard tooManyWeaponCard) {

        }

        updateCorrectAction();
    }

    public ArrayList<Player> fromArrayToArrayListPlayer(int[] defenders){
        ArrayList<Player> playerDefender = new ArrayList<>();
        for(int i = 0; i < defenders.length; i++){
            playerDefender.add(players.get(defenders[i]));
        }
        return playerDefender;
    }

    public void insertDeadPlayer(ArrayList<Player> players){
        for(Player player : players){
            if(player.getRealPlayerBoard().numOfDamages()>= 1){
                deadPlayer.add(player);
            }
        }
    }

    public void insertShootPlayer(ArrayList<Player> players){
        for(Player player : players){
            shootPlayer.add(player);
        }
    }
    /**
     * implementation of shoot action
     * @param indexCard index of weapon card
     * @param orderEffect array of effects that player want use
     * @param defenders array of players attacked
     * @param coordinates coordinates of squares
     * @param payment for to use effects
     */
    public void useWeaponCard( int indexCard,int[] orderEffect,int[][] defenders, int[][] coordinates, int[][] payment){

        AbstractWeaponCard weaponCard = currentPlayer.getWeaponCards().get(indexCard);

     for(int i = 0 ; i < orderEffect.length; i ++) {
         if(orderEffect[i] == 1) {
             ArrayList<Player> defenders1= fromArrayToArrayListPlayer(defenders[0]);
             try {
                 weaponCard.firstEffect(gameBoard,currentPlayer,defenders1,coordinates[0]);
                 insertShootPlayer(defenders1);

             } catch (NoEffectException e) {
                 updateNotCorrectAction(e.getMessage());
             } catch (ErrorEffectException e) {
                 updateNotCorrectAction(e.getMessage());
             } catch (DamageTrackException e) {
                 insertShootPlayer(defenders1);
                 insertDeadPlayer(defenders1);
             }
             finally {
                 handlePayment(weaponCard.getRechargeCube());
             }
         }

         if (orderEffect[i] == 2) {
             ArrayList<Player> defenders1= fromArrayToArrayListPlayer(defenders[1]);
             try {
                 weaponCard.secondEffect(gameBoard,currentPlayer, defenders1, coordinates[1]);
                 insertShootPlayer(defenders1);
             } catch (NoEffectException e) {
                 updateNotCorrectAction(e.getMessage());
                 return;
             } catch (ErrorEffectException e) {
                 updateNotCorrectAction(e.getMessage());
                 return;
             } catch (DamageTrackException e) {
                 insertShootPlayer(defenders1);
                 insertDeadPlayer(defenders1);
             }
             finally {
                 handlePayment(weaponCard.getPaySecondEffect());
             }
         }

         if (orderEffect[i] == 3) {
             ArrayList<Player> defenders1= fromArrayToArrayListPlayer(defenders[2]);
             try {

                 weaponCard.thirdEffect(gameBoard,currentPlayer, defenders1, coordinates[2]);
                 insertShootPlayer(defenders1);
             } catch (NoEffectException e) {
                 updateNotCorrectAction(e.getMessage());
                 return;
             } catch (ErrorEffectException e) {
                 updateNotCorrectAction(e.getMessage());
                 return;
             } catch (DamageTrackException e) {
                 insertShootPlayer(defenders1);
                 insertDeadPlayer(defenders1);
             }
             finally {
                 handlePayment(weaponCard.getPayThirdEffect());
             }
         }
     }
     currentPlayer.getWeaponCards().get(indexCard).changeState(StateCard.DISCHARGE);
     sendActionUpdateMessage();

    }


    public void usePowerUpCard(int positionPowerUp,int positionPlayer,int[] coo){

        PowerUpCard powerUpCard = currentPlayer.getPowerUpCards().get(positionPowerUp);

        try {
            powerUpCard.effect(gameBoard, currentPlayer, players.get(positionPlayer), coo[0], coo[1]);

        }catch(DamageTrackException e){

            for(Player player : players){
                if(!deadPlayer.contains(player)){
                    deadPlayer.add(player);
                }
            }
        }

        currentPlayer.getPowerUpCards().remove(powerUpCard);
        gameBoard.addPowerUpCardDiscarded(powerUpCard);

        updateCorrectAction();
    }
    /**
     * change cubes to integer
     * @param colorCube type of color
     * @return
     */
    public int[] fromCubesToInt(ColorCube[] colorCube){
        int[] toBePaid1 = new int[3];

        for ( int i = 0; i < 3; i++)
            toBePaid1[i] = 0;

        for(ColorCube color : colorCube){
            if(color.equals(ColorCube.BLUE))
                toBePaid1[0] ++;
            else if(color.equals(ColorCube.RED))
                toBePaid1[1] ++;
            else if(color.equals(ColorCube.YELLOW))
                toBePaid1[2] ++;
        }
        return toBePaid1;

    }

    /**
     * Method to control if the payment is valid
     * @param payment type of payment
     * @param toBePaid color cube used to pay
     * @return
     */

    public boolean checkValidityPayment(int[] payment, ColorCube[] toBePaid){

        int[] toBePaid1 = new int[6];

        for(int i = 0; i < toBePaid1.length; i++)
            toBePaid1[i] = 0;

        for(ColorCube color : toBePaid){
            if(color.equals(ColorCube.BLUE))
                toBePaid1[0] ++;
            else if(color.equals(ColorCube.RED))
                toBePaid1[1] ++;
            else if(color.equals(ColorCube.YELLOW))
                toBePaid1[2] ++;
             }

        for(int i = payment.length-1; i !=2 ; i--){
            if(payment[i]!= -1){
                try {
                    if((currentPlayer.fromIntToColorCube(payment[i])).equals(ColorCube.BLUE))
                        toBePaid1[0] ++;
                    else  if((currentPlayer.fromIntToColorCube(payment[i])).equals(ColorCube.RED))
                        toBePaid1[1] ++;
                    else if((currentPlayer.fromIntToColorCube(payment[i])).equals(ColorCube.YELLOW))
                        toBePaid1[2] ++;
                } catch (NotPossesPowerUp notPossesPowerUp) {
                    //notPossesPowerUp.printStackTrace();
                }
            }
            }

        for(int i = 0; i< 3; i++){
           if(payment[i]-toBePaid1[i]<0){
               return false;
           }
       }

       return true;
    }

    public AbstractWeaponCard getTmpWeaponCard() {
        return tmpWeaponCard;
    }

    public void setTmpWeaponCard(AbstractWeaponCard tmpWeaponCard) {
        this.tmpWeaponCard = tmpWeaponCard;
    }

    public String getEntireGameDescription() {
        return entireGameDescription;
    }

    public String toString(){
        updateGameStatus();
        return getEntireGameDescription();
    }

    public int[][] getPlayerRepresentation() {
        setGameRepresentation();
        return playerRepresentation;
    }

    /**
     * setter of representation of the game
     */

    public void setGameRepresentation() {
        System.out.println("arena rep : " + gameBoard.getGameArena().getArenaRepresentation());
        for (int i = 0; i < players.size(); i++) {
            playerRepresentation[i] = players.get(i).getStatusPlayer();
            weaponCardDescription[i] = players.get(i).getWeaponCardDescription();
            powerUpCardDescription[i] = players.get(i).getPowerUpDescription();
            featuresAvailable[i] = players.get(i).getFeaturesAvailable();
        }

    }

    /**
     *  method to see player's coordinates in the game
     * @return
     */


    public int[][] returnCoordinatesOfPlayerInGame(){

           int[][] coordinatesPlayerInGame ;
           if(terminator!= null)
               coordinatesPlayerInGame =new int[players.size()+1][3];
           else
               coordinatesPlayerInGame = new int[players.size()][3];

           for(int i = 0; i < players.size(); i++){
               coordinatesPlayerInGame[i][0] = players.get(i).getIndexPlayer();
               coordinatesPlayerInGame[i][1] = players.get(i).getX();
               coordinatesPlayerInGame[i][2] = players.get(i).getY();
           }
           if(terminator == null)
               return coordinatesPlayerInGame;
           else {
               coordinatesPlayerInGame[players.size()][0] = -1;
               coordinatesPlayerInGame[players.size()][1] = terminator.getX();
               coordinatesPlayerInGame[players.size()][2] = terminator.getY();
               return coordinatesPlayerInGame;
           }

        }

    public void updateEndTurn(){

        gameBoard.getGameArena().setStatusCardOnBoard();
        updatePlayerDeath();

    }

    public void removePowerUpFromPlayer(int idCardChoosen){
        try {
            //NB -> cambiare la tua power up!
            currentPlayer.takePowerUpCard(tmpPowerUpCard.get(0),currentPlayer.getPowerUpCards().get(idCardChoosen));
        } catch (TooManyPowerUpCard tooManyPowerUpCard) {
            //non ci dovrebbe mai arrivare
        }
    }

    public void addCubesFromPowerUp(int[] features){

        for(int i = features.length-1; i > 3; i --) {
            if (features[i] != -1) {
                for (int j = currentPlayer.getPowerUpCards().size() -1; j > 0; j--) {
                    if (currentPlayer.getPowerUpCards().get(j).getId() == features[i]) {
                        PowerUpCard tmp = currentPlayer.getPowerUpCards().get(i);
                        currentPlayer.getPowerUpCards().remove(currentPlayer.getPowerUpCards().get(i));
                        gameBoard.addPowerUpCardDiscarded(tmp);
                    }
                }
            }
        }
    }
    public void updatePlayerDeath(){
    if(deadPlayer.contains(currentPlayer)) {
        ArrayList<ColorPlayer> colorPlayerDoKill = currentPlayer.returnKillDamage();

        System.out.println("has died! " + deadPlayer.size());
        addDamageOnKillShotTrack(colorPlayerDoKill.get(0), colorPlayerDoKill.size());

        currentPlayer.getRealPlayerBoard().resetAfterDeath();
        // -> respawn = TRUE
        currentPlayer.setRespawned(true);
        //end turn della morte del player!
        //restore playerBoard
        addScoreAfterDeath(currentPlayer);
        //distribuzione dei punti
        // marchi rimangono
        //invochi set frenzy
        if (killShotTrack.getNumSkull() == mod) {
            setFrenzyMood();
            frenzyPlayer = players.size();
        }

        deadPlayer.remove(currentPlayer);
        //sendUpdateAction()!
         }

     }

     public void terminatorAction(boolean shoot, int[] coordinates){
            if(shoot){
                ArrayList<Player> playersWhoCanSee = gameBoard.playersWhoCanSee(terminator);

                for(Player player : playersWhoCanSee){
                    if(terminator.playerDamage()>2){
                        try {
                            player.sufferDamageOrMark(terminator.getColor(),1,1);
                        } catch (DamageTrackException e) {
                          //insertDeadPlayer();
                        }
                    }
                    else{
                        try {
                            player.sufferDamageOrMark(terminator.getColor(),1,0);
                        } catch (DamageTrackException e) {
                            //insertDeadPlayer
                        }
                    }
                }

                updateCorrectAction();
            }
            else {
                if (!getGameBoard().isSquareAvailableOnArena(terminator,coordinates[0], coordinates[1])) {
                    updateNotCorrectAction("unreachable square, insert again");
                }
                else{
                    gameBoard.getGameArena().movePlayer(terminator,coordinates[0], coordinates[1]);
                }
            }
     }

    public ArrayList<Player> getDeadPlayer() {
        return deadPlayer;
    }



    public void receiveAnswer (){
        timer.stopTimer();
    }

    public void sendMessage (){
        timer.startTimer();
        notifyObservers(currentPlayer.getSingleMessageToBeSent());
    }

    public void playerNotAnswer (){
        System.out.println("Disattivo il palyer");
        currentPlayer.setActive(false);
        changePlayer();
    }
}



