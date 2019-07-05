package it.polimi.isw2019.network.rmi;

import it.polimi.isw2019.message.movemessage.*;
import it.polimi.isw2019.message.playermove.TerminatorMove;

public interface VirtualViewVisitorInterface {

    void sendActionView(ActionMessage actionMessage);

    void sendRun(RunMessage runMessage);

    void sendGrab(GrabMessage grabMessage);

    void sendReload(ReloadMessage reloadMessage);

    void sendUpdateView(UpdateMessage updateMessage);

    void sendWaitForStart(EndRegistration endRegistration);

    void sendUseWeaponCard(UseWeaponCardMessage useWeaponCardMessage);

    void sendPowerUpChoice(ChoiceCard choiceCard);

    void sendUsePowerUpCard(UsePowerUpCardMessage usePowerUpCardMessage);

    void sendFirstPlayerChooseMap(FirstMessageFirstPlayer firstMessageFirstPlayer);

    void sendEndGame (EndGame endGame);

    void sendTerminatorMessage (TerminatorMessage terminatorMessage);

}
