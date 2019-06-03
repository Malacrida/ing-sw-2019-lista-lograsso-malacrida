package it.polimi.isw2019.view;

import it.polimi.isw2019.message.movemessage.*;


public interface VisitorView{

    public abstract void visitSetupView(SetUpMessage setUpMessage);

    public abstract void visitActionView(ActionMessage actionMessage);

    public abstract  void errorMessageView(MoveMessage moveMessage);

    public abstract void visitRun(RunMessage runMessage);

    public abstract void visitRunGrab(RunGrabMessage runGrabMessage);

    public abstract void visitReload(ReloadMessage reloadMessage);

    public abstract void visitTurnView(TurnMessage turnMessage);

    public abstract void visitUpdateView(UpdateMessage updateMessage);

    public abstract void visitOkRegistration(RegistrationPlayer registrationPlayer);

    public abstract void waitForStart(EndRegistration endRegistration);

    public abstract void weaponCardChoice(UseWeaponCardMessage useWeaponCardMessage);
}
