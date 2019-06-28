package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.model.ArenaInterface;
import it.polimi.isw2019.view.VisitorView;

import java.util.ArrayList;

public class FirstMessageFirstPlayer extends MoveMessage {

    String[] possibleMaps ;
    ArrayList<String> colorAvailable;

    public FirstMessageFirstPlayer(String nicknamePlayer) {
        super(nicknamePlayer);
    }

    public String[] getPossibleMaps() {
        return possibleMaps;
    }

    public void setArenaInterfaces(String[] possibleMaps) {
        this.possibleMaps = possibleMaps;
    }

    public ArrayList<String> getColorAvailable() {
        return colorAvailable;
    }

    public void setColorAvailable(ArrayList<String> colorAvailable) {
        this.colorAvailable = colorAvailable;
    }

    @Override
    public void accept(VisitorView visitorview) {
        visitorview.firstPlayerChooseMap(this);
    }

    @Override
    public int[] getFeaturesAvailable() {
        return new int[0];
    }

    @Override
    public void setFeaturesAvailable(int[] featuresAvailable) {

    }
}
