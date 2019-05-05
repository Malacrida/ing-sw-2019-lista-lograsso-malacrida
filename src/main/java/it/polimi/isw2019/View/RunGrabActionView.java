package it.polimi.isw2019.View;

import it.polimi.isw2019.Message.PlayerMove.PlayerMove;
import it.polimi.isw2019.Message.PlayerMove.RunGrabMove;

import java.util.Scanner;

public class RunGrabActionView extends ViewCLIAction {
    //non mi interessa la card position, perche suppongo che il giocatore la prenda nello spawn/ square in cui e'

    private char cardType;
    private int positionWeaponCard;

    public RunGrabActionView(String idPlayerMove,String idAction){
        super(idPlayerMove,idAction);
        // 0 e' il default
        cardType = '0';
        positionWeaponCard = -1;

    }
    public PlayerMove handleAction(ViewCLI view){
        //creata un'azione di tipo run per gestire l'inserimento corretto della matrice degli spostamenti
        handleInsertData(view,this);
        //insertiti dalla VIEWCLI
        return new RunGrabMove(getIdPlayerMove(), getIdAction(), getMatrix(),getCardType(), getPositionWeaponCard());
    }

    public void handleInsertData(ViewCLI view, ViewCLIAction action){
        //visualizzare tutte weaponCard disponibili e le ammoCard disponibili

        Scanner input = new Scanner(System.in);
        //loop
        System.out.println("Press 1 to run and grab or 2 to grab  or -1 to end the action");
        int idPartialAction ;
        idPartialAction = input.nextInt();

        boolean run = false;

        if( idPartialAction == 1) {
            run = true;
        }

        if(run)
            this.setMatrix((new RunActionView(this.getIdPlayerMove(), this.getIdAction())).getMatrix());
        else
            this.setMatrix(null);

        //loop for control
        System.out.println("Would you like to pick up a weapon card (W)  or an ammo card (A)?");
        char cardChoice = input.next().charAt(0);

        if(cardChoice == 'A')
            handleInsertAmmoCard();
        if(cardChoice == 'W')
            handleInsertWeaponCard();


    }

    public void handleInsertAmmoCard(){
        this.cardType = 'A';

    }

    public void handleInsertWeaponCard(){

        //output of all weapon cards
        //control for correct insert
        Scanner input = new Scanner(System.in);
        System.out.println("Which weapon card do you want to pick (1) (2) (3)?");
        int position= input.nextInt();

        this.positionWeaponCard = position;

        //come viene gestito il pagamento dei cubi (?)
    }

    public char getCardType(){
        return this.cardType;
    }

    public int getPositionWeaponCard(){
        return this.positionWeaponCard;
    }
}
