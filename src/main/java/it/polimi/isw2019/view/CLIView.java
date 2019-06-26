package it.polimi.isw2019.view;

import it.polimi.isw2019.message.playermove.*;
import it.polimi.isw2019.model.*;
import it.polimi.isw2019.model.powerupcard.InterfacePowerUpCard;
import it.polimi.isw2019.model.powerupcard.PowerUpCard;
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

    public void setActionHero(String actionHero) {
        this.actionHero = actionHero;
    }

    public void displayErrorMessage(String displayErrorMessage) {
        if (displayErrorMessage != null)
            System.out.println(displayErrorMessage);
        else
            return;
    }

    @Override
    public void update(MoveMessage message) {

        System.out.println("update : " + nicknamePlayer);

        if(message.isNotifyAll()){
            message.accept(this);
        }
        else if(nicknamePlayer == message.getNicknamePlayer()) {
            if (message.getNicknamePlayer().compareTo(nicknamePlayer) == 0) {
                message.accept(this);
            }
        }
    }

    public int insertSingleCoordinate(int rawOrColum){
        int insert ;
        Scanner input = new Scanner(System.in);
        boolean inputOk = false;

        do {

            System.out.println("Insert the coordinate or -1 to terminate : \n ");

            insert = Integer.parseInt(input.next());

            if(insert >=0 && insert <= rawOrColum){
                inputOk = true;
            }
            else if(insert == -1){
                inputOk = true;
            }
            else{
                System.out.println("try again");
            }


        }while(!inputOk);
        return insert;
    }

    public int[][] insertCoordinates(int num){

        int[][] movement = new int[num][2];
        boolean end = false;
        int i = 0;

            do {

                movement[i][0] = insertSingleCoordinate(2);

                if(movement[i][0] != -1)
                    movement[i][1] = insertSingleCoordinate(3);
                else
                    end = true;

                i++;

                if(i==num)
                    end = true;

                } while (!end);

        return movement;
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

                    for(InterfacePowerUpCard powerUpCard : powerUpCards)
                        System.out.println(powerUpCard.getPowerUpCardRepresentation());

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


    public void startView() {

        SetUpMove message;
        Scanner input = new Scanner(System.in);
        String nickname;
        String phrase;
        // nickname con eventualmente la connessione client server
        System.out.println("Insert nickname:");
        nickname = input.nextLine();

        nicknamePlayer = nickname;

        System.out.println("Insert phrase:");
        phrase = input.nextLine();

        actionHero = phrase;

        notifyObservers(new FirstMessage(this, nickname, phrase));
    }

    @Override
    public void firstPlayerChooseMap(FirstMessageFirstPlayer firstMessageFirstPlayer) {

        boolean inputOk = false;
        int indexMap = -1, indexColor = -1;
        if(firstMessageFirstPlayer.getPossibleMaps()!= null) {
            System.out.println("Choose one of the following Arena :");
            for(int i = 0; i < firstMessageFirstPlayer.getPossibleMaps().length; i++)
                        System.out.println(i +" " + firstMessageFirstPlayer.getPossibleMaps()[i]);


            do {
                Scanner input = new Scanner(System.in);
                String tmpInput = input.next();

                if (Integer.parseInt(tmpInput) >= 0 && Integer.parseInt(tmpInput) <= firstMessageFirstPlayer.getPossibleMaps().length) {
                    indexMap = Integer.parseInt(tmpInput);
                    inputOk = true;
                }
            } while (!inputOk);
        }

        inputOk = false;
        System.out.println("Choose one of the following color :");
        for(int i = 0; i < firstMessageFirstPlayer.getColorAvailable().size(); i++)
            System.out.println(i +" " + firstMessageFirstPlayer.getColorAvailable().get(i));
        do {
            Scanner input = new Scanner(System.in);

            String tmpInput = input.next();

            if(Integer.parseInt(tmpInput) >= 0 && Integer.parseInt(tmpInput)<= firstMessageFirstPlayer.getColorAvailable().size()){
                indexColor = Integer.parseInt(tmpInput);
                inputOk = true;
            }
        } while(!inputOk);

        notifyObservers(new ChooseMapMove(nicknamePlayer,indexMap,indexColor));


    }

    @Override
    public void powerUpChoice(ChoicePowerUpCard choicePowerUpCard) {

        displayErrorMessage(choicePowerUpCard.getError());

        Scanner input = new Scanner(System.in);

        String tmpCardChoosen;
        int cardChoosen;

        boolean inputOk = false;

        // case respawn or not!
        do {
            System.out.println("choose one of the following PowerUp Card to respawn : ");


            for(InterfacePowerUpCard powerUpCard : choicePowerUpCard.getPowerUpCards())
                System.out.println(powerUpCard.getPowerUpCardRepresentation());

            tmpCardChoosen = input.nextLine();

            cardChoosen = Integer.parseInt(tmpCardChoosen);

            if (cardChoosen >= 0 && cardChoosen < choicePowerUpCard.getPowerUpCards().size())
                inputOk = true;

        } while (!inputOk);

        notifyObservers(new PowerUpChoice(nicknamePlayer, choicePowerUpCard.getPowerUpCards().get(cardChoosen).getIdPowerUpCard()));

    }


    @Override
    public void visitActionView(ActionMessage actionMessage) {

        displayErrorMessage(actionMessage.getError());
        Scanner input = new Scanner(System.in);
        int actionChoosen = -1;
        String tmpActionChoosen;

        boolean okInput = false;

        do {
            int i = 0;
            for (String action : actionMessage.getActionPlayerCanPerform()) {
                System.out.println(i + " " + action);
                i++;
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
    public void weaponCardChoice(ChoiceWeaponCard choiceWeaponCard) {

        displayErrorMessage(choiceWeaponCard.getError());
        Scanner input = new Scanner(System.in);
        String tmpCardChoosen;
        int cardChoosen;
        boolean inputOk = false;
        ArrayList<InterfacePowerUpCard> tmpPowerUpCards = new ArrayList<>();

        do {
            System.out.println("choose one of the following WeaponCard : ");

                for(WeaponCardInterface weaponCard : choiceWeaponCard.getWeaponCards())
                    System.out.println(weaponCard.getWeaponCardDescription());

            tmpCardChoosen = input.nextLine();

            cardChoosen = Integer.parseInt(tmpCardChoosen);

            if (cardChoosen >= 0 && cardChoosen < choiceWeaponCard.getWeaponCards().size())
                inputOk = true;

        } while (!inputOk);


        String[] payment = new String[choiceWeaponCard.getWeaponCards().get(cardChoosen).getNumCubes() - 2];

        for (int i = 1, j = 0; i < choiceWeaponCard.getWeaponCards().get(cardChoosen).getNumCubes(); i++, j++) {

            for(WeaponCardInterface weaponCard : choiceWeaponCard.getWeaponCards())
                System.out.println(weaponCard.getWeaponCardDescription());

            System.out.println("insert the payment of that card: \n" +
                    "Insert : cubes-color or PU to choose a PowerUpCard ");

            payment[j] = input.next();

            String tmpPowerUp;
            if (payment[j].compareTo("PU") > 0) {
                inputOk = false;

                do {


                    System.out.println("Choose one of the following powerUp cards to pay, inserting the index:");
                    for(InterfacePowerUpCard powerUpCard : powerUpCards)
                        System.out.println(powerUpCard.getPowerUpCardRepresentation());

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
            //scelta prima
             System.out.println(useWeaponCard.getWeaponCard().getWeaponCardDescription());

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
            System.out.println(powerUpCard.getPowerUpCardRepresentation());

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

        this.nicknamePlayer = endRegistration.getNicknamePlayer();

        System.out.println("waiting for other player to enter the game!");

    }


    @Override
    public void visitUpdateView(UpdateMessage updateMessage) {

        /*updateGameBoard(updateMessage.getGameBoard());
        updatePlayers(updateMessage.getPlayers());
        printGameBoard(updateMessage.getGameBoard());
        for(PlayerInterface player : players)
            printPlayerFeatures(player);
        */
        System.out.println(updateMessage.getGameBoard().getArenaInterface().toString());
        for(PlayerInterface player : updateMessage.getPlayers()){
            System.out.println(player.toString());
            /*if(player.getPlayerBoard()!= null)
                System.out.println(player.getPlayerBoard().getPlayerBoardRepresentation());*/

        }
        System.out.println("end turn of " + updateMessage.getNicknamePlayer());
    }

    @Override
    public void visitSetupView(SetUpMessage setUpMessage) {
    }

    @Override
    public void visitRun(RunMessage runMessage) {
        int[][] tmpMovement;

        System.out.println(runMessage.getNumMovement());
        tmpMovement = insertCoordinates(runMessage.getNumMovement());

        notifyObservers(new RunMove(nicknamePlayer, tmpMovement));
    }

    @Override
    public void visitGrab(GrabMessage grabMessage) {

        Scanner input = new Scanner(System.in);
        GrabMove grabMove = new GrabMove(nicknamePlayer);
        int choiceCard = -2;
        int positionWeaponCard = -1;
        boolean inputOk = false;
        String tmpInput;

        if(grabMessage.isGrabWeapon()){
            do {
                System.out.println("Insert 0/1/2 to grab " +
                        "one of weaponCard, otherwise press -1");
                // grabMove.setCardSelection()
                tmpInput = input.next();
                if (Integer.parseInt(tmpInput) >= 0 || Integer.parseInt(tmpInput) <= 2) {
                    inputOk = true;
                }
            }while(!inputOk);
            positionWeaponCard = Integer.parseInt(tmpInput);

            String payment;

            System.out.println("If you've choosen to grab a WeaponCard, insert (eventually) the color of the payment");
            payment = input.next();

            grabMove.setPositionWeaponCard(positionWeaponCard);
            grabMove.setPayment(payment);
        }

        else{

        }

        notifyObservers(grabMove);

    }

    @Override
    public void visitReload(ReloadMessage reloadMessage) {


        Scanner input = new Scanner(System.in);
        for(WeaponCardInterface weaponCard : reloadMessage.getWeaponCardInterfaces())
            System.out.println(weaponCard.getWeaponCardDescription());
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
                System.out.println(reloadMessage.getWeaponCardInterfaces().get(i).getWeaponCardDescription());

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
