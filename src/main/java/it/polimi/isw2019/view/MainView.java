package it.polimi.isw2019.view;

import it.polimi.isw2019.message.playermove.*;
import it.polimi.isw2019.model.*;
import it.polimi.isw2019.model.powerupcard.InterfacePowerUpCard;
import it.polimi.isw2019.model.weaponcard.WeaponCardInterface;
import it.polimi.isw2019.utilities.Observable;
import it.polimi.isw2019.message.movemessage.*;
import it.polimi.isw2019.utilities.Observer;

import java.util.ArrayList;
import java.util.Scanner;

public class MainView extends Observable<PlayerMove> implements Observer<MoveMessage>, VisitorView {

    private String nicknamePlayer;
    private String actionHero;

    private GameBoardInterface gameBoard;
    private ArrayList<PlayerInterface> players;

    //arrayList OtherPlayerBoard!
    //MyPlayerBoard
    //MyWeaponCards
    //My Score

    private ArrayList<InterfacePowerUpCard> powerUpCards;

    public String getNicknamePlayer() {
        return nicknamePlayer;
    }

    public void setNicknamePlayer(String nicknamePlayer) {
        this.nicknamePlayer = nicknamePlayer;
    }

    public String getActionHero() {
        return actionHero;
    }


    public void printWeaponCard(int i){
            System.out.println("Weapon card "+ (i+1) + " : ");
            //for(int j = 0; i < weaponCard[i].length; j++){
               // System.out.println(weaponCard[i][j]);
           // }

    }

    public void printWeaponsCard(){
       // for(int i = 0; i< weaponCard.length; i++)
           // printWeaponCard(i);
    }

    public void displayErrorMessage(String displayErrorMessage){
        if(displayErrorMessage != null)
            System.out.println(displayErrorMessage);
        else
            return;
    }

    @Override
    public void update(MoveMessage message) {
        message.accept(this);
    }

    public void updateGameBoard(GameBoardInterface gameBoard){
        this.gameBoard = gameBoard;
    }

    public void updatePlayers(ArrayList<PlayerInterface> players){
        this.players = players;
    }

    public void updatePowerUpCards(ArrayList<InterfacePowerUpCard> powerUpCards){
        this.powerUpCards = powerUpCards;
    }

    public void printPlayerBoard(PlayerBoardInterface playerBoard){

        String[][] playerBoardRepresentation = playerBoard.getPlayerBoardRepresentation();

        for(int i=0;i<playerBoardRepresentation.length; i++){
            for(int j = 0; j< playerBoardRepresentation[i].length; j ++) {
                System.out.println(playerBoardRepresentation[i][j]);
            }
            System.out.println("\n");
        }

    }

    public void printArena(ArenaInterface arena){
        String[][] arenaRepresentation = arena.getArenaRepresentation();
        for(int i = 0; i< arenaRepresentation.length; i++) {
            for (int j = 0; j < arenaRepresentation[i].length; j++) {
                System.out.println(arenaRepresentation[i][j]);
            }
            System.out.println("\n");
        }

    }

    public void printWeaponCard(WeaponCardInterface weaponCard){
        String[][] weaponCardRepresentation = weaponCard.getWeaponCardDescription();

        for (int i = 0; i < weaponCardRepresentation.length; i ++){
            for (int j = 0; j < weaponCardRepresentation[i].length; j++){
                System.out.println(weaponCardRepresentation[i][j]);
            }
            System.out.println("\n");
        }
    }

    public void printPowerUpCard(InterfacePowerUpCard powerUp){
        String[][] powerUpRepresentation = powerUp.getPowerUpCardRepresentation();

        for (int i = 0; i < powerUpRepresentation.length; i ++){
            for (int j = 0; j < powerUpRepresentation[i].length; j++){
                System.out.println(powerUpRepresentation[i][j]);
            }
            System.out.println("\n");
        }
    }

