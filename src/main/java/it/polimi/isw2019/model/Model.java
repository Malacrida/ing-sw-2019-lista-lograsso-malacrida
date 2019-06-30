package it.polimi.isw2019.model;


import it.polimi.isw2019.message.movemessage.*;
import it.polimi.isw2019.model.ammotile.AmmoTile;
import it.polimi.isw2019.model.exception.*;
import it.polimi.isw2019.model.powerupcard.PowerUpCard;
import it.polimi.isw2019.model.weaponcard.*;
import it.polimi.isw2019.utilities.Database;
import it.polimi.isw2019.utilities.Observable;

import java.util.ArrayList;

public class Model extends Observable {

    private Player currentPlayer;
    private int turn;
    private GameBoard gameBoard;
    private KillShotTrack killShotTrack;
    private int shift;
    private ArrayList<PowerUpCard> tmp;
    private AbstractWeaponCard tmpWeaponCard;
    private int numArena;

    //assume that the player are in order!!
    //se un giocatore si disconnette, mettiamo il suo STATO a DISCONNECTED

    private ArrayList<Player> players ;
    private ArrayList<Player> tmpShootPlayer;
    private ArrayList<Player> tmpDeadPlayer;
    private ArrayList<PlayerBoard> playerBoards;
    private ArrayList<PlayerBoard> playerBoardsAvailable= new ArrayList<>();
    int [][] damageRanking;

    private int[][] playerRepresentation ;
    private String[][] weaponCardDescription ;
    private String[][] powerUpCardDescription;
    private int[][] featuresAvailable ;

    private ArrayList<String> colorAvailable;

    private ArrayList<AbstractWeaponCard> weaponCards;

    private ArrayList<PowerUpCard> tmpPowerUpCard = new ArrayList<>();

    private String entireGameDescription;

    private ArrayList<Player> deadPlayer;
    private ArrayList<Player> shootPlayer;

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
     *
     * @param mod type of game mod
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

    public void addPlayer(String nickName, String actionHeroComment) throws IndexOutOfBoundsException{

        if(players.size()<5) {
            players.add(new Player(nickName, actionHeroComment));
            System.out.println("ok registration model");
            System.out.println("player : " + players.get(0).getName());
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

    public void chooseFirstPlayer(int firstPlayer){

        shift = firstPlayer;

        players.get(shift).setFirstPlayer(true);

        for(int i = shift , j = 0; i < players.size() ; i++, j++){
            players.get(i).setIndexPlayer(j);
        }

        for(int i = 0, j = players.size() - shift ; i < shift ; i++ , j ++){
            players.get(i).setIndexPlayer(j);
        }

        System.out.println("first player" + shift + "\n" );

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

        firstMessageFirstPlayer.setIdPlayer(players.indexOf(currentPlayer));

        firstMessageFirstPlayer.setNotifyAll(false);

        notifyObservers(firstMessageFirstPlayer);


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

        for(PlayerBoard playerBoard1 : playerBoardsAvailable){
            colorAvailable.add(playerBoard1.getColor().getColorPlayerRepresentation());
        }

        this.colorAvailable = colorAvailable;

    }

    public int positionPlayerBoardAvailable (ColorPlayer color) throws ColorNotAvailableException {
        for (int i=0; i<playerBoardsAvailable.size(); i++){
            if (playerBoardsAvailable.get(i).getColor()==color) return i;
        }
        throw new ColorNotAvailableException();
    }


    public void changePlayer(){

        int index = players.indexOf(currentPlayer);

        if(index == players.size() - 1){
            currentPlayer = players.get(0);
        }

        else{
            currentPlayer = players.get(index+1);
        }

        sendUpdateMessage();

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
                ChoicePowerUpCard choicePowerUpCard = new ChoicePowerUpCard(currentPlayer.getName());

                tmpPowerUpCard.add(gameBoard.takePowerUpCard());
                tmpPowerUpCard.add(gameBoard.takePowerUpCard());

                choicePowerUpCard.setDescriptionPowerUp(setDescriptionPowerUp());
                currentPlayer.setFirstTurn(true);
                currentPlayer.setRespawned(true);
                notifyObservers(choicePowerUpCard);
                return;
            } else if (!currentPlayer.isRespawn() && !currentPlayer.isFirstTurn()) {
                tmpPowerUpCard.clear();
                ChoicePowerUpCard choicePowerUpCard = new ChoicePowerUpCard(currentPlayer.getName());
                PowerUpCard powerUpCard1 = gameBoard.takePowerUpCard();
                //choicePowerUpCard.addPowerUpCard(gameBoard.takePowerUpCard());
                tmpPowerUpCard.add(powerUpCard1);
                currentPlayer.setRespawned(true);
                notifyObservers(choicePowerUpCard);
                return;
            }
        }

    }

