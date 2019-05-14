package it.polimi.isw2019.Server.View;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import it.polimi.isw2019.Server.Message.MoveMessage.*;
import it.polimi.isw2019.Server.Message.PlayerMove.*;
import it.polimi.isw2019.Server.View.*;


public class ViewCLI extends View {
    Scanner input;
    PlayerMove playerMove;
    boolean fromTheirToYourTurn;
    char statusPlayer;
    int idPlayer;
    private ArrayList<String> weaponCardRecharged;
    private ArrayList<String> weaponCardNotRecharged;
    private ArrayList<String> ammoCubesAvailable;
    private ArrayList<String> ammoCubesNotAvailable;
    private ArrayList <Integer> powerUp;
    private Map<String,Integer> effectOfWeaponCardAvailable;
    public ViewCLI(){
        //register as observable of model
        //register the controller as observable of the view
        input = new Scanner(System.in);
    }

    @Override
    public void welcome(){

        String enteringGame = null;
        boolean ok = true;

         do{
            System.out.println("Type Y to enter the game!");

            try {
                enteringGame =  input.nextLine();
            }
            catch (NullPointerException e) {
                input.nextLine();
                ok= false;
            }
        }
        while (!ok);
        //method in which the user insert the data
    }

    @Override
    public void insertDate(){

            this.nickname = null;
            this.phrase = null;

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
        System.out.println("Tapping the first upper case letter of the name of the game mode " +
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



    }


    public void handleYourTurn(ActionMessage action){

        //print playerboard
        //print gameboard
        //visualizzare le azioni
        int choiceAction = -1;
        for(int i=0; i< action.getActionYouCanPerform().size(); i++)
            System.out.println(" Press "+ i + " for the action" + action.getActionYouCanPerform().get(i).getIdMoveMessage());
        //index out of bound exception
        choiceAction = input.nextInt();

        //assume it's right
        switch (choiceAction){
            case 0 :


                break;

            case 1 :
                break;

            case 2 :

                break;


        }

    }

    public void actionYouCanDo(){
        //displayActionYouCanDo
        //grande if che prendera come
        int idAction ;
        idAction = input.nextInt();
        //in base all idAction, ci sara' uno switch case che istanziera la playerMove
        //caso RUN
        notifyObservers((new RunActionView("ACTION","RUN")).handleAction(this));
        //caso RUN,GRAB
        notifyObservers((new RunGrabActionView("ACTION","RUNGRAB")).handleAction(this));
        //caso useWeaponCard
        notifyObservers((new UseWeaponCardView("ACTION","USEWEAPONCARD")).handleAction(this));
        //caso reload
        notifyObservers((new ReloadWeaponView("ACTION","RELOAD")).handleAction(this));
        //caso powerUp
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



    public void displayStatus(){
        displayColor();
    }

    public void displayWeaponCard(){
        // file json (?)
        // inseriti dal model durante la fase di settaggio del gioco
    }

    public void displayWeaponCardAvailable(){

    }

    public void displayWeaponCardOnBoard(){

    }

    public Map<String,Integer> getEffectOfWeaponCardAvailable(){
        return this.effectOfWeaponCardAvailable;
    }

    public int getEffect(String weaponCard){
        return effectOfWeaponCardAvailable.get(weaponCard);
    }

    public String getNameWeaponCard(int i){
        return weaponCardRecharged.get(i);
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

            case "Action" :
                //choose action
                handleYourTurn();
                break;
        }
    }
}
