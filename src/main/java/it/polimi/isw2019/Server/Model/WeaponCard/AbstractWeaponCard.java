package it.polimi.isw2019.Server.Model.WeaponCard;

import it.polimi.isw2019.Server.Model.*;
import it.polimi.isw2019.Server.Model.Exception.ErrorEffectException;
import it.polimi.isw2019.Server.Model.Exception.DamageTrackException;
import it.polimi.isw2019.Server.Model.Exception.NoEffectException;

import java.util.ArrayList;

public abstract class AbstractWeaponCard{
    protected int id;
    protected String name;
    protected ColorCube color;
    protected ArrayList<String> infoEffect;
    protected ArrayList<Player> deathPlayers;
    protected int[] rechargeCube = new int[3]; //LEGENDA [0] -> RED [1] -> YELLOW [2] -> BLUE
    protected StateCard stateCard = StateCard.DECK;
    protected int maxPossibleEffects;
    protected boolean firstIsValid = false;
    protected boolean secondIsValid = false;

    public AbstractWeaponCard(int id, String name, ColorCube color, int maxPossibleEffects) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.maxPossibleEffects = maxPossibleEffects;
    }

    //Methods


    public int getID(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int[] getRechargecube(){ return rechargeCube; }

    public ColorCube getColor(){
        return color;
    }

    public ArrayList<String> getInfoEffect(){
        return infoEffect;
    }

    public StateCard checkState(){
        return stateCard;
    }

    public void changeState(StateCard newStateCard) {
        this.stateCard = newStateCard;
    }

    //AGGIUNGERE METODO playerIsVisible


    /* I due giocatori distano di 1 sull'asse delle X e hanno la stessa Y*/

    public boolean oneDistanceY (Player firstPlayer, Player secondPlayer){

        if (firstPlayer.getX() == secondPlayer.getX()){

            if ((firstPlayer.getY() - secondPlayer.getY()) == 1){
                return true;
            }

            else return false;
        }

        else return false;
    }


    /* I due giocatori distano di 1 sull'asse delle X e hanno la stessa Y*/

    public boolean oneDistanceX (Player firstPlayer, Player secondPlayer){

        if (firstPlayer.getY() == secondPlayer.getY()){

            if ((firstPlayer.getX() - secondPlayer.getX()) == 1){
                return true;
            }

            else return false;
        }

        else return false;
    }

    public boolean sameSquare (Player firstPlayer, Player secondPlayer){

        int x1 = firstPlayer.getX();
        int y1 = firstPlayer.getY();
        int x2 = secondPlayer.getX();
        int y2 = secondPlayer.getY();

        if ((x1 == x2) && (y1 == y2)){

            return true;

        }

        else return false;

    }

    public boolean oneDistance(Player firstPlayer, Player secondPlayer) {

        if ((oneDistanceX(firstPlayer, secondPlayer)) && (!oneDistanceY(firstPlayer, secondPlayer))){

            return true;

        }

        else if ((!oneDistanceX(firstPlayer, secondPlayer)) && (oneDistanceY(firstPlayer, secondPlayer))){

            return true;

        }

        else return false;
    }

    public char sameDirection (Player firstPlayer, Player secondPlayer) {

        int x1 = firstPlayer.getX();
        int y1 = firstPlayer.getY();
        int x2 = secondPlayer.getX();
        int y2 = secondPlayer.getY();
        char direction;

        if (x1 == x2){

            direction = 'x';

            return direction;

        }

        else if (y1 == y2) {

            direction = 'y';

            return direction;

        }

        else {

            direction = 'n';

            return direction;

        }

    }

    public boolean aboveSquare(int x1, int y1, int x2, int y2){

        if ((x1 - x2 == 1) && (y1 - y2 == 0)){
            return true;
        }

        else if ((y1 - y2 == 1) && (x1 - x2 == 0)){
            return true;
        }

        else return false;

    }

    public boolean machineGunAndPlasmaGunEffect(Player attacker, Player firstDefender, boolean firstValid){

        if(firstIsValid){

            try {
                firstDefender.sufferDamageOrMark(attacker.getColor(), 1,0);
            } catch (DamageTrackException e) {
                e.printStackTrace();
            }
            return true;
        } else {
            return false;
        }
    }

    public void giveOneDamageNoMarksInOneSquare(Player attacker, ArrayList<Player> playerList) {
        for (Player aPlayerList : playerList) {

            try {

                aPlayerList.sufferDamageOrMark(attacker.getColor(), 1, 0);

            } catch (DamageTrackException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    * attackSquare -> square in cui vuole attaccare
    * attacker -> Player attaccante
    * defender -> Player colpito
    */
    public abstract void firstEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException, ErrorEffectException, DamageTrackException;

    public abstract void secondEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException, ErrorEffectException, DamageTrackException;

    public abstract void thirdEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException, ErrorEffectException, DamageTrackException;
}