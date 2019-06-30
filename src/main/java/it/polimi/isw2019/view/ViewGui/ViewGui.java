package it.polimi.isw2019.view.ViewGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewGui {

    static MainFrame frame;


    public void setFrame() {

        frame= new MainFrame();

    }


     static class CustomActionListener implements ActionListener {
        int i;
        public void actionPerformed(ActionEvent e)
        {


            if (e.getActionCommand().equals("shoot")){
                frame.changeShoot();
            }
            if (e.getActionCommand().equals("run"))
                i=1;
            frame.changeRun();

            if (e.getActionCommand().equals("endMove"))
                frame.changePanelDisabled();
            if (e.getActionCommand().equals("grab"))
                frame.changeGrab();
            if (e.getActionCommand().equals("00")||e.getActionCommand().equals("01")||e.getActionCommand().equals("02")||
                    e.getActionCommand().equals("03")||e.getActionCommand().equals("10")||e.getActionCommand().equals("01")){
                i++;

                frame.changeRun();
                System.out.println(e.getActionCommand());
            }


            else System.out.println(e.getActionCommand());

        }

    }
}
