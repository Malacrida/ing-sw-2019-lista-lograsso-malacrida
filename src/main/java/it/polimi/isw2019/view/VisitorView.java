package it.polimi.isw2019.view;

import it.polimi.isw2019.message.movemessage.*;


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

    void powerUpChoice(ChoicePowerUpCard choicePowerUpCard);

    void visitStartTurn(StartTurn startTurn);
}
