package it.polimi.isw2019.view;

import it.polimi.isw2019.message.movemessage.*;
import it.polimi.isw2019.model.exception.EndTurn;


public interface VisitorView{

    void visitActionView(ActionMessage actionMessage);

    void visitRun(RunMessage runMessage);

    void visitGrab(GrabMessage grabMessage);

    void visitReload(ReloadMessage reloadMessage);

    void visitUpdateView(UpdateMessage updateMessage);

    void waitForStart(EndRegistration endRegistration);

    void useWeaponCard(UseWeaponCardMessage useWeaponCardMessage);

    void visitCardChoice(ChoiceCard choiceCard);

    void usePowerUpCard(UsePowerUpCardMessage usePowerUpCardMessage);

    void firstPlayerChooseMap(FirstMessageFirstPlayer firstMessageFirstPlayer);

    void visitStartView (StartMessage startMessage);

    void terminatorAction(TerminatorMessage terminatorMessage);

    void visitEndGame(EndGame endGame);
}
