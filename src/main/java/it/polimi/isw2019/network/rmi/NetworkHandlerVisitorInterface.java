package it.polimi.isw2019.network.rmi;

import it.polimi.isw2019.message.playermove.*;


public interface NetworkHandlerVisitorInterface {

    void sendChooseMap(ChooseMapMove chooseMapMove);

    void sendActionChoose(ChooseActionMove chooseActionMove);

    void sendRun(RunMove runMove);

    void sendGrab(GrabMove grabMove);

    void sendRegisterPlayer(FirstMessage firstMessage);

    void sendReload(ReloadMove reloadMove);

    void sendPowerUpChoice(PowerUpChoice powerUpChoice);

    void sendUsePowerUpCard(UsePowerUpCard usePowerUpCard);

    void sendWeaponCardChoice(WeaponCardChoice weaponCardChoice);

    void sendUseWeaponCard(UseWeaponCard useWeaponCard);
}


