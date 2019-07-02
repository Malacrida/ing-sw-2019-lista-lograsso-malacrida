package it.polimi.isw2019.view.ViewGui;

import javax.swing.*;
import java.awt.*;

public class Background extends JLabel {

    private int w;


    private int h;

    public Background (int w, int h, String relPath){
        this.w = w;
        this.h = h;

        Image backgroundImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/"+relPath));

        backgroundImage = backgroundImage.getScaledInstance(w, h, Image.SCALE_DEFAULT);

        Icon backgroundIcon = new ImageIcon(backgroundImage);

        setIcon(backgroundIcon);

        setLayout(null);

    }


    public void setNewImage(String newPath){
        Image backgroundImage = Toolkit.getDefaultToolkit().getImage(getClass().
                getResource("/img/"+newPath));

        backgroundImage = backgroundImage.getScaledInstance(w, h, Image.SCALE_DEFAULT);

        Icon backgroundIcon = new ImageIcon(backgroundImage);

        setIcon(backgroundIcon);

        repaint();
    }
}
