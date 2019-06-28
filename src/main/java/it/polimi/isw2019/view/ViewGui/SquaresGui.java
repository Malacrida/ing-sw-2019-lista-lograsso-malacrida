package it.polimi.isw2019.view.ViewGui;

import javax.swing.*;
import java.awt.*;

public class SquaresGui {

    static ImageIcon red00;
    static int width = Toolkit.getDefaultToolkit().getScreenSize().width/2;
    static int height = Toolkit.getDefaultToolkit().getScreenSize().height/2;


    public static ImageIcon setRed00() {

        ImageIcon leftButtonIcon = new ImageIcon("./src/main/resources/img/squares/00red.png");
        Image img = leftButtonIcon.getImage() ;
        Image newImg = img .getScaledInstance( width, height,  java.awt.Image.SCALE_SMOOTH ) ;
        ImageIcon red00 = new ImageIcon( newImg );

        return red00;

    }
}
