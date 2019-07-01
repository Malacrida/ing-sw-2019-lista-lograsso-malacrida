package it.polimi.isw2019.model;

import it.polimi.isw2019.message.movemessage.*;
import it.polimi.isw2019.model.ammotile.AmmoTile;
import it.polimi.isw2019.model.exception.*;
import it.polimi.isw2019.model.powerupcard.PowerUpCard;
import it.polimi.isw2019.model.weaponcard.AbstractWeaponCard;

import java.util.ArrayList;

public class Player{
    private String name;
    private String actionHeroComment; //frase effetto
    private int playerID;
    private ColorPlayer color;
    private int indexPlayer;

    private boolean firstPlayer;
    private ArrayList<AbstractWeaponCard> weaponCards = new ArrayList<>();
    private ArrayList<PowerUpCard> powerUpCards = new ArrayList<>();
    private PlayerBoard playerBoard;
    private int score; // punteggio del giocatore
    // x, y, colorRoom show the player's position
    private int x;
    private int y;
    private ColorRoom colorRoom;

    private boolean frenzy;

    private boolean isTerminator;
    private boolean moveTerminator;

    private int runAndGrab;
    private int run;
    private int runAndUseWeaponCard;
    private boolean isShoot;

    private boolean respawn;

    private boolean firstTurn;

    private int numActionPerformed;
    private final int numActionToBePerformed = 1;
    private int numActionToBePerformedFrenzy;
    private int numActionCancelled;

    private int[] featuresAvailable = new int[6];

    private int stateGame;

    private boolean endTurn;
    private ArrayList<PowerUpCard> tmpPowerUpCard = new ArrayList<>();

    private boolean correctAction;

    private ArrayList<MoveMessage> messageToBeSent = new ArrayList<>();

    private String error;

    private String descriptionPlayer;

    private int[][] playerToAttack;

    private int[] statusPlayer = new int[14];

    public Player(String name, String actionHeroComment, int playerID) {
        this.name = name;
        this.actionHeroComment=actionHeroComment;
        this.playerID=playerID;
        //value out of range of play
        x=-1;
        y=-1;
        colorRoom= null;
        //it just been created. Must respawn -> false!
        respawn = false;
        firstTurn = true;

    }


    public Player(String name, String actionHeroComment) {
        this.name = name;
        this.actionHeroComment=actionHeroComment;

        x=-1;
        y=-1;
        colorRoom= null;
        //it just been created. Must respawn -> false!
        respawn = false;
        firstTurn = true;

        score = 0;
    }

    public int getNumActionPerformed() {
        return numActionPerformed;
    }

    public int getNumActionToBePerformed() {
        return numActionToBePerformed;
    }

    public ArrayList<MoveMessage> getMessageToBeSent() {
        return messageToBeSent;
    }

    public MoveMessage getSingleMessageToBeSent(){

        featuresAvailable();

        if(messageToBeSent.get(0) instanceof  ReloadMessage){
            updateMessage((ReloadMessage)(messageToBeSent.get(0)));
        }
        else if(messageToBeSent.get(0) instanceof UsePowerUpCardMessage){
            updateMessage((UsePowerUpCardMessage) (messageToBeSent.get(0)));
        }
        else if(messageToBeSent.get(0) instanceof UseWeaponCardMessage) {
            updateMessage((UseWeaponCardMessage) (messageToBeSent.get(0)));
        } else if(messageToBeSent.get(0) instanceof GrabMessage){
            updateMessage((GrabMessage) (messageToBeSent.get(0)));
        }

        return messageToBeSent.get(0);
    }

    //state game 1 -> normale, 2 -> attaccato, 3 -> difende
    public void updateMessage(UsePowerUpCardMessage usePowerUpCardMessage){
        usePowerUpCardMessage.setFeaturesAvailable(getFeaturesAvailable());
        usePowerUpCardMessage.setEffectCard(effectPowerUpCard());
        usePowerUpCardMessage.setStateCard(statePowerUp());
        //usePowerUpCardMessage.setAttacked();
        //usePowerUpCardMessage.setStateGame();

    }

