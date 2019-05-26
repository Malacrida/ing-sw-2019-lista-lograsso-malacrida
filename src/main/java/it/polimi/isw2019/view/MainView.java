package it.polimi.isw2019.view;

import it.polimi.isw2019.message.MoveMessage.MoveMessage;
import it.polimi.isw2019.Utilities.Observer;

public class MainView implements Observer<MoveMessage> {

    private StatusView statusView;

    @Override
    public void update(MoveMessage message) {

    }
}
