package it.polimi.isw2019.Model;

public class SquareSpawn extends Square {

    // Array weapons

    SquareSpawn(int squareID, int squareN, int squareE, int squareO, int squareS, boolean sideN, boolean sideE, boolean sideO, boolean sideS) {
        super(squareID, squareN, squareE, squareO, squareS, sideN, sideE, sideO, sideS, true);
    }

    public void spawnPlayer (Player player){

    }

    public boolean takeWeapon (/*da mettere le carte*/ ){
        //da sistemare
        return false;
    }

    public void putNewWeponCard(){

    }
}