    public void printGameBoard(GameBoardInterface gameBoard) {
        printArena(gameBoard.getArenaInterface());

        for (WeaponCardInterface weapon : gameBoard.getWeaponCard(ColorCube.BLUE))
            printWeaponCard(weapon);

        for (WeaponCardInterface weapon : gameBoard.getWeaponCard(ColorCube.RED))
            printWeaponCard(weapon);

        for (WeaponCardInterface weapon : gameBoard.getWeaponCard(ColorCube.YELLOW))
            printWeaponCard(weapon);


    }

    public void printPowerUpCards(ArrayList<InterfacePowerUpCard> powerUpCard){
        for(InterfacePowerUpCard powerUp : powerUpCard)
            printPowerUpCard(powerUp);
    }

    public void printWeaponCards(ArrayList<WeaponCardInterface> weaponCard){
        for(WeaponCardInterface weapon : weaponCard)
            printWeaponCard(weapon);
    }

    public void printPlayerFeatures(PlayerInterface player){

        printPlayerBoard(player.getPlayerBoard());
        printPowerUpCards(player.getPowerUpCard());
        printWeaponCards(player.getWeaponCard());
        System.out.println(player.getScore());

    }

    @Override
    public void visitStartTurn(StartTurn startTurn) {
        printPowerUpCards(startTurn.getPowerUpCard());

    }

    @Override
    public void weaponCardChoice(ChoiceWeaponCard choiceWeaponCard) {

        displayErrorMessage(choiceWeaponCard.getError());
        Scanner input = new Scanner(System.in);
        String tmpCardChoosen;
        int cardChoosen;
        boolean inputOk = false;

        do {
            System.out.println("choose one of the following WeaponCard : ");

            for(WeaponCardInterface weaponCard: choiceWeaponCard.getWeaponCards())
                    printWeaponCard(weaponCard);

            tmpCardChoosen = input.nextLine();

            cardChoosen = Integer.parseInt(tmpCardChoosen);

            if(cardChoosen >= 0 && cardChoosen < choiceWeaponCard.getWeaponCards().size())
                    inputOk = true;

        }while(!inputOk);

        inputOk = false;
        boolean exit = false, effectIncorrect = false , payment = false;

        int[] numEffect = new int[choiceWeaponCard.getWeaponCards().get(cardChoosen).getNumMaxEffect()];

        ArrayList<Integer> effect;

        int countEffect = 0;

        String tmpEffect;

        int effectInserted = -1;

        //mettere in useWeaponCard
        do{

            do {


                System.out.println("Insert  -1  if you don't want to perform the " + (countEffect + 1) + " effect");
                tmpEffect = input.nextLine();

                effectInserted = Integer.parseInt(tmpEffect);

                if( effectInserted == -1 || (effectInserted >= 0 && effectInserted <= numEffect.length ))
                    effectIncorrect = true;

            }while(! effectIncorrect);

            if(effectInserted != -1){

                if(choiceWeaponCard.getWeaponCards().get(cardChoosen).getNumMaxCoordinates()!=0){
                    //inserire coordinate
                }

                if(choiceWeaponCard.getWeaponCards().get(cardChoosen).getNumMaxDefenders()!= 0){
                    //inserire maxDefenders
                }
                //insertCubesForReload
            }



        }while(!inputOk || !exit || countEffect < choiceWeaponCard.getWeaponCards().get(cardChoosen).getNumMaxEffect());


    }


    @Override
    public void powerUpChoice(ChoicePowerUpCard choicePowerUpCard){

        displayErrorMessage(choicePowerUpCard.getError());

        Scanner input = new Scanner(System.in);

        String tmpCardChoosen;
        int cardChoosen;

        boolean inputOk = false;

        do {
            System.out.println("choose one of the following PowerUp Card : ");

            for(InterfacePowerUpCard powerUpCard: choicePowerUpCard.getPowerUpCards())
                printPowerUpCard(powerUpCard);

            tmpCardChoosen = input.nextLine();

            cardChoosen = Integer.parseInt(tmpCardChoosen);

            if(cardChoosen >= 0 && cardChoosen < choicePowerUpCard.getPowerUpCards().size())
                inputOk = true;

        }while(!inputOk);

        notifyObservers(new PowerUpChoice(nicknamePlayer,choicePowerUpCard.getPowerUpCards().get(cardChoosen).getIdPowerUpCard()));

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

        System.out.println("waiting for other player to enter the game!");
    }

