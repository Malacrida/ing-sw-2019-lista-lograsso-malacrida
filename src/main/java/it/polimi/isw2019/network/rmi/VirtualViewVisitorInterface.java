package it.polimi.isw2019.network.rmi;

import it.polimi.isw2019.message.movemessage.*;

public interface VirtualViewVisitorInterface {


    void sendActionView(ActionMessage actionMessage);

    void sendRun(RunMessage runMessage);

    void sendGrab(GrabMessage grabMessage);

    void sendReload(ReloadMessage reloadMessage);

    void sendUpdateView(UpdateMessage updateMessage);

    //void sendOkRegistration(RegistrationPlayer registrationPlayer);

    void sendWaitForStart(EndRegistration endRegistration);

    //void sendWeaponCardChoice(ChoiceWeaponCard choiceWeaponCard);

    void sendUseWeaponCard(UseWeaponCardMessage useWeaponCardMessage);

    void sendPowerUpChoice(ChoicePowerUpCard choicePowerUpCard);

    void sendUsePowerUpCard(UsePowerUpCardMessage usePowerUpCardMessage);

    void sendFirstPlayerChooseMap(FirstMessageFirstPlayer firstMessageFirstPlayer);

    void sendFailRegistration(FailRegistration failRegistration);
}
