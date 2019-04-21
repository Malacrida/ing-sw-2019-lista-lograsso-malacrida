package it.polimi.isw2019.Model;

import it.polimi.isw2019.Model.Exception.ColorNotAvailable;

import java.util.ArrayList;

public class Model {

    private int turn;
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<PlayerBoard> playerBoardsAvailable= new ArrayList<>();

    Model (){

    }

    public void gameSetting (){
        playerBoardsAvailable= SetUpGame.setPlayerBoard();
    }

    public boolean containsColor (ColorPlayer color) throws ColorNotAvailable {
        for (int i=0; i<playerBoardsAvailable.size(); i++){
            if (playerBoardsAvailable.get(i).getColor()==color) return true;
        }
        throw new ColorNotAvailable();
    }

    public int positionPlayerBoardAviable (ColorPlayer color) throws ColorNotAvailable{
        for (int i=0; i<playerBoardsAvailable.size(); i++){
            if (playerBoardsAvailable.get(i).getColor()==color) return i;
        }
        throw new ColorNotAvailable();
    }

    public void setPlayer (String name, ColorPlayer colorPlayer) throws ColorNotAvailable {
        try {
            if (containsColor(colorPlayer)){
                Player player= new Player(name,colorPlayer, playerBoardsAvailable.get(positionPlayerBoardAviable(colorPlayer)));
                players.add(player);
                playerBoardsAvailable.remove(playerBoardsAvailable.get(positionPlayerBoardAviable(colorPlayer)));
            }
        }
        catch (ColorNotAvailable e){
            throw new ColorNotAvailable();
            //Manda un messaggio di scelta sbagliata -> Update!!
            // al posto di rilanciare l'eccezione
        }
    }

}