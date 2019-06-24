package it.polimi.isw2019.network;

import it.polimi.isw2019.message.movemessage.MoveMessage;
import it.polimi.isw2019.message.playermove.ChooseActionMove;
import it.polimi.isw2019.message.playermove.PlayerMove;
import it.polimi.isw2019.utilities.Observable;
import it.polimi.isw2019.utilities.Observer;

import java.util.Scanner;

public class ViewProvvisioria extends Observable<PlayerMove> implements Observer<MoveMessage> {

    private String nickname;
    Scanner input = new Scanner(System.in);
    int num;

    ViewProvvisioria (String nickname){
        this.nickname=nickname;
    }

    public void createChooseAction (){
        System.out.println("Inserisci un numero: ");
        num = input.nextInt();

        ChooseActionMove chooseActionMove=new  ChooseActionMove(nickname,num);
        notifyObservers(chooseActionMove);

    }

    @Override
    public void update(MoveMessage message) {
        System.out.println("ricevuto una move messagge per: "+message.getNicknamePlayer());
    }
}
