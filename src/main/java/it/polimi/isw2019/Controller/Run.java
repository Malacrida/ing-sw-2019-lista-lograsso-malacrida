package it.polimi.isw2019.Controller;

import it.polimi.isw2019.Message.PlayerMove.PlayerMove;
import it.polimi.isw2019.Message.RunMessage;
import it.polimi.isw2019.Model.Model;

public class Run extends StrategyAction {
    private boolean notify;
    @Override
    public void visitModel(Model model, PlayerMove playerMove){

        // cast in RUN MESSAGE
        // checkNumActionPerformed -> >2 allora invocare model un metodo che ritorna che ha esaurito il numero delle azioni
        //check che l'azione puo essere effettivamente svolta : check sullo status del player. altrimenti mex di errore
        //va a creare in base all'id E allo status del player corrente, l'azione desiderata
    }
}
