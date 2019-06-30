package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.network.rmi.VirtualViewVisitorInterface;
import it.polimi.isw2019.view.VisitorView;

public class EndRegistration extends MoveMessage {

    public EndRegistration(String nicknamePlayer) {
        super(nicknamePlayer);
        System.out.println("ok accept end registration constriuction");

    }

    @Override
    public void accept(VisitorView visitorview) {
        System.out.println("ok accept end registration");
            visitorview.waitForStart(this);
    }

    @Override
    public void accept(VirtualViewVisitorInterface virtualView) {
        virtualView.sendWaitForStart(this);
    }

}
