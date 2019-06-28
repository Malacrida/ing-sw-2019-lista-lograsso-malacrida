package it.polimi.isw2019.view.ViewGui.PlayerBoard;

import javax.swing.*;
import java.awt.*;

public class PlayerBoardBlue {

     private  JFrame mainFrame;
     private static JLabel statusLabel;

     public PlayerBoardBlue(){

         mainFrame = new JFrame("Java SWING Elements Examples");
         statusLabel= new JLabel();
        // statusLabel.setSize(100,10);

         //mainFrame.setLayout(new FlowLayout());
        // mainFrame.setSize(100,10);

         mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         //JFrame.setDefaultLookAndFeelDecorated(true);

         //load and resize image
         ImageIcon leftButtonIcon = new ImageIcon("./src/main/resources/img/pb/PbViolet copia.png");
          Image img = leftButtonIcon.getImage() ;
         Image newImg = img .getScaledInstance( 500, 100,  java.awt.Image.SCALE_SMOOTH ) ;
        ImageIcon leftButtonIconSmaller = new ImageIcon( newImg );

         JLabel background= new JLabel("", leftButtonIconSmaller,JLabel.CENTER);
         background.setBounds(0,0,100,10);
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

     public static void setPlayerBoard (){
         JFrame.setDefaultLookAndFeelDecorated(true);


         statusLabel= new JLabel();
         statusLabel.setSize(1500,1100);

         //mainFrame.setLayout(new FlowLayout());
         //mainFrame.setSize(1500,900);

         //button with image

         //load and resize image
         ImageIcon leftButtonIcon = new ImageIcon("./src/main/resources/img/pb/PbBlue.png");
         /* Image img = leftButtonIcon.getImage() ;
         Image newImg = img .getScaledInstance( 20, 20,  java.awt.Image.SCALE_SMOOTH ) ;
         ImageIcon leftButtonIconSmaller = new ImageIcon( newImg );*/

         JLabel background= new JLabel("", leftButtonIcon,JLabel.CENTER);
         background.setBounds(0,0,100,50);
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
     }

    public static JLabel getPb() {
         setPlayerBoard();
         return statusLabel;
    }
}
