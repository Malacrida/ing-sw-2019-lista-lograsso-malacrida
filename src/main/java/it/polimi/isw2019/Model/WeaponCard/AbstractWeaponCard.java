package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.Exception.ErrorEffectException;
import it.polimi.isw2019.Model.Exception.NoEffectException;
import it.polimi.isw2019.Model.Player;
import it.polimi.isw2019.Model.StateCard;

import java.util.ArrayList;

public abstract class AbstractWeaponCard{
    protected int id;
    protected String name;
    protected ColorCube color;
    protected ArrayList<String> infoEffect;
    protected ArrayList<ColorCube> rechargeCube;
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

    public ArrayList<ColorCube> getRechargecube(){
        return rechargeCube;
    }

    public ColorCube getColor(){
        return color;
    }

    public ArrayList<String> getInfoEffect(){
        return infoEffect;
    }

    public StateCard checkState(){
        return stateCard;
    }

    public void changeState( StateCard newStateCard) {
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

    /*
    * attackSquare -> square in cui vuole attaccare
    * attacker -> Player attaccante
    * defender -> Player colpito
    */
    public abstract void firstEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException, ErrorEffectException;

    public abstract void secondEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException, ErrorEffectException;

    public abstract void thirdEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException, ErrorEffectException;
}