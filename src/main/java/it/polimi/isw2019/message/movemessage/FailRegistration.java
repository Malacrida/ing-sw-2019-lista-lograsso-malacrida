package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.view.VisitorView;

public class FailRegistration extends MoveMessage {
    public FailRegistration(String nicknamePlayer) {
        super(nicknamePlayer);
    }

    @Override
    public void accept(VisitorView visitorview) {
        visitorview.failRegistration(this);
    }

    @Override
    public int[] getFeaturesAvailable() {
        return new int[0];
    }

    @Override
    public void setFeaturesAvailable(int[] featuresAvailable) {

    }
}