    public String[] setDescriptionPowerUp(){
        String[] cardRepresentation = new String[tmpPowerUpCard.size()];
        for(int i = 0 ; i < tmpPowerUpCard.size(); i++){
            cardRepresentation[i] = tmpPowerUpCard.get(i).getPowerUpCardRepresentation();
        }
        return cardRepresentation;
    }

    public String[] setDescriptionWeaponCard(){
        String[] cardRepresentation = new String[9];
        return cardRepresentation;
    }

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

            for ( int i = shift ; i < players.indexOf(currentPlayer); i ++ ){
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

    public boolean isSpawnPoint(int x, int y){
       return gameBoard.getGameArena().isSpawnSquare(x,y);
    }

    public void sendActionUpdateMessage(){

            currentPlayer.removeMessageToBeSend();
            sendUpdateMessage();

            if(currentPlayer.updatePlayerMessageStatus())
                notifyObservers(currentPlayer.getSingleMessageToBeSent());
            else
                changePlayer();

    }

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

     //reload viene invocata se la lunghezza del messaggio e' pari a 1!! oppure con la scelta delle powerUp
     public void sendUpdateMessage(){
        setGameRepresentation();
        notifyObservers(new UpdateMessage(currentPlayer.getName(),gameBoard.getGameArena().getArenaRepresentation(),playerRepresentation,featuresAvailable,weaponCardDescription,powerUpCardDescription,true));
     }



    public void sendActionMessage(){

       if (currentPlayer.getSingleMessageToBeSent() instanceof GrabMessage) {
            updateGrabMessage();
            return;
        }
       else if(currentPlayer.getSingleMessageToBeSent() instanceof UseWeaponCardMessage) {
           currentPlayer.setPlayerInUseWeaponCardMessage(returnCoordinatesOfPlayerInGame());
       }
        else
           System.out.println("ok send message");
            notifyObservers(currentPlayer.getSingleMessageToBeSent());
    }

    public void updateGrabMessage(){

                if(!isSpawnPoint(currentPlayer.getX(),currentPlayer.getY())) {
                    try {
                        System.out.println(getCurrentPlayer().getX() + " "+ " " + getCurrentPlayer().getY());
                        AmmoTile ammo = getGameBoard().pickUpAmmoTile(currentPlayer.getX(), currentPlayer.getY());
                        System.out.println(ammo.toString());
                        currentPlayer.takenAmmoTileColor(ammo);
                        gameBoard.addAmmoTileDiscarded(ammo);
                        gameBoard.placeAmmoTile(currentPlayer.getX(),currentPlayer.getY());
                        gameBoard.getGameArena().getAmmoTileOnSquare(currentPlayer.getX(),currentPlayer.getY()).setCheckState(StateCard.DECK);
                        if (ammo.isPowerUpCard()) {
                            tmp.add(gameBoard.takePowerUpCard());
                            currentPlayer.takePowerUpCard(tmp.get(0), null);
                        }

                        updateCorrectAction();

                    } catch (AmmoTileUseException a) {
                        //"there's nothing to grab"
                        //end action
                    }
                    catch (TooManyPowerUpCard tooManyPowerUpCard) {
                        //powerUpChoice -> 4 -> change, altrimenti shalla
                        ((GrabMessage) currentPlayer.getSingleMessageToBeSent()).setIdPowerUp(tmp.get(0).getId());
                        ((GrabMessage) currentPlayer.getSingleMessageToBeSent()).setGrabWeapon(false);
                        notifyObservers(currentPlayer.getSingleMessageToBeSent());
                    }
                }
                else{
                    //weaponCardChoice stesso concetto power up!!
                    ((GrabMessage) currentPlayer.getSingleMessageToBeSent()).setWeaponCardAvailable(gameBoard.getWeaponCardDescription(currentPlayer.getColorRoom()));
                    ((GrabMessage) currentPlayer.getSingleMessageToBeSent()).setGrabWeapon(true);
                    notifyObservers(currentPlayer.getSingleMessageToBeSent());

                }
            }

    public void setGame(int indexMap){

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

        } catch (OutOfBoundsException e) {

        } catch(InstanceArenaException e){

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

        sendUpdateMessage();

        notifyObservers(currentPlayer.setCorrectNormalActionChooseMessages(false));
        //updateCorrectAction();

    }

    public void updateNotCorrectAction(String error){

        if(currentPlayer.updatePlayerStatusIncorrectAction(error))
            notifyObservers(getCurrentPlayer().getSingleMessageToBeSent());
        else
            changePlayer();
    }

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

    public void run(int[][] movement){

        //square adiacenti alla cella iniziale
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
                return;
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

    public void handlePayment(int[] payment){

                try {
                    currentPlayer.getRealPlayerBoard().removeBlueCubes(payment[0]);
                    currentPlayer.getRealPlayerBoard().removeRedCubes(payment[1]);
                    currentPlayer.getRealPlayerBoard().removeYellowCubes(payment[2]);
                } catch (OutOfBoundsException e) {
                }

        if(payment.length >3) {
            for (int i = 3; i < payment.length; i++) {
                PowerUpCard  tmp = currentPlayer.getPowerUpCards().get(payment[i]);
                currentPlayer.handlePaymentWithPowerUpCards(currentPlayer.getPowerUpCards().get(payment[i]));
                try {
                    currentPlayer.getRealPlayerBoard().addCube(tmp.getColorCard());
                } catch (TooManyCubes tooManyCubes) {

                }
                try {
                    currentPlayer.getRealPlayerBoard().removeCube(tmp.getColorCard());
                } catch (NoCubesException e) {
                    //appena aggiunto -> impossibile
                }
                gameBoard.addPowerUpCardDiscarded(tmp);

            }
        }
    }

    public void reload(int[][] payment, int[] weaponToRecharge){

        for(int i = 0; i < weaponToRecharge.length; i++){
            if(weaponToRecharge[i]==1){
                handlePayment(fromCubesToInt(currentPlayer.getWeaponCards().get(i).getRechargeCube()));
                currentPlayer.getWeaponCards().get(i).changeState(StateCard.HOLDING);
            }
        }

        updateCorrectAction();

    }

    public void grabWeaponCard(AbstractWeaponCard weaponCard) {
        handlePayment(fromCubesToInt(weaponCard.getRechargeCube()));
        try {
            tmpWeaponCard = gameBoard.takeWeaponCard(weaponCard,getCurrentPlayer().getX(),getCurrentPlayer().getY());
        } catch (OutOfBoundsException e) {

        }
        currentPlayer.takeWeaponCards(tmpWeaponCard,null);
        //gameBoard.getGameArena().placeAnotherWeaponCardsOnSquareSpawn(currentPlayer.getX(),currentPlayer.getY());
        //mettere un'altra weapon card
        updateCorrectAction();
    }

    public ArrayList<Player> fromArrayToArrayListPlayer(int[] defenders){
        ArrayList<Player> playerDefender = new ArrayList<>();
        for(int i = 0; i < defenders.length; i++){
            playerDefender.add(players.get(defenders[i]));
        }
        return playerDefender;
    }

    public void useWeaponCard( int indexCard,int[] orderEffect,int[][] defenders, int[][] coordinates, int[][] payment){

        AbstractWeaponCard weaponCard = currentPlayer.getWeaponCards().get(indexCard);
        System.out.println("sono nel model delle use weapon card" + orderEffect.length);
     for(int i = 0 ; i < orderEffect.length; i ++) {

         if(orderEffect[i] == 1) {
             try {
                 weaponCard.firstEffect(gameBoard,currentPlayer, fromArrayToArrayListPlayer(defenders[0]), coordinates[0]);
                 handlePayment(payment[0]);
                 for(Player player: fromArrayToArrayListPlayer(defenders[0])) {
                     players.get(players.indexOf(player)).setShoot(true);
                     shootPlayer.add(player);
                 }
             } catch (NoEffectException e) {
                 updateNotCorrectAction(e.getMessage());
                 return;
             } catch (ErrorEffectException e) {
                 updateNotCorrectAction(e.getMessage());
                 return;
             } catch (DamageTrackException e) {
                 for(Player player : players){
                     if(player.getRealPlayerBoard().numOfDamages()>= 11){
                         deadPlayer.add(player);
                     }
                 }
             }
         }

         if (orderEffect[i] == 2) {
             try {
                 weaponCard.secondEffect(gameBoard,currentPlayer, fromArrayToArrayListPlayer(defenders[1]), coordinates[1]);
                 handlePayment(payment[1]);
                 for(Player player: fromArrayToArrayListPlayer(defenders[1])) {
                     players.get(players.indexOf(player)).setShoot(true);
                     shootPlayer.add(player);
                 }
             } catch (NoEffectException e) {
                 updateNotCorrectAction(e.getMessage());
                 return;
             } catch (ErrorEffectException e) {
                 updateNotCorrectAction(e.getMessage());
                 return;
             } catch (DamageTrackException e) {
                 for(Player player : players){
                     if(player.getRealPlayerBoard().numOfDamages()>= 11){
                         deadPlayer.add(player);
                     }
                 }
             }
         }

         if (orderEffect[i] == 3) {
             try {
                 weaponCard.thirdEffect(gameBoard,currentPlayer, fromArrayToArrayListPlayer(defenders[2]), coordinates[2]);
                 handlePayment(payment[2]);
                 for(Player player: fromArrayToArrayListPlayer(defenders[2])) {
                     players.get(players.indexOf(player)).setShoot(true);
                     shootPlayer.add(player);
                 }
             } catch (NoEffectException e) {
                 updateNotCorrectAction(e.getMessage());
                 return;
             } catch (ErrorEffectException e) {
                 updateNotCorrectAction(e.getMessage());
                 return;
             } catch (DamageTrackException e) {
                 for(Player player : players){
                     if(player.getRealPlayerBoard().numOfDamages()>= 11){
                         deadPlayer.add(player);
                     }
                 }
             }
         }
     }
        System.out.println();
     currentPlayer.getWeaponCards().get(indexCard).changeState(StateCard.DISCHARGE);
     sendActionUpdateMessage();

    }

    public void usePowerUpCard(PowerUpCard powerUpCard,Player player1, Player player2,int[] coo){

        try {
            powerUpCard.effect(gameBoard, player1, player2, coo[0], coo[1]);
        }catch(DamageTrackException e){
            //deadPlayer
        }

    }

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
    public int[] retrievePayment(int[][] payment2){
        int[] payment1 = new int[3];

        for(int i = 0 ; i < payment1.length; i++)
            payment1[i] = 0;

        for(int i = 0 ; i <payment2.length; i++){
            payment1[0] = payment1[0] + payment2[i][0];
            payment1[1] = payment1[1] + payment2[i][1];
            payment1[2] = payment1[2] + payment2[i][2];
            if(payment2[i].length>3){
                for (int j = 3; j < payment2.length; j++) {
                    if (currentPlayer.getPowerUpCards().get(payment2[i][j]).getColor().equals("red")) {
                        payment1[1]++;
                    } else if (currentPlayer.getPowerUpCards().get(payment2[i][j]).getColor().equals("yellow")) {
                        payment1[2]++;
                    } else if (currentPlayer.getPowerUpCards().get(payment2[i][j]).getColor().equals("blue")) {
                        payment1[0]++;
                    }
                }
            }
        }
        return payment1;

    }

    public boolean checkValidityPayment(int[] payment, ColorCube[] toBePaid){

        int[] toBePaid1 = fromCubesToInt(toBePaid);

        if (payment.length > 3) {
            for (int i = 3; i < payment.length; i++)
                payment[i] = 0;
            for (int i = 3; i < payment.length; i++) {
                if(payment[i] >  0) {
                    if (currentPlayer.getPowerUpCards().get(payment[i]).getColor().equals("red")) {
                        payment[1]++;
                    } else if (currentPlayer.getPowerUpCards().get(payment[i]).getColor().equals("yellow")) {
                        payment[2]++;
                    } else if (currentPlayer.getPowerUpCards().get(payment[i]).getColor().equals("blue")) {
                        payment[0]++;
                    }
                }
            }
        }

        for(int i = 0; i< 3; i++){
           System.out.println(payment[i]-toBePaid1[i]<0);
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

    public void setGameRepresentation() {
        for (int i = 0; i < players.size(); i++) {
            playerRepresentation[i] = players.get(i).getStatusPlayer();
            weaponCardDescription[i] = players.get(i).getWeaponCardDescription();
            powerUpCardDescription[i] = players.get(i).getPowerUpDescription();
            featuresAvailable[i] = players.get(i).getFeaturesAvailable();
        }

    }

       public int[][] returnCoordinatesOfPlayerInGame(){
           int[][] coordinatesPlayerInGame = new int[players.size()][2];
           for(int i = 0; i < players.size(); i++){
               coordinatesPlayerInGame[i][0] = players.get(i).getX();
               coordinatesPlayerInGame[i][1] = players.get(i).getY();
           }
           return coordinatesPlayerInGame;
        }

    public void updateEndTurn(){
        //set delle ammo e delle weapon a on board

    }

    public void updatePlayerDeath(){
        //inizio del turno del player morto!
        // -> respawn = TRUE
        //end turn della morte del player!
        //restore playerBoard
        //distribuzione dei punti
        // marchi rimangono!
        //tolto un teschio -> final frenzy!!!
        //invochi set frenzy
        //sendUpdateAction()!
    }

    }



