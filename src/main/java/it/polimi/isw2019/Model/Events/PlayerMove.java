package it.polimi.isw2019.Model.Events;
import it.polimi.isw2019.Model.GameBoard;
import it.polimi.isw2019.Model.Player;
import it.polimi.isw2019.View.View;
import java.util.ArrayList;

public class PlayerMove {
    private Player player;
    private int idAction;
    private View view;
    private ArrayList<Object> actionDate;

    public PlayerMove(Player player, int idAction, ArrayList<Object> actionDate, View view) {
        this.player = player;
        this.idAction = idAction;
        this.actionDate = actionDate;
        this.idAction = idAction;
    }

    public Player getPlayer(){
        return this.player.getPlayer();
    }

    public int getIdAction() {
        return this.idAction;
    }

    public ArrayList<Object> getActionDate(){
        return this.actionDate;
    }

    public View getView() {
        return view;
    }
}

