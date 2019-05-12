package it.polimi.isw2019.Server.Model.WeaponCard;

import it.polimi.isw2019.Server.Model.ColorCube;
import it.polimi.isw2019.Server.Model.Exception.ErrorEffectException;
import it.polimi.isw2019.Server.Model.Exception.KillShotException;
import it.polimi.isw2019.Server.Model.Exception.NoEffectException;
import it.polimi.isw2019.Server.Model.Exception.OverKillException;
import it.polimi.isw2019.Server.Model.GameBoard;
import it.polimi.isw2019.Server.Model.Player;

import java.util.ArrayList;

public class GrenadeLauncher extends AbstractWeaponCard {

    public GrenadeLauncher() {
        super(13, "Grenade Launcher", ColorCube.RED, 2);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT: Deal 1 damage to 1 target you can see. Then you may move\n" +
                "the target 1 square.\n");
        this.infoEffect.add("WITH EXTRA GRENADE: Deal 1 damage to every player on a square you can\n" +
                "see. You can use this before or after the basic effect's move.\n");
        this.infoEffect.add("NOTES: For example, you can shoot a target, move it onto a square with\n" +
                "other targets, then damage everyone including the first target.\n" +
                "Or you can deal 2 to a main target, 1 to everyone else on that square,\n" +
                "then move the main target. Or you can deal 1 to an isolated target and\n" +
                "1 to everyone on a different square. If you target your own square,\n" +
                "you will not be moved or damaged.\n");
        this.rechargeCube[0] = 1;
        this.rechargeCube[1] = 0;
        this.rechargeCube[2] = 0;
    }

    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException {


        if (firstDefender != null){
            try {
                firstDefender.sufferDamageOrMark(attacker.getColor(), 1, 0);
            } catch (OverKillException e) {
                e.printStackTrace();
            } catch (KillShotException e) {
                e.printStackTrace();
            }

            if (gameBoard.changePositionPlayer(firstDefender, x1, y1, false)){

                    System.out.println("In attesa di changePosition");

                }

        } else {

            throw new ErrorEffectException();

        }

    }

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException {

        /* AGGIUNGERE CONTROLLO SE POSSO VEDERE UN QUADRATO */


        ArrayList<Player> playerList = gameBoard.playersInOneSquare(x1, y1, null);

        ArrayList<Player> playerList2 = gameBoard.playersWhoCanSee(x1, y1, attacker);

        if (playerList == playerList2){

            for (Player aPlayerList : playerList) {

                try {

                    aPlayerList.sufferDamageOrMark(attacker.getColor(), 1, 1);

                }catch (OverKillException | KillShotException e) {
                    e.printStackTrace();
                }

            }
        } else {
            throw new ErrorEffectException();
        }


    }

    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException {

        /* NON ESISTE QUESTO EFFETTO*/
        throw new NoEffectException();
    }

}
