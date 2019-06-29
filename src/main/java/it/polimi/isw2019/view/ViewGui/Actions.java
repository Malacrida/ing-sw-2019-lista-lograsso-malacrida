package it.polimi.isw2019.view.ViewGui;

import javax.swing.*;

public class Actions {


    Actions(){

    }

    public JPanel setPanelActions ()
    {

        JPanel panel = new JPanel();

        JButton run = new JButton("Run");

        JButton grab = new JButton("Grab");

        JButton shoot = new JButton("Shoot");

        JButton reload = new JButton("Reload");

        run.setLocation(5,5);
        grab.setLocation(100, 5);
        shoot.setLocation(5,20);
        reload.setLocation(100,20);

        run.setActionCommand("run");
        run.addActionListener(new SceenRun.CustomActionListener());

        grab.setActionCommand("grab");
        grab.addActionListener(new SceenRun.CustomActionListener());

        shoot.setActionCommand("shoot");
        shoot.addActionListener(new SceenRun.CustomActionListener());

        reload.setActionCommand("reload");
        reload.addActionListener(new SceenRun.CustomActionListener());


        panel.add(run);
        panel.add(grab);
        panel.add(shoot);
        panel.add(reload);

        return panel;
    }




}
