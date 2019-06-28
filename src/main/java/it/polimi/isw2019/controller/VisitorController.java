package it.polimi.isw2019.controller;

import it.polimi.isw2019.message.playermove.*;
import it.polimi.isw2019.model.exception.EndAction;
import it.polimi.isw2019.model.exception.EndSingleAction;


public interface VisitorController {

    void visitControllerSetUpPlayer(SetUpMove setUpMove);

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

    //method invoked by server when the game is started
}
