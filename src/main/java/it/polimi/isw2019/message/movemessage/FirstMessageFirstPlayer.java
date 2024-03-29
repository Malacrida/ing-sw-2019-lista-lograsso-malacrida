package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.network.rmi.VirtualViewVisitorInterface;
import it.polimi.isw2019.view.VisitorView;

import java.util.ArrayList;

public class FirstMessageFirstPlayer extends MoveMessage {

    String[] possibleMaps ;
    ArrayList<String> colorAvailable;

    public FirstMessageFirstPlayer(String nicknamePlayer) {
        super(nicknamePlayer);
    }

    public FirstMessageFirstPlayer (String nicknamePlayer, int idPlayer, String[] possibleMaps, ArrayList<String> colorAvailable){
        super(nicknamePlayer,idPlayer);
        this.possibleMaps= possibleMaps;
        this.colorAvailable=colorAvailable;

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
    public void accept(VirtualViewVisitorInterface virtualView) {
        virtualView.sendFirstPlayerChooseMap(this);
    }


}
