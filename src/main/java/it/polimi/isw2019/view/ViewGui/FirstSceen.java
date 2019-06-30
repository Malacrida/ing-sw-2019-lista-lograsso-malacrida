package it.polimi.isw2019.view.ViewGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FirstSceen {

    private JFrame mainFrame;
    private JLabel statusLabel;

    FirstSceen(){
        JFrame.setDefaultLookAndFeelDecorated(true);

        mainFrame = new JFrame("Java SWING Elements Examples");
        statusLabel= new JLabel();
        statusLabel.setSize(350,100);

        //mainFrame.setLayout(new FlowLayout());
        //mainFrame.setSize(1500,900);

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //simple button
        JButton jb1 = new JButton("SOCKET");
        jb1.setActionCommand("premuto socket");
        jb1.addActionListener(new CustomActionListener());
        mainFrame.add(jb1);

        JButton jb2 = new JButton("RMI");
        jb2.setActionCommand("premuto rmi");
        jb2.addActionListener(new CustomActionListener());
        mainFrame.add(jb2);

        //button with image

        //load and resize image
        ImageIcon leftButtonIcon = new ImageIcon("./src/main/resources/img/Background.png");
       /* Image img = leftButtonIcon.getImage() ;
        Image newImg = img .getScaledInstance( 20, 20,  java.awt.Image.SCALE_SMOOTH ) ;
        ImageIcon leftButtonIconSmaller = new ImageIcon( newImg );*/

        JLabel background= new JLabel("", leftButtonIcon,JLabel.CENTER);
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


        /*
        //set orientation ( this is the default and wont change anything with NORD, SOUTH, WEST AND EST.
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        //set the layout
        panel.setLayout(new BorderLayout());


        panel.add(jb1, BorderLayout.WEST);
        panel.add(jb2, BorderLayout.EAST);*/

        GridBagConstraints gbcB1 = new GridBagConstraints();
        gbcB1.gridx=0;
        gbcB1.gridy=4;
        panel.add(jb1,gbcB1);

        GridBagConstraints gbcB2 = new GridBagConstraints();
        gbcB2.gridx=1;
        gbcB2.gridy=4;
        panel.add(jb2,gbcB2);

        mainFrame.add(panel);

        mainFrame.pack();
        mainFrame.setVisible(true);

    }

    class CustomActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            statusLabel.setText("Button Clicked.");
            System.out.println(e.getActionCommand());
        }
    }

    class CustomMouseListener implements MouseListener {
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
    }
}
