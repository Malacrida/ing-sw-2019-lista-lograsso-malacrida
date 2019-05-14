package it.polimi.isw2019.Server.View;

import it.polimi.isw2019.Server.Message.MoveMessage.ActionMessage;
import it.polimi.isw2019.Server.Message.PlayerMove.*;
import it.polimi.isw2019.Server.Message.RunMessage;
import it.polimi.isw2019.Server.View.ViewCLI;
import it.polimi.isw2019.Server.View.ViewCLIAction;


import java.util.Scanner;

public class RunActionView extends ViewCLIAction {

    //crea un'azione singola di movimento
    public RunActionView(String idPlayerMove,String idAction){
        super(idPlayerMove,idAction);
    }



    //in handle action ci dovrà essere lo status del player in modo che si possono fare inserire il num corretto di spostamenti che puo fare
    public void handleInsertData(ViewCLI view, ViewCLIAction action){
        int raw = 0, column = 0;
        int finishAction = 0;
        int rawValue = 0 ,columnValue = 0;
        Scanner input = new Scanner(System.in);
        while(finishAction == 0 || raw < action.getMaxMovement() ){
            //displayGameBoard
            //try catch per gli errori
            System.out.println("Insert raw or -1 to finish the action or -2 to display the gameBoard: ");
            //display gameboard
            rawValue= input.nextInt();
            if(rawValue!= -1) {
                System.out.println("Insert column : ");
                columnValue = input.nextInt();
                action.setMovement(raw,column,rawValue,columnValue);
                raw++;
            }
            else
                finishAction = -1;

        }
    }

    @Override
    public  PlayerMove handleAction(ViewCLI view){
        handleInsertData(view,this);
        return (new RunMessage(getIdPlayerMove(),getIdAction(),getMatrix()));
    }

    public int[][] handleSingleAction(ViewCLI view, ViewCLIAction action){
        handleInsertData(view, action);
        return this.getMatrix();

    }

}