    @Override
    public void visitOkRegistration(RegistrationPlayer registrationPlayer) {

        Scanner input = new Scanner(System.in);
        setActionHero(registrationPlayer.getActionHero());
        setNicknamePlayer(registrationPlayer.getNicknamePlayer());

        //while true!!!
        System.out.println("Your registered!");

        //System.out.println("Choose one of those color copying the name :");

        /*for(String color : registrationPlayer.getColors()){
            System.out.println(color);
        }


        String colorChoosen = input.next();
        */

    }


    @Override
    public void visitUpdateView(UpdateMessage updateMessage) {

        updateGameBoard(updateMessage.getGameBoard());
        updatePlayers(updateMessage.getPlayers());
        printGameBoard(updateMessage.getGameBoard());



    }

    @Override
    public void visitSetupView(SetUpMessage setUpMessage) {
    }



    @Override
    public void visitActionView(ActionMessage actionMessage) {

            displayErrorMessage(actionMessage.getError());
            Scanner input = new Scanner(System.in);
            int actionChoosen = -1;
            String tmpActionChoosen;

            boolean okInput = false;

            do{

                for (String action : actionMessage.getActionYouCanPerform()) {
                    System.out.println(action);
                }

                System.out.println("Choose one of the following action to be performed");
                tmpActionChoosen = input.nextLine();
                actionChoosen = Integer.parseInt(tmpActionChoosen);

                if(actionChoosen >= 0 && actionChoosen < actionMessage.getActionYouCanPerform().length)
                    okInput = true;

            }while(!okInput);

        notifyObservers(new ChooseActionMove(nicknamePlayer,actionChoosen));
    }

    @Override
    public void visitRun(RunMessage runMessage) {
        int[][] tmpMovement = new int[runMessage.getNumMovement()][2];
        Scanner input = new Scanner(System.in);
        //display gameboard
        String tmpRaw , tmpColumn;
        int raw = 0, column = 0;

        boolean okInput = false;
        for(int i=0;i<runMessage.getNumMovement();i++) {

            do {

                System.out.println("Insert raw :");
                tmpRaw = input.nextLine();
                raw = Integer.parseInt(tmpRaw);
                System.out.println("Insert column : ");
                tmpColumn = input.nextLine();
                column = Integer.parseInt(tmpColumn);

                if (raw >= 0 && raw <= 2 && column >= 0 && column <= 3)
                    okInput = true;

            } while (!okInput);

            tmpMovement[i][0] = raw;
            tmpMovement[i][1] = column;
        }
        notifyObservers(new RunMove(nicknamePlayer,tmpMovement));
    }

    @Override
    public void visitGrab(GrabMessage grabMessage) {

        Scanner input = new Scanner(System.in);
        GrabMove grabMove = new GrabMove(nicknamePlayer);

        char c;

        System.out.println("Insert 'A' if you want to grab an AmmoCard or 'W' if you want to grab an ammo or '0' if you don't want to grab anything");
        // grabMove.setCardSelection()
        c = input.next().charAt(0);
        grabMove.setCardSelection(c);

        int positionWeaponCard;

        System.out.println("If you've choosen to grab a WeaponCard, insert 0/1/2 to grab " +
                "one of weaponCard, otherwise press -1");
        //nextLine + controllo
        positionWeaponCard = input.nextInt();

        String payment;

        System.out.println("If you've choosen to grab a WeaponCard, insert (eventually) the color of the payment");
        payment = input.next();

        grabMove.setPositionWeaponCard(positionWeaponCard);
        grabMove.setPayment(payment);

        notifyObservers(grabMove);

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



}
