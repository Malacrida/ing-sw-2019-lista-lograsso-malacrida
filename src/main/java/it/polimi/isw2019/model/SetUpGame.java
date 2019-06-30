package it.polimi.isw2019.model;

import it.polimi.isw2019.model.weaponcard.*;

import java.util.ArrayList;

public class SetUpGame {

    private static ArrayList<PlayerBoard> playerBoards= new ArrayList<>();
    private static ArrayList<AbstractWeaponCard> weaponCards = new ArrayList<>();

    /**
     * create playerboard
     * @return
     */

    public static ArrayList<PlayerBoard> setPlayerBoard (){
        PlayerBoard playerBoardYellow = new PlayerBoard(ColorPlayer.YELLOW);
        PlayerBoard playerBoardGreen = new PlayerBoard(ColorPlayer.GREEN);
        PlayerBoard playerBoardBlue = new  PlayerBoard(ColorPlayer.BLUE);
        PlayerBoard playerBoardViolet = new PlayerBoard(ColorPlayer.VIOLET);
        PlayerBoard playerBoardGrey = new PlayerBoard(ColorPlayer.GREY);

        playerBoards.add(playerBoardYellow);
        playerBoards.add(playerBoardGreen);
        playerBoards.add(playerBoardBlue);
        playerBoards.add(playerBoardViolet);
        playerBoards.add(playerBoardGrey);

        return playerBoards;
    }

    /**
     * create deck of weapon cards
     * @return
     */

    public static ArrayList<AbstractWeaponCard> setWeaponCards(){

      weaponCards.add(new CyberBlade());
      weaponCards.add(new Electroscythe());
      weaponCards.add(new Flamethrower());
      weaponCards.add(new Furnace());
      weaponCards.add(new GrenadeLauncher());
      weaponCards.add(new HeatSeeker());
      weaponCards.add(new Hellion());
      weaponCards.add(new LockRifle());
      weaponCards.add(new MachineGun());
      weaponCards.add(new PlasmaGun());
      weaponCards.add(new PowerGlove());
      weaponCards.add(new RailGun());
      weaponCards.add(new RocketLauncher());
      weaponCards.add(new ShockWave());
      weaponCards.add(new ShotGun());
      weaponCards.add(new SledgeHammer());
      weaponCards.add(new Thor());
      weaponCards.add(new TractorBeam());
      weaponCards.add(new VortexCannon());
      weaponCards.add(new Whisper());
      weaponCards.add(new ZX_2());

      return weaponCards;
    }



}
