package it.polimi.isw2019.message.movemessage;

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
    public int[] getFeaturesAvailable() {
        return new int[0];
    }

    @Override
    public void setFeaturesAvailable(int[] featuresAvailable) {

    }
}
