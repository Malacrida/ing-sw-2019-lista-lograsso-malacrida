package it.polimi.isw2019.controller;

import it.polimi.isw2019.message.playermove.RunMove;
import it.polimi.isw2019.model.Model;
import it.polimi.isw2019.view.MainView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MainControllerTest {
    MainView view;
    Model model;
    MainController controller;
    @Before
    public void setUp() throws Exception {
        view = new MainView();
        view.setNicknamePlayer("Alba");
        model = new Model();
        controller = new MainController();
        view.registerObserver(controller);
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void checkPayment() {
    }

    @Test
    public void translateInputIntoCubes() {
    }

    @Test
    public void update() {
    }

    @Test
    public void visitControllerSetUpPlayer() {
    }

    @Test
    public void visitControllerRegisterPlayer() {
    }

    @Test
    public void visitColorChoosen() {
    }

    @Test
    public void powerUpChoice() {
    }

    @Test
    public void visitControllerActionChoosen() {
    }

    @Test
    public void visitWeaponCardChoice() {
    }

    @Test
    public void useWeaponCard() {
    }

    @Test
    public void visitReload() {
    }

    @Test
    public void visitControllerRun() {
       // RunMove move = new RunMove(view.getNicknamePlayer(),)
    }

    @Test
    public void visitControllerGrab() {
    }

    @Test
    public void visitControllerChooseAction() {
    }

    @Test
    public void usePowerUpCard() {
    }
}