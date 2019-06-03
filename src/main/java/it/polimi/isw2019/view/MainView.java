package it.polimi.isw2019.view;

import it.polimi.isw2019.message.playermove.*;
import it.polimi.isw2019.utilities.Observable;
import it.polimi.isw2019.message.movemessage.*;
import it.polimi.isw2019.utilities.Observer;

import java.util.ArrayList;
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

    public void printWeaponCard(int i){
            System.out.println("Weapon card "+ (i+1) + " : ");
            for(int j = 0; i < weaponCard[i].length; j++){
                System.out.println(weaponCard[i][j]);
            }

    }

    public void printWeaponsCard(){
        for(int i = 0; i< weaponCard.length; i++)
            printWeaponCard(i);
    }

    @Override
    public void weaponCardChoice(UseWeaponCardMessage useWeaponCardMessage) {

        Scanner input = new Scanner(System.in);
        System.out.println("choose one of the following WeaponCard : ");
        printWeaponsCard();
        int cardChoosen = input.nextInt();

        System.out.println("You've choosen the following card : \n" );
        printWeaponCard(cardChoosen);




    }

    public void setActionHero(String actionHero) {
        this.actionHero = actionHero;
    }



    public void startView(){
        SetUpMove message ;
        Scanner input = new Scanner(System.in);
        String nickname;
        String phrase;
        // nickname con eventualmente la connessione client server
        System.out.println("Insert nickname:");
        nickname = input.next();
        System.out.println("Insert phrase:");
        phrase = input.nextLine();
        notifyObservers(new FirstMessage(this,nickname,phrase));
    }

    @Override
    public void waitForStart(EndRegistration endRegistration) {
        //loop
        System.out.println("waiting for other player to enter the game!");
    }

    @Override
    public void visitOkRegistration(RegistrationPlayer registrationPlayer) {

        Scanner input = new Scanner(System.in);
        setActionHero(registrationPlayer.getActionHero());
        setNicknamePlayer(registrationPlayer.getNicknamePlayer());

        System.out.println("Your registered!");

        System.out.println("Choose one of those color copying the name :");

        for(String color : registrationPlayer.getColors()){
            System.out.println(color);
        }

        String colorChoosen = input.next();

    }
    @Override
    public void update(MoveMessage message) {

            message.accept(visitorView);
    }

    @Override
    public void visitUpdateView(UpdateMessage updateMessage) {

    }

    @Override
    public void visitSetupView(SetUpMessage setUpMessage) {

    }



    @Override
    public void visitActionView(ActionMessage actionMessage) {
            Scanner input = new Scanner(System.in);
            int actionChoosen = -1;

            do{

                for (String action : actionMessage.getActionYouCanPerform()) {
                    System.out.println(action);
                }

                System.out.println("Choose one of the following action to be performed");
                actionChoosen = input.nextInt();
            }while(actionChoosen< 0 || actionChoosen>actionMessage.getActionYouCanPerform().size());

        notifyObservers(new ChooseActionMove(nicknamePlayer,actionChoosen));
    }

    @Override
    public void errorMessageView(MoveMessage moveMessage) {
        System.out.println(((ErrorMessage)(moveMessage)).getErrorMessage());
    }

    public int[][] insertMovement(int numMovement){

        int[][] tmpMovement = new int[numMovement][2];
        Scanner input = new Scanner(System.in);
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
            //check
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

            Scanner input = new Scanner(System.in);
            printWeaponsCard();
            //check per i per i payment
            System.out.println("Choose the weapon card you want to reload or -1 to terminate (insert position) ");
            int cardChoosen = input.nextInt();

            ReloadMove reloadMove = new ReloadMove(getNicknamePlayer());

            //num di weapon card
            //manca
            int j = 0;
            int paymentChoose  = 0, k = 0;
            String[][] choice = new String[reloadMessage.getNumWeaponCard()][];
            while(cardChoosen!= -1 || j < reloadMessage.getNumWeaponCard()) {
                k = 0;
                System.out.println("Choose if you want to pay with (1) PowerUp or with (2)Cubes or -1 to exit : \n");
                paymentChoose = input.nextInt();
                while (paymentChoose != -1 || k < reloadMessage.getNumCubesForRecharge()[cardChoosen]) {

                    if (paymentChoose == '1') {
                        System.out.println("Insert the color of the cubes you want to pay with : \n");
                        choice[cardChoosen][k] = input.next();

                    } else {
                        System.out.println("Insert the number idPowerUp or -1 to terminate : \n");
                        choice[cardChoosen][k] = String.valueOf(input.nextInt());
                    }

                    k++;

                    System.out.println("Choose if you want to pay with (1) PowerUp or with (2)Cubes or -1 to exit : \n");
                    paymentChoose = input.nextInt();

                }

                j++;

                System.out.println("Choose the weapon card you want to reload");
                cardChoosen = input.nextInt();
             }
            reloadMove.setPayment(choice);
            notifyObservers(reloadMove);
            }

    @Override
    public void visitTurnView(TurnMessage turnMessage) {

    }


}
