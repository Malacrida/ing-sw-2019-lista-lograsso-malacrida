package it.polimi.isw2019.Server.View;

import it.polimi.isw2019.Server.Message.PlayerMove.ReloadMove;
import it.polimi.isw2019.Server.Message.PlayerMove.*;


import java.util.Scanner;

public class ReloadWeaponView extends  ViewCLIAction{

    private char[][] paymentForRecharge;
    private int weaponCardChoosen;

    public ReloadWeaponView(String idPlayerMove, String idAction){
        super(idPlayerMove,idAction);
    }

    public PlayerMove handleAction(ViewCLI view){
        //display delle weaponCardNotRecharged
        Scanner input = new Scanner(System.in);
        //controllo sull'inserimento di una carta corretta
        System.out.println("Choose the weapon card you want to recharge");
        int positionWeaponCardToRecharge = input.nextInt();
        //devo parlare bene con davi
        paymentForRecharge = new char[1][2];
        //controllo sulle munizioni
        System.out.println("Choose payment :");
        char typePayment = input.next().charAt(0);
        char color = input.next().charAt(0);

        return new ReloadMove(getIdPlayerMove(), getIdAction(), weaponCardChoosen,paymentForRecharge);


    }
}
