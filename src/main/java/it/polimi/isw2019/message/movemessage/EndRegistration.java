package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.view.VisitorView;

public class EndRegistration extends MoveMessage {
    private String nicknamePlayer;

    @Override
    public String getNicknamePlayer() {
        return nicknamePlayer;
    }

    public EndRegistration(String nicknamePlayer) {
        super(nicknamePlayer);

    }

    @Override
    public void accept(VisitorView visitorview) {
            visitorview.waitForStart(this);
    }
}
