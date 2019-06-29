package it.polimi.isw2019.view.ViewGui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SceenRun {

    static MainFrame frame;

    public static void main(String[] args)
    {

        frame= new MainFrame();

    }


    static class CustomActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            if (e.getActionCommand().equals("shoot")){
                frame.changeShoot();
            }
            if (e.getActionCommand().equals("run"))
                frame.changeRun();

            if (e.getActionCommand().equals("endMove"))
                frame.changePanelDisabled();

            else System.out.println(e.getActionCommand());

        }

    }

}
