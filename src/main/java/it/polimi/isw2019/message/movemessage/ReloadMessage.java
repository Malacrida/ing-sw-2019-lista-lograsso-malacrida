package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.view.VisitorView;

public class ReloadMessage extends MoveMessage{

    @Override
    public void accept(VisitorView visitorView) {
        visitorView.visitReload(this);
    }

    public ReloadMessage(String nicknamePlayer) {
        super(nicknamePlayer);
    }
}
