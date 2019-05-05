package it.polimi.isw2019.View;

import it.polimi.isw2019.Message.PlayerMove.*;
import it.polimi.isw2019.Message.RunMessage;


import java.util.Scanner;

public class RunActionView extends ViewCLIAction{

    //crea un'azione singola di movimento
    public RunActionView(String idPlayerMove,String idAction){
        super(idPlayerMove,idAction);
        setNumberMovement(this);
    }

    //movimenti vengono settati a runTime da questo metodo
    public void setNumberMovement(ViewCLIAction action){
        if(action.getIdPlayerMove().compareTo("RUN") == 0) {
            if (action.getStatus() == 'N') {
                this.setMaxMovement(3);
                this.setMatrix(new int[3][2]);
            }
        }
        else if(action.getIdPlayerMove().compareTo("RUNGRAB") == '0'){
            if (action.getStatus() == 'N')
                this.setMaxMovement(1);
                this.setMatrix(new int[1][2]);
        }
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

    public  PlayerMove handleAction(ViewCLI view){
        handleInsertData(view,this);
        return (new RunMessage(getIdPlayerMove(),getIdAction(),getMatrix()));
    }

    public int[][] handleSingleAction(ViewCLI view, ViewCLIAction action){
        handleInsertData(view, action);
        return this.getMatrix();

    }

}
