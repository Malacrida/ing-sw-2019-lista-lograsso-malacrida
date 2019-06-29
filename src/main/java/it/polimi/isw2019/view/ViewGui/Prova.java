package it.polimi.isw2019.view.ViewGui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Prova extends JFrame{


    public static void main(String[] args)
    {
        FirstSceen firstSceen = new FirstSceen();
        //Scermata scermata = new Scermata();

/*
        Map1 map1 = new Map1();
        PlayerBoardBlue playerBoardBlue = new PlayerBoardBlue();
        //WeaponCardBotton weaponCardBotton = new WeaponCardBotton();*/

        setBottoni();
    }


    public static void setMap (){


        JFrame frame= new JFrame("esempio squeres" );


        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);




        JPanel panel = new JPanel();


        frame.add(panel);
        //frame.add(squares00);

        frame.pack();
        //playerBlue.setVisible(true);
        frame.setVisible(true);

    }

    public static void setBottoni (){

        JFrame frame = new JFrame("Absolute Layout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setOpaque(true);
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(null);

        JLabel label = new JLabel("",SquaresGui.setRed00(),JLabel.CENTER);
        label.setSize(300, 30);
        label.setLocation(5, 105);

        JButton button = new JButton("USELESS");
        button.setSize(100, 30);
        button.setLocation(95, 45);

        contentPane.add(label);
        contentPane.add(button);

        frame.setContentPane(contentPane);
        frame.setSize(310, 125);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }



}
