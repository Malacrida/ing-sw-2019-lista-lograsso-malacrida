package it.polimi.isw2019.Server.Model.WeaponCard;

import it.polimi.isw2019.Server.Model.ColorCube;
import it.polimi.isw2019.Server.Model.Exception.ErrorEffectException;
import it.polimi.isw2019.Server.Model.Exception.DamageTrackException;
import it.polimi.isw2019.Server.Model.Exception.NoEffectException;
import it.polimi.isw2019.Server.Model.GameBoard;
import it.polimi.isw2019.Server.Model.Player;

import java.util.ArrayList;

public class Whisper extends AbstractWeaponCard {

    public Whisper() {
        super(5, "Whisper", ColorCube.BLUE, 1);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("EFFECT: Deal 3 damage and 1 mark to 1 target you can see.\n" +
                "Your target must be at least 2 moves away from you.\n");
        this.infoEffect.add("NOTES: Notes: For example, in the 2-by-2 room, you cannot shoot\n" +
                "a target on an adjacent square, but you can shoot a target\n" +
                "on the diagonal. If you are beside a door, you can't shoot\n" +
                "a target on the other side of the door, but you can shoot\n" +
                "a target on a different square of that room.\n");
        this.rechargeCube[0] = 0;
        this.rechargeCube[1] = 1;
        this.rechargeCube[2] = 2;
    }

    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException, ErrorEffectException, DamageTrackException {

        ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker.getX(), attacker.getY(), attacker);

        if((firstDefender != null) && (moreThanOneOrTwoDistance(attacker.getX(), attacker.getY(), firstDefender.getX(), firstDefender.getY(), 2)) && (visiblePlayers.contains(firstDefender))){
            try {
                firstDefender.sufferDamageOrMark(attacker.getColor(), 3, 1);
            } catch (DamageTrackException e) {
                e.printStackTrace();
            }

        } else {
            throw new ErrorEffectException();

        }

    }

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException, ErrorEffectException, DamageTrackException {
        throw new NoEffectException();

    }

    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException, ErrorEffectException, DamageTrackException {
        throw new NoEffectException();

    }
}
