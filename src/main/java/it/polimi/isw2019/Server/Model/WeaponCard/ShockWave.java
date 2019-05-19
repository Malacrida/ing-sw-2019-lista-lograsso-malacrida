package it.polimi.isw2019.Server.Model.WeaponCard;

import it.polimi.isw2019.Server.Model.ColorCube;
import it.polimi.isw2019.Server.Model.Exception.ErrorEffectException;
import it.polimi.isw2019.Server.Model.Exception.DamageTrackException;
import it.polimi.isw2019.Server.Model.Exception.NoEffectException;
import it.polimi.isw2019.Server.Model.GameBoard;
import it.polimi.isw2019.Server.Model.Player;

import java.util.ArrayList;

public class ShockWave extends AbstractWeaponCard {

    public ShockWave() {
        super(20, "Shock Wave", ColorCube.YELLOW, 1);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT : choose up to 3 targets on different squares, each exactly 1 move away deal 1 damage to each target.");
        this.infoEffect.add("TSUNAMI MODE : Deal 1 damage to all targets that are exactly 1 move away\n");
        this.rechargeCube[0] = 0;
        this.rechargeCube[1] = 1;
        this.rechargeCube[2] = 0;
    }

    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException, ErrorEffectException, DamageTrackException {



        if(firstDefender != null){

            x1 = firstDefender.getX();
            y1 = firstDefender.getY();
            x2 = secondDefender.getX();
            y2 = secondDefender.getY();

            if (gameBoard.isSquareAvailableOnArena(attacker, x1, y1)) {

                try {
                    firstDefender.sufferDamageOrMark(attacker.getColor(), 1, 0);
                } catch (DamageTrackException e) {
                    e.printStackTrace();
                }
            }

            else if ((gameBoard.isSquareAvailableOnArena(attacker, x2, y2)) && !sameSquare(x1, y1, x2, y2)){

                try {
                    secondDefender.sufferDamageOrMark(attacker.getColor(), 1, 0);
                } catch (DamageTrackException e) {
                    e.printStackTrace();
                }
            }

            else if ((gameBoard.isSquareAvailableOnArena(attacker,thirdDefender.getX(), thirdDefender.getY())) && !sameSquare(x1, y1, thirdDefender.getX(), thirdDefender.getY()) && !sameSquare(x2, y2, thirdDefender.getX(), thirdDefender.getY())){

                try {
                    thirdDefender.sufferDamageOrMark(attacker.getColor(), 1, 0);
                } catch (DamageTrackException e) {
                    e.printStackTrace();
                }

        } else {

            throw new ErrorEffectException();

        }
    }
    }

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException, ErrorEffectException, DamageTrackException {

        if (firstDefender != null) {
            ArrayList<Player> firstSquare= gameBoard.playersInOneSquare(firstDefender.getX(), firstDefender.getY(), null);
            ArrayList<Player> secondSquare = gameBoard.playersInOneSquare(secondDefender.getX(), secondDefender.getY(), null);
            ArrayList<Player> thirdSquare = gameBoard.playersInOneSquare(thirdDefender.getX(), thirdDefender.getY(), null);
            for (Player playerInFirstSqaure : firstSquare) {

                try {
                    playerInFirstSqaure.sufferDamageOrMark(attacker.getColor(), 1, 0);
                } catch (DamageTrackException  e) {
                    e.printStackTrace();
                }

            }

            for (Player playerInSecondSquare : secondSquare) {

                try {
                    playerInSecondSquare.sufferDamageOrMark(attacker.getColor(), 1, 0);
                } catch (DamageTrackException  e) {
                    e.printStackTrace();
                }

            }

            for (Player playerInThirdSquare : thirdSquare) {

                try {
                    playerInThirdSquare.sufferDamageOrMark(attacker.getColor(), 1, 0);
                } catch (DamageTrackException e) {
                    e.printStackTrace();
                }

            }
        } else {
            throw new ErrorEffectException();
        }
    }

    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException, ErrorEffectException, DamageTrackException {

        throw new NoEffectException();

    }


}
