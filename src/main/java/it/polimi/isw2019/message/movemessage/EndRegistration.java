package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.network.rmi.VirtualViewVisitorInterface;
import it.polimi.isw2019.view.VisitorView;

public class EndRegistration extends MoveMessage {

    public EndRegistration(String nicknamePlayer) {
        super(nicknamePlayer);

    }

    @Override
    public void accept(VisitorView visitorview) {
            visitorview.waitForStart(this);
    }

    @Override
    public void accept(VirtualViewVisitorInterface virtualView) {
        virtualView.sendWaitForStart(this);
    }

}
