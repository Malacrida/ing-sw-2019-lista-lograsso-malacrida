package it.polimi.isw2019.View;

import it.polimi.isw2019.Utilities.Observable;
import it.polimi.isw2019.Utilities.Observer;

public class View extends Observable implements Observer {

    private String nickname;


    public void welcome(){

    }
    
    public void insertDate(String nickname){

        this.nickname=nickname;
    }

    public void chooseGameMode(){

    }

    public void gameView (){

    }

    public void chooseAction(){

    }

    @Override
    public void update() {

    }

    @Override
    public <C> void update(C change) {

    }


}
