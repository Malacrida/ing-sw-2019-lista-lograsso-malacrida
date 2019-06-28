package it.polimi.isw2019.view.ViewGui.Map;

import it.polimi.isw2019.view.ViewGui.FirstSceen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Map1 {

    private JFrame mainFrame;
    private JLabel statusLabel;

    public Map1(){
        JFrame.setDefaultLookAndFeelDecorated(true);

        mainFrame = new JFrame("Java SWING Elements Examples");
        statusLabel= new JLabel();
        statusLabel.setSize(350,100);

        //mainFrame.setLayout(new FlowLayout());
        //mainFrame.setSize(1500,900);

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        //button with image

        //load and resize image
        ImageIcon leftButtonIcon = new ImageIcon("./src/main/resources/img/map/Map1.png");
        Image img = leftButtonIcon.getImage() ;
        Image newImg = img .getScaledInstance( 500, 500,  java.awt.Image.SCALE_SMOOTH ) ;
        ImageIcon iconSmaller = new ImageIcon( newImg );

        JLabel background= new JLabel("",iconSmaller ,JLabel.CENTER);
        background.setBounds(0,0,930,634);
        JPanel panelImage = new JPanel();

        // Define the panel to hold the buttons
        JPanel panel = new JPanel();

        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);


        panelImage.add(background, BorderLayout.CENTER);
        GridBagConstraints gbcImage = new GridBagConstraints();
        gbcImage.gridx=0;
        gbcImage.gridy=0;
        gbcImage.gridwidth=2;
        panel.add(panelImage,gbcImage);



        mainFrame.add(panel);

        mainFrame.pack();
        mainFrame.setVisible(true);

    }


    /*
    class static CustomActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            statusLabel.setText("Button Clicked.");
            System.out.println(e.getActionCommand());
        }
    }

    class static CustomMouseListener implements MouseListener {
        public void mouseClicked(MouseEvent e)
        {
            statusLabel.setText("Mouse Clicked: ("+e.getX()+", "+e.getY() +")");
        }
        public void mousePressed(MouseEvent e)
        {
            statusLabel.setText("Mouse pressed.");
        }
        public void mouseReleased(MouseEvent e)
        {
            statusLabel.setText("Mouse released.");
        }
        public void mouseEntered(MouseEvent e)
        {
            statusLabel.setText("Mouse entered.");
        }

        public void mouseExited(MouseEvent e)
        {
            statusLabel.setText("Mouse exit.");
        }
    }*/

    public static void setMap1(){

    }


    public static JLabel getMap() {
        return null;
    }
}
