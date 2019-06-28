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

    private ArrayList<InterfacePowerUpCard> powerUpCards;

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

        System.out.println("update : " + message.getNicknamePlayer());

        if(message.isNotifyAll()){
            message.accept(this);
        }
        else if(nicknamePlayer.equals(message.getNicknamePlayer())) {
            System.out.println("accetto la Movemessage");
            message.accept(this);
        }
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

    public void insertPayment(int[] featuresAvailable, ArrayList<InterfacePowerUpCard> powerUpCard, WeaponCardInterface weaponCardInterface){
        boolean endInsertment = false;
        Scanner input = new Scanner(System.in);
        String tmp ;
        System.out.println(weaponCardInterface.getWeaponCardDescription());
        if(powerUpCard!= null)
            System.out.println("you can pay both with power up or with cubes");
        if(featuresAvailable[0] != 0){
                do {
                    System.out.println("Insert the number of blue cubes. Press 0 not to pay with cubes");
                    tmp = input.next();
                    if (Integer.parseInt(tmp) >= 0 && Integer.parseInt(tmp) <= featuresAvailable[0]) {
                        featuresAvailable[0] = Integer.parseInt(tmp);
                        endInsertment = true;
                    }
                }while(!endInsertment);
                endInsertment = false;
            }
            if(featuresAvailable[1]!= 0){
                do {
                    System.out.println("Insert the number of red cubes.  Press 0 not to pay with cubes");
                    tmp = input.next();
                    if (Integer.parseInt(tmp) >= 0 && Integer.parseInt(tmp) <= featuresAvailable[1]) {
                        featuresAvailable[1] = Integer.parseInt(tmp);
                        endInsertment = true;
                    }
                }while(!endInsertment);

                endInsertment = false;
            }
            if(featuresAvailable[2]!= 0){
                System.out.println("Insert the number of  cubes");
                do {
                    System.out.println("Insert the number of yellow cubes. Press 0 not to pay with cubes ");
                    tmp = input.next();
                    if (Integer.parseInt(tmp) >= 0 && Integer.parseInt(tmp) <= featuresAvailable[2]) {
                        featuresAvailable[2] = Integer.parseInt(tmp);
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
                if (Integer.parseInt(tmp) >= 3 && Integer.parseInt(tmp) <= powerUpCard.size()) {
                    featuresAvailable[Integer.parseInt(tmp)] = Integer.parseInt(tmp);
                    i++;
                }
                if(i == powerUpCard.size())
                    endInsertment = true;
                if(Integer.parseInt(tmp) == -1)
                    endInsertment = true;
            }while(!endInsertment);
        }
    }

    @Override
    public void useWeaponCard(UseWeaponCardMessage useWeaponCardMessage){
        int i = 0;
        String cardChoosen;
        boolean endInsertment = false;
        int index = 0;
        Scanner input = new Scanner(System.in);
        do{
            System.out.println("choose one of the following weapon card");

            for(WeaponCardInterface weaponCardInterface: useWeaponCardMessage.getWeaponCard())
                System.out.println(weaponCardInterface.getWeaponCardDescription());

            cardChoosen = input.next();

            if(Integer.parseInt(cardChoosen) >= 0 && Integer.parseInt(cardChoosen)<useWeaponCardMessage.getWeaponCard().size()) {
                index = Integer.parseInt(cardChoosen);
                endInsertment = true;
            }

        }while(!endInsertment);

        String tmp;
        int[][] coordinates = null;
        int[][] payment = new int[useWeaponCardMessage.getWeaponCard().get(index).getNumMaxEffect()][3+useWeaponCardMessage.getPowerUpCards().size()];
        endInsertment = false;

        PlayerInterface[][] players = new PlayerInterface[useWeaponCardMessage.getWeaponCard().get(index).getNumMaxEffect()][useWeaponCardMessage.getWeaponCard().get(index).getNumMaxDefenders()];

        ArrayList<PlayerInterface> playerInterfaces = new ArrayList<>();

        System.out.println(useWeaponCardMessage.getWeaponCard().get(index).getWeaponCardDescription());

        //mappa

        do{

            System.out.println("Insert " +(i+1) +" to insert the features for the " + (i + 1) + "effect . Press 0 not to use this effect or -1 terminate !");
            tmp = input.next();

            if(Integer.parseInt(tmp)  == -1)
                endInsertment = true;

            else if(Integer.parseInt(tmp) == 0)
                i = i +1;

            else if(Integer.parseInt(tmp)>= 1 && Integer.parseInt(tmp) <= 3) {
                if(useWeaponCardMessage.getWeaponCard().get(index).getNumMaxCoordinates()!= 0)
                    coordinates = insertCoordinates(useWeaponCardMessage.getWeaponCard().get(index).getNumMaxCoordinates());
                if(useWeaponCardMessage.getWeaponCard().get(index).getNumMaxDefenders()!=0){
                    int k = 0;
                    for(PlayerInterface player : useWeaponCardMessage.getPlayersToAttack()) {
                        System.out.println("press 1 if you want to attack this player, otherwise press 0");
                        player.getPlayerInterface().toString();

                        tmp = input.next();

                        if(Integer.parseInt(tmp) == 1){
                            //come togliere i player
                            players[i][k] = player.getPlayerInterface();
                            k++;
                        }
                    }
                }
                payment[i] = useWeaponCardMessage.getFeaturesAvailable();
                //print del pagamento
                insertPayment(payment[i], useWeaponCardMessage.getPowerUpCards(), useWeaponCardMessage.getWeaponCard().get(index));
            }


        }while(!endInsertment);

        UseWeaponCard useWeaponCard = new UseWeaponCard(nicknamePlayer,index);
        useWeaponCard.setHandleEffectCoordinates(coordinates);
        useWeaponCard.setHandleEffectPayment(payment);
        useWeaponCard.setPeopleToBeShoot(players);
        notifyObservers(useWeaponCard);

    }

    public void visitStartView(StartMessage startMessage) {

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
                //settare una reload move vuota
                notifyObservers(new ReloadMove(nicknamePlayer));
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

        insertPayment(features,usePowerUpCardMessage.getPowerUpCard(),null);
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

        /*updateGameBoard(updateMessage.getGameBoard());
        updatePlayers(updateMessage.getPlayers());
        printGameBoard(updateMessage.getGameBoard());
        for(PlayerInterface player : players)
            printPlayerFeatures(player);
        */
        System.out.println("Arena ");

        System.out.println(updateMessage.getGameBoard().getArenaInterface().toString());

        for(PlayerInterface player : updateMessage.getPlayers()) {
            System.out.println(player.toString());
        }
            /*if(player.getPlayerBoard()!= null)
                System.out.println(player.getPlayerBoard().getPlayerBoardRepresentation());

        }*/
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
        int positionWeaponCard = -1;
        boolean inputOk = false;
        String tmpInput;

        if(grabMessage.isGrabWeapon()) {
            for(WeaponCardInterface weaponCard: grabMessage.getWeaponCardAvailable())
                System.out.println(weaponCard.getWeaponCardDescription());

            do {
                System.out.println("Insert 0/1/2 to grab " +
                        "one of weaponCard, otherwise press -1");
                tmpInput = input.next();
                if (Integer.parseInt(tmpInput) >= 0 || Integer.parseInt(tmpInput) <= 2) {
                    inputOk = true;
                }
            } while (!inputOk);
            positionWeaponCard = Integer.parseInt(tmpInput);

            System.out.println("weaponCard :" + grabMessage.getWeaponCardAvailable().size() + "power up :" + grabMessage.getYourPowerUpCard().size());

            insertPayment(grabMessage.getFeaturesAvailable(),grabMessage.getYourPowerUpCard(),grabMessage.getWeaponCardAvailable().get(positionWeaponCard));
            grabMove.setPositionWeaponCard(positionWeaponCard);
            grabMove.setPayment(grabMessage.getFeaturesAvailable());
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
        int[] payment = new int[3+reloadMessage.getPowerUpCards().size()];


        for (int i = 0; i < reloadMessage.getWeaponCardInterfaces().size(); i++) {
            do {


                System.out.println("Insert Y if you want to reload the following weapon card, otherwise insert N");
                System.out.println(reloadMessage.getWeaponCardInterfaces().get(i).getWeaponCardDescription());

                tmpChoice = input.next();

                if (tmpChoice.compareTo("Y") > 0 || tmpChoice.compareTo("N")> 0)
                    inputOk = true;

            } while (!inputOk);

            if(tmpChoice.compareTo("Y") > 0){
               insertPayment(payment,reloadMessage.getPowerUpCards(),reloadMessage.getWeaponCardInterfaces().get(i));
            }

        }
        notifyObservers(new ReloadMove(getNicknamePlayer()));

    }

    @Override
    public void failRegistration(FailRegistration failRegistration) {
        System.out.println("too many people : OUT");
    }
}
