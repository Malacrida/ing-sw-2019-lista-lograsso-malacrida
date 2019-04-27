package it.polimi.isw2019.Model;

import it.polimi.isw2019.Model.Exception.OutOfRangeException;
import it.polimi.isw2019.Model.WeaponCard.AbstractWeaponCard;
import java.util.ArrayList;
import static it.polimi.isw2019.Model.ColorRoom.*;

public class Arena {

    private static Arena instance; // guardare se fare l'attributo static
    private Square[][] squares= new Square[3][4];

    private ArrayList<Room> rooms= new ArrayList<>();

    Arena(){

    }

    public static Arena instanceArena (){
        if (instance==null) {
            instance = new Arena();
        }
        return instance;
    }

    public void chooseArena (int numArena) throws OutOfRangeException{
        try {
            squares= CreateArena.chooseMap(numArena);
            rooms= CreateArena.chooseRoom(numArena);
        }
        catch (OutOfRangeException e){
            throw new OutOfRangeException();
        }

    }

    //I colori indicano i punti di spawn
    public void setWeaponsCardOnSquareSpawn (ArrayList<AbstractWeaponCard> weaponCardsRed, ArrayList<AbstractWeaponCard> weaponCardsBlue, ArrayList<AbstractWeaponCard> weaponCardsYellow){
        squares[0][2].setWeaponCards(weaponCardsRed);
        squares[1][0].setWeaponCards(weaponCardsBlue);
        squares[2][3].setWeaponCards(weaponCardsYellow);
    }

    public void placeAnotherWeaponCardsOnSquareSpawn (AbstractWeaponCard weaponCard, int x, int y){
        squares[x][y].putNewWeaponCard(weaponCard);
    }

    public boolean containsWeaponOnSpawnSquare (int x, int y, AbstractWeaponCard weaponCard){
        return squares[x][y].containsWeapon(weaponCard);
    }

    public void takeWeaponCardsOnSquareSpawn (AbstractWeaponCard weaponCard, int x, int y){
        squares[x][y].takeWeapon(weaponCard);
    }


}
