package it.polimi.isw2019.message.PlayerMove;

import it.polimi.isw2019.controller.VisitorController;

import java.util.ArrayList;

public class UseWeaponCard extends PlayerMove {
    private String idPlayerMove;
    private String idAction;
    private int weaponCardChoosen;
    private int[] effect;
    private int[] cubes;
    private char[][] paymentEffect;
    private ArrayList<Integer> playerAttacked;
    private int [][] squareToAttack;

    public UseWeaponCard(String idPlayerMove, String idAction, int weaponCardChoosen,int[] effect,
                         char[][] paymentEffect, ArrayList<Integer> playerAttacked, int [][] squareToAttack){
        this.idPlayerMove = idPlayerMove;
        this.idAction = idAction;
        this.weaponCardChoosen = weaponCardChoosen;
        this.effect = effect;
        //this.cubes = cubes;
        this.paymentEffect = paymentEffect;
        this.playerAttacked = playerAttacked;
        this.squareToAttack = squareToAttack;
    }

    @Override
    public void visit(VisitorController singleMoveController) {

    }
}
