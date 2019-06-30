package it.polimi.isw2019.view.ViewGui;

import javax.swing.*;

public class RunAndGrabBotton {

    RunAndGrabBotton (){

    }

    public JPanel setRunButton(){

        JPanel panel = new JPanel();

        JButton pos00 = new JButton("0 0");
        JButton pos01 = new JButton("0 1");
        JButton pos02 = new JButton("0 2");
        JButton pos03 = new JButton("0 3");
        JButton pos10 = new JButton("1 0");
        JButton pos11 = new JButton("1 1");
        JButton pos12 = new JButton("1 2");
        JButton pos13 = new JButton("1 3");
        JButton pos20 = new JButton("2 0");
        JButton pos21 = new JButton("2 1");
        JButton pos22 = new JButton("2 2");
        JButton pos23 = new JButton("2 3");

        JButton endMove = new JButton("endMove");

        pos00.setActionCommand("00");
        pos00.addActionListener(new ViewGui.CustomActionListener());

        pos01.setActionCommand("01");
        pos01.addActionListener(new ViewGui.CustomActionListener());

        pos02.setActionCommand("02");
        pos02.addActionListener(new ViewGui.CustomActionListener());

        pos03.setActionCommand("03");
        pos03.addActionListener(new ViewGui.CustomActionListener());

        pos10.setActionCommand("10");
        pos10.addActionListener(new ViewGui.CustomActionListener());

        pos11.setActionCommand("11");
        pos11.addActionListener(new ViewGui.CustomActionListener());

        pos12.setActionCommand("12");
        pos12.addActionListener(new ViewGui.CustomActionListener());

        pos13.setActionCommand("13");
        pos13.addActionListener(new ViewGui.CustomActionListener());

        pos20.setActionCommand("20");
        pos20.addActionListener(new ViewGui.CustomActionListener());

        pos21.setActionCommand("21");
        pos21.addActionListener(new ViewGui.CustomActionListener());

        pos22.setActionCommand("22");
        pos22.addActionListener(new ViewGui.CustomActionListener());

        pos23.setActionCommand("23");
        pos23.addActionListener(new ViewGui.CustomActionListener());

        endMove.setActionCommand("endMove");
        endMove.addActionListener(new ViewGui.CustomActionListener());


        panel.add(pos00);
        panel.add(pos01);
        panel.add(pos02);
        panel.add(pos03);

        panel.add(pos10);
        panel.add(pos11);
        panel.add(pos12);
        panel.add(pos13);

        panel.add(pos20);
        panel.add(pos21);
        panel.add(pos22);
        panel.add(pos23);

        panel.add(endMove);

        return panel;
    }

    public JPanel setGrab (){
        JPanel panel = new JPanel();

        JButton pos00 = new JButton("0 0");
        JButton pos01 = new JButton("0 1");
        JButton pos02 = new JButton("0 2");
        JButton pos03 = new JButton("0 3");
        JButton pos10 = new JButton("1 0");
        JButton pos11 = new JButton("1 1");
        JButton pos12 = new JButton("1 2");
        JButton pos13 = new JButton("1 3");
        JButton pos20 = new JButton("2 0");
        JButton pos21 = new JButton("2 1");
        JButton pos22 = new JButton("2 2");
        JButton pos23 = new JButton("2 3");

        JButton grab = new JButton("endMove");

        pos00.setActionCommand("00");
        pos00.addActionListener(new ViewGui.CustomActionListener());

        pos01.setActionCommand("01");
        pos01.addActionListener(new ViewGui.CustomActionListener());

        pos02.setActionCommand("02");
        pos02.addActionListener(new ViewGui.CustomActionListener());

        pos03.setActionCommand("03");
        pos03.addActionListener(new ViewGui.CustomActionListener());

        pos10.setActionCommand("10");
        pos10.addActionListener(new ViewGui.CustomActionListener());

        pos11.setActionCommand("11");
        pos11.addActionListener(new ViewGui.CustomActionListener());

        pos12.setActionCommand("12");
        pos12.addActionListener(new ViewGui.CustomActionListener());

        pos13.setActionCommand("13");
        pos13.addActionListener(new ViewGui.CustomActionListener());

        pos20.setActionCommand("20");
        pos20.addActionListener(new ViewGui.CustomActionListener());

        pos21.setActionCommand("21");
        pos21.addActionListener(new ViewGui.CustomActionListener());

        pos22.setActionCommand("22");
        pos22.addActionListener(new ViewGui.CustomActionListener());

        pos23.setActionCommand("23");
        pos23.addActionListener(new ViewGui.CustomActionListener());

        grab.setActionCommand("grabButton");
        grab.addActionListener(new ViewGui.CustomActionListener());


        panel.add(pos00);
        panel.add(pos01);
        panel.add(pos02);
        panel.add(pos03);

        panel.add(pos10);
        panel.add(pos11);
        panel.add(pos12);
        panel.add(pos13);

        panel.add(pos20);
        panel.add(pos21);
        panel.add(pos22);
        panel.add(pos23);

        panel.add(grab);

        return panel;

    }

}
