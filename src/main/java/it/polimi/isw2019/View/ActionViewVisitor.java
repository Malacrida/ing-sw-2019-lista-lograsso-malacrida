package it.polimi.isw2019.View;

import it.polimi.isw2019.Message.MoveMessage.MoveMessage;
import it.polimi.isw2019.Utilities.Observer;

public class ActionViewVisitor implements Observer<MoveMessage> {

    //ModelView

    @Override
    public void update(MoveMessage message) {
        //invoke method handleMessage
    }
    //for that invokes the right VisitorView. Returns the correct message


}
