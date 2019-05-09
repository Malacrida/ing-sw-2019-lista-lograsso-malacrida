package it.polimi.isw2019.Server.Message.PlayerMove;

import java.util.ArrayList;

public class UseWeaponCard extends Action {

    private int weaponCardChoosen;
    private int[] effect;
    private int[] cubes;
    private char[][] paymentEffect;
    private ArrayList<Integer> playerAttacked;
    private int [][] squareToAttack;

    public UseWeaponCard(String idPlayerMove, String idAction, int weaponCardChoosen,int[] effect,
                         char[][] paymentEffect, ArrayList<Integer> playerAttacked, int [][] squareToAttack){
        super(idPlayerMove,idAction);
        this.weaponCardChoosen = weaponCardChoosen;
        this.effect = effect;
        //this.cubes = cubes;
        this.paymentEffect = paymentEffect;
        this.playerAttacked = playerAttacked;
        this.squareToAttack = squareToAttack;
    }

}
