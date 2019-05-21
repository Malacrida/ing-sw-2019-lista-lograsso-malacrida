package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.Exception.DamageTrackException;
import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.Exception.ErrorEffectException;
import it.polimi.isw2019.Model.Exception.NoEffectException;
import it.polimi.isw2019.Model.GameBoard;
import it.polimi.isw2019.Model.Player;

import java.util.ArrayList;

public class Flamethrower extends AbstractWeaponCard {


    public Flamethrower() {
        super(12, "Flamethrower", ColorCube.RED, 1);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC MODE: Choose a square 1 move away and possibly a second square\n" +
                "1 more move away in the same direction. On each square, you may\n" +
                "choose 1 target and give it 1 damage.\n");
        this.infoEffect.add("IN BARBECUE MODE: Choose 2 squares as above. Deal 2 damage to\n" +
                "everyone on the first square and 1 damage to everyone on the second\n" +
                "square.\n");
        this.infoEffect.add("NOTES: This weapon cannot damage anyone in your square. However,\n" +
                "it can sometimes damage a target you can't see – the flame won't go\n" +
                "through walls, but it will go through doors. Think of it as a straight-line\n" +
                "blast of flame that can travel 2 squares in a cardinal direction.\n");
        this.rechargeCube[0] = 1;
        this.rechargeCube[1] = 0;
        this.rechargeCube[2] = 0;
    }


    /**
     *
     * @param gameBoard
     * @param attacker
     * @param firstDefender
     * @param secondDefender
     * @param thirdDefender
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @throws ErrorEffectException
     */
    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException, ErrorEffectException, DamageTrackException {

        ArrayList<Player> visilePlayers = gameBoard.playersWhoCanSee(attacker);
        char dir1, dir2;


        if (gameBoard.isSquareAvailableOnArena(attacker, firstDefender.getX(), firstDefender.getY())) {
            dir1 = direction(attacker, firstDefender);

            try{
                firstDefender.sufferDamageOrMark(attacker.getColor(), 1, 0);
            } catch (DamageTrackException e) {
                e.printStackTrace();
            }

            dir2 = direction(attacker, secondDefender);

            if ((visilePlayers.contains(secondDefender)) && (dir1 == dir2)){
                try{
                    firstDefender.sufferDamageOrMark(attacker.getColor(), 1, 0);
                } catch (DamageTrackException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            throw new ErrorEffectException();
        }
    }

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException, ErrorEffectException, DamageTrackException {

        char dir1, dir2;

        if(gameBoard.isSquareAvailableOnArena(attacker, x1,  y1)){
            ArrayList<Player> playersListFirstSquare = gameBoard.playersInOneSquare(x1, y1, null);

            dir1 = direction(attacker, playersListFirstSquare.get(0));

            for (Player player:playersListFirstSquare) {

                try {
                    player.sufferDamageOrMark(attacker.getColor(), 2, 0);
                } catch (DamageTrackException e){
                    e.printStackTrace();
                }
            }

            ArrayList<Player> playersListSecondSquare = gameBoard.playersInOneSquare(x2, y2, null);
            dir2 = direction(attacker, playersListSecondSquare.get(0));

            if ((gameBoard.isSquareAvailableOnArena(playersListFirstSquare.get(0), x2, y2)) && (dir1 == dir2)) {

                for (Player player : playersListSecondSquare) {

                    try {
                        player.sufferDamageOrMark(attacker.getColor(), 2, 0);
                    } catch (DamageTrackException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        else {
            throw new ErrorEffectException();
        }

    }

    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException, ErrorEffectException, DamageTrackException {

        /*NON C'È L'EFFETTO */

        throw new NoEffectException();

    }


}
