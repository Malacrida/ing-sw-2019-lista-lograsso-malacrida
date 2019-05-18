package it.polimi.isw2019.Server.Model.WeaponCard;

import it.polimi.isw2019.Server.Model.ColorCube;
import it.polimi.isw2019.Server.Model.Exception.ErrorEffectException;
import it.polimi.isw2019.Server.Model.Exception.DamageTrackException;
import it.polimi.isw2019.Server.Model.Exception.NoEffectException;
import it.polimi.isw2019.Server.Model.GameBoard;
import it.polimi.isw2019.Server.Model.Player;

import java.util.ArrayList;

public class VortexCannon extends AbstractWeaponCard {

    public VortexCannon() {
        super(8, "Vortex Cannon", ColorCube.RED, 1);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT: basic effect: Choose a square you can see, but not your" +
                "square. Call it the vortex. Choose a target on the vortex" +
                "or 1 move away from it. Move it onto the vortex and give it" +
                "2 damage.");
        this.infoEffect.add("WITH BLACK HOLE :Choose up to 2 other targets on the" +
                "vortex or 1 move away from it. Move them onto the vortex" +
                "and give them each 1 damage.");
        this.infoEffect.add("NOTE :The 3 targets must be different, but some might" +
                "start on the same square. It is legal to choose targets on" +
                "your square, on the vortex, or even on squares you can't" +
                "see. They all end up on the vortex. ");
        this.rechargeCube[0] = 1;
        this.rechargeCube[1] = 0;
        this.rechargeCube[2] = 1;
    }

    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException, ErrorEffectException, DamageTrackException {

        ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker.getX(), attacker.getY(), attacker);

        if((firstDefender != null) && (visiblePlayers.contains(firstDefender)) && (!sameSquare(attacker.getX(), attacker.getY(), x1, y1))){
            /*MUOVI DI UNO*/
            try {
                firstDefender.sufferDamageOrMark(attacker.getColor(), 2, 0);
            } catch (DamageTrackException e) {
                e.printStackTrace();
            }

            firstIsValid = true;

        } else {

            throw new ErrorEffectException();
        }


    }

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException, ErrorEffectException, DamageTrackException {

        ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker.getX(), attacker.getY(), attacker);

        if (firstIsValid){
            if((secondDefender != null) && (visiblePlayers.contains(secondDefender))){
                /*MUOVI DI UNO IL SECONDO E IL TERZO GIOCATORE*/
                try {
                    secondDefender.sufferDamageOrMark(attacker.getColor(), 1, 0);
                } catch (DamageTrackException e) {
                    e.printStackTrace();
                }

                if((thirdDefender != null) && visiblePlayers.contains(thirdDefender)){
                    try {
                        thirdDefender.sufferDamageOrMark(attacker.getColor(), 1, 0);
                    } catch (DamageTrackException e) {
                        e.printStackTrace();
                    }
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
