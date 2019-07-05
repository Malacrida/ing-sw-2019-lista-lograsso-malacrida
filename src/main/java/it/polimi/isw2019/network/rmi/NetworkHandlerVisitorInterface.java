package it.polimi.isw2019.network.rmi;

import it.polimi.isw2019.message.playermove.*;


public interface NetworkHandlerVisitorInterface {


    /**
     * method to do update with visitor pattern and after send it to the server
     * @param chooseMapMove type message
     */
    void sendChooseMap(ChooseMapMove chooseMapMove);

    /**
     * method to do update with visitor pattern and after send it to the server
     * @param chooseActionMove type message
     */
    void sendActionChoose(ChooseActionMove chooseActionMove);

    /**
     * method to do update with visitor pattern and after send it to the server
     * @param runMove type message
     */
    void sendRun(RunMove runMove);

    /**
     * method to do update with visitor pattern and after send it to the server
     * @param grabMove type message
     */
    void sendGrab(GrabMove grabMove);

    /**
     * method to do update with visitor pattern and after send it to the server
     * @param firstMessage type message
     */
    void sendRegisterPlayer(FirstMessage firstMessage);

    /**
     * method to do update with visitor pattern and after send it to the server
     * @param reloadMove type message
     */
    void sendReload(ReloadMove reloadMove);

    /**
     * method to do update with visitor pattern and after send it to the server
     * @param powerUpChoice type message
     */
    void sendPowerUpChoice(PowerUpChoice powerUpChoice);

    /**
     * method to do update with visitor pattern and after send it to the server
     * @param usePowerUpCard type message
     */
    void sendUsePowerUpCard(UsePowerUpCard usePowerUpCard);

    /**
     * method to do update with visitor pattern and after send it to the server
     * @param weaponCardChoice type message
     */
    void sendWeaponCardChoice(WeaponCardChoice weaponCardChoice);

    /**
     * method to do update with visitor pattern and after send it to the server
     * @param useWeaponCard type message
     */
    void sendUseWeaponCard(UseWeaponCard useWeaponCard);

    /**
     * method to do update with visitor pattern and after send it to the server
     * @param connectionMove type message
     */
    void sendConnectionClient(ConnectionMove connectionMove);

    /**
     * method to do update with visitor pattern and after send it to the server
     * @param terminatorMove type message
     */
    void sendTerminatorMove (TerminatorMove terminatorMove);
}


