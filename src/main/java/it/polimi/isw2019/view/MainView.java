package it.polimi.isw2019.view;

import it.polimi.isw2019.utilities.Observable;
import it.polimi.isw2019.message.movemessage.*;
import it.polimi.isw2019.utilities.Observer;
import it.polimi.isw2019.message.playermove.PlayerMove;
import it.polimi.isw2019.message.playermove.RunGrabMove;
import it.polimi.isw2019.message.playermove.RunMove;
import it.polimi.isw2019.message.playermove.SetUpMove;

import java.util.Scanner;

public class MainView extends Observable<PlayerMove> implements Observer<MoveMessage>, VisitorView {

    VisitorView visitorView;
    private String nicknamePlayer;
    private String actionHero;
    private String gameBoard;
    private String[][] playerBoard;
    private String[][] weaponCard;
    private String[][] powerUpCard;
    private int temporaryScore;



    public String getNicknamePlayer() {
        return nicknamePlayer;
    }

    public void setNicknamePlayer(String nicknamePlayer) {
        this.nicknamePlayer = nicknamePlayer;
    }

    public String getActionHero() {
        return actionHero;
    }

    public String[][] getPlayerBoard() {
        return playerBoard;
    }

    public void setPlayerBoard( String[][] playerBoard) {
        this.playerBoard = playerBoard;
    }

    public  String[][] getWeaponCard() {
        return weaponCard;
    }

    public void setWeaponCard(String[][] weaponCard) {
        this.weaponCard = weaponCard;
    }

    public  String[][] getPowerUpCard() {
        return powerUpCard;
    }

    public void setPowerUpCard( String[][] powerUpCard) {
        this.powerUpCard = powerUpCard;
    }

    public int getTemporaryScore() {
        return temporaryScore;
    }

    public void setTemporaryScore(int temporaryScore) {
        this.temporaryScore = temporaryScore;
    }

    public String getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(String gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void setActionHero(String actionHero) {
        this.actionHero = actionHero;
    }

    @Override
    public void update(MoveMessage message) {

            message.visitView(visitorView);
    }

    @Override
    public void visitUpdateView(UpdateMessage updateMessage) {

    }

    @Override
    public void visitSetupView(MoveMessage moveMessage) {
        SetUpMove message ;
        Scanner input = new Scanner(System.in);
        String nickname;
        String phrase;
        String color;
        char gameMood;
        // nickname con eventualmente la connessione client server
        System.out.println("Insert nickname:");
        nickname = input.nextLine();
        System.out.println("Insert phrase:");
        phrase = input.nextLine();
        for(int i = 0; i< ((SetUpMessage)(moveMessage)).getColorAvailable().size(); i++)
            System.out.println("Color available :" + ((SetUpMessage)(moveMessage)).getColorAvailable());

        System.out.println("Insert color:");
        color = input.nextLine();
        System.out.println("Tapping the first upper case letter of the name of the game mode " +
                "to choose one of them : /n 1) N -> Normal ; 2) T -> Terminator ; 3) B ->Boot; ");
        //consume only one char
        gameMood = input.next().charAt(0);
        message = new SetUpMove(this,nickname,phrase,color, gameMood);

        notifyObservers(message);

    }



    @Override
    public void visitActionView(MoveMessage moveMessage) {

    }

    @Override
    public void errorMessageView(MoveMessage moveMessage) {
        System.out.println(((ErrorMessage)(moveMessage)).getErrorMessage());
    }

    public int[][] insertMovement(int numMovement){
        int[][] tmpMovement = new int[numMovement][2];
        Scanner input = new Scanner(System.in);
        //check se ne vuoi fare meno di quelli segnati
        //display gameboard

        for(int i=0;i<numMovement;i++){
            System.out.println("Insert raw :");
            tmpMovement[i][0] = input.nextInt();
            System.out.println("Insert column : ");
            tmpMovement[i][1] = input.nextInt();

        }
        return tmpMovement;
    }

    //check per l'inserimento corretto dei caratteri
    public char[][] insertGrab(){
        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to pick up a weapon card (W)  or an ammo card (A)?");
        char cardChoice = input.next().charAt(0);
        char weaponChoice = 'A';
        if(cardChoice == 'W'){
            System.out.println("Select B-C-D to take one of the following weapon card :");
            //print delle tue weapon card
            weaponChoice = input.next().charAt(0);

        }

        char[][] cardSelection = new char[1][2];

        cardSelection[0][0] = cardChoice;
        cardSelection[0][1] = weaponChoice;

        return cardSelection;
    }

    @Override
    public void visitRun(RunMessage runMessage) {
        notifyObservers(new RunMove(insertMovement(runMessage.getNumMovement())));
    }

    @Override
    public void visitRunGrab(RunGrabMessage runGrabMessage) {

        Scanner input = new Scanner(System.in);
        RunGrabMove runGrabMove = new RunGrabMove();
        runGrabMove.setMovement(insertMovement(0));

        char c;

        System.out.println("Insert 'A' if you want to grab an AmmoCard or 'W' if you want to grab an ammo or '0' if you don't want to grab anything");
        // runGrabMove.setCardSelection()
        c = input.next().charAt(0);
        runGrabMove.setCardSelection(c);
        int positionWeaponCard;

        System.out.println("If you've choosen to grab a WeaponCard, insert 0/1/2 to grab " +
                "one of weaponCard, otherwise press -1");
        positionWeaponCard = input.nextInt();

        System.out.println("If you've choosen to grab a WeaponCard, insert (eventually) the color of the payment");
        c = input.next().charAt(0);
        runGrabMove.setPositionWeaponCard(positionWeaponCard);
        notifyObservers(runGrabMove);
    }

    @Override
    public void visitReload(ReloadMessage reloadMessage) {

    }

    @Override
    public void visitTurnView(TurnMessage turnMessage) {

    }
}
