package it.polimi.isw2019.model.powerupcard;

import it.polimi.isw2019.model.GameBoard;
import it.polimi.isw2019.model.Player;
import it.polimi.isw2019.model.StateCard;
import it.polimi.isw2019.model.exception.DamageTrackException;

public interface PowerUpCardInterface {

    public int getId();

    public String getName();

    public String getInfoEffect();

    public String getColor();

    public StateCard getCheckState();

    public void effect(GameBoard gameBoard, Player attacker, Player defender, int [][] coordinates) throws DamageTrackException;
}
