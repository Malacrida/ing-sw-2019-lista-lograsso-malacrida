package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.network.rmi.VirtualViewVisitorInterface;
import it.polimi.isw2019.view.VisitorView;


public class UpdateMessage extends MoveMessage {

    private String gameBoardDescription;
    private int[][] playersDescription;
    private int[][] featuresOfPlayersAvailable;
    private String[][] weaponCardDescription;
    private String[][] powerUpCardDescription;


    public UpdateMessage(String nicknamePlayer,String gameBoardDescription, int[][] playersDescription, int[][] featuresOfPlayersAvailable,String[][] weaponCardDescription, String[][] powerUpCardDescription,boolean notifyAll) {
        super(nicknamePlayer,notifyAll);
        this.gameBoardDescription = gameBoardDescription;
        this.playersDescription = playersDescription;
        this.featuresOfPlayersAvailable = featuresOfPlayersAvailable;
        this.weaponCardDescription = weaponCardDescription;
        this.powerUpCardDescription = powerUpCardDescription;
    }

    @Override
    public void accept(VisitorView visitorview) {
        visitorview.visitUpdateView(this);
    }

    @Override
    public void accept(VirtualViewVisitorInterface virtualView) {
        virtualView.sendUpdateView(this);
    }

    public int[][] getPlayersDescription() {
        return playersDescription;
    }

    public void setPlayersDescription(int[][] playersDescription) {
        this.playersDescription = playersDescription;
    }

    public int[][] getFeaturesOfPlayersAvailable() {
        return featuresOfPlayersAvailable;
    }

    public void setFeaturesOfPlayersAvailable(int[][] featuresOfPlayersAvailable) {
        this.featuresOfPlayersAvailable = featuresOfPlayersAvailable;
    }

    public String[][] getWeaponCardDescription() {
        return weaponCardDescription;
    }

    public void setWeaponCardDescription(String[][] weaponCardDescription) {
        this.weaponCardDescription = weaponCardDescription;
    }

    public String[][] getPowerUpCardDescription() {
        return powerUpCardDescription;
    }

    public void setPowerUpCardDescription(String[][] powerUpCardDescription) {
        this.powerUpCardDescription = powerUpCardDescription;
    }

    public String getGameBoardDescription() {
        return gameBoardDescription;
    }

    public void setGameBoardDescription(String gameBoardDescription) {
        this.gameBoardDescription = gameBoardDescription;
    }

    @Override
    public boolean isNotifyAll() {
        return super.isNotifyAll();
    }
}

