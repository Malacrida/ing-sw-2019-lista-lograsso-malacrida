package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.network.rmi.VirtualViewVisitorInterface;
import it.polimi.isw2019.view.VisitorView;

public class StartMessage extends MoveMessage {


    public StartMessage(String nicknamePlayer) {
        super(nicknamePlayer);
    }
    @Override
    public void accept(VisitorView visitorview) {
        visitorview.visitStartView(this);
    }

    @Override
    public void accept(VirtualViewVisitorInterface virtualView) {

    }
}
