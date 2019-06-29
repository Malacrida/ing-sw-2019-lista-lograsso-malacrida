package it.polimi.isw2019.view.ViewGui;

import javax.swing.*;
import java.awt.*;

public class WeaponCardBotton {

    private static JFrame mainFrame;

    JButton lockRifle = new JButton();
    JButton electroscythe = new JButton();
    JButton machineGun = new JButton();
    JButton tractorBeam = new JButton();
    JButton thor = new JButton();
    JButton plasmaGun = new JButton();
    JButton whisper = new JButton();
    JButton vortexCannon = new JButton();
    JButton furnace = new JButton();
    JButton heatseeker = new JButton();
    JButton hellion = new JButton();
    JButton flamethorwer = new JButton();
    JButton granadeLauncher = new JButton();
    JButton rocketLauncher = new JButton();
    JButton railgun = new JButton();
    static JButton cyberblade = new JButton();
    JButton zx2 = new JButton();
    JButton shotgun = new JButton();
    static JButton powerGlove = new JButton();
    JButton shockwave = new JButton();
    static JButton sledgehammer = new JButton();
    JButton backCard = new JButton();

    WeaponCardBotton(){

    }

    public JButton setSledgehammer() {

        ImageIcon leftButtonIcon = new ImageIcon("./src/main/resources/img/weapons/weapons2.png");
        Image img = leftButtonIcon.getImage() ;
        Image newImg = img .getScaledInstance( 150, 200,  java.awt.Image.SCALE_SMOOTH ) ;
        ImageIcon leftButtonIconSmaller = new ImageIcon( newImg );

        sledgehammer = new JButton("", leftButtonIconSmaller);

        return sledgehammer;
    }

    public JButton setCyberblade() {

        ImageIcon leftButtonIcon = new ImageIcon("./src/main/resources/img/weapons/weapons3.png");
        Image img = leftButtonIcon.getImage() ;
        Image newImg = img .getScaledInstance( 150, 200,  java.awt.Image.SCALE_SMOOTH ) ;
        ImageIcon leftButtonIconSmaller = new ImageIcon( newImg );

        cyberblade = new JButton("", leftButtonIconSmaller);

       return cyberblade;
    }

    public JButton setPowerGlove() {


        ImageIcon leftButtonIcon = new ImageIcon("./src/main/resources/img/weapons/weapons4.png");
        Image img = leftButtonIcon.getImage() ;
        Image newImg = img .getScaledInstance( 150, 200,  java.awt.Image.SCALE_SMOOTH ) ;
        ImageIcon leftButtonIconSmaller = new ImageIcon( newImg );

        powerGlove = new JButton("", leftButtonIconSmaller);
        powerGlove.setHorizontalTextPosition(AbstractButton.LEADING); //place image vs text,  aka LEFT, for left-to-right locales
         return powerGlove;
    }

    public static void main(String[] args)
    {

    }

    public JPanel setPannelWeaponCards (){
        JPanel panel= new JPanel();
        setPowerGlove();
        setCyberblade();
        setSledgehammer();

        panel.add(powerGlove);
        panel.add(cyberblade);
        panel.add(sledgehammer);

        return panel;
    }

}
