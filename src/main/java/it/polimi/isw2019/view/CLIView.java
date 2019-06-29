package it.polimi.isw2019.view;

import it.polimi.isw2019.message.playermove.*;
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

    private int[] featuresAvailable;
    private String[] weaponCard;
    private String[] powerUpCard;
    private int[] playerBoard;
    private String gameBoard;
    private int index;

    public CLIView(String nicknamePlayer){
        this.nicknamePlayer = nicknamePlayer;
    }
    public String getNicknamePlayer() {
        return nicknamePlayer;
    }

    public void setNicknamePlayer(String nicknamePlayer) {
        this.nicknamePlayer = nicknamePlayer;
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

    public void printStatus(){

        System.out.println("Position on arena : x " + playerBoard[1] + " , y " + playerBoard[2]);
        System.out.println("Blue cubes available :" + featuresAvailable[0]);
        System.out.println("Red cubes available :" + featuresAvailable[1]);
        System.out.println(" Yellow cubes available: " + featuresAvailable[2]);

        for (String weaponCard : weaponCard){
            System.out.println(weaponCard);
        }
        for (String powerUp : powerUpCard){
            System.out.println(powerUp);
        }

        System.out.println("Blue damage :" + playerBoard[3]);
        System.out.println("Green damage :" + playerBoard[4]);
        System.out.println("Grey damage :" + playerBoard[5]);
        System.out.println("Violet damage :" + playerBoard[6]);
        System.out.println("Yellow damage :" + playerBoard[7]);
        System.out.println("Blue mark :" + playerBoard[8]);
        System.out.println("Green mark :" + playerBoard[9]);
        System.out.println("Grey mark :" + playerBoard[10]);
        System.out.println("Violet mark :" + playerBoard[11]);
        System.out.println("Yellow mark :" + playerBoard[12]);

    }

    public int insertSingleCoordinate(int rawOrColum){
        int insert ;
        Scanner input = new Scanner(System.in);
        boolean inputOk = false;
        String tmp;
        do {

            System.out.println("Insert the coordinate or -1 to terminate : \n ");
            tmp = input.next();
            insert = Integer.parseInt(tmp);

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

    public int[] insertCoordinatesVector(int num){

        int[] movement = new int[num*2];
        boolean end = false;
        int i = 0;

            do {

                movement[i] = insertSingleCoordinate(2);

                if(movement[i] != -1)
                    movement[i+1] = insertSingleCoordinate(3);
                else
                    end = true;

                i++;

                if(i==num)
                    end = true;

                } while (!end);

        return movement;
    }

    public int[][] insertCoordinates(int num){

        int[][] movement = new int[num][2];
        boolean end = false;
        int i = 0;

        do {

            movement[i][0] = insertSingleCoordinate(2);

            if(movement[i][0] != -1)
                movement[i+1][0] = insertSingleCoordinate(3);
            else
                end = true;

            i++;

            if(i==num)
                end = true;

        } while (!end);

        return movement;
    }

    public void insertPayment(int[] payment){
        boolean endInsertment = false;
        Scanner input = new Scanner(System.in);
        String tmp ;
        if(powerUpCard!= null)
            System.out.println("you can pay both with power up or with cubes");
        if(payment[0] != 0){
                do {
                    System.out.println("Insert the number of blue cubes. Press 0 not to pay with cubes");
                    tmp = input.next();
                    if (Integer.parseInt(tmp) >= 0 && Integer.parseInt(tmp) <= payment[0]) {
                        payment[0] = Integer.parseInt(tmp);
                        endInsertment = true;
                    }
                }while(!endInsertment);
                endInsertment = false;
            }
            if(payment[1]!= 0){
                do {
                    System.out.println("Insert the number of red cubes.  Press 0 not to pay with cubes");
                    tmp = input.next();
                    if (Integer.parseInt(tmp) >= 0 && Integer.parseInt(tmp) <= payment[1]) {
                        payment[1] = Integer.parseInt(tmp);
                        endInsertment = true;
                    }
                }while(!endInsertment);

                endInsertment = false;
            }
            if(payment[2]!= 0){
                System.out.println("Insert the number of  cubes");
                do {
                    System.out.println("Insert the number of yellow cubes. Press 0 not to pay with cubes ");
                    tmp = input.next();
                    if (Integer.parseInt(tmp) >= 0 && Integer.parseInt(tmp) <= payment[2]) {
                        payment[2] = Integer.parseInt(tmp);
                        endInsertment = true;
                    }
                }while(!endInsertment);

                endInsertment = false;
            }
        if(powerUpCard!= null) {
            int i = 3;
            do {
                System.out.println("Insert " + i + " if you want to pay with this power up card, otherwise press -1");
                tmp = input.next();
                if (Integer.parseInt(tmp) >= 3 && Integer.parseInt(tmp) <= payment.length) {
                    payment[Integer.parseInt(tmp)] = Integer.parseInt(tmp);
                    i++;
                }
                if(i ==  payment.length)
                    endInsertment = true;
                if(Integer.parseInt(tmp) == -1)
                    endInsertment = true;
            }while(!endInsertment);
        }
    }

    @Override
    public void useWeaponCard(UseWeaponCardMessage useWeaponCardMessage){
        String cardChoosen;
        boolean endInsertment = false;
        int index = 0;
        Scanner input = new Scanner(System.in);
        do{
            System.out.println("choose one of the following weapon card");
            for(int i = 0 ; i < weaponCard.length; i ++){
                if(useWeaponCardMessage.getWeaponCard()[i] == 1){
                    System.out.println("press" + i + " to use this card");
                    System.out.println(weaponCard[i]);
                }
            }

            cardChoosen = input.next();

            if(Integer.parseInt(cardChoosen) >= 0 && Integer.parseInt(cardChoosen)<weaponCard.length) {
                index = Integer.parseInt(cardChoosen);
                endInsertment = true;
            }

        }while(!endInsertment);

        String tmp;
        int[][] coordinates = null;
        int numMaxEffect = useWeaponCardMessage.getFeaturesForEffect()[index][0];
        int[][] payment = new int[numMaxEffect][useWeaponCardMessage.getFeaturesAvailable().length];
        int[][] peopleToBeShoot = new int[numMaxEffect][useWeaponCardMessage.getFeaturesForEffect()[index][1]];
        endInsertment = false;

        //first index -> maxEffect

        int[][] playersToAttack = new int[numMaxEffect][useWeaponCardMessage.getPlayersToAttack().length];

        int i = 0;

        endInsertment = false;
        boolean endChoice = false;
        do {
            do {
                System.out.println("insert the effect you want to use  :");
                tmp = input.next();

                if(Integer.parseInt(tmp) >= 0 && Integer.parseInt(tmp)<=numMaxEffect){
                    index = Integer.parseInt(tmp);
                    endChoice = true;
                }
            }while(!endChoice);

            if(useWeaponCardMessage.getFeaturesForEffect()[index][1]!= 0){
                coordinates[index] = insertCoordinatesVector(useWeaponCardMessage.getFeaturesForEffect()[index][1]);
            }

            if(useWeaponCardMessage.getFeaturesForEffect()[index][2]!=0){
                //inseert people
            }
            payment[index] = featuresAvailable;
            insertPayment(payment[index]);

            endChoice = false;

        }while(!endInsertment);

        UseWeaponCard useWeaponCard = new UseWeaponCard(nicknamePlayer,index);
        useWeaponCard.setHandleEffectCoordinates(coordinates);
        useWeaponCard.setHandleEffectPayment(payment);
        useWeaponCard.setPeopleToBeShoot(peopleToBeShoot);
        notifyObservers(useWeaponCard);

    }

    public void startView() {

        Scanner input = new Scanner(System.in);
        String phrase;
        System.out.println("Insert phrase:");
        phrase = input.nextLine();

        actionHero = phrase;

        notifyObservers(new FirstMessage(this, nicknamePlayer, phrase));
    }

    @Override
    public void firstPlayerChooseMap(FirstMessageFirstPlayer firstMessageFirstPlayer) {

        boolean inputOk = false;

        index = firstMessageFirstPlayer.getIdPlayer();

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
        // case of choice of powerUpCard to be discarded

        do {
            System.out.println("choose one of the following PowerUp Card :");


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
                //reload vuota
                notifyObservers(new ReloadMove(nicknamePlayer,null,null));
                return;
            } else if (actionChoosen >= 0 && actionChoosen < actionMessage.getActionPlayerCanPerform().size())
                okInput = true;

        } while (!okInput);

        notifyObservers(new ChooseActionMove(nicknamePlayer, actionMessage.getIntIdAction().get(actionChoosen)));
    }

    @Override
    public void usePowerUpCard(UsePowerUpCardMessage usePowerUpCardMessage) {

        displayErrorMessage(usePowerUpCardMessage.getError());

        Scanner input = new Scanner(System.in);

        String tmpCardChoosen;
        int cardChoosen;

        boolean inputOk = false;

        int[] features = new int[3];
        ArrayList<InterfacePowerUpCard> powerUpCards = new ArrayList<>();
        int[] payment = featuresAvailable.clone();
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

        insertPayment(payment);
        UsePowerUpCard usePowerUpCard = new UsePowerUpCard(nicknamePlayer,usePowerUpCardMessage.getPowerUpCard().get(cardChoosen));
        usePowerUpCard.setCubes(features);
        usePowerUpCard.setPowerUpCardInterfaces(powerUpCards);

        switch (usePowerUpCardMessage.getPowerUpCard().get(cardChoosen).getName()) {
            case "Newton": {
                System.out.println("move another player :");
                usePowerUpCard.setCoordinates(insertCoordinates(2));
            }
                break;
            case "Teleporter": {
                System.out.println("move yourself");
                usePowerUpCard.setCoordinates(insertCoordinates(1));
                break;
            }
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

        playerBoard = updateMessage.getPlayersDescription()[index];
        //featuresAvailable = updateMessage.getFeaturesAvailable()[index];
        weaponCard = updateMessage.getWeaponCardDescription()[index];
        powerUpCard = updateMessage.getPowerUpCardDescription()[index];
        gameBoard = updateMessage.getGameBoardDescription();
        //print di tutto
        System.out.println("end turn of " + updateMessage.getNicknamePlayer());
    }

    @Override
    public void visitRun(RunMessage runMessage) {
        int[][] tmpMovement;

        //System.out.println(runMessage.getNumMovement());
        tmpMovement = insertCoordinates(runMessage.getNumMovement());

        notifyObservers(new RunMove(nicknamePlayer, tmpMovement));
    }

    @Override
    public void visitGrab(GrabMessage grabMessage) {

        Scanner input = new Scanner(System.in);
        GrabMove grabMove = new GrabMove(nicknamePlayer);
        int choiceCard = -2;
        int i = 0;
        int positionWeaponCard = -1;
        boolean inputOk = false;
        String tmpInput;

        if(grabMessage.isGrabWeapon()) {
            for(String weaponCard : grabMessage.getWeaponCardAvailable()) {
                System.out.println("press" + i + "to take the following card");
                System.out.println(weaponCard);
                i ++;
            }

            do {
                tmpInput = input.next();
                if (Integer.parseInt(tmpInput) >= 0 || Integer.parseInt(tmpInput) <= 2) {
                    inputOk = true;
                }
            } while (!inputOk);
            positionWeaponCard = Integer.parseInt(tmpInput);

            //System.out.println("weaponCard :" + grabMessage.getWeaponCardAvailable().size() + "power up :" + grabMessage.getYourPowerUpCard().size());

            System.out.println("number of cubes needed" + grabMessage.getWeaponCardAvailable()[i]);
            insertPayment(grabMessage.getFeaturesAvailable());
            grabMove.setPositionWeaponCard(positionWeaponCard);
            grabMove.setPayment(grabMessage.getFeaturesAvailable());
        }

        notifyObservers(grabMove);
        }


    @Override
    public void visitReload(ReloadMessage reloadMessage) {


        Scanner input = new Scanner(System.in);
        for(int i = 0; i < weaponCard.length;i++){
            if(reloadMessage.getWeaponYouCanReload()[i] == 1)
                    System.out.println(weaponCard[i]);
        }

        //check per i per i payment
        boolean inputOk = false;
        String tmpChoice;

        int[][] payment = new int[reloadMessage.getWeaponYouCanReload().length][2];

        int[] tmpWeaponCard = reloadMessage.getWeaponYouCanReload().clone();

        for (int i = 0; i < reloadMessage.getFeaturesAvailable().length; i++) {
            if (reloadMessage.getWeaponYouCanReload()[i] == 1) {
                do {
                    System.out.println("Insert Y if you want to reload the following weapon card, otherwise insert N");
                    System.out.println(weaponCard[i]);

                    tmpChoice = input.next();

                    if (tmpChoice.compareTo("Y") > 0 || tmpChoice.compareTo("N") > 0)
                        inputOk = true;

                } while (!inputOk);

                if (tmpChoice.compareTo("Y") > 0) {
                    payment[i] = featuresAvailable.clone();
                    insertPayment(payment[i]);
                }
            }
        }

        notifyObservers(new ReloadMove(nicknamePlayer,tmpWeaponCard,payment));
    }

    @Override
    public void failRegistration(FailRegistration failRegistration) {
        System.out.println("too many people : OUT");
    }
}
