package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.view.VisitorView;

public interface VisitableMessageMove {

    public void accept(VisitorView visitorview);
}