    public void updateMessage(UseWeaponCardMessage useWeaponCardMessage){
        useWeaponCardMessage.setWeaponCard(getWeaponCard());
        useWeaponCardMessage.setFeaturesForEffect(getWeaponCardFeatures());
        useWeaponCardMessage.setPlayersToAttack(playerToAttack);
        useWeaponCardMessage.setFeaturesAvailable(getFeaturesAvailable());

    }

    public void setPlayerInUseWeaponCardMessage(int[][] playerInUseWeaponCardMessage){
        playerToAttack = playerInUseWeaponCardMessage;
    }

    public void updateMessage(ReloadMessage reloadMessage){
        reloadMessage.setWeaponYouCanReload(getWeaponDischarge());
    }

    public void updateMessage(GrabMessage grabMessage){
        grabMessage.setFeaturesAvailable(featuresAvailable);
    }

    public int getIndexPlayer() {
        return indexPlayer;
    }

    public void setIndexPlayer(int indexPlayer) {
        this.indexPlayer = indexPlayer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFrenzy() {
        return frenzy;
    }

    public void setFrenzy(boolean frenzy) {
        this.frenzy = frenzy;
    }

    public Player(int playerID){
        this.playerID = playerID;
    }

    public void setNicknameAndActionHeroComment(String nickName, String actionHeroComment){
        this.name = nickName;
        this.actionHeroComment = actionHeroComment;
    }
    public void setPlayerBoardAndColor (PlayerBoard playerBoard, ColorPlayer color) {
        this.playerBoard = playerBoard;
        this.color = color;
    }

    public void setFirstPlayer(boolean firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public boolean isFirstPlayer() {
        return firstPlayer;
    }

    public Player getPlayer() {
      return this;
   }

    public String getName() {
      return this.name;
   }

    public ColorPlayer getColor(){
      return this.color;
   }

    public int getY() {
        return y;
    }
    public int getX() {
        return x;
    }

    public ColorRoom getColorRoom() {
        return colorRoom;
    }

    public int getScore() {
        return score;
    }
    public int getPlayerID() {
        return playerID;
    }

    public boolean isRespawn() {
        return respawn;
    }

    public void setRespawned(boolean respawn) {
        this.respawn = respawn;
    }

    public boolean isFirstTurn() {
        return firstTurn;
    }

    public void setFirstTurn(boolean firstTurn) {
        this.firstTurn = firstTurn;
    }
    //Nel model un metodo che unisce questo del player e quello con la gameboard
    //Dalla playerMove la carta da scartare
    //Ricordarsi il cambio di stato
    //Inserimento di una nuova carta

    /**
     * method to take weapon card
     * @param insertWeaponCard weapon card taken
     * @param removeWeaponCard weapon card rejected if player has already three weapon card
     */

    public void takeWeaponCards(AbstractWeaponCard insertWeaponCard, AbstractWeaponCard removeWeaponCard) {

        if (weaponCards.size()<3) {
            weaponCards.add(insertWeaponCard);
        }
        else{
            weaponCards.remove(removeWeaponCard);
            weaponCards.add(insertWeaponCard);
        }

    }

    //nel model un metodo che unisce questo del player e quello con la gameboard
    //Dalla playerMove la carta da scartare
    //Ricordarsi il cambio di stato
    //Inserimento di una nuova carta

    /**
     * method to take power up card
     * @param insertPowerUpCard power up card taken
     * @param removePowerUpCard power up card rejected if player has already three weapon card
     * @throws TooManyPowerUpCard
     */

    public void takePowerUpCard (PowerUpCard insertPowerUpCard, PowerUpCard removePowerUpCard) throws TooManyPowerUpCard {
        if (powerUpCards.size()<3){
            powerUpCards.add(insertPowerUpCard);
        }
        else if(removePowerUpCard != null) {
            powerUpCards.remove(removePowerUpCard);
            powerUpCards.add(insertPowerUpCard);
        }
        else{
            throw new TooManyPowerUpCard();
        }

    }

    /**
     *
     * @param powerUpCard
     */
    //Cambio di stato della carta
    //rimozione che un utilizzo
    public void usePowerUpCard (PowerUpCard powerUpCard){
        //Uso della powerUp
        //Cambio di stato
        powerUpCard.changeStateCard(StateCard.DISCARD);
        powerUpCards.remove(powerUpCard);
    }

    public boolean isShoot() {
        return isShoot;
    }

    public void setShoot(boolean shoot) {
        isShoot = shoot;
    }


    /**
     * method used to pay cubes to use weapon card's effect
     * @param costRed number of red cubes
     * @param costYellow number of yellow cubes
     * @param costBlue number of blue cubes
     * @throws OutOfBoundsException
     */

    //non fare test
    public void payEffect (int costRed, int costYellow, int costBlue) throws OutOfBoundsException {
        try {
            if (costRed > 0) {
                playerBoard.removeRedCubes(costRed);
            }
        }
        catch (OutOfBoundsException e){
            throw new OutOfBoundsException(e.getInfo());
        }
        try {

            if (costYellow > 0) {
                playerBoard.removeYellowCubes(costYellow);
            }
        }
        catch (OutOfBoundsException e){
            throw new OutOfBoundsException(e.getInfo());
        }
        try {
            if (costBlue > 0){
                playerBoard.removeBlueCubes(costBlue);
            }
        }
        catch (OutOfBoundsException e){
            throw new OutOfBoundsException(e.getInfo());
        }

    }

    /**
     * take an ammo tile
     * @param ammoTile
     */

    public void takenAmmoTileColor(AmmoTile ammoTile){

        if(!ammoTile.isPowerUpCard()){
            try {
                getRealPlayerBoard().addCube(ammoTile.getFirstColor());
            } catch (TooManyCubes tooManyCubes) {
            }
        }
        try {
            getRealPlayerBoard().addCube(ammoTile.getSecondColor());
            getRealPlayerBoard().addCube(ammoTile.getThirdColor());

        } catch (TooManyCubes tooManyCubes) {

        }


    }

    /**
     * change power up in cube
     * @param powerUpCard1
     */

    public void fromPowerUpCardIntoCubes(PowerUpCard powerUpCard1){
        for (PowerUpCard powerUpCard2 : powerUpCards) {
            if (powerUpCard2.getId() == powerUpCard1.getId()) {
                try {
                    this.getRealPlayerBoard().addCube(powerUpCard2.getColorCard());
                    this.usePowerUpCard(powerUpCard2);
                }catch(TooManyCubes e){

                }
            }
        }

    }

    /**
     *
     * @param powerUpCard
     */
    public void handlePaymentWithPowerUpCards(PowerUpCard powerUpCard) {
            fromPowerUpCardIntoCubes(powerUpCard);
    }

    /**
     * add score
     * @param point score
     */

    public void addScore (int point){
        score= score+point;
    }


    /**
     * Give damage token or mark token to the player
     * @param colorPlayer!= null Color of the player who give damage
     * @param numDamage>=0 number of damage
     * @param numMark>=0 number of mark
     * @throws DamageTrackException if player, who take damage, have 11 or 12 damage token
     */

    public void sufferDamageOrMark (ColorPlayer colorPlayer, int numDamage, int numMark)throws DamageTrackException {

        try {
            if (numDamage>0){
                playerBoard.takeDamage(colorPlayer, numDamage);
            }
        }
        catch (DamageTrackException e){
            throw new DamageTrackException(colorPlayer);
        }
        finally {
            if (numMark>0){
                playerBoard.addMark(colorPlayer, numMark);
            }
        }
    }



    public int damageDoByAnotherPlayer(ColorPlayer colorPlayer){
        return playerBoard.numOfDamagesOfOneColor(colorPlayer);
    }

    public int markDoByAnotherPlayer(ColorPlayer colorPlayer){
        return playerBoard.numOfMarkOfOneColor(colorPlayer);
    }

    public int playerDamage () {
        return playerBoard.numOfDamages();
    }

    /**
     * first damage
     * @return color player
     */
    public ColorPlayer firstPlayerDoDamage (){
        return playerBoard.firstBlood();
    }

    /**
     * last damage
     * @return color of player who did last damage
     */
    public ColorPlayer lastPlayerDoDamage () { return playerBoard.killShot();}

    /**
     * getter of number of skulls on one player board
     * @return number of skulls
     */

    public int getNumberOfSkulls (){
        return playerBoard.getPlayerSkulls();
    }

    /**
     * new position of player
     * @param x first coordinate
     * @param y second coordinate
     * @param colorRoom of that square
     */

    public void changePosition (int x, int y, ColorRoom colorRoom){
        this.x=x;
        this.y=y;
        this.colorRoom=colorRoom;
    }

    /**
     * new square of player
     * @param x first coordinate
     * @param y second coordinate
     */

    public void changeSquare(int x, int y){
        this.x=x;
        this.y=y;
    }

    /**
     * new room of player
     * @param colorRoom of that square
     */

    public void changeRoom (ColorRoom colorRoom){
        this.colorRoom=colorRoom;
    }

    /**
     * reset player board if player is died
     */

    public void playerDeath (){
        playerBoard.resetAfterDeath();
    }
    /**
     * getter weapon card of this player
     * @return player's weapon card
     */


    public ArrayList<AbstractWeaponCard> getWeaponCards(){
        return this.weaponCards;
    }

    /**
     * getter power up of this player
     * @return player's power up
     */

    public ArrayList<PowerUpCard> getPowerUpCards(){
        return this.powerUpCards;
    }

    /**
     * get instance of player board not interface
     * @return player board
     */

    public PlayerBoard getRealPlayerBoard(){
        return playerBoard;
    }

    /**
     * getter of weapon card information
     * @return description of weapon card
     */

    public String[] getWeaponCardDescription(){
        String[] tmpWeaponCardDescription = new String[weaponCards.size()];
        if(weaponCards.size() == 0){
            return null;
        }
        else {
            for (int i = 0; i < weaponCards.size(); i++)
                tmpWeaponCardDescription[i] = weaponCards.get(i).getWeaponCardDescription();
        }
        return tmpWeaponCardDescription;
    }

    /**
     * getter of power up card information
     * @return description of power up card
     */

    public String[] getPowerUpDescription(){
        String[] tmpPowerUpCardDescription = new String[powerUpCards.size()];
        if(powerUpCards.size() == 0 ) return null;
        else {
            for (int i = 0; i < powerUpCards.size(); i++)
                tmpPowerUpCardDescription[i] = powerUpCards.get(i).getPowerUpCardRepresentation();
        }

        return tmpPowerUpCardDescription;

    }

    /**
     * getter of weapon card information
     * @return description of weapon card
     */

    public int[][] getWeaponCardFeatures(){
        int[][] weaponCardFeatures = new int[weaponCards.size()][3];

        for (int i = 0 ; i < weaponCards.size(); i++){
            weaponCardFeatures[i][0] = weaponCards.get(i).getMaxPossibleEffects();
            weaponCardFeatures[i][1] = weaponCards.get(i).getMaxPossibleDefenders();
            weaponCardFeatures[i][2] = weaponCards.get(i).getMaxPossibleCoordinates();
        }

        return weaponCardFeatures;
    }
    public int[] getWeaponDischarge(){
        int[] weaponDischarged = new int[weaponCards.size()];

        for(int i = 0 ; i < weaponCards.size(); i ++){
            if(weaponCards.get(i).getStateCard().equals(StateCard.DISCHARGE)){
                weaponDischarged[i] = 1;
            }
            else
                weaponDischarged[i] = 0;
        }
        return weaponDischarged;
    }

    /**
     * get weapon card
     * @return
     */

    //1-> recharged, 0 -> discharged
    public int[] getWeaponCard() {

       int[] tmpWeaponCards = new int[weaponCards.size()];

        for(int i = 0 ; i < weaponCards.size(); i++){
            if(weaponCards.get(i).getStateCard().equals(StateCard.HOLDING))
                tmpWeaponCards[i] = 1;
            else
                tmpWeaponCards[i] = 0;

        }
        return tmpWeaponCards;
    }

    //add a powerUp in the UsePowerUp if it has the correct feature

    /**
     * add a powerUp in the UsePowerUp if it has the correct feature
     * @return boolean
     */

    public boolean canAddPowerUp(){
        for(PowerUpCard powerUpCard : powerUpCards){
            if(powerUpCard.isCanBeUsed()){
                return true;
            }
        }
        return false;
    }

    /**
     * reloade weapon card
     * @return boolean
     */

    public boolean reloadedWeaponCard(){
        for(AbstractWeaponCard weaponCard : weaponCards){
            if(weaponCard.getStateCard().equals(StateCard.HOLDING)){
                return true;
            }
        }
        return  false;
    }

    public boolean notReloadedWeaponCard(){
        for(AbstractWeaponCard weaponCard : weaponCards){
            if(weaponCard.getStateCard().equals(StateCard.DISCHARGE)){
                return true;
            }
        }
        return  false;
    }

    public ActionMessage setCorrectNormalActionChooseMessages(boolean endTurn){

        ActionMessage actionMessage = new ActionMessage(getName());

        if(!endTurn) {

            run = 3;
            actionMessage.setRunAction(3);

            if (playerDamage() < 2) {
                runAndGrab = 1;
                actionMessage.setRunAndGrab(1);
            }
            else {
                runAndGrab = 2;
                actionMessage.setRunAndGrab(2);
            }
            //tolto can add power up
            if (!powerUpCards.isEmpty()) {
                actionMessage.setPowerUpAction();
            }

            if (!weaponCards.isEmpty()) {
                if (reloadedWeaponCard()) {
                    actionMessage.setUseWeaponCard();
                    if (playerDamage() >= 5) {
                        runAndUseWeaponCard = 1;
                        actionMessage.setRunUseWeaponCardAction();
                    }
                }
                if (notReloadedWeaponCard()) {
                    actionMessage.setReloadWeaponCard();
                }
            }

            messageToBeSent.clear();
            numActionCancelled = 0;
        }
        if(endTurn){

            if (!powerUpCards.isEmpty() && canAddPowerUp()) {
                actionMessage.setPowerUpAction();
            }
            if (notReloadedWeaponCard()) {
                actionMessage.setReloadWeaponCard();
            }

        }
        return actionMessage;
    }


    public ActionMessage setCorrectFrenzyActionChooseMessage(boolean afterFirstPlayer){

        ActionMessage actionMessage = new ActionMessage(getName());

        if(afterFirstPlayer){

            if(!weaponCards.isEmpty() && reloadedWeaponCard()) {
                runAndUseWeaponCard = 1;
                actionMessage.setRunReloadAndUseWeaponCard(1);
            }
            actionMessage.setRunAction(4);
            run = 4;
            actionMessage.setRunAndGrab(3);
            runAndGrab = 3;
            numActionToBePerformedFrenzy = 1;
        }

        else{
            if(!weaponCards.isEmpty()&& reloadedWeaponCard()) {
                runAndUseWeaponCard = 2;
                actionMessage.setRunReloadAndUseWeaponCard(2);
            }
            runAndGrab = 3;
            numActionToBePerformedFrenzy = 2;
            actionMessage.setRunAndGrab(3);
        }

        messageToBeSent.clear();
        numActionCancelled = 0;
        numActionPerformed = 0;

        return actionMessage;
    }


    public void setMessagesToBeSent(int idAction) {

        endTurn = false;

        messageToBeSent.clear();

        if(idAction == 0) {
            RunMessage runMessage = new RunMessage(name);
            runMessage.setNumMovement(run);
            messageToBeSent.add(runMessage);
        }
        else if(idAction == 1) {
            RunMessage runMessage = new RunMessage(name);
            runMessage.setNumMovement(run);
            messageToBeSent.add(runMessage);
            GrabMessage grabMessage = new GrabMessage(name);
            //settare grab message
            messageToBeSent.add(grabMessage);
        }
        else if(idAction == 2) {
            UseWeaponCardMessage useWeaponCard = new UseWeaponCardMessage(name);
            //rivedere cosa inserire (stringhe)
            messageToBeSent.add(useWeaponCard);
        } else if(idAction == 3) {
            RunMessage runMessage = new RunMessage(name);
            runMessage.setNumMovement(run);
            messageToBeSent.add(runMessage);
            UseWeaponCardMessage useWeaponCard = new UseWeaponCardMessage(name);
            //rivedere cosa inserire (stringhe)
            messageToBeSent.add(useWeaponCard);
        }
         else if(idAction == 4) {
            RunMessage runMessage = new RunMessage(name);
            runMessage.setNumMovement(run);
            messageToBeSent.add(runMessage);
            ReloadMessage reloadMessage = new ReloadMessage(name);
            //modifiche
            messageToBeSent.add(reloadMessage);
            UseWeaponCardMessage useWeaponCard = new UseWeaponCardMessage(name);
            //rivedere cosa inserire (stringhe)
            messageToBeSent.add(useWeaponCard);
        }
        else  if(idAction == 5) {
            ReloadMessage reloadMessage = new ReloadMessage(name);
            //modifiche
            endTurn = true;
            messageToBeSent.add(reloadMessage);
        }
        else  if(idAction == 6) {
            UsePowerUpCardMessage powerUpCardMessage = new UsePowerUpCardMessage(name);
            //fare modifiche
            messageToBeSent.add(powerUpCardMessage);
        }

    }


    public boolean updatePlayerStatusIncorrectAction(String error) {

        if(numActionCancelled <1){
            numActionCancelled++;
            messageToBeSent.get(0).setError(error);
            return true;
        }
        else{
            numActionCancelled = 0;
           // messageToBeSent.clear();
            //throw new Failethird time that you've inserted an invalid action  : this action ends!dActionException("");
            //error = "third time that you've inserted an invalid action  : this action ends!";
            messageToBeSent.clear();
            return updatePlayerMessageStatus();
        }
    }

    public void removeMessageToBeSend(){
        messageToBeSent.remove(0);
    }

    public Boolean updatePlayerMessageStatus(){

        if(!messageToBeSent.isEmpty()){
            System.out.println("ko not empty");
            return true;
        }

        else {
             if (numActionPerformed < numActionToBePerformed) {
                 numActionPerformed++;
                 System.out.println(" numAc :" + numActionPerformed);
                 messageToBeSent.clear();
                 messageToBeSent.add(setCorrectNormalActionChooseMessages(false));
                 System.out.println("size : " +messageToBeSent.size());
                 return true;
            }
             else {
                 ActionMessage tmpMessage = setCorrectNormalActionChooseMessages(true);
                if (tmpMessage.getActionPlayerCanPerform().isEmpty()) {
                    System.out.println(" numAc end :" + numActionPerformed);
                    return false;
                } else if(endTurn) {
                    System.out.println("termino");
                    return false;
                } else{
                    System.out.println("end 2");
                    return messageToBeSent.add(tmpMessage);
                }
            }
        }

    }

    public int getNumActionCancelled() {
        return numActionCancelled;
    }

    public void setNumActionCancelled(int numActionCancelled) {
        this.numActionCancelled = numActionCancelled;
    }


    public void featuresAvailable(){
        if(playerBoard!= null) {
            featuresAvailable[0] = playerBoard.numOfBlueCubes();
            featuresAvailable[1] = playerBoard.numOfRedCubes();
            featuresAvailable[2] = playerBoard.numOfYellowCubes();
            int j = 3;
            for (int i = 0; i < powerUpCards.size(); i++, j++)
                featuresAvailable[j] = powerUpCards.get(i).getId();
            for (int i = j + 1; i < 6; i++)
                featuresAvailable[i] = -1;
        }
    }

    public int[] getStatusPlayer() {
        setStatusPlayer();
        return statusPlayer;
    }

    public void setStatusPlayer() {
        statusPlayer[0] = indexPlayer;
        statusPlayer[1] = x;
        statusPlayer[2] = y;
        if(playerBoard!= null) {
            statusPlayer[3] = playerBoard.numOfDamagesOfOneColor(ColorPlayer.BLUE);
            statusPlayer[4] = playerBoard.numOfDamagesOfOneColor(ColorPlayer.GREEN);
            statusPlayer[5] = playerBoard.numOfDamagesOfOneColor(ColorPlayer.GREY);
            statusPlayer[6] = playerBoard.numOfDamagesOfOneColor(ColorPlayer.VIOLET);
            statusPlayer[7] = playerBoard.numOfDamagesOfOneColor(ColorPlayer.YELLOW);
            statusPlayer[8] = playerBoard.numOfMarkOfOneColor(ColorPlayer.BLUE);
            statusPlayer[9] = playerBoard.numOfMarkOfOneColor(ColorPlayer.GREEN);
            statusPlayer[10] = playerBoard.numOfMarkOfOneColor(ColorPlayer.GREY);
            statusPlayer[11] = playerBoard.numOfMarkOfOneColor(ColorPlayer.VIOLET);
            statusPlayer[12] = playerBoard.numOfMarkOfOneColor(ColorPlayer.YELLOW);
            statusPlayer[13] = playerBoard.getPlayerSkulls();
        }
        else {
            for(int i = 3; i < 14; i ++){
                statusPlayer[i] = -1;
            }
        }

    }

    public int[] getFeaturesAvailable() {
        featuresAvailable();
        return featuresAvailable;
    }

    public boolean[] statePowerUp(){
        boolean[] powerUpState = new boolean[powerUpCards.size()];
        for(int i = 0; i < powerUpCards.size();i++){
            powerUpState[i] = powerUpCards.get(i).isCanBeUsed();
        }
        return powerUpState;
    }

    /**
     * method of effect of power up
     * @return effect
     */

    public int[] effectPowerUpCard(){
        int[] effectPowerUp = new int[powerUpCards.size()];
        for(int i = 0 ; i < powerUpCards.size();i++){
            switch (powerUpCards.get(i).getName()){
                case "Newton":
                    effectPowerUp[i] = 2;
                    break;
                case "Teleporter":
                    effectPowerUp[i] = 1;
                    break;
                case "Tagback Grenade":
                    effectPowerUp[i] = -1;
                    break;
                case "Targeting Scope":
                    effectPowerUp[i] = -2;
                    break;
            }
        }
        return effectPowerUp;
    }

    public ArrayList<ColorPlayer> returnKillDamage(){
        ArrayList<ColorPlayer> damage = new ArrayList<>();
        for(int i = 10; i < playerBoard.getDamageTokens().size(); i++){
            damage.add(playerBoard.getDamageTokens().get(i));
        }
        return damage;
    }

    public String toString(){
        descriptionPlayer = "Name : " + name ;
        descriptionPlayer += "\n";
        // descriptionPlayer += "Current position :" + x + " " + y + " " + colorRoom.getColorRoomRepresentation() + "\n";
        descriptionPlayer += "Score : " + score + "\n";
        return descriptionPlayer;
    }


}
