package it.polimi.isw2019.Client.View;

import it.polimi.isw2019.Server.Message.MoveMessage.ActionMessage;
import it.polimi.isw2019.Server.Message.MoveMessage.MoveMessage;
import it.polimi.isw2019.Server.Message.PlayerMove.PlayerMove;
import it.polimi.isw2019.Server.Message.PlayerMove.RunGrabMove;
import it.polimi.isw2019.Server.Message.PlayerMove.RunMove;
import it.polimi.isw2019.Server.Utilities.Observable;
import java.util.Scanner;

public class ActionView  extends Observable<PlayerMove> implements VisitorView{

    @Override
    public void visitSetupView(MoveMessage messageMove) {

    }

    @Override
    public void visitActionView(MoveMessage moveMessage) {
        Scanner input = new Scanner(System.in);

        for(int i=0; i<((ActionMessage)(moveMessage)).getActionYouCanPerform().size(); i++)
            System.out.println("press "+ i + " to choose the following action "+((ActionMessage)(moveMessage)).getActionYouCanPerform().get(i).getIdMoveMessage());
        int actionChoosen = input.nextInt();

        ActionMessage message = (ActionMessage)(moveMessage);
        int idAction = message.getActionYouCanPerform().get(actionChoosen).getIdAction();

        RunMove runMove = null;
        RunGrabMove runGrabMove = null;
        int numMovement = -1;

        switch(idAction){
            case 0 :

                runMove = new RunMove();
                numMovement = message.getActionYouCanPerform().get(actionChoosen).getNumMovement();
                runMove.setMovement(insertMovement(numMovement));
                notifyObservers(runMove);

                break;
            case 1:
                runGrabMove = new RunGrabMove();
                numMovement = message.getActionYouCanPerform().get(actionChoosen).getNumMovement();
                runGrabMove.setMovement(insertMovement(numMovement));
                runGrabMove.setCardSelection(insertGrab());

                notifyObservers(runGrabMove);
                break;

            case 2 :

                break;

            case 3 :
                break;

            case 4:

                break;

            case 5:

                break;

            case 6 :

                break;



        }


    }

    public int[][] insertMovement(int numMovement){
        int[][] tmpMovement = new int[numMovement][2];
        Scanner input = new Scanner(System.in);
        //check se ne vuoi fare meno di quelli segnati
        //display gameboard

        for(int i=0;i<numMovement;i++){
            System.out.println("Insert raw :");
            tmpMovement[i][0] = input.nextInt();
            System.out.println("Insert column : ");
            tmpMovement[i][1] = input.nextInt();

        }
        return tmpMovement;
    }

    //check per l'inserimento corretto dei caratteri
    public char[][] insertGrab(){
        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to pick up a weapon card (W)  or an ammo card (A)?");
        char cardChoice = input.next().charAt(0);
        char weaponChoice = 'A';
        if(cardChoice == 'W'){
            System.out.println("Select B-C-D to take one of the following weapon card :");
            //print delle tue weapon card
            weaponChoice = input.next().charAt(0);

        }

        char[][] cardSelection = new char[1][2];

        cardSelection[0][0] = cardChoice;
        cardSelection[0][1] = weaponChoice;

        return cardSelection;
    }

    public void insertWeaponCard(){

    }

    // insert powerupcard + display powerup che ha + pagamento
    // insertWeaponCard +  display effetti che ha quella weapon card .
    // reload + display weapon card scariche + gestione pagamenti

    @Override
    public void errorMessageView(MoveMessage moveMessage) {

    }


}
