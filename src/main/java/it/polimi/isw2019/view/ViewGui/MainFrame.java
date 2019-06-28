package it.polimi.isw2019.view.ViewGui;

import javax.swing.*;

public class MainFrame {

    JPanel panelAction;
    JPanel panelShoot;
    JPanel panelRun;
    JPanel panelDisabled;
    JFrame frame = new JFrame("Title");

    public MainFrame() {
        setPanelDisabled();


        JFrame.setDefaultLookAndFeelDecorated(true);
        //set title


        //set behaviour on close
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Actions action = new Actions();
        panelAction = action.setPanelActions();
        //add panel to the frame
        frame.add(panelAction);

        //Sizes the frame so that all its contents are at or above their preferred sizes.
        frame.pack();

        // Set the window to be visible as the default to be false
        frame.setVisible(true);

    }

    public void setPanelDisabled() {
        panelDisabled= new JPanel();
        ImageIcon imgDisabled = new ImageIcon("./src/main/resources/img/panelDisabled.png");
       /* Image img = leftButtonIcon.getImage() ;
        Image newImg = img .getScaledInstance( 20, 20,  java.awt.Image.SCALE_SMOOTH ) ;
        ImageIcon leftButtonIconSmaller = new ImageIcon( newImg );*/

        JLabel label = new JLabel("", imgDisabled,JLabel.CENTER);

        panelDisabled.add(label);
    }

    public void setPanelAction(int numPanel){
        JPanel actualPanel;
        switch (numPanel){
            case 0:
                actualPanel=panelRun;
                break;
            default:
                actualPanel=panelDisabled;
        }

        frame.invalidate();
        frame.remove(actualPanel);
        frame.add(panelAction);
        frame.revalidate();
        frame.setVisible(true);
        frame.repaint();
    }

    public void setPanelShoot(){
        WeaponCardBotton weaponCardBotton = new WeaponCardBotton();
        panelShoot = weaponCardBotton.setPannelWeaponCards();
    }

    public void changeShoot (){

        frame.invalidate();
        frame.remove(panelAction);
        setPanelShoot();
        frame.add(panelShoot);
        frame.revalidate();
        frame.setVisible(true);
        frame.repaint();
    }

    public void setPanelRun (){
        RunBotton runBotton = new RunBotton();
        panelRun=runBotton.setRunButton();
    }

    public void changeRun (){

        frame.invalidate();
        frame.remove(panelAction);
        setPanelRun();
        frame.add(panelRun);
        frame.revalidate();
        frame.setVisible(true);
        frame.repaint();
    }

    public void changePanelDisabled (){
        frame.invalidate();
        frame.remove(panelRun);
        frame.add(panelDisabled);
        frame.revalidate();
        frame.setVisible(true);
        frame.repaint();
    }
}
