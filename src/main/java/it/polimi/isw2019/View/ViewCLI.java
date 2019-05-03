package it.polimi.isw2019.View;

import java.util.Scanner;

import it.polimi.isw2019.Message.MoveMessage.*;
import it.polimi.isw2019.Message.PlayerMove.*;


public class ViewCLI extends View {
    Scanner input;
    PlayerMove playerMove;
    boolean fromTheirToYourTurn;
    char statusPlayer;

    public ViewCLI(){
        //register as observable of model
        //register the controller as observable of the view
        input = new Scanner(System.in);
    }

    @Override
    public void welcome(){

        String enteringGame = null;

        while (enteringGame.compareTo("Y") != 1) {
            System.out.println("Type Y to enter the game!");

            try {
                enteringGame =  input.nextLine();
            }
            catch (NullPointerException e) {
                input.nextLine();
            }
        }
        //method in which the user insert the data
    }

    @Override
    public void insertDate(){

            String nickname = null;
            String phrase = null;

            System.out.println("Insert nickname : ");
            nickname = input.nextLine();

            System.out.println("Insert phrase :");
            phrase = input.nextLine();

            //are inserted correctly

            setNickname(nickname);
            setPhrase(phrase);

            notifyObservers(new SetUpMove("STARTING MESSAGE",nickname,phrase));

    }

    public void chooseColor(SetUpMessage moveMessage){
        int position = 6;
        //try-catch per vedere se effettivamente il colore scelto e' contenuto all'interno dei colori
        while(position > moveMessage.getColorAvailable().size() && position < 0) {

            try {
                System.out.println("Choose one of the following color :" + moveMessage.getColorAvailable());
                position = input.nextInt();

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Insert a correct index");
            }
        }

        //modificato colore player
        setColor(moveMessage.getColorAvailable().get(position));
        displayStatus();
        char gameMood = 'a';

        //try catch finche non viene
        System.out.println("Tapping the first upper caseletter of the name of the game mode " +
                "to choose one of them : /n 1) N -> Normal ; 2) T -> Terminator ; 3) B ->Boot; ");
        //consume only one char
        gameMood = input.next().charAt(0);
        notifyObservers(new SetUpMove("COLOR CHOOSEN",idPlayer,color,gameMood));

    }

    //invocated only once


    public void firstScreen(){

    }

    public void handleYourTurn(){

        //displayGamePlay
        //actionYouCanDo
        //choose to : do action, reload weapon, use powerup card


    }

    public void actionYouCanDo(String statusGame){
        //displayActionYouCanDo
        //grande if che prendera come
        int idAction ;
        idAction = input.nextInt();
        //in base all idAction, ci sara' uno switch case che istanziera la playerMove
        //caso della RUN
        notifyObservers((new RunActionView("ACTION","RUN",statusPlayer)).handleAction(this));
        //caso d
    }


    public void handleTheirTurn(){
        int tmp = 0;
        System.out.println("Press one to display the game status\n");
        tmp = input.nextInt();
        if(input.nextInt()== 1)
                displayStatus();
    }



    public void displayColor(){
        System.out.println("Your color is :" + color);
    }

    public void displayGamePlay(){
        //gameboard
        //playerBoard + weaponCard + ammo
        //description only of id of weaponcard on gameboard + in the hand of all players
        //weaponCard
        //your powerUp
    }

    public int displayActionBootMode(){
        int lastAction = -1;
        return lastAction;
    }

    public int displayActionFrenzyIPMode(){
        int lastAction = -1;
        return lastAction;
    }

    public int displayActionTyFrenzyMode(){
        int lastAction = -1;
        return lastAction;
    }

    public int displayActionNormalMode(){
        int lastAction = -1;
        return lastAction;
    }

    public int displayPoweredActionI(){
        //displayNormalAction + new
        int lastAction = -1;
        return lastAction;
    }

    public int displayPoweredActionII(){
        //invoke displayPoweredAction I
        int lastAction = -1;
        return lastAction;
    }

    public int displayActionTerminatorMode(){
        int lastAction = -1;
        return lastAction;
    }

    public void displayActionYouCanDo(){
        //richiamati i vari metodi
    }


    public void displayStatus(){
        displayColor();
    }

    public void updateIdPlayer(SetUpMessage moveMessage){
        this.idPlayer = moveMessage.getIdPlayer();
    }
    //metodi per visualizzare le powerUpCard e le weaponCard + descrizione

    public void updateView(MoveMessage moveMessage){
        switch(moveMessage.getIdMoveMessage()){
            case "ChooseColorMessage":
                updateIdPlayer((SetUpMessage)moveMessage);
                chooseColor((SetUpMessage)moveMessage);
                break;
            case "StarGame":
                if(((TurnMessage)(moveMessage)).isStartGame()) {
                    //display
                    firstScreen();
                    handleTheirTurn();
                }
                break;

            case "FirstPlayer" :
                //fromTheirToYour viene messo ad uno cosicche si stoppa handle their turn e si passa ad handle your turn
                handleYourTurn();
        }
    }
}
