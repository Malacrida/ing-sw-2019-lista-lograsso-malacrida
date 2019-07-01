package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.network.rmi.VirtualViewVisitorInterface;
import it.polimi.isw2019.view.VisitorView;

public class UsePowerUpCardMessage extends MoveMessage {


    private int[] featuresAvailable;
    private boolean[] stateCard;
    private int stateGame;
    private boolean attacked;
    private int effectCard[];

    public UsePowerUpCardMessage(String nicknamePlayer) {
        super(nicknamePlayer);
    }


    public UsePowerUpCardMessage(String nicknamePlayer, int[] featuresAvailable) {
        super(nicknamePlayer);
        this.featuresAvailable = featuresAvailable;
    }

    public UsePowerUpCardMessage(String nicknamePlayer, int[] featuresAvailable,boolean[] stateCard, int stateGame, boolean attacked, int[] effectCard, String error) {
        super(nicknamePlayer,error);
        this.featuresAvailable = featuresAvailable;
        this.stateCard = stateCard;
        this.stateGame = stateGame;
        this.attacked = attacked;
        this.effectCard = effectCard;
    }

    public int[] getFeaturesAvailable() {
        return featuresAvailable;
    }

    public void setFeaturesAvailable(int[] featuresAvailable) {
        this.featuresAvailable = featuresAvailable;
    }

    public boolean[] getStateCard() {
        return stateCard;
    }

    public void setStateCard(boolean[] stateCard) {
        this.stateCard = stateCard;
    }

    public int getStateGame() {
        return stateGame;
    }

    public void setStateGame(int stateGame) {
        this.stateGame = stateGame;
    }

    public boolean isAttacked() {
        return attacked;
    }

    public void setAttacked(boolean attacked) {
        this.attacked = attacked;
    }

    public int[] getEffectCard() {
        return effectCard;
    }

    public void setEffectCard(int[] effectCard) {
        this.effectCard = effectCard;
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
