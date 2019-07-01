package it.polimi.isw2019.view.ViewGui;

import javax.swing.*;
import java.util.ArrayList;

public class SquareLabel extends JLabel{
    /**
     * Size value unit of the single label
     */
    private int s;
    private Icon icon;
    private ArrayList<JLabel> players;

    public SquareLabel(int s, String path){
        this.s = s;
        this.setLayout(null);
        players=new ArrayList<>(5);
        UsefulMethods u = new UsefulMethods();

        for (int i=0;i<5;i++) {
            JLabel label = new JLabel();
            players.add(i, label);

        }

        players.get(0).setBounds(0,0,s,s);
        players.get(0).setIcon(u.getRelativeImage(s,s,path+"/blue.png"));
        players.get(0).setVisible(false);

        players.get(1).setBounds(2*s,0,s,s);
        players.get(1).setIcon(u.getRelativeImage(s,s,path+"/red.png"));
        players.get(1).setVisible(false);

        players.get(2).setBounds(1*s,1*s,s,s);
        players.get(2).setIcon(u.getRelativeImage(s,s,path+"/yellow.png"));
        players.get(2).setVisible(false);

        players.get(3).setBounds(0,2*s,s,s);
        players.get(3).setIcon(u.getRelativeImage(s,s,path+"/green.png"));
        players.get(3).setVisible(false);

        players.get(4).setBounds(2*s,2*s,s,s);
        players.get(4).setIcon(u.getRelativeImage(s,s,path+"/violet.png"));
        players.get(4).setVisible(false);

        for (int i=0;i<5;i++){
            this.add(players.get(i));
        }
    }

    public Icon getIcon() {
        return icon;
    }

    public void setImmage(Icon icon) {
        this.icon = icon;
    }

    public ArrayList<JLabel> getPlayers() {
        return players;
    }
}
