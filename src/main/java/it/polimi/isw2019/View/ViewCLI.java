package it.polimi.isw2019.View;

import java.util.Scanner;

import it.polimi.isw2019.Message.MoveMessage.MoveMessage;
import it.polimi.isw2019.Message.PlayerMove.*;
import it.polimi.isw2019.View.View;


public class ViewCLI extends View {
    Scanner input;
    PlayerMove playerMove;

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

            notifyObservers(new PlayerMove("Starting Message",nickname,phrase));

    }

    public void displayStatus(){
        displayColor();

    }

    public void displayColor(){
        System.out.println("Your color is :" + color);
    }

    public void chooseColor(MoveMessage moveMessage){
        int position = 6;
        //try-catch per vedere se effettivamente il colore scelto e' contenuto all'interno dei colori
        while(position > moveMessage.getColor().size() && position < 0) {

            try {
                System.out.println("Choose one of the following color :" + moveMessage.getColor());
                position = input.nextInt();

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Insert a correct index");
            }
        }

        //modificato colore player
        setColor(moveMessage.getColor().get(position));
        displayStatus();

        notifyObservers(new PlayerMove("Color choosen",idPlayer,color));

    }

    //invocated only once

    public void updateIdPlayer(MoveMessage moveMessage){
        this.idPlayer = moveMessage.getIdPlayer();
    }

    public void updateView(MoveMessage moveMessage){
        switch(moveMessage.getIdMoveMessage()){
            case "ChooseColorMessage":
                updateIdPlayer(moveMessage);
                chooseColor(moveMessage);
                break;

        }
    }




}
