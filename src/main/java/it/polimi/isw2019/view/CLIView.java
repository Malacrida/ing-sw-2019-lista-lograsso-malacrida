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
        System.out.println("player " + message.getNicknamePlayer() + " view " + nicknamePlayer+" message " + message.getClass());
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

        for(int i = 0 ; i < payment.length; i++){
            realPayment[i] = -1;
        }
        if(payment[0] != 0){
                do {
                    System.out.println("Insert the number of blue cubes. Press 0 not to pay with cubes");
                    tmp = input.next();
                    if (Integer.parseInt(tmp) >= 0 && Integer.parseInt(tmp) <= payment[0]) {
                        realPayment[0] = Integer.parseInt(tmp);
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
                        realPayment[1] = Integer.parseInt(tmp);
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
                        realPayment[2] = Integer.parseInt(tmp);
                        endInsertment = true;
                    }
                }while(!endInsertment);

                endInsertment = false;
            }

            for( int i = 3; i < payment.length; i++)
                    realPayment[i] = -1;

            int i = 3;

            do {

                System.out.println("Insert 0 if you want to pay with this power up card, otherwise press -1");
                tmp = input.next();
                if (Integer.parseInt(tmp) == 0) {
                    realPayment[Integer.parseInt(tmp)] = (i-3);
                    i++;
                }
                if(i ==  payment.length)
                    endInsertment = true;
                if(Integer.parseInt(tmp) == -1)
                    endInsertment = true;
            }while(!endInsertment);

        return  realPayment;
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

            if(Integer.parseInt(cardChoosen) >= 0 && Integer.parseInt(cardChoosen)<weaponCard.length) {
                index = Integer.parseInt(cardChoosen);
                endInsertment = true;
            }

        }while(!endInsertment);

        String tmp;
        int cardIndex = index;
        int numMaxEffect = useWeaponCardMessage.getFeaturesForEffect()[index][0];
        int[][] peopleToBeShoot = new int[3][useWeaponCardMessage.getFeaturesForEffect()[index][1]];
        int[][] coordinates =  new int[3][useWeaponCardMessage.getFeaturesForEffect()[index][2]];
        int[][] payment = new int[3][useWeaponCardMessage.getFeaturesAvailable().length];

        //first index -> maxEffect

        int[][] playersToAttack = new int[3][useWeaponCardMessage.getPlayersToAttack().length];
        int[] effectUsed = new int[3];
        int i = 0;

        endInsertment = false;
        boolean endChoice = false;
        do {
            do {
                System.out.println("insert the number of the effect you want to use or -1 to exit:");
                tmp = input.next();

                if(Integer.parseInt(tmp) >= 1 && Integer.parseInt(tmp)<=numMaxEffect){
                    index = Integer.parseInt(tmp);
                    effectUsed[i] = Integer.parseInt(tmp);
                    i++;
                    endChoice = true;
                }
                else if(Integer.parseInt(tmp) == -1){
                    endChoice = true;
                }

            }while(!endChoice);

            if(Integer.parseInt(tmp) == -1) {
                //setto tutto a null
                endInsertment = true;
            }
            else {
                if (useWeaponCardMessage.getFeaturesForEffect()[cardIndex][1] != 0) {
                    coordinates[index] = insertCoordinatesVector(useWeaponCardMessage.getFeaturesForEffect()[cardIndex][1]);
                }

                if (useWeaponCardMessage.getFeaturesForEffect()[cardIndex][2] != 0) {
                    //inseert people
                }
            }

            if(i == numMaxEffect)
                endInsertment = true;
            payment[index] = featuresAvailable.clone();
            insertPayment(payment[index]);

            endChoice = false;

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
            for(int i = 0; i < firstMessageFirstPlayer.getPossibleMaps().length; i++)
                        System.out.println((i+1) +" " + firstMessageFirstPlayer.getPossibleMaps()[i]);


            do {
                tmpInput = input.next();
                for(int i = 0 ; i <firstMessageFirstPlayer.getPossibleMaps().length ; i++){
                    if(tmpInput.equals(String.valueOf(i+1))){
                        indexMap = Integer.parseInt(tmpInput);
                        inputOk = true;
                        break;
                    }
                }
                if(!inputOk){
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
            do {tmpInput = input.next();

                if(tmpInput.equals(String.valueOf(1))||tmpInput.equals(String.valueOf(2))){
                    mod = Integer.parseInt(tmpInput);
                    inputOk = true;
                }
                else{
                    System.out.println("try again");
                }
            } while(!inputOk);

            System.out.println("Press 1 to choose the game mode terminator \n" +
                    "press 0 to choose the game mode without terminator:");

            inputOk = false;
            terminator = -1;
            do {tmpInput = input.next();

                if(tmpInput.equals(String.valueOf(0) )||tmpInput.equals(String.valueOf(1))){
                    terminator = Integer.parseInt(tmpInput);
                    inputOk = true;
                }
                else{
                    System.out.println("try again");
                }
            } while(!inputOk);
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
           /* if(Integer.parseInt(tmpInput) >= 0 && Integer.parseInt(tmpInput)<= firstMessageFirstPlayer.getColorAvailable().size()){
                indexColor = Integer.parseInt(tmpInput);
                inputOk = true;
            }*/
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
        int actionChoosen = -1;
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
            actionChoosen = Integer.parseInt(tmpActionChoosen);

            if (actionChoosen == -1) {
                //reload vuota
                notifyObservers(new ReloadMove(nicknamePlayer,null,null));
                return;
            }
            else if (actionChoosen==9){
                notifyObservers(new ConnectionMove(nicknamePlayer, 0));
                reconnectClient();
                return;
            }
            else if (actionChoosen >= 0 && actionChoosen < actionMessage.getActionPlayerCanPerform().size())
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
        int[] payment = featuresAvailable.clone();
        UsePowerUpCard usePowerUpCard = new UsePowerUpCard(nicknamePlayer);

        if(usePowerUpCardMessage.getStateGame() == 0) {
            do {
                System.out.println("choose one of the following PowerUp Card : ");

                for (String powerUpCard : powerUpCard)
                    System.out.println(powerUpCard);

                tmpCardChoosen = input.nextLine();

                cardChoosen = Integer.parseInt(tmpCardChoosen);

                if (cardChoosen >= 0 && cardChoosen < powerUpCard.length)
                    inputOk = true;

            } while (!inputOk);

            System.out.println("the effect of the card is:");
            System.out.println(powerUpCard);



            usePowerUpCard.setCubes(features);

            for (int i = 0; i < usePowerUpCardMessage.getStateCard().length; i++) {
                if (usePowerUpCardMessage.getStateCard()[i]) {

                }
            }
        }

            insertPayment(payment);
            //attack e defend!
       // }

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
                i ++;
            }

            do {
                tmpInput = input.next();
                if (Integer.parseInt(tmpInput) >= 0 || Integer.parseInt(tmpInput) <= 2) {
                    inputOk = true;
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

    @Override
    public void failRegistration(FailRegistration failRegistration) {
        System.out.println("too many people : OUT");
    }

    public void reconnectClient (){
        Scanner input = new Scanner(System.in);
        int chosen;

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
