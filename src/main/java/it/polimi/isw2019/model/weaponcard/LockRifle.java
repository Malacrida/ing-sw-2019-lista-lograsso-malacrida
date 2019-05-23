package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.ColorCube;
import it.polimi.isw2019.model.exception.ErrorEffectException;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.NoEffectException;
import it.polimi.isw2019.model.GameBoard;
import it.polimi.isw2019.model.Player;

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
    public void secondEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2, int x3, int y3) throws ErrorEffectException, DamageTrackException {
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
    public void thirdEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException {

        //lancia eccezione perché non esiste questo effetto

        throw new NoEffectException();

    }


}
