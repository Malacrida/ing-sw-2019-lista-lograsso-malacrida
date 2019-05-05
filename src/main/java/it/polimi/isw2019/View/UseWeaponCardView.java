package it.polimi.isw2019.View;

import it.polimi.isw2019.Message.PlayerMove.*;

import java.util.ArrayList;
import java.util.Scanner;

public class UseWeaponCardView extends ViewCLIAction {

    private int weaponCardChoosen;
    private int[] effect;
    private int[] cubes;
    private char[][] paymentEffect;
    private ArrayList<Integer> playerAttacked;
    private int [][] squareToAttack;

    public UseWeaponCardView(String idPlayerMove, String idAction){
        super(idPlayerMove,idAction);
        weaponCardChoosen = -1;

    }

    public PlayerMove handleAction(ViewCLI view){

       choiceWeaponCard(view);
       chooseEffect(view, view.getNameWeaponCard(weaponCardChoosen));
       return new UseWeaponCard(getIdPlayerMove(),getIdAction(),weaponCardChoosen,effect,paymentEffect,playerAttacked,squareToAttack);

    }

    public void choiceWeaponCard(ViewCLI view){
        Scanner input = new Scanner(System.in);
        //loop per scegliere la carta corretta
        System.out.println("Choose one of the following weapon card :");
        //inserisci per la prima uno, per la seconda due...
        view.displayWeaponCardAvailable();
        int tmpWeaponCard = input.nextInt();
        weaponCardChoosen = tmpWeaponCard;
    }

    public void chooseEffect(ViewCLI view, String weaponCard){
        Scanner input = new Scanner(System.in);
        effect = new int[view.getEffect(weaponCard)];
        paymentEffect = new char[view.getEffect(weaponCard)][2];

        //parlare con davide!!
        squareToAttack = new int[1][2];
        playerAttacked = new ArrayList<Integer>(3);
        //display effetti della weapon card
        //for per l'inserimento degli effetti
        //ci penso poi
        System.out.println("Insert which of the following effect you want to use:");
        int tmpEffect = input.nextInt();

        System.out.println("Insert payment through powerUp or Ammo");
        char typePayment = input.next().charAt(0);
        char color = input.next().charAt(0);
        System.out.println("Insert player you want to attack");
        System.out.println("Insert square you want to attack");


        //inserimento
        for(int i=0; i<view.getEffect(weaponCard); i++) {
            effect[i] = tmpEffect;
            paymentEffect[i][0] = typePayment;
            paymentEffect[i][1] = color;
        }



    }


    public void setNumberEffect(String weaponCard){
        //switch case che in base al nome della carta ti restituisce il numero di effetti che puo eseguire
    }

}
