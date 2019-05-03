package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.Player;
import it.polimi.isw2019.Model.Square;
import it.polimi.isw2019.Model.StateCard;

import java.util.ArrayList;

public abstract class AbstractWeaponCard{
    protected int id;
    protected String name;
    protected ColorCube color;
    protected ArrayList<String> infoEffect;
    protected ArrayList<ColorCube> rechargeCube;
    protected StateCard stateCard = StateCard.DECK;

    public AbstractWeaponCard(int id, String name, ColorCube color) {
        this.id = id;
        this.name = name;
        this.color = color;
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

    /*
    * attackSquare -> square in cui vuole attaccare
    * attacker -> Player attaccante
    * defender -> Player colpito
    */
    public abstract boolean firstEffect(Player attacker, Square firstAttackSquare, Player firstDefender, Square secondAttackSquare, Player secondDefender, Square thirdAttackSquare, Player thirdDefender);

    public abstract boolean secondEffect(Player attacker, Square firstAttackSquare, Player firstDefender, Square secondAttackSquare, Player secondDefender, Square thirdAttackSquare, Player thirdDefender);

    public abstract boolean thirdEffect(Player attacker, Square firstAttackSquare, Player firstDefender, Square secondAttackSquare, Player secondDefender, Square thirdAttackSquare, Player thirdDefender);
}