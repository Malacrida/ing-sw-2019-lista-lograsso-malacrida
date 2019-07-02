package it.polimi.isw2019.view.ViewGui;

import javax.swing.*;
import java.awt.*;

public class PlayersGui extends JLabel {


    Icon imagePlayerBlue;
    ImageIcon imagePlayerYellow;
    ImageIcon imagePlayerViolet;
    ImageIcon imagePlayerGreen;
    ImageIcon imagePlayerGrey;

    JButton playerYellow;
    JButton playerViolet;
    JButton playerGreen;
    JButton playerGrey;
    JButton playerBlue;


    public void setPlayerBlue() {


        int width = (Toolkit.getDefaultToolkit().getScreenSize().width/2)/8;
        int height = (Toolkit.getDefaultToolkit().getScreenSize().height/2)/8;

        ImageIcon icon = new ImageIcon("./src/main/resources/img/playerBlue.png");
        Image img = icon.getImage() ;
        Image newImg = img .getScaledInstance( width, height,  java.awt.Image.SCALE_SMOOTH ) ;
        imagePlayerBlue = new ImageIcon( newImg );

        setIcon(imagePlayerBlue);

        setVisible(true);
        setLayout(null);

    }


    public void setPlayerYellow() {

        ImageIcon icon = new ImageIcon("./src/main/resources/img/player/playerYellow.png");
        Image img = icon.getImage() ;
        Image newImg = img .getScaledInstance( 50, 50,  java.awt.Image.SCALE_SMOOTH ) ;
        imagePlayerYellow = new ImageIcon( newImg );
    }

    public void setPlayerViolet() {

        ImageIcon icon = new ImageIcon("./src/main/resources/img/player/playerViolet.png");
        Image img = icon.getImage() ;
        Image newImg = img .getScaledInstance( 50, 50,  java.awt.Image.SCALE_SMOOTH ) ;
        imagePlayerViolet = new ImageIcon( newImg );
    }

    public void setPlayerGreen() {

        ImageIcon icon = new ImageIcon("./src/main/resources/img/player/playerGreen.png");
        Image img = icon.getImage() ;
        Image newImg = img .getScaledInstance( 50, 50,  java.awt.Image.SCALE_SMOOTH ) ;
        imagePlayerGreen = new ImageIcon( newImg );

    }

    public void setPlayerGrey() {

        ImageIcon icon = new ImageIcon("./src/main/resources/img/player/playerGrey.png");
        Image img = icon.getImage() ;
        Image newImg = img .getScaledInstance( 50, 50,  java.awt.Image.SCALE_SMOOTH ) ;
        imagePlayerGrey = new ImageIcon( newImg );
    }

}
