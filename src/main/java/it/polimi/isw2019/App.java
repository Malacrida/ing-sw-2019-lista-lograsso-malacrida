package it.polimi.isw2019;


import it.polimi.isw2019.controller.MainController;
import it.polimi.isw2019.message.movemessage.StartMessage;
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
        CLIView view3 = new CLIView("davide");
        MainController controller = new MainController();

        StartMessage startMessage = new StartMessage("alba");
        StartMessage startMessage1 = new StartMessage("sara");
        StartMessage startMessage2 = new StartMessage("davide");

        view.registerObserver(controller);
        view2.registerObserver(controller);
        view3.registerObserver(controller);

        view.visitStartView(startMessage);
        view2.visitStartView(startMessage1);
        view3.visitStartView(startMessage2);

        controller.startGame();
    }
}
