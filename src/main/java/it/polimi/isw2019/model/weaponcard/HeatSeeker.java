package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.ColorCube;
import it.polimi.isw2019.model.exception.ErrorEffectException;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.NoEffectException;
import it.polimi.isw2019.model.GameBoard;
import it.polimi.isw2019.model.Player;

import java.util.ArrayList;

public class HeatSeeker extends AbstractWeaponCard {

    public HeatSeeker() {
        super(10, "HeatSeeker", ColorCube.RED, 1);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("EFFECT: Choose 1 target you cannot see and deal 3 damage " +
                "to it.");
        this.infoEffect.add("NOTE : Yes, this can only hit targets you cannot see. ");
        this.rechargeCube[0] = 2;
        this.rechargeCube[1] = 1;
        this.rechargeCube[2] = 0;
    }

    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException, DamageTrackException {

        ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

        if (firstDefender != null) {
            if (visiblePlayers.contains(firstDefender)) {

                throw new ErrorEffectException();
            } else firstDefender.sufferDamageOrMark(attacker.getColor(), 3, 0);

        }
        else{
            throw new ErrorEffectException();
        }
    }

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2, int x3, int y3) throws NoEffectException {
        // NON ESISTE L'EFFETTO
        throw new NoEffectException();
    }

    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException {
        /* NON ESISTE L'EFFETTO */
        throw new NoEffectException();
    }

}
