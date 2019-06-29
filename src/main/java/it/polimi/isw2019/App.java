package it.polimi.isw2019;


import it.polimi.isw2019.controller.MainController;
import it.polimi.isw2019.view.CLIView;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        CLIView view = new CLIView("alba");
        CLIView view2 = new CLIView("sara");


        MainController controller = new MainController();

        view2.registerObserver(controller);

        view.startView();
        view2.startView();

        controller.startGame();
    }
}
