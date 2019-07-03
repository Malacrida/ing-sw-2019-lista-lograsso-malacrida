package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.network.rmi.VirtualViewVisitorInterface;
import it.polimi.isw2019.view.VisitorView;

public class UsePowerUpCardMessage extends MoveMessage {

    private int[] featuresAvailable;
    private boolean attacked;
    private boolean[] canBeUsed;
    private int[][] cooPlayer;

    public UsePowerUpCardMessage(String nicknamePlayer) {
        super(nicknamePlayer);
    }


    public UsePowerUpCardMessage(String nicknamePlayer, int[] featuresAvailable) {
        super(nicknamePlayer);
        this.featuresAvailable = featuresAvailable;
    }

    public UsePowerUpCardMessage(String nicknamePlayer, int[] featuresAvailable, boolean attacked, boolean[] canBeUsed, String error, int[][] cooPlayer) {
        super(nicknamePlayer,error);
        this.featuresAvailable = featuresAvailable;
        this.attacked = attacked;
        this.canBeUsed = canBeUsed;
        this.cooPlayer = cooPlayer;
    }

    public int[] getFeaturesAvailable() {
        return featuresAvailable;
    }

    public void setFeaturesAvailable(int[] featuresAvailable) {
        this.featuresAvailable = featuresAvailable;
    }

    public boolean isAttacked() {
        return attacked;
    }

    public void setAttacked(boolean attacked) {
        this.attacked = attacked;
    }

    public boolean[] getEffectCard() {
        return canBeUsed;
    }

    public void setEffectCard(boolean[] canBeUsed) {
        this.canBeUsed = canBeUsed;
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
