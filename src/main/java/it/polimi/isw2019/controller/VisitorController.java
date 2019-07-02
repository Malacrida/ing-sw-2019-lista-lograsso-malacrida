package it.polimi.isw2019.controller;

import it.polimi.isw2019.message.playermove.*;


public interface VisitorController {

    void chooseMap(ChooseMapMove chooseMapMove);

    void visitControllerActionChoose(ChooseActionMove chooseActionMove);

    void visitControllerRun(RunMove runMove);

    void visitControllerGrab(GrabMove grabMove);

    void visitControllerRegisterPlayer(FirstMessage firstMessage);

    void visitReload(ReloadMove reloadMove);

    void powerUpChoice(PowerUpChoice powerUpChoice);

    void usePowerUpCard(UsePowerUpCard usePowerUpCard);

    void visitWeaponCardChoice(WeaponCardChoice weaponCardChoice);

    void useWeaponCard(UseWeaponCard useWeaponCard);


    void disconnectionPlayer (ConnectionMove connectionMove);

    //method invoked by server when the game is started
}
