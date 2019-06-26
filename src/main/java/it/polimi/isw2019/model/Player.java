package it.polimi.isw2019.model;

import it.polimi.isw2019.message.movemessage.*;
import it.polimi.isw2019.model.ammotile.AmmoTile;
import it.polimi.isw2019.model.exception.*;
import it.polimi.isw2019.model.powerupcard.InterfacePowerUpCard;
import it.polimi.isw2019.model.powerupcard.PowerUpCard;
import it.polimi.isw2019.model.weaponcard.AbstractWeaponCard;
import it.polimi.isw2019.model.weaponcard.WeaponCardInterface;

import java.util.ArrayList;

public class Player implements PlayerInterface {
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

    private boolean respawn;

    private boolean firstTurn;

    private int numActionPerformed;
    private final int numActionToBePerformed = 1;
    private int numActionToBePerformedFrenzy;
    private int numActionCancelled;

    private ArrayList<PowerUpCard> tmpPowerUpCard = new ArrayList<>();

    private boolean correctAction;

    private ArrayList<MoveMessage> messageToBeSent = new ArrayList<>();

    private String error;

    private String descriptionPlayer;
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
        return messageToBeSent.get(0);
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

    //Cambio di stato della carta
    //rimozione che un utilizzo
    public void usePowerUpCard (PowerUpCard powerUpCard){
        //Uso della powerUp
        powerUpCards.remove(powerUpCard);
    }


    public void reloadWeaponCard (AbstractWeaponCard weaponCard) throws OutOfBoundsException {
        int [] price = new int[3];
        //price = weaponCard.getPrice ();
        for (int i=0; i<3; i++){
            if (i==0){
                playerBoard.removeRedCubes(price[0]);
            }
            if (i==1){
                playerBoard.removeYellowCubes(price[1]);
            }
            if (i==2){
                playerBoard.removeBlueCubes(price[2]);
            }
        }
    }

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
            if (costBlue==2){
                playerBoard.removeBlueCubes(costBlue);
            }
        }
        catch (OutOfBoundsException e){
            throw new OutOfBoundsException(e.getInfo());
        }

    }

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

    public boolean isContainedPowerUp(PowerUpCard powerUpCard1){

        for (PowerUpCard powerUpCard : powerUpCards) {
            if (powerUpCard.getId() == powerUpCard1.getId()) {
                return true;
            }
        }
        return false;
    }

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

    public void handlePaymentWithPowerUpCards(ArrayList<PowerUpCard> powerUpCards) throws NotPossesPowerUp {

            for(PowerUpCard powerUpCard1 : powerUpCards) {
                if(!isContainedPowerUp(powerUpCard1)){
                    throw new NotPossesPowerUp("Do not have that power up");
                }
            }

            for(PowerUpCard powerUpCard1 : powerUpCards){
                fromPowerUpCardIntoCubes(powerUpCard1);
            }
    }

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



    public ColorPlayer firstPlayerDoDamage (){
        return playerBoard.firstBlood();
    }

    public ColorPlayer lastPlayerDoDamage () { return playerBoard.killShot();}

    public int getNumberOfSkulls (){
        return playerBoard.getPlayerSkulls();
    }

    public void changePosition (int x, int y, ColorRoom colorRoom){
        this.x=x;
        this.y=y;
        this.colorRoom=colorRoom;
    }

    public void changeSquare(int x, int y){
        this.x=x;
        this.y=y;
    }

    public void changeRoom (ColorRoom colorRoom){
        this.colorRoom=colorRoom;
    }

    public void playerDeath (){
        playerBoard.resetAfterDeath();
    }

    public ArrayList<AbstractWeaponCard> getWeaponCards(){
        return this.weaponCards;
    }

    public ArrayList<PowerUpCard> getPowerUpCards(){
        return this.powerUpCards;
    }

    public PlayerBoard getRealPlayerBoard(){
        return playerBoard;
    }

    @Override
    public String getActionHeroComment() {
        return actionHeroComment;
    }

    @Override
    public PlayerBoardInterface getPlayerBoard () {
        return playerBoard.getPlayerBoardInterface();
    }

    @Override
    public ArrayList<InterfacePowerUpCard> getPowerUpCard() {
        ArrayList<InterfacePowerUpCard> powerUpCardInterfaces = new ArrayList<>();

        for(PowerUpCard powerUp: powerUpCards){
            powerUpCardInterfaces.add(powerUp.getPowerUpCard());
        }
        return powerUpCardInterfaces;
    }

    @Override
    public PlayerInterface getPlayerInterface() {
        return this;
    }

    @Override
    public ArrayList<WeaponCardInterface> getWeaponCard() {
        ArrayList<WeaponCardInterface> weaponCardInterfaces = new ArrayList<>();
        for(AbstractWeaponCard weaponCard : weaponCards){
            weaponCardInterfaces.add(weaponCard.getWeaponCard());
        }
        return weaponCardInterfaces;
    }

    //add a powerUp in the
    public boolean canAddPowerUp(){
        for(PowerUpCard powerUpCard : powerUpCards){
            if(powerUpCard.isCanBeUsed()){
                return true;
            }
        }
        return false;
    }

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
            messageToBeSent.add(reloadMessage);
        }
        else  if(idAction == 6) {
            UsePowerUpCardMessage powerUpCardMessage = new UsePowerUpCardMessage(name);
            //fare modifiche
            messageToBeSent.add(powerUpCardMessage);
        }


    }


    public boolean updatePlayerStatusIncorrectAction(String error) {

        System.out.println("ko");

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
            return updatePlayerMessageStatus();
        }
    }

    public Boolean updatePlayerMessageStatus(){
        //rimuovi -> ok azione
        messageToBeSent.remove(0);

        if(!messageToBeSent.isEmpty()){
            return true;
        }
        else {
             if (numActionPerformed < numActionToBePerformed) {
                 numActionPerformed++;
                 System.out.println(" numAc :" + numActionPerformed);
                 messageToBeSent.clear();
                 messageToBeSent.add(setCorrectNormalActionChooseMessages(false));
                 return true;
            }
             else {
                 ActionMessage tmpMessage = setCorrectNormalActionChooseMessages(true);
                if (tmpMessage.getActionPlayerCanPerform().isEmpty()) {
                    System.out.println(" numAc end :" + numActionPerformed);
                    return false;
                } else {
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

    public Player fromPlayerInterfaceToPlayer(PlayerInterface playerInterface){
        if(getPlayerInterface().equals(playerInterface))
            return this;
        else
            return null;
    }

    public String toString(){
        descriptionPlayer = "Name : " + name ;
        descriptionPlayer += "\n";
        // descriptionPlayer += "Current position :" + x + " " + y + " " + colorRoom.getColorRoomRepresentation() + "\n";
        descriptionPlayer += "Score : " + score + "\n";
        return descriptionPlayer;
    }


}
