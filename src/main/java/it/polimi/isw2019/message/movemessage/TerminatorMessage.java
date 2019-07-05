package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.network.rmi.VirtualViewVisitorInterface;
import it.polimi.isw2019.view.VisitorView;

import java.util.ArrayList;

public class TerminatorMessage extends MoveMessage {

    private boolean runOrDamage;

    private ArrayList<String> colorSpawn;

    private int movement;

    private int numPeopleToKill;

    private int[][] cooPeople;
    public TerminatorMessage(String nicknamePlayer){
        super(nicknamePlayer);
    }
    public TerminatorMessage(String nicknamePlayer, boolean runOrDamage, ArrayList<String> colorSpawn, int movement, int numPeopleToKill, int[][] cooPeople,String error) {
        super(nicknamePlayer,error);
        this.runOrDamage = runOrDamage;
        this.colorSpawn = colorSpawn;
        this.movement = 1;
        this.numPeopleToKill = 1;
        this.cooPeople = cooPeople;
    }

    public boolean isRunOrDamage() {
        return runOrDamage;
    }

    public void setRunOrDamage(boolean runOrDamage) {
        this.runOrDamage = runOrDamage;
    }

    public ArrayList<String> getColorSpawn() {
        return colorSpawn;
    }

    public void setColorSpawn(ArrayList<String> colorSpawn) {
        this.colorSpawn = colorSpawn;
    }

    public int[][] getCooPeople() {
        return cooPeople;
    }

    public void setCooPeople(int[][] cooPeople) {
        this.cooPeople = cooPeople;
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public int getNumPeopleToKill() {
        return numPeopleToKill;
    }

    public void setNumPeopleToKill(int numPeopleToKill) {
        this.numPeopleToKill = numPeopleToKill;
    }

    @Override
    public void accept(VisitorView visitorview) {
            visitorview.terminatorAction(this);
    }

    @Override
    public void accept(VirtualViewVisitorInterface virtualView) {
        virtualView.sendTerminatorMessage(this);
    }
}
