package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.network.rmi.VirtualViewVisitorInterface;
import it.polimi.isw2019.view.VisitorView;

public class UsePowerUpCardMessage extends MoveMessage {

    private int[] featuresAvailable;
    private int stateGame;
    private boolean[] canBeUsed;
    private int[][] cooPlayer;

    public UsePowerUpCardMessage(String nicknamePlayer) {
        super(nicknamePlayer);
    }


    public UsePowerUpCardMessage(String nicknamePlayer, int[] featuresAvailable) {
        super(nicknamePlayer);
        this.featuresAvailable = featuresAvailable;
    }

    public UsePowerUpCardMessage(String nicknamePlayer, int[] featuresAvailable, int stateGame, boolean[] canBeUsed, String error, int[][] cooPlayer) {
        super(nicknamePlayer,error);
        this.featuresAvailable = featuresAvailable;
        this.stateGame = stateGame;
        this.canBeUsed = canBeUsed;
        this.cooPlayer = cooPlayer;
    }

    public int[] getFeaturesAvailable() {
        return featuresAvailable;
    }

    public void setFeaturesAvailable(int[] featuresAvailable) {
        this.featuresAvailable = featuresAvailable;
    }

    public int getStateGame() {
        return stateGame;
    }

    public void setStateGame(int stateGame) {
        this.stateGame = stateGame;
    }

    public boolean[] getEffectCard() {
        return canBeUsed;
    }

    public void setEffectCard(boolean[] canBeUsed) {
        this.canBeUsed = canBeUsed;
    }

    public boolean[] getCanBeUsed() {
        return canBeUsed;
    }

    public void setCanBeUsed(boolean[] canBeUsed) {
        this.canBeUsed = canBeUsed;
    }

    public int[][] getCooPlayer() {
        return cooPlayer;
    }

    public void setCooPlayer(int[][] cooPlayer) {
        this.cooPlayer = cooPlayer;
    }

    @Override
    public void accept(VisitorView visitorview) {
            visitorview.usePowerUpCard(this);
    }

    @Override
    public void accept(VirtualViewVisitorInterface virtualView) {
        virtualView.sendUsePowerUpCard(this);
    }
}
