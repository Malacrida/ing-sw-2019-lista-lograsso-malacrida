package it.polimi.isw2019.model;

import java.util.ArrayList;

public interface ModelInterface {
    //getter degli elementi del model
    //sia getRepresentation che getdell oggetto stesso
    ArrayList<PlayerInterface> getPlayersInterface();
    PlayerInterface getCurrentPlayer();

}
