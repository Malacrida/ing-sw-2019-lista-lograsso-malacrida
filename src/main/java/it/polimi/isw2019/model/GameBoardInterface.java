package it.polimi.isw2019.model;

import it.polimi.isw2019.model.weaponcard.WeaponCardInterface;

import java.util.ArrayList;

public interface GameBoardInterface {

    public GameBoardInterface getGameBoardInterface();

    public ArenaInterface getArenaInterface();

    public WeaponCardInterface getWeaponCard(ColorCube color, int index);

    //ti restituisce le varie carte in base ai punti di spawn
    public ArrayList<WeaponCardInterface> getWeaponCard(ColorCube color);

    //aggiungere i vari getter e vari toString

}
