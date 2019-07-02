package it.polimi.isw2019.view.ViewGui;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ArenaLabel extends JLabel {
    private int s;
    private ArrayList<SquareLabel> squares;

    public ArenaLabel(int s, int type){
        this.s = s;
        this.setLayout(null);
        this.squares = new ArrayList<>(Arrays.asList(null,null,null,null,null,null,null,null,null,null,null,null));
        int contat=0;
        String arenaName="";
        String arenaNameL="",arenaNameR="";
        int yBlank=0;
        UsefulMethods u = new UsefulMethods();
        switch (type){
            case 2:
                arenaName="Arena1-L1R1";
                yBlank=2;
                contat=0;
                arenaNameL="/Squares/L1.png";
                arenaNameR="/Squares/R1.png";
                break;
            case 3:
                arenaName="Arena2-L1R2";
                yBlank=6;
                contat=0;
                break;
            case 4:
                arenaName="Arena3-L2R1";
                yBlank=0;
                break;
            case 1:
                arenaName="Arena4-L2R2";
                yBlank=0;
                contat=1;
                break;
        }
        if(type>0) {
            for (int i = 0; i < 4; i++) {
                JLabel jlabel = new JLabel("" + i, SwingConstants.CENTER);
                jlabel.setBounds(2 * s + 3 * s * i, 0, s, s);
                jlabel.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, s / 2));
                jlabel.setBackground(Color.WHITE);
                jlabel.setOpaque(true);
                Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
                jlabel.setBorder(border);
                jlabel.setVisible(true);
                this.add(jlabel);
            }
            for (int i = 0; i < 3; i++) {
                JLabel jlabel = new JLabel("" + i, SwingConstants.CENTER);
                jlabel.setBounds(0, 2 * s + 3 * s * i, s, s);
                jlabel.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, s / 2));
                jlabel.setOpaque(true);
                Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
                jlabel.setBorder(border);
                jlabel.setVisible(true);
                this.add(jlabel);
            }
            String tempor = "";
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 4; j++) {
                    if (!(j == 0 && i == yBlank)) {
                        SquareLabel squareLabel = new SquareLabel(s, "/Damage");
                        squareLabel.setBounds(s + j * s * 3, s + i * s * 3, s * 3, s * 3);
                        tempor = "/Squares/" + arenaName + "/" + i + "" + j + ".png";
                        squareLabel.setImmage(u.getRelativeImage(s * 3, s * 3, tempor));
                        squareLabel.setVisible(true);
                        squares.add(contat, squareLabel);
                        this.add(squares.get(contat));
                        contat++;
                    }

                }
            }

        /*JLabel left = new JLabel();
        left.setIcon(u.getRelativeImage(s*6,s*9,arenaNameL));
        left.setBounds(s,s,s*6,s*9);
        left.setVisible(true);
        this.add(left);
        JLabel right = new JLabel();
        right.setIcon(u.getRelativeImage(s*6,s*9,arenaNameR));
        right.setBounds(s*7,s,s*6,s*9);
        right.setVisible(true);
        this.add(right);*/


            this.setVisible(true);
        }
    }

    public ArrayList<SquareLabel> getSquares() {
        return squares;
    }
}
