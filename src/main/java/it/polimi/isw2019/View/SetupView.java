package it.polimi.isw2019.View;
import it.polimi.isw2019.Message.MoveMessage.*;
import it.polimi.isw2019.Message.PlayerMove.SetUpMove;
import it.polimi.isw2019.Utilities.Observable;

import java.util.Scanner;

public class SetupView extends Observable implements VisitorView  {

    @Override
    public void visitSetupView(MoveMessage moveMessage) {
        SetUpMove message ;
        Scanner input = new Scanner(System.in);
        String nickname;
        String phrase;
        String color;
        char gameMood;
        System.out.println("Insert nickname:");
        nickname = input.nextLine();
        System.out.println("Insert phrase:");
        phrase = input.nextLine();
        for(int i = 0; i< ((SetUpMessage)(moveMessage)).getColorAvailable().size(); i++)
                System.out.println("Color available :" + ((SetUpMessage)(moveMessage)).getColorAvailable());

        System.out.println("Insert color:");
        color = input.nextLine();
        System.out.println("Tapping the first upper case letter of the name of the game mode " +
                "to choose one of them : /n 1) N -> Normal ; 2) T -> Terminator ; 3) B ->Boot; ");
        //consume only one char
        gameMood = input.next().charAt(0);
        message = new SetUpMove(nickname,phrase,color, gameMood);

    }

    @Override
    public void visitActionView(MoveMessage moveMessage) {

    }

    @Override
    public void errorMessageView(MoveMessage moveMessage) {

    }

    @Override
    public void visitRun(RunMessage runMessage) {

    }

    @Override
    public void visitRunGrab(RunGrabMessage runGrabMessage) {

    }

    @Override
    public void visitReload(ReloadMessage reloadMessage) {

    }
}
