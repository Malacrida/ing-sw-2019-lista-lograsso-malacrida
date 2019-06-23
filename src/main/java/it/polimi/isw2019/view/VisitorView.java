package it.polimi.isw2019.view;

import it.polimi.isw2019.message.movemessage.*;
import it.polimi.isw2019.message.playermove.FirstMessage;


public interface VisitorView{

    void visitSetupView(SetUpMessage setUpMessage);

    void visitActionView(ActionMessage actionMessage);

    void visitRun(RunMessage runMessage);

    void visitGrab(GrabMessage grabMessage);

    void visitReload(ReloadMessage reloadMessage);

    void visitUpdateView(UpdateMessage updateMessage);

    void visitOkRegistration(RegistrationPlayer registrationPlayer);

    void waitForStart(EndRegistration endRegistration);

    void weaponCardChoice(ChoiceWeaponCard choiceWeaponCard);

    void useWeaponCard(UseWeaponCardMessage useWeaponCardMessage);

    void powerUpChoice(ChoicePowerUpCard choicePowerUpCard);

    void usePowerUpCard(UsePowerUpCardMessage usePowerUpCardMessage);

    void visitStartTurn(StartTurn startTurn);

    void firstPlayerChooseMap(FirstMessageFirstPlayer firstMessageFirstPlayer);

    void colorChoice();

    void failRegistration(FailRegistration failRegistration);
}
