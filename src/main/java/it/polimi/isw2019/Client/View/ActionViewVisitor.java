package it.polimi.isw2019.Client.View;

import it.polimi.isw2019.Server.Message.MoveMessage.MoveMessage;
import it.polimi.isw2019.Server.Utilities.Observer;

public class ActionViewVisitor implements Observer<MoveMessage> {

    //ModelView

    @Override
    public void update(MoveMessage message) {
        //invoke method handleMessage
    }
    //for that invokes the right VisitorView. Returns the correct message


}
