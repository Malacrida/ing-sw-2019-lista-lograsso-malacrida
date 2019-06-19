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


    public void printWeaponCard(int i) {
        System.out.println("Weapon card " + (i + 1) + " : ");
        //for(int j = 0; i < weaponCard[i].length; j++){
        // System.out.println(weaponCard[i][j]);
        // }

    }

    public void printWeaponsCard() {
        // for(int i = 0; i< weaponCard.length; i++)
        // printWeaponCard(i);
    }

    public void displayErrorMessage(String displayErrorMessage) {
        if (displayErrorMessage != null)
            System.out.println(displayErrorMessage);
        else
            return;
    }

    @Override
    public void update(MoveMessage message) {
        message.accept(this);
    }

    public void updateGameBoard(GameBoardInterface gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void updatePlayers(ArrayList<PlayerInterface> players) {
        this.players = players;
    }

    public void updatePowerUpCards(ArrayList<InterfacePowerUpCard> powerUpCards) {
        this.powerUpCards = powerUpCards;
    }

    public void printPlayerBoard(PlayerBoardInterface playerBoard) {

        String[][] playerBoardRepresentation = playerBoard.getPlayerBoardRepresentation();

        for (int i = 0; i < playerBoardRepresentation.length; i++) {
            for (int j = 0; j < playerBoardRepresentation[i].length; j++) {
                System.out.println(playerBoardRepresentation[i][j]);
            }
            System.out.println("\n");
        }

    }

    public void printArena(ArenaInterface arena) {
        String[][] arenaRepresentation = arena.getArenaRepresentation();
        for (int i = 0; i < arenaRepresentation.length; i++) {
            for (int j = 0; j < arenaRepresentation[i].length; j++) {
                System.out.println(arenaRepresentation[i][j]);
            }
            System.out.println("\n");
        }

    }

    public void printWeaponCard(WeaponCardInterface weaponCard) {
        String[][] weaponCardRepresentation = weaponCard.getWeaponCardDescription();

        for (int i = 0; i < weaponCardRepresentation.length; i++) {
            for (int j = 0; j < weaponCardRepresentation[i].length; j++) {
                System.out.println(weaponCardRepresentation[i][j]);
            }
            System.out.println("\n");
        }
    }

    public void printPowerUpCard(InterfacePowerUpCard powerUp) {
        String[][] powerUpRepresentation = powerUp.getPowerUpCardRepresentation();

        for (int i = 0; i < powerUpRepresentation.length; i++) {
            for (int j = 0; j < powerUpRepresentation[i].length; j++) {
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

    public void printPowerUpCards(ArrayList<InterfacePowerUpCard> powerUpCard) {
        for (InterfacePowerUpCard powerUp : powerUpCard)
            printPowerUpCard(powerUp);
    }

    public void printWeaponCards(ArrayList<WeaponCardInterface> weaponCard) {
        for (WeaponCardInterface weapon : weaponCard)
            printWeaponCard(weapon);
    }

    public void printPlayerFeatures(PlayerInterface player) {

        printPlayerBoard(player.getPlayerBoard());
        printPowerUpCards(player.getPowerUpCard());
        printWeaponCards(player.getWeaponCard());
        System.out.println(player.getScore());

    }

    public void setActionHero(String actionHero) {
        this.actionHero = actionHero;
    }

    public void startView() {

        SetUpMove message;
        Scanner input = new Scanner(System.in);
        String nickname;
        String phrase;
        // nickname con eventualmente la connessione client server
        System.out.println("Insert nickname:");
        nickname = input.next();
        System.out.println("Insert phrase:");
        phrase = input.nextLine();

        notifyObservers(new FirstMessage(this, nickname, phrase));
    }

    @Override
    public void visitOkRegistration(RegistrationPlayer registrationPlayer) {

        Scanner input = new Scanner(System.in);
        setActionHero(registrationPlayer.getActionHero());
        setNicknamePlayer(registrationPlayer.getNicknamePlayer());

        /*
        while (true) {
            //timestamp che ad ogni tot fa il printf
            System.out.println("Your registered!");
        }*/

    }

    @Override
    public void visitStartTurn(StartTurn startTurn) {

        Scanner input = new Scanner(System.in);
        boolean inputOk = false;
        String tmpChoice;
        int choice;

        do {
            System.out.println("choose one of the following card to decide where to spawn :");

            printPowerUpCards(startTurn.getPowerUpCard());

            tmpChoice = input.next();

            choice = Integer.parseInt(tmpChoice);
            if (choice >= 0 && choice <= startTurn.getPowerUpCard().size()) {
                inputOk = true;
            }

        } while (!inputOk);

        notifyObservers(new PowerUpChoice(nicknamePlayer, startTurn.getPowerUpCard().get(choice).getIdPowerUpCard()));

    }

    @Override
    public void weaponCardChoice(ChoiceWeaponCard choiceWeaponCard) {

        displayErrorMessage(choiceWeaponCard.getError());
        Scanner input = new Scanner(System.in);
        String tmpCardChoosen;
        int cardChoosen;
        boolean inputOk = false;
        ArrayList<InterfacePowerUpCard> tmpPowerUpCards = new ArrayList<>();

        do {
            System.out.println("choose one of the following WeaponCard : ");

            for (WeaponCardInterface weaponCard : choiceWeaponCard.getWeaponCards())
                printWeaponCard(weaponCard);

            tmpCardChoosen = input.nextLine();

            cardChoosen = Integer.parseInt(tmpCardChoosen);

            if (cardChoosen >= 0 && cardChoosen < choiceWeaponCard.getWeaponCards().size())
                inputOk = true;

        } while (!inputOk);

        String[] payment = new String[choiceWeaponCard.getWeaponCards().get(cardChoosen).getNumCubes() - 2];

        for (int i = 1, j = 0; i < choiceWeaponCard.getWeaponCards().get(cardChoosen).getNumCubes(); i++, j++) {

            printWeaponCard(choiceWeaponCard.getWeaponCards().get(cardChoosen));

            System.out.println("insert the payment of that card: \n" +
                    "Insert : cubes-color or PU to choose a PowerUpCard ");

            payment[j] = input.next();

            String tmpPowerUp;
            if (payment[j].compareTo("PU") > 0) {
                inputOk = false;

                do {


                    System.out.println("Choose one of the following powerUp cards to pay, inserting the index:");
                    printPowerUpCards(choiceWeaponCard.getPowerUpCards());

                    tmpPowerUp = input.next();

                    if (Integer.parseInt(tmpPowerUp) >= 0 && Integer.parseInt(tmpPowerUp) <= choiceWeaponCard.getPowerUpCards().size()) {
                        tmpPowerUpCards.add(choiceWeaponCard.getPowerUpCards().get(Integer.parseInt(tmpPowerUp)));
                        inputOk = true;
                    }

                } while (!inputOk);
            }

            notifyObservers(new WeaponCardChoice(nicknamePlayer, cardChoosen, payment, tmpPowerUpCards, choiceWeaponCard.isGrab()));

        }
    }

    public void handleInsertment(ArrayList<PlayerInterface> playerToAttack, ArrayList<Integer> coordinates, ArrayList<String> payment, ArrayList<PlayerInterface> playersToAttack){

        Scanner input = new Scanner(System.in);

        boolean endInsertment = false;
        int numEffect = 1;
        int tmopIndex = 0;
        int count = 0;
        String tmpInput;
        String tmpX,tmpY;

        System.out.println("for the effect number"+ numEffect+" :");
        endInsertment = false;
        do {
            System.out.println("If you want to attack one or more player , insert the index of them " +
                    "If you want to end the action/ don't want to insert no one, insert -1");
            tmpInput = input.next();

            if (Integer.parseInt(tmpInput) >= 0 && Integer.parseInt(tmpInput) < playersToAttack.size()) {
                playerToAttack.add(playersToAttack.get(Integer.parseInt(tmpInput)));
                count++;
            }

            if(Integer.parseInt(tmpInput) == -1){
                endInsertment = true;
            }

        } while (!endInsertment);

        endInsertment = false;
        do {
            System.out.println("If you want to move one or more player or use the effect of the card upon a square , insert the coordinate of it " +
                    "If you want to end the action/ don't want to insert no one, insert -1 ");
            tmpX = input.next();
            tmpY = input.next();
            if (Integer.parseInt(tmpX) >= 0 && Integer.parseInt(tmpX) <=2 && Integer.parseInt(tmpY) >= 0 && Integer.parseInt(tmpY)<=3) {
                coordinates.add(Integer.parseInt(tmpX));
                coordinates.add(Integer.parseInt(tmpY));
            }

            if(Integer.parseInt(tmpX) == -1 || Integer.parseInt(tmpY) == -1){
                endInsertment = true;
            }

        } while (!endInsertment);

        endInsertment = false;
        do {
            System.out.println("Insert cubes-color or powerUp-color if you have to pay for this effect" +
                    "otherwise press out");
            tmpInput= input.next();

            if(tmpInput.compareTo("out") > 0){
                endInsertment = true;
            }
            else {
                payment.add(tmpInput);
            }

        } while (!endInsertment);
    }
    @Override
    public void useWeaponCard(UseWeaponCardMessage useWeaponCardMessage) {

            boolean inputOk = false;
            int numEffect = 1;
            Scanner input = new Scanner(System.in);

            ArrayList<PlayerInterface> playerToAttackFirstEffect = new ArrayList<>();
            ArrayList<Integer> coordinatesFirstEffect = new ArrayList<>();
            ArrayList<String> paymentFirstEffect = new ArrayList<>();
            ArrayList<PlayerInterface> playerToAttackSecondEffect = new ArrayList<>();
            ArrayList<Integer> coordinatesSecondEffect = new ArrayList<>();
            ArrayList<String> paymentSecondEffect = new ArrayList<>();
            ArrayList<PlayerInterface> playerToAttackThirdEffect = new ArrayList<>();
            ArrayList<Integer> coordinatesThirdEffect = new ArrayList<>();
            ArrayList<String> paymentThirdEffect = new ArrayList<>();

            UseWeaponCard useWeaponCard = new UseWeaponCard(nicknamePlayer);

            useWeaponCard.initializeMessage();

            String tmpInput;

            printWeaponCard(useWeaponCardMessage.getWeaponCard());

            do{
                do {

                    System.out.println("Press Y if you want to use the effect number " + numEffect + "otherwise press N");
                    tmpInput = input.next();

                    if(tmpInput.compareTo("N") > 0|| tmpInput.compareTo("Y")> 0) {
                        inputOk = true;
                    }
                    } while (!inputOk);
                    inputOk = false;
                    if (tmpInput.compareTo("N") > 0) {
                        numEffect++;

                    } else if (tmpInput.compareTo("Y") > 0) {

                        if(numEffect == 1) {
                            handleInsertment(playerToAttackFirstEffect, coordinatesFirstEffect, paymentFirstEffect, useWeaponCardMessage.getPlayersToAttack());
                            useWeaponCard.setFirstEffect(playerToAttackFirstEffect,coordinatesFirstEffect,paymentFirstEffect);
                        }
                        else if(numEffect == 2) {
                            handleInsertment(playerToAttackSecondEffect, coordinatesSecondEffect, paymentSecondEffect, useWeaponCardMessage.getPlayersToAttack());
                            useWeaponCard.setSecondEffect(playerToAttackSecondEffect,coordinatesSecondEffect,paymentSecondEffect);
                        }
                        else if(numEffect == 3) {
                            handleInsertment(playerToAttackThirdEffect, coordinatesThirdEffect, paymentThirdEffect, useWeaponCardMessage.getPlayersToAttack());
                            useWeaponCard.setThirdEffect(playerToAttackThirdEffect,coordinatesThirdEffect,paymentThirdEffect);
                        }
                    }
                }while(numEffect < useWeaponCardMessage.getWeaponCard().getNumMaxEffect());

                notifyObservers(useWeaponCard);

            }

    @Override
    public void powerUpChoice(ChoicePowerUpCard choicePowerUpCard) {

        displayErrorMessage(choicePowerUpCard.getError());

        Scanner input = new Scanner(System.in);

        String tmpCardChoosen;
        int cardChoosen;

        boolean inputOk = false;

        do {
            System.out.println("choose one of the following PowerUp Card : ");

            for (InterfacePowerUpCard powerUpCard : choicePowerUpCard.getPowerUpCards())
                printPowerUpCard(powerUpCard);

            tmpCardChoosen = input.nextLine();

            cardChoosen = Integer.parseInt(tmpCardChoosen);

            if (cardChoosen >= 0 && cardChoosen < choicePowerUpCard.getPowerUpCards().size())
                inputOk = true;

        } while (!inputOk);

        notifyObservers(new PowerUpChoice(nicknamePlayer, choicePowerUpCard.getPowerUpCards().get(cardChoosen).getIdPowerUpCard()));

    }


    @Override
    public void usePowerUpCard(UsePowerUpCardMessage usePowerUpCardMessage) {

    }

    @Override
    public void waitForStart(EndRegistration endRegistration) {
        //chooseColor
        System.out.println("waiting for other player to enter the game!");
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

        do {

            for (String action : actionMessage.getActionYouCanPerform()) {
                System.out.println(action);
            }

            System.out.println("Choose one of the following action to be performed or -1 to end your turn");

            tmpActionChoosen = input.nextLine();
            actionChoosen = Integer.parseInt(tmpActionChoosen);

            if (actionChoosen == -1) {
                //settare una reload move vuota
                notifyObservers(new ReloadMove(nicknamePlayer,null));
                return;
            } else if (actionChoosen >= 0 && actionChoosen < actionMessage.getActionYouCanPerform().length)
                okInput = true;

        } while (!okInput);

        notifyObservers(new ChooseActionMove(nicknamePlayer, actionChoosen));
    }

    @Override
    public void visitRun(RunMessage runMessage) {
        int[][] tmpMovement = new int[runMessage.getNumMovement()][2];
        Scanner input = new Scanner(System.in);
        //display gameboard
        String tmpRaw, tmpColumn;
        int raw = 0, column = 0;

        boolean okInput = false;
        for (int i = 0; i < runMessage.getNumMovement(); i++) {

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
        notifyObservers(new RunMove(nicknamePlayer, tmpMovement));
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
        printWeaponCards(reloadMessage.getWeaponCardInterfaces());
        //check per i per i payment
        boolean inputOk = false;
        int cardChoosen = -1;
        char choice;
        String tmpChoice;
        //metterci il numero giusto di cubi
        String[][] payment = new String[reloadMessage.getWeaponCardInterfaces().size()][];


        for (int i = 0; i < reloadMessage.getWeaponCardInterfaces().size(); i++) {
            do {
                System.out.println("Insert Y if you want to reload the following weapon card, otherwise insert N");
                printWeaponCard(reloadMessage.getWeaponCardInterfaces().get(i));

                tmpChoice = input.next();

                if (tmpChoice.compareTo("Y") > 0 || tmpChoice.compareTo("N")> 0)
                    inputOk = true;

            } while (!inputOk);

            if(tmpChoice.compareTo("Y") > 0){
                //loop per inserire dal giocatore il pagamento
                System.out.println("Insert cubes-color or powerUp-color to pay");
                payment[i][0] = input.next();
            }
            else{
                payment[i][0] = null;
            }
            notifyObservers(new ReloadMove(getNicknamePlayer(),payment));

        }


    }


}
