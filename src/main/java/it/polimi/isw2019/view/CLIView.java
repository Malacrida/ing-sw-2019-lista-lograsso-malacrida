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

public class CLIView extends Observable<PlayerMove> implements Observer<MoveMessage>, VisitorView {

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
      //  if(message.getNicknamePlayer() == null) {
            message.accept(this);
         //   return;
       // }
       // else if(message.getNicknamePlayer().equals(nicknamePlayer)) {
            //    message.accept(this);
          //      return;
         //   }
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
        printWeaponCards(player.getWeaponCard());

        if(player.getName().compareTo(nicknamePlayer) == 0) {
            printPowerUpCards(player.getPowerUpCard());
            System.out.println(player.getScore());
        }

    }

    public int[][] insertCoordinates(int num){
        int[][] tmpMovement = new int[num][];
        int raw,column;
        String tmpRaw, tmpColumn;
        Scanner input = new Scanner(System.in);
        boolean okInput = false;
        boolean end = false;
        int i = 0;
        do {
            do {
                    System.out.println("Insert raw or -1 to terminate:");
                    tmpRaw = input.nextLine();
                    raw = Integer.parseInt(tmpRaw);
                    System.out.println("Insert column  or -1 to terminate: ");
                    tmpColumn = input.nextLine();
                    column = Integer.parseInt(tmpColumn);

                    if (raw >= 0 && raw <= 2 && column >= 0 && column <= 3){
                        tmpMovement[i][0] = raw;
                        tmpMovement[i][1] = column;
                        okInput = true;
                    }
                    else if(raw == -1 || column == -1){
                        tmpMovement[i][0] = 0;
                        tmpMovement[i][1] = 0;
                        okInput = true;
                        end = true;
                    }

                } while (!okInput);

        }while(i<num || !end);

        return tmpMovement;
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
        nickname = input.nextLine();
        System.out.println("Insert phrase:");
        phrase = input.nextLine();

        notifyObservers(new FirstMessage(this, nickname, phrase));
    }

    @Override
    public void visitOkRegistration(RegistrationPlayer registrationPlayer) {

        Scanner input = new Scanner(System.in);
        setActionHero(registrationPlayer.getActionHero());
        setNicknamePlayer(registrationPlayer.getNicknamePlayer());

        System.out.println("ok registration, wait for other players to enter the game!");
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

    public void insertPayment(ArrayList<String> cubes, ArrayList<InterfacePowerUpCard> powerUpCardsPayment, int cardChoice){
        Scanner input = new Scanner(System.in);

        boolean endInsertment = false;
        int numEffect = 1;
        int tmopIndex = 0;
        int count = 0;
        String tmpInput;
        String tmpX,tmpY;
        endInsertment = false;

        do {
            System.out.println("Insert 1 if you want to insert a cube or 2 if you want to insert a powerUp if you have to pay for this effect" +
                    "otherwise press 0");
            tmpInput= input.next();

            if(Integer.parseInt(tmpInput)== 0){
                endInsertment = true;
            }
            else if(Integer.parseInt(tmpInput) == 1) {
                System.out.println("Insert the color of the cube :");
                cubes.add(tmpInput);
            }
            else if(Integer.parseInt(tmpInput)== 2) {
                if (powerUpCards.isEmpty()) {
                    System.out.println("you don't have a card you can pay with!");
                } else {
                    int tmp = -1;
                    boolean end = false;
                    do {
                        System.out.println("Insert the index of the powerUp you want to use");
                        tmpInput = input.next();
                        if(Integer.parseInt(tmpInput) >= 0 && Integer.parseInt(tmpInput)< powerUpCards.size() && Integer.parseInt(tmpInput)!= cardChoice){
                            powerUpCardsPayment.add(powerUpCards.get(Integer.parseInt(tmpInput)));
                            end = true;
                        }
                    } while (!end);
                }
            }

        } while (!endInsertment);
    }


    public void handleInsertWeaponCard(ArrayList<PlayerInterface> playerToAttack, ArrayList<Integer> coordinates, ArrayList<String> payment, ArrayList<InterfacePowerUpCard> powerUpCardPayment, ArrayList<PlayerInterface> playersToAttack){

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
            System.out.println("Insert 1 if you want to insert a cube or 2 if you want to insert a powerUp if you have to pay for this effect" +
                    "otherwise press 0");
            tmpInput= input.next();

            if(Integer.parseInt(tmpInput)== 0){
                endInsertment = true;
            }
            else if(Integer.parseInt(tmpInput) == 1) {
                System.out.println("Insert the color of the cube :");
                payment.add(tmpInput);
            }
            else if(Integer.parseInt(tmpInput)== 2) {
                if (powerUpCards.isEmpty()) {
                    System.out.println("you don't have a card you can pay with!");
                } else {
                    int tmp = -1;
                    boolean end = false;
                    printPowerUpCards(powerUpCards);
                    do {
                        System.out.println("Insert the index of the powerUp you want to use");
                        tmpInput = input.next();
                        if(Integer.parseInt(tmpInput) >= 0 && Integer.parseInt(tmpInput)< powerUpCards.size()){
                            powerUpCardPayment.add(powerUpCards.get(Integer.parseInt(tmpInput)));
                            end = true;
                        }
                    } while (!end);
                }
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
            ArrayList<InterfacePowerUpCard> paymentFirstEffectPowerUp = new ArrayList<>();
            ArrayList<PlayerInterface> playerToAttackSecondEffect = new ArrayList<>();
            ArrayList<Integer> coordinatesSecondEffect = new ArrayList<>();
            ArrayList<String> paymentSecondEffect = new ArrayList<>();
            ArrayList<InterfacePowerUpCard> paymentSecondEffectPowerUp = new ArrayList<>();
            ArrayList<PlayerInterface> playerToAttackThirdEffect = new ArrayList<>();
            ArrayList<Integer> coordinatesThirdEffect = new ArrayList<>();
            ArrayList<String> paymentThirdEffect = new ArrayList<>();
            ArrayList<InterfacePowerUpCard> paymentThirdEffectPowerUp = new ArrayList<>();
            UseWeaponCard useWeaponCard = new UseWeaponCard(nicknamePlayer, useWeaponCardMessage.getWeaponCard());

            useWeaponCard.initializeMessage();

            String tmpInput;

            printWeaponCard(useWeaponCardMessage.getWeaponCard());

            do{
                do {
                    //riguardarlo, gli effetti possono essere inseriti in ordine sparso
                    //ripenso!!
                    //contatore!


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
                            handleInsertWeaponCard(playerToAttackFirstEffect, coordinatesFirstEffect, paymentFirstEffect, paymentFirstEffectPowerUp,useWeaponCardMessage.getPlayersToAttack());
                            useWeaponCard.setFirstEffect(playerToAttackFirstEffect,coordinatesFirstEffect,paymentFirstEffect, paymentFirstEffectPowerUp);
                        }
                        else if(numEffect == 2) {
                            handleInsertWeaponCard(playerToAttackSecondEffect, coordinatesSecondEffect, paymentSecondEffect, paymentSecondEffectPowerUp,useWeaponCardMessage.getPlayersToAttack());
                            useWeaponCard.setSecondEffect(playerToAttackSecondEffect,coordinatesSecondEffect,paymentSecondEffect,paymentSecondEffectPowerUp);
                        }
                        else if(numEffect == 3) {
                            handleInsertWeaponCard(playerToAttackThirdEffect, coordinatesThirdEffect, paymentThirdEffect, paymentThirdEffectPowerUp,useWeaponCardMessage.getPlayersToAttack());
                            useWeaponCard.setThirdEffect(playerToAttackThirdEffect,coordinatesThirdEffect,paymentThirdEffect, paymentThirdEffectPowerUp);
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
        displayErrorMessage(usePowerUpCardMessage.getError());

        Scanner input = new Scanner(System.in);

        String tmpCardChoosen;
        int cardChoosen;

        boolean inputOk = false;

        ArrayList<String> cubes = new ArrayList<>();
        ArrayList<InterfacePowerUpCard> powerUpCards = new ArrayList<>();

        do{
        System.out.println("choose one of the following PowerUp Card : ");

        for (InterfacePowerUpCard powerUpCard : usePowerUpCardMessage.getPowerUpCard())
            printPowerUpCard(powerUpCard);

        tmpCardChoosen = input.nextLine();

        cardChoosen = Integer.parseInt(tmpCardChoosen);

        if (cardChoosen >= 0 && cardChoosen < usePowerUpCardMessage.getPowerUpCard().size())
            inputOk = true;

        } while (!inputOk);

        System.out.println("the effect of the card is:");
        System.out.println(usePowerUpCardMessage.getPowerUpCard().get(cardChoosen).infoEffect());
        insertPayment(cubes,powerUpCards,cardChoosen);
        UsePowerUpCard usePowerUpCard = new UsePowerUpCard(nicknamePlayer,usePowerUpCardMessage.getPowerUpCard().get(cardChoosen));
        usePowerUpCard.setCubes(cubes);
        usePowerUpCard.setPowerUpCardInterfaces(powerUpCards);

        switch (usePowerUpCardMessage.getPowerUpCard().get(cardChoosen).getName()) {
            case "Newton":
                usePowerUpCard.setCoordinates(insertCoordinates(2));
                break;
            case "Teleporter":
                usePowerUpCard.setCoordinates(insertCoordinates(1));
                break;
            //attack e defend!
        }

        notifyObservers(usePowerUpCard);


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
        for(PlayerInterface player : players)
            printPlayerFeatures(player);

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

            for (String action : actionMessage.getActionPlayerCanPerform()) {
                System.out.println(action);
            }

            System.out.println("Choose one of the following action to be performed or -1 to end your turn");

            tmpActionChoosen = input.nextLine();
            actionChoosen = Integer.parseInt(tmpActionChoosen);

            if (actionChoosen == -1) {
                //settare una reload move vuota
                notifyObservers(new ReloadMove(nicknamePlayer));
                return;
            } else if (actionChoosen >= 0 && actionChoosen < actionMessage.getActionPlayerCanPerform().size())
                okInput = true;

        } while (!okInput);

        notifyObservers(new ChooseActionMove(nicknamePlayer, actionMessage.getIntIdAction().get(actionChoosen)));
    }

    @Override
    public void visitRun(RunMessage runMessage) {
        int[][] tmpMovement;
        Scanner input = new Scanner(System.in);
        tmpMovement = insertCoordinates(runMessage.getNumMovement());
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

                //p per le powerUpCards, c per i cubi, N per uscire
                //powerUpCard id-colore
                //cubi colore

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
            notifyObservers(new ReloadMove(getNicknamePlayer()));

        }


    }

    @Override
    public void failRegistration(FailRegistration failRegistration) {
        System.out.println("too many people : OUT");
    }
}
