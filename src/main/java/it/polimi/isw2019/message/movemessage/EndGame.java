package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.network.rmi.VirtualViewVisitorInterface;
import it.polimi.isw2019.view.VisitorView;

public class EndGame extends MoveMessage {

    private String [] ranking;
    private int[] points;
    private int pointMax;
    private String winner;
    private String phrase;

    public EndGame (String nickname, String[] ranking, int [] points, int pointMax, String winner, String phrase, boolean notifyAll){
        super(nickname,notifyAll);
        this.ranking= ranking;
        this.points=points;
        this.pointMax= pointMax;
        this.winner= winner;
        this.phrase=phrase;
    }

    public String getPhrase() {
        return phrase;
    }

    public int[] getPoints() {
        return points;
    }

    public String getWinner() {
        return winner;
    }

    public int getPointMax() {
        return pointMax;
    }

    public String[] getRanking() {
        return ranking;
    }

    @Override
    public void accept(VisitorView visitorview) {
        visitorview.visitEndGame(this);
    }

    @Override
    public void accept(VirtualViewVisitorInterface virtualView) {
        virtualView.sendEndGame(this);
    }
}
