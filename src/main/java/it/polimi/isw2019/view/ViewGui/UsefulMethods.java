package it.polimi.isw2019.view.ViewGui;

import javax.swing.*;
import java.awt.*;

public class UsefulMethods {
    public Icon getRelativeImage(int w ,int h ,String path){
        Image backgroundImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource(path));

        backgroundImage = backgroundImage.getScaledInstance(w, h, Image.SCALE_DEFAULT);
        Icon backgroundIcon = new ImageIcon(backgroundImage);
        return backgroundIcon;
    }
    public Image getRelativeIm(int w ,int h ,String path){
        Image backgroundImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource(path));

        backgroundImage = backgroundImage.getScaledInstance(w, h, Image.SCALE_DEFAULT);
        return backgroundImage;
    }
}
