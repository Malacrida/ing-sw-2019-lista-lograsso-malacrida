package it.polimi.isw2019.network.rmi;

import it.polimi.isw2019.message.movemessage.*;
import it.polimi.isw2019.message.playermove.*;
import it.polimi.isw2019.network.network_interface.ClientInterface;
import it.polimi.isw2019.utilities.Observable;
import it.polimi.isw2019.utilities.Observer;

import java.rmi.RemoteException;

public class VirtualViewRmi extends Observable<PlayerMove> implements Observer<MoveMessage>, VirtualViewVisitorInterface {

    private String nickname;
    private ClientInterface networkHandler;
    private int idPlayer;
    private boolean active;

    public VirtualViewRmi(String nickname, ClientInterface networkHandler){
        this.nickname=nickname;
        this.networkHandler = networkHandler;
        active=true;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void startView (){
        try {
            networkHandler.startViewClient();
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public String getNickname() {
        return nickname;
    }

    public void setNetworkHandler(ClientInterface networkHandler) {
        this.networkHandler = networkHandler;
    }

    @Override
    public void update(MoveMessage message) {
        System.out.println("ricevo un messagio per: "+message.getNicknamePlayer() +" sono in :"+nickname);
        if (active && (message.getNicknamePlayer().equals(nickname) || message.isNotifyAll())){
            message.accept(this);
            System.out.println("procedo con l'update per: " + message.getNicknamePlayer());
            System.out.println("sono in : " +nickname);
        }
        if(message instanceof EndGame){
            message.accept(this);
        }
    }



    /**
     * method to notify observer and set player activity
     * @param nickname player's nickname
     * @param connection type connection
     * @throws RemoteException exception
     */
    public void createConnectionPlayer(String nickname, int connection){
        System.out.println("ricevo una connection di: " +nickname);
        System.out.println("sono in: " +this.nickname);
        if (connection==0)
            active= false;
        else if (connection==1)
            active=true;
        ConnectionMove disconnectionMove = new ConnectionMove(nickname, connection);
        notifyObservers(disconnectionMove);
    }

    /**
     * method to notify observer
     * @param player player's nickname
     * @param numAction type of action
     * @throws RemoteException exception
     */
    public void createChooseActionMove (String player, int numAction){
        ChooseActionMove chooseActionMove= new ChooseActionMove(player,numAction);
        notifyObservers(chooseActionMove);
    }

    /**
     *method to notify observer
     * @param player player's nickname
     * @param index map
     * @param color color player
     * @param mod game mod
     * @param terminator active terminator
     * @throws RemoteException exception
     */
    public void createChooseMap(String player, int index, int color, int mod, int terminator){
        ChooseMapMove chooseMapMove= new ChooseMapMove(player, index, color, mod,terminator);
        notifyObservers(chooseMapMove);
    }

    /**
     * method to notify observer
     * @param player player's nickname
     * @param movement player's movement
     * @throws RemoteException exception
     */
    public void createRun(String player,int[][] movement){
        RunMove runMove = new RunMove(player, movement);
        notifyObservers(runMove);
    }

    /**
     * method to notify observer
     * @param player player's nickname
     * @param positionWeaponCard position of Card
     * @param paymen payment to take the card
     * @throws RemoteException exception
     */
    public void createGrab(String player, int positionWeaponCard, int[] paymen){
        GrabMove grabMove= new GrabMove(player, positionWeaponCard, paymen);
        notifyObservers(grabMove);
    }

    /**
     * method to notify observer
     * @param player player's nickname
     * @param actionHero player's phrase
     * @throws RemoteException exception
     */
    public void createRegisterPlayer( String player, String actionHero){
        FirstMessage firstMessage = new FirstMessage(this, player, actionHero);
        notifyObservers(firstMessage);
    }

    /**
     * method to notify observer
     * @param player player's nickname
     * @param weaponCard Card to reload
     * @param payment payment to reload
     * @throws RemoteException exception
     */
    public void createReload(String player, int[] weaponCard, int[][] payment){
        ReloadMove reloadMove = new ReloadMove(player, weaponCard, payment);
        notifyObservers(reloadMove);
    }

    /**
     * method to notify observer
     * @param player player's nickname
     * @param idPowerUp power up selected
     * @throws RemoteException exception
     */
    public void createPowerUpChoice(String player, int idPowerUp){
        PowerUpChoice powerUpChoice= new PowerUpChoice(player,idPowerUp);
        notifyObservers(powerUpChoice);
    }

    /**
     * method to notify observer
     * @param player player's nickname
     * @param coordinates coordinates of player inside the arena and the index of each player
     * @param idPlayer id of the player
     * @param defend if the power up can be used only to attack or defend
     * @param positionPowerUp position of powerup inside the deck of the player
     * @throws RemoteException exception
     */
    public void createUsePowerUpCard(String player, int[][] coordinates, int idPlayer, boolean defend, int positionPowerUp){
        System.out.println("la sto creando");
        UsePowerUpCard usePowerUpCard = new UsePowerUpCard(player,coordinates,idPlayer,defend,positionPowerUp);
        notifyObservers(usePowerUpCard);
    }

    /**
     * method to notify observer
     * @param player player's nickname
     * @param indexWeaponCard index of weapon card choice
     * @param payment payment to take this card
     * @throws RemoteException exception
     */
    public void createWeaponCardChoice(String player, int indexWeaponCard, int[] payment){
        WeaponCardChoice weaponCardChoice = new WeaponCardChoice(player,indexWeaponCard,payment);
        notifyObservers(weaponCardChoice);
    }

    /**
     * method to notify observer
     * @param player player's nickname
     * @param weaponCard weapon card selected
     * @param effectUsed type of effect
     * @param handleEffectCoordinates handel effect coordinates
     * @param peopleToBeShoot people to shoot
     * @throws RemoteException exception
     */
    public void createUseWeaponCard (String player, int weaponCard, int[] effectUsed, int[][] handleEffectCoordinates, int[][] peopleToBeShoot){
        UseWeaponCard useWeaponCard = new UseWeaponCard(player,weaponCard, effectUsed, handleEffectCoordinates, peopleToBeShoot);
        notifyObservers(useWeaponCard);
    }

    /**
     * method to notify observer
     * @param player player's nickname
     * @param coordinates coordinates to move terminator
     * @param shootPeople people shoot by terminator
     * @param colorSpawn where terminator spawn
     * @param idPlayerToShoot player to shoot
     * @throws RemoteException exception
     */
    public void createTerminatorMove(String player, int[] coordinates, boolean shootPeople, int colorSpawn, int[] idPlayerToShoot){
        TerminatorMove terminatorMove = new TerminatorMove(player,coordinates,shootPeople,colorSpawn, idPlayerToShoot);
        notifyObservers(terminatorMove);
    }

    @Override
    public void sendActionView(ActionMessage actionMessage) {
        System.out.println("invio la move message Action a :" + actionMessage.getNicknamePlayer());

        try {
            networkHandler.createActionMessage(actionMessage.getNicknamePlayer(), actionMessage.getActionYouCanPerform(), actionMessage.getActionPlayerCanPerform(), actionMessage.getIntIdAction());
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendRun(RunMessage runMessage) {
        try {
            networkHandler.createRun(runMessage.getNicknamePlayer(), runMessage.getError(), runMessage.getNumMovement());
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendGrab(GrabMessage grabMessage) {
        try {
            networkHandler.createGrab(grabMessage.getNicknamePlayer(), grabMessage.getError(), grabMessage.getWeaponCardAvailable(), grabMessage.getFeaturesAvailable(), grabMessage.isGrabWeapon(), grabMessage.getAmmoTileDescription(), grabMessage.getPowerUpDescription());
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendReload(ReloadMessage reloadMessage) {
        try {
            networkHandler.createReload(reloadMessage.getNicknamePlayer(),reloadMessage.getFeaturesAvailable(),reloadMessage.getWeaponYouCanReload(),reloadMessage.getError());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void sendUpdateView(UpdateMessage updateMessage) {
        try {
            networkHandler.createUpdateView(updateMessage.getNicknamePlayer(),updateMessage.getGameBoardDescription(),
                    updateMessage.getPlayersDescription(), updateMessage.getFeaturesOfPlayersAvailable(),
                    updateMessage.getWeaponCardDescription(), updateMessage.getPowerUpCardDescription(),updateMessage.isNotifyAll());
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendWaitForStart(EndRegistration endRegistration) {
        try {
            networkHandler.createWaitForStart(endRegistration.getNicknamePlayer());
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void sendUseWeaponCard(UseWeaponCardMessage useWeaponCardMessage) {
        try {
            networkHandler.createUseWeaponCardMessage(useWeaponCardMessage.getNicknamePlayer(),useWeaponCardMessage.getWeaponCard(), useWeaponCardMessage.getFeaturesForEffect(), useWeaponCardMessage.getFeaturesAvailable(), useWeaponCardMessage.getPlayersToAttack(), useWeaponCardMessage.getError());
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendPowerUpChoice(ChoiceCard choiceCard) {
        try {
            System.out.println("invio ChoicePower Up a:" + choiceCard.getNicknamePlayer());
            networkHandler.createChoiceCard(choiceCard.getNicknamePlayer(), choiceCard.getDescriptionPowerUp(), choiceCard.getIdPowerUp(), choiceCard.getError(),choiceCard.isDiscardOne(),choiceCard.isPowerUp());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void sendUsePowerUpCard(UsePowerUpCardMessage usePowerUpCardMessage) {
        try {
            networkHandler.createUsePowerUpCard(usePowerUpCardMessage.getNicknamePlayer(), usePowerUpCardMessage.getFeaturesAvailable(),usePowerUpCardMessage.getStateGame(),usePowerUpCardMessage.getCanBeUsed(), usePowerUpCardMessage.getError(), usePowerUpCardMessage.getCooPlayer());
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void sendFirstPlayerChooseMap(FirstMessageFirstPlayer firstMessageFirstPlayer) {
        try {
            networkHandler.createFirstPlayerChooseMap(firstMessageFirstPlayer.getNicknamePlayer(), firstMessageFirstPlayer.getIdPlayer(), firstMessageFirstPlayer.getPossibleMaps(), firstMessageFirstPlayer.getColorAvailable());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendEndGame(EndGame endGame) {
        try {
            System.out.println("invio una endGame a: " +nickname);
            networkHandler.createEndGame(endGame.getNicknamePlayer(),endGame.getRanking(),endGame.getPoints(),endGame.getPointMax(),endGame.getWinner(),endGame.getPhrase(), endGame.isNotifyAll());
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }

    @Override
    public void sendTerminatorMessage(TerminatorMessage terminatorMessage) {
        try {
            networkHandler.createTerminatorMessage(terminatorMessage.getNicknamePlayer(),terminatorMessage.isRunOrDamage(), terminatorMessage.getColorSpawn(),terminatorMessage.getMovement(),terminatorMessage.getNumPeopleToKill(),terminatorMessage.getCooPeople(),terminatorMessage.getError());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


}
