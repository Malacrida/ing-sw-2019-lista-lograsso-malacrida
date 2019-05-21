package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.Exception.ErrorEffectException;
import it.polimi.isw2019.Model.Exception.DamageTrackException;
import it.polimi.isw2019.Model.Exception.NoEffectException;
import it.polimi.isw2019.Model.GameBoard;
import it.polimi.isw2019.Model.Player;

import java.util.ArrayList;

public class LockRifle extends AbstractWeaponCard {

    public LockRifle() {
        super(1, "Lock Rifle", ColorCube.BLUE, 2);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT: Deal 2 damage and 1 mark to 1 target you can see.\n");
        this.infoEffect.add("WITH DECOND LOCK: Deal 1 mark to a different target you can see. You have to pay a RED cube");
        this.rechargeCube[0] = 0;
        this.rechargeCube[1] = 0;
        this.rechargeCube[2] = 2;
    }

    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException, DamageTrackException {

        ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

        if ((firstDefender != null) && (visiblePlayers.contains(firstDefender))){
            try {
                firstDefender.sufferDamageOrMark(attacker.getColor(), 2, 1);
            } catch ( DamageTrackException e) {
                e.printStackTrace();
            }
            firstIsValid = true;

        } else {
            throw new ErrorEffectException();
        }
    }

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException, ErrorEffectException, DamageTrackException {
        if (firstIsValid){

            ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

            if ((secondDefender != null) && (visiblePlayers.contains(secondDefender))){
                try {
                    secondDefender.sufferDamageOrMark(attacker.getColor(), 0, 1);
                } catch ( DamageTrackException e) {
                    e.printStackTrace();
                }
            } else {

                throw new ErrorEffectException();

            }
        }
        else {
            throw new ErrorEffectException();
        }
    }

    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException, ErrorEffectException, DamageTrackException {

        //lancia eccezione perché non esiste questo effetto

        throw new NoEffectException();

    }


}