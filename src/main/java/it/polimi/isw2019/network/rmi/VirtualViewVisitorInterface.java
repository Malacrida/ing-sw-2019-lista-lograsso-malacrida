package it.polimi.isw2019.network.rmi;

import it.polimi.isw2019.message.movemessage.*;
import it.polimi.isw2019.message.playermove.TerminatorMove;

public interface VirtualViewVisitorInterface {


    /**
     * method to do update with visitor pattern and after send it to the networkHandler
     * @param actionMessage type message
     */
    void sendActionView(ActionMessage actionMessage);

    /**
     * method to do update with visitor pattern and after send it to the networkHandler
     * @param runMessage type message
     */
    void sendRun(RunMessage runMessage);

    /**
     * method to do update with visitor pattern and after send it to the networkHandler
     * @param grabMessage type message
     */
    void sendGrab(GrabMessage grabMessage);

    /**
     * method to do update with visitor pattern and after send it to the networkHandler
     * @param reloadMessage type message
     */
    void sendReload(ReloadMessage reloadMessage);

    /**
     * method to do update with visitor pattern and after send it to the networkHandler
     * @param updateMessage type message
     */
    void sendUpdateView(UpdateMessage updateMessage);

    /**
     * method to do update with visitor pattern and after send it to the networkHandler
     * @param endRegistration type message
     */
    void sendWaitForStart(EndRegistration endRegistration);

    /**
     * method to do update with visitor pattern and after send it to the networkHandler
     * @param useWeaponCardMessage type message
     */
    void sendUseWeaponCard(UseWeaponCardMessage useWeaponCardMessage);

    /**
     * method to do update with visitor pattern and after send it to the networkHandler
     * @param choiceCard type message
     */
    void sendPowerUpChoice(ChoiceCard choiceCard);

    /**
     * method to do update with visitor pattern and after send it to the networkHandler
     * @param usePowerUpCardMessage type message
     */
    void sendUsePowerUpCard(UsePowerUpCardMessage usePowerUpCardMessage);

    /**
     * method to do update with visitor pattern and after send it to the networkHandler
     * @param firstMessageFirstPlayer type message
     */
    void sendFirstPlayerChooseMap(FirstMessageFirstPlayer firstMessageFirstPlayer);

    /**
     * method to do update with visitor pattern and after send it to the networkHandler
     * @param endGame type message
     */
    void sendEndGame (EndGame endGame);

    /**
     * method to do update with visitor pattern and after send it to the networkHandler
     * @param terminatorMessage type message
     */
    void sendTerminatorMessage (TerminatorMessage terminatorMessage);

}
