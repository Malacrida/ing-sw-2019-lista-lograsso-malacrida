package it.polimi.isw2019.view;

import it.polimi.isw2019.message.playermove.*;
import it.polimi.isw2019.utilities.Observable;
import it.polimi.isw2019.message.movemessage.*;
import it.polimi.isw2019.utilities.Observer;

import java.io.Serializable;
import java.util.Scanner;

public class CLIView extends Observable<PlayerMove> implements Observer<MoveMessage>, VisitorView, Serializable {

    private String nicknamePlayer;
    private String actionHero;

    private int[] featuresAvailable;
    private String[] weaponCard;
    private String[] powerUpCard;
    private int[] playerBoard;
    private String gameBoard;
    private int index;
    private boolean endGame=false;

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
        if(message.isNotifyAll()){
            message.accept(this);
        }
        else if(nicknamePlayer.equals(message.getNicknamePlayer())) {
            message.accept(this);
        }
    }

    public void printStatus(){
        System.out.println("index" + index);
        System.out.println("Position on arena : x " + playerBoard[1] + " , y " + playerBoard[2]);
        if(featuresAvailable!= null) {
                System.out.println("Blue cubes available :" + featuresAvailable[0]);
                System.out.println("Red cubes available :" + featuresAvailable[1]);
                System.out.println(" Yellow cubes available: " + featuresAvailable[2]);
        }

        if(weaponCard!= null) {
            for (String weaponCard : weaponCard) {
                System.out.println(weaponCard);
            }
        }

        if(powerUpCard!= null) {
            for (String powerUp : powerUpCard) {
                System.out.println(powerUp);
            }
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
        int insert = -1;
        Scanner input = new Scanner(System.in);
        boolean inputOk = false;
        String tmp;
        do {

            System.out.println("Insert the coordinate or -1 to terminate : \n ");
            tmp = input.next();
            for( int i = 0 ; i <= rawOrColum; i ++){
                if(tmp.equals(String.valueOf(i))){
                    insert = Integer.parseInt(tmp);
                    inputOk = true;
                    break;
                }
            }
            if(tmp.equals(String.valueOf(-1))) {
                insert = Integer.parseInt(tmp);
                inputOk = true;
            }
            if(!inputOk)
                System.out.println("TRY AGAIN");

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
                movement[i][1] = insertSingleCoordinate(3);
            else
                end = true;

            i++;

            if(i==num)
                end = true;

        } while (!end);

        return movement;
    }

    public int[] insertPayment(int[] payment){
        boolean endInsertment = false;

        Scanner input = new Scanner(System.in);
        String tmp ;

        int[] realPayment = new int[payment.length];

        for(int i = 0 ; i< 3 ; i++){
            realPayment[i] = 0;
        }

        for(int i = 3 ; i<3+powerUpCard.length; i++){
            realPayment[i] = -1;
        }

        for (int i = 3+powerUpCard.length; i<payment.length; i ++){
            realPayment[i] = -2;
        }

        if(payment[0] != 0){
                do {
                    System.out.println("Insert the number of blue cubes. Press 0 not to pay with cubes");
                    tmp = input.next();
                    for(int i = 0 ; i < 4 ; i ++) {
                        if (tmp.equals(String.valueOf(i))) {
                            realPayment[0] = Integer.parseInt(tmp);
                            endInsertment = true;
                            break;
                        }
                    }
                }while(!endInsertment);
                endInsertment = false;
            }
            if(payment[1]!= 0){
                do {
                    System.out.println("Insert the number of red cubes. Press 0 not to pay with cubes");
                    tmp = input.next();
                    for(int i = 0 ; i < 4 ; i ++) {
                        if (tmp.equals(String.valueOf(i))) {
                            realPayment[1] = Integer.parseInt(tmp);
                            endInsertment = true;
                            break;
                        }
                    }
                }while(!endInsertment);

                endInsertment = false;
            }
            if(payment[2]!= 0){
                do {
                    System.out.println("Insert the number of yellow cubes. Press 0 not to pay with cubes");
                    tmp = input.next();
                    for(int i = 0 ; i < 4 ; i ++) {
                        if (tmp.equals(String.valueOf(i))) {
                            realPayment[1] = Integer.parseInt(tmp);
                            endInsertment = true;
                            break;
                        }
                    }
                }while(!endInsertment);
            }
            int i = 3;
            do {
                if(payment[i] != -1) {
                    System.out.println("Insert 0 if you want to pay with this power up card, otherwise press -1");
                    tmp = input.next();

                    if (tmp.equals(String.valueOf(0))) {
                        //nb id!
                        realPayment[Integer.parseInt(tmp)] = payment[i];
                        i++;
                    }
                    if (i == (payment.length - 1))
                        endInsertment = true;

                    if (tmp.equals(String.valueOf(-1)))
                        endInsertment = true;
                }else{
                    endInsertment = true;
                }
            }while(!endInsertment);

        return  realPayment;
    }

    public int[] choosePeopleToKill(int[][] coordinates){

        System.out.println("LENGHT COO" + coordinates.length);
        int[] peopleToKill = new int[coordinates.length];

        Scanner input = new Scanner(System.in);
        String tmp;
        boolean inputOk = false;
        for(int i = 0 ; i < coordinates.length; i ++){
            System.out.println("Index player : " + coordinates[i][0]);
            System.out.println("X position : " + coordinates[i][1]);
            System.out.println("Y position : " + coordinates[i][2] );
        }

        for(int i = 0 ; i < coordinates.length; i++){
            System.out.println("Press 1 to shoot the following player, otherwise press 0");
            tmp = input.next();
            inputOk  = false;
            do{
            if(tmp.equals(String.valueOf(1))){
                peopleToKill[i] = coordinates[i][0];
                inputOk = true;
            }
            if(tmp.equals(String.valueOf(0))){
                peopleToKill[i] = -2;
                inputOk = true;
            }
            else{
                System.out.println("TRY AGAIN");
                }
            }while (!inputOk);
        }

        return peopleToKill;
    }
    @Override
    public void useWeaponCard(UseWeaponCardMessage useWeaponCardMessage){
        String cardChoosen;
        boolean endInsertment = false;
        int index = 0;
        Scanner input = new Scanner(System.in);
        if(useWeaponCardMessage.getError()!= null)
            System.out.println(useWeaponCardMessage.getError());
        do{
            System.out.println("choose one of the following weapon card");
            for(int i = 0 ; i < weaponCard.length; i ++){
                if(useWeaponCardMessage.getWeaponCard()[i] == 1){
                    System.out.println("press " + i + " to use this card");
                    System.out.println(weaponCard[i]);
                }
            }
            cardChoosen = input.next();
            for( int i = 0 ; i < weaponCard.length; i ++){
                if(cardChoosen.equals(String.valueOf(i))){
                    index = Integer.parseInt(cardChoosen);
                    endInsertment = true;
                    break;
                }
            }
            if(!endInsertment)
                System.out.println("TRY AGAIN");

        }while(!endInsertment);

        String tmp;
        int cardIndex = index;
        int numMaxEffect = useWeaponCardMessage.getFeaturesForEffect()[index][0];
        int[][] peopleToBeShoot = new int[numMaxEffect][useWeaponCardMessage.getPlayersToAttack().length];
        int[][] coordinates =  new int[numMaxEffect][useWeaponCardMessage.getFeaturesForEffect()[index][2]];
        int[][] payment = new int[numMaxEffect][3];

        //first index -> maxEffect

        int[] effectUsed = new int[numMaxEffect];
        int i = 0;
        int j = 0;
        endInsertment = false;
        boolean endChoice = false;
        do {
            endChoice = false;
            do {

                System.out.println("insert the number of the effect you want to use or -1 to exit:");
                tmp = input.next();

                for( i = 1 ; i <= 3; i ++){
                    if(tmp.equals(String.valueOf(i)) || tmp.equals(String.valueOf(-1))){
                        effectUsed[j] = Integer.parseInt(tmp);
                        endChoice = true;
                        break;
                    }
                }

                if(tmp.equals(String.valueOf(-1))){
                    endChoice = true;
                }

               if(!endChoice){
                    System.out.println("TRY AGAIN");
                }

            }while(!endChoice);
            //index e' la carta che e' stata scelta

            if(effectUsed[j] == -1){
                peopleToBeShoot = null;
                coordinates = null;
                endInsertment = true;
            }
            else {

                if (useWeaponCardMessage.getFeaturesForEffect()[index][1] != 0) {
                    coordinates[j] = insertCoordinatesVector(useWeaponCardMessage.getFeaturesForEffect()[index][1]);
                }

                if (useWeaponCardMessage.getFeaturesForEffect()[index][2] != 0) {
                    peopleToBeShoot[j] = choosePeopleToKill(useWeaponCardMessage.getPlayersToAttack());
                }

                else{
                    coordinates[j] = null;
                    peopleToBeShoot[j] = null;
                }

                if(j == 0)
                    payment[j] = featuresAvailable.clone();
                else
                    payment[j] = payment[j-1];

                payment[j] = featuresAvailable.clone();
                insertPayment(payment[j]);

                j++;
            }

            if(j == numMaxEffect)
                endInsertment = true;

        }while(!endInsertment);

        UseWeaponCard useWeaponCard = new UseWeaponCard(nicknamePlayer,cardIndex);
        useWeaponCard.setEffectUsed(effectUsed);
        useWeaponCard.setHandleEffectCoordinates(coordinates);
        useWeaponCard.setHandleEffectPayment(payment);
        useWeaponCard.setPeopleToBeShoot(peopleToBeShoot);
        notifyObservers(useWeaponCard);

    }

    public void visitStartView(StartMessage startMessage) {

        Scanner input = new Scanner(System.in);
        String phrase;
        System.out.println("Insert phrase:");
        phrase = input.nextLine();

        actionHero = phrase;
        nicknamePlayer = startMessage.getNicknamePlayer();
        notifyObservers(new FirstMessage(this, nicknamePlayer, phrase));
    }

    @Override
    public void firstPlayerChooseMap(FirstMessageFirstPlayer firstMessageFirstPlayer) {

        boolean inputOk = false;

        index = firstMessageFirstPlayer.getIdPlayer();
        Scanner input = new Scanner(System.in);
        String tmpInput;
        int mod=-1,terminator=-1;
        int indexMap = -1, indexColor = -1;
        if(firstMessageFirstPlayer.getPossibleMaps()!= null) {
            System.out.println("Choose one of the following Arena :");
            for (int i = 0; i < firstMessageFirstPlayer.getPossibleMaps().length; i++)
                System.out.println((i + 1) + " " + firstMessageFirstPlayer.getPossibleMaps()[i]);


            do {
                tmpInput = input.next();
                for (int i = 0; i < firstMessageFirstPlayer.getPossibleMaps().length; i++) {
                    if (tmpInput.equals(String.valueOf(i + 1))) {
                        indexMap = Integer.parseInt(tmpInput);
                        inputOk = true;
                        break;
                    }
                }
                if (!inputOk) {
                    System.out.println("try again");
                }
                    /*(Integer.parseInt(tmpInput) >= 1 && Integer.parseInt(tmpInput) <= firstMessageFirstPlayer.getPossibleMaps().length) {
                    indexMap = Integer.parseInt(tmpInput);
                    inputOk = true;
                }*/
            } while (!inputOk);
            inputOk = false;
            mod = -1;
            System.out.println("Press 1 to choose the game mode with 5 skulls \n" +
                    "press 2 to choose the game mode with 8 skull:");
            do {
                tmpInput = input.next();

                if (tmpInput.equals(String.valueOf(1)) || tmpInput.equals(String.valueOf(2))) {
                    mod = Integer.parseInt(tmpInput);
                    inputOk = true;
                } else {
                    System.out.println("try again");
                }
            } while (!inputOk);

            if (firstMessageFirstPlayer.getError() == null) {
                System.out.println("Press 1 to choose the game mode terminator \n" +
                        "press 0 to choose the game mode without terminator:");

                inputOk = false;
                terminator = -1;
                do {
                    tmpInput = input.next();

                    if (tmpInput.equals(String.valueOf(0)) || tmpInput.equals(String.valueOf(1))) {
                        terminator = Integer.parseInt(tmpInput);
                        inputOk = true;
                    } else {
                        System.out.println("try again");
                    }
                } while (!inputOk);
            }
        }
        else {
            terminator = 0;
        }

        inputOk = false;
        System.out.println("Choose one of the following color :");
        for(int i = 0; i < firstMessageFirstPlayer.getColorAvailable().size(); i++)
            System.out.println(i +" " + firstMessageFirstPlayer.getColorAvailable().get(i));

        do {
            tmpInput = input.next();

            for(int i = 0 ; i <firstMessageFirstPlayer.getColorAvailable().size() ; i++){
                if(tmpInput.equals(String.valueOf(i))){
                    indexColor = Integer.parseInt(tmpInput);
                    inputOk = true;
                    break;
                }
            }
            if(!inputOk){
                System.out.println("try again");
            }
        } while(!inputOk);


        notifyObservers(new ChooseMapMove(nicknamePlayer,indexMap,indexColor,mod, terminator));


    }

    @Override
    public void visitCardChoice(ChoiceCard choiceCard) {

        displayErrorMessage(choiceCard.getError());

        Scanner input = new Scanner(System.in);

        String tmpCardChoosen;
        int cardChoosen = -1;

        boolean inputOk = false;

        // case respawn or not!
        // case of choice of powerUpCard to be discarded

        if(choiceCard.isPowerUp()) {
            if (!choiceCard.isDiscardOne()) {

                int i = 0;
                for (String powerUp : choiceCard.getDescriptionPowerUp()) {
                    System.out.println("press " + i + " to select the following power up to discard, otherwise press -1:");
                    System.out.println(powerUp);
                    i++;
                }
                do {
                    tmpCardChoosen = input.nextLine();
                    for(i = -1 ; i < choiceCard.getDescriptionPowerUp().length ; i++) {
                        if (tmpCardChoosen.equals(String.valueOf(i))) {
                            cardChoosen = Integer.parseInt(tmpCardChoosen);
                            inputOk = true;
                            break;
                        }
                    }
                    if(!inputOk)
                        System.out.println("try again");
                    //eventually see if it's correct

                } while (!inputOk);
            } else {


                    System.out.println("you should choose if you want to discard on of your powerUpCard or not" + choiceCard.getDescriptionPowerUp());
                    int i = 0;
                    for (String powerUp : powerUpCard) {
                        System.out.println("press " + i + " to select the following power up to discard from you deck, otherwise press -1:");
                        System.out.println(powerUp);
                        i++;
                    }
                    do{
                        tmpCardChoosen = input.nextLine();
                        for(i = -1; i < choiceCard.getDescriptionPowerUp().length ; i++) {
                            if (tmpCardChoosen.equals(String.valueOf(i))) {
                                cardChoosen = Integer.parseInt(tmpCardChoosen);
                                inputOk = true;
                                break;
                            }
                        }
                        if(!inputOk)
                            System.out.println("try again");
                        //eventually see if it's correct

                    } while (!inputOk);

            }
            notifyObservers(new PowerUpChoice(nicknamePlayer,cardChoosen));
        } else{
            do {
                System.out.println("you should choose if you want to discard on of your weaponCard or not" + choiceCard.getDescriptionPowerUp());
                int i = 0;
                for (String weaponCards : weaponCard) {
                    System.out.println("press " + i + " to select the following weapon to discard from you deck, otherwise press -1:");
                    System.out.println(weaponCards);
                    i++;
                }
                do{
                    tmpCardChoosen = input.nextLine();
                    for(i = -1 ; i < weaponCard.length ; i++) {
                        if (tmpCardChoosen.equals(String.valueOf(i))) {
                            cardChoosen = Integer.parseInt(tmpCardChoosen);
                            inputOk = true;
                            break;
                        }
                    }
                    if(!inputOk)
                        System.out.println("try again");
                    //eventually see if it's correct

                } while (!inputOk);

            } while (!inputOk);
            int[] payment = new int[featuresAvailable.length];
            if(cardChoosen != -1){
                insertPayment(payment);
            }
            notifyObservers(new WeaponCardChoice(nicknamePlayer,cardChoosen,payment));
        }

    }


    @Override
    public void visitActionView(ActionMessage actionMessage) {

        displayErrorMessage(actionMessage.getError());
        Scanner input = new Scanner(System.in);
        int actionChoosen = -2;
        String tmpActionChoosen;

        boolean okInput = false;

        System.out.println("Nickname " + nicknamePlayer);
        do {
            int i = 0;
            System.out.println("Choose one of the following action to be performed or -1 to end your turn");

            for (String action : actionMessage.getActionPlayerCanPerform()) {
                System.out.println(i + " " + action);
                i++;
            }

            System.out.println("Choose one of the following action to be performed or -1 to end your turn");
            System.out.println("press 9 to exit the game");

            tmpActionChoosen = input.nextLine();

            for( i = 0 ; i < actionMessage.getActionYouCanPerform().length; i ++){
                if(tmpActionChoosen.equals(String.valueOf(i))){
                    actionChoosen = Integer.parseInt(tmpActionChoosen);
                    okInput = true;
                    break;
                }
                else if(tmpActionChoosen.equals(String.valueOf(9))){
                    actionChoosen = Integer.parseInt(tmpActionChoosen);
                    break;
                }
                else if(tmpActionChoosen.equals(String.valueOf(-1))) {
                    actionChoosen = Integer.parseInt(tmpActionChoosen);
                    break;
                }
            }

            if (actionChoosen == -1) {
                int[][] payment = new int[1][1];
                payment[0][0] = -1;
                notifyObservers(new ReloadMove(nicknamePlayer,null,payment));
                return;
            }
            else if (actionChoosen==9){
                notifyObservers(new ConnectionMove(nicknamePlayer, 0));
                reconnectClient();
                return;
            }

            if(!okInput)
                System.out.println("Try again");

        } while (!okInput);

        notifyObservers(new ChooseActionMove(nicknamePlayer, actionMessage.getIntIdAction().get(actionChoosen)));
    }

    @Override
    public void usePowerUpCard(UsePowerUpCardMessage usePowerUpCardMessage) {

        if(usePowerUpCardMessage.getError()!= null)
                displayErrorMessage(usePowerUpCardMessage.getError());

        Scanner input = new Scanner(System.in);

        String tmpCardChoosen;
        int cardChoosen = 0;

        boolean inputOk = false;
        UsePowerUpCard usePowerUpCard = new UsePowerUpCard(nicknamePlayer);

        do{
            System.out.println("choose one of the following powerUp card");
            for(int i = 0 ; i < powerUpCard.length; i ++){
                    System.out.println("press " + i + " to use this card");
                    System.out.println(powerUpCard[i]);
            }
            tmpCardChoosen = input.next();

            for( int i = 0 ; i < powerUpCard.length; i ++){
                if(tmpCardChoosen.equals(String.valueOf(i))){
                    cardChoosen = Integer.parseInt(tmpCardChoosen);
                    inputOk = true;
                    break;
                }
            }
            if(!inputOk)
                System.out.println("TRY AGAIN");

        }while(!inputOk);

        String tmp;
        int cardIndex = index;
        int[] peopleToBeShoot = null;
        if(usePowerUpCard.getCoordinates().length != 0)
                peopleToBeShoot = new int[usePowerUpCard.getCoordinates().length];

        int[][] coordinates =  new int[usePowerUpCard.getPositionPowerUp()][2];


        inputOk = false;
        boolean endChoice = false;

       switch (usePowerUpCardMessage.getFeaturesAvailable()[cardChoosen]){
           case 0 :
               if(usePowerUpCard.getCoordinates().length != 0)
                    peopleToBeShoot = choosePeopleToKill(usePowerUpCard.getCoordinates());
               break;
           case 1 :
               coordinates = insertCoordinates(1);
               break;
           case 2 :
               coordinates = insertCoordinates(2);
               break;
           case 3:
               //attacchi l'ultimo che ti ha danneggiato
               usePowerUpCard.setDefend(true);
               break;
            default:

       }
       int person = 0 ;

       for(int i = 0 ; i < peopleToBeShoot.length; i ++){
           if(peopleToBeShoot[i]!= -1) {
               person = peopleToBeShoot[i];
               break;
           }
       }
        usePowerUpCard.setIdPlayer(person);
        usePowerUpCard.setPositionPowerUp(cardChoosen);
        usePowerUpCard.setCoordinates(coordinates);
        notifyObservers(usePowerUpCard);
    }

    @Override
    public void waitForStart(EndRegistration endRegistration) {

        this.nicknamePlayer = endRegistration.getNicknamePlayer();

        System.out.println("waiting for other player to enter the game!");

    }

    @Override
    public void visitUpdateView(UpdateMessage updateMessage) {
        System.out.println();
        System.out.println();
        playerBoard = updateMessage.getPlayersDescription()[index];
        featuresAvailable = updateMessage.getFeaturesOfPlayersAvailable()[index];
        weaponCard = updateMessage.getWeaponCardDescription()[index];
        powerUpCard = updateMessage.getPowerUpCardDescription()[index];
        gameBoard = updateMessage.getGameBoardDescription();

        System.out.println(updateMessage.getGameBoardDescription());
        System.out.println("x coordinate :" + playerBoard[1]);
        System.out.println("x coordinate :" + playerBoard[2]);

        printStatus();
        if(weaponCard!= null) {
            for (int i = 0; i < weaponCard.length; i++)
                System.out.println(weaponCard[i]);
         }
        if(powerUpCard!= null) {
            for (int i = 0; i < powerUpCard.length; i++)
                System.out.println(powerUpCard[i]);
        }

        System.out.println("END UPDATE");
        System.out.println();
        System.out.println();
    }

    @Override
    public void visitRun(RunMessage runMessage) {
        int[][] tmpMovement;
        System.out.println(gameBoard);
        if(runMessage.getError() != null)
            System.out.println(runMessage.getError());
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
                i++;
            }

            do {

                tmpInput = input.next();

                for(i = 0 ; i < 2 ; i ++){
                    if(tmpInput.equals(String.valueOf(i))){
                        inputOk= true;
                        break;
                    }
                }

                if(!inputOk){
                    System.out.println("TRY AGAIN");
                }
            } while (!inputOk);

            positionWeaponCard = Integer.parseInt(tmpInput);

            System.out.println("number of cubes needed" + grabMessage.getWeaponCardAvailable()[positionWeaponCard]);
            grabMove.setPositionWeaponCard(positionWeaponCard);
            grabMove.setPayment(insertPayment(grabMessage.getFeaturesAvailable()));
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

        int[] tmpWeaponCard = reloadMessage.getWeaponYouCanReload();

        for (int i = 0; i < reloadMessage.getWeaponYouCanReload().length; i++) {
            if (reloadMessage.getWeaponYouCanReload()[i] == 1) {
                do {
                    System.out.println("Insert Y if you want to reload the following weapon card, otherwise insert N");
                    System.out.println(weaponCard[i]);

                    tmpChoice = input.next();

                    if (tmpChoice.compareTo("Y") == 0 || tmpChoice.compareTo("N") == 0)
                        inputOk = true;

                } while (!inputOk);

                if (tmpChoice.compareTo("Y") == 0) {
                    payment[i] = featuresAvailable;
                    insertPayment(payment[i]);
                }
            }
        }

        notifyObservers(new ReloadMove(nicknamePlayer,tmpWeaponCard,payment));
    }

    public void reconnectClient (){
        Scanner input = new Scanner(System.in);
        int chosen;

        if (!endGame){
            do{
                System.out.println("Insert 1 to reconnect to the server");
                chosen = input.nextInt();
                if (chosen == 1){
                    notifyObservers(new ConnectionMove(nicknamePlayer,1));
                    return;
                }
            }while (chosen!=1);
        }

    }

    @Override
    public void terminatorAction(TerminatorMessage terminatorMessage) {
        Scanner input = new Scanner(System.in);
        String tmp;
        int j=0;
        int indexColor = -1;
        boolean inputOk = false;
        int[] coordinates = null;
        int[] idPlayerShoot = null;
        boolean shootPeople = false;
        if(terminatorMessage.getColorSpawn()!= null){
            System.out.println("Choose the index of the color where the terminator will spawn :");
            for(String color : terminatorMessage.getColorSpawn()) {
                System.out.println("PRESS " + j + " TO CHOOSE THE FOLLOWING COLOR " + color );
                j++;
            }
            do {
                tmp = input.next();

                for (int i = 0; i <= 2; i++) {
                    if (tmp.equals(String.valueOf(i))) {
                        indexColor = Integer.parseInt(tmp);
                        inputOk = true;
                    }
                    if(!inputOk)
                        System.out.println("TRY AGAIN");
                }
            }while(!inputOk);

        }
        else{
            if(terminatorMessage.isRunOrDamage()){
                coordinates = insertCoordinatesVector(terminatorMessage.getMovement());
            }
            else{
                idPlayerShoot = choosePeopleToKill(terminatorMessage.getCooPeople());
                shootPeople = true;
            }
        }
        notifyObservers(new TerminatorMove(nicknamePlayer,coordinates,shootPeople,indexColor, idPlayerShoot));
    }

    @Override
    public void visitEndGame(EndGame endGame) {
        this.endGame=true;
        System.out.println("END GAME");

        System.out.println("The winner is: " +endGame.getWinner());
        System.out.println("The points is: " + endGame.getPointMax());
        System.out.println("The winner says: " +endGame.getPhrase());

        System.out.println("All score: ");
        for (int i=0; i<endGame.getRanking().length; i++){
            System.out.println(endGame.getRanking()[i] + " : "+endGame.getPoints()[i]);
        }

    }
}
