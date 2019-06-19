package it.polimi.isw2019.controller;

import it.polimi.isw2019.message.playermove.*;
import it.polimi.isw2019.model.exception.EndAction;
import it.polimi.isw2019.model.exception.EndSingleAction;


public interface VisitorController {

    void visitControllerSetUpPlayer(SetUpMove setUpMove);

    void visitControllerActionChoosen(ChooseActionMove chooseActionMove);

    void visitControllerRun(RunMove runMove) throws EndAction, EndSingleAction;

    void visitControllerGrab(GrabMove grabMove);

    void visitControllerChooseAction(ChooseActionMove chooseActionMove);

    void visitControllerRegisterPlayer(FirstMessage firstMessage);

    void visitColorChoosen(ColorChoosen colorChoosen);

    void visitReload(ReloadMove reloadMove);

    void powerUpChoice(PowerUpChoice powerUpChoice);

    void usePowerUpCard(UsePowerUpCard usePowerUpCard);

    void visitWeaponCardChoice(WeaponCardChoice weaponCardChoice);

    void useWeaponCard(UseWeaponCard useWeaponCard);
}
