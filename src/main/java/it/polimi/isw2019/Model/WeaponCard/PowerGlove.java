package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.Exception.DamageTrackException;
import it.polimi.isw2019.Model.Exception.ErrorEffectException;
import it.polimi.isw2019.Model.Exception.NoEffectException;
import it.polimi.isw2019.Model.GameBoard;
import it.polimi.isw2019.Model.Player;

import java.util.ArrayList;

public class PowerGlove extends AbstractWeaponCard {

    public PowerGlove() {
        super(19, "Power Glove", ColorCube.YELLOW, 1);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC MODE: choose 1 target on any square exactly 1 move away. Move onto that square and give the target 1 damage and 2 marks");
        this.infoEffect.add("IN ROCKET FIST MODE : Choose a square exactly 1 move away. " +
                "Move onto that square you may deal 2 damage to 1 target there if you want, you may move 1 more square in that same direction " +
                "(but only if it is a legal move). You may deal 2 damage to 1 target there, as well");
        this.infoEffect.add("NOTE : In rocket fist mode, you're flying squares in a straight line, punching person per square ");
        this.rechargeCube[0] = 0;
        this.rechargeCube[1] = 1;
        this.rechargeCube[2] = 1;
    }

    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException, ErrorEffectException, DamageTrackException {

        if(gameBoard.isSquareAvailableOnArena(attacker, firstDefender.getX(), firstDefender.getY())){ //se il defender è la stanza accanto
            /* AGGIUNGI UN MOVIMENTO */
            System.out.println("In attesa di ChangePosition");

            try {
                firstDefender.sufferDamageOrMark(attacker.getColor(), 1, 2);
            } catch (DamageTrackException e) {
                e.printStackTrace();
            }


        } else {
            throw new ErrorEffectException();
        }
    }

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException, ErrorEffectException, DamageTrackException {

        if(gameBoard.isSquareAvailableOnArena(attacker, firstDefender.getX(), firstDefender.getY())){ //se il firstDefender è la stanza accanto
            /* AGGIUNGI UN MOVIMENTO */
            System.out.println("In attesa di ChangePosition");

            try {
                firstDefender.sufferDamageOrMark(attacker.getColor(), 2, 0);
            } catch (DamageTrackException e) {
                e.printStackTrace();
            }

            if(gameBoard.isSquareAvailableOnArena(attacker, secondDefender.getX(), secondDefender.getY())){ //se il secondDefender è la stanza accanto
                /* AGGIUNGI UN MOVIMENTO */
                System.out.println("In attesa di ChangePosition");

                try {
                    secondDefender.sufferDamageOrMark(attacker.getColor(), 2,0);
                } catch (DamageTrackException e) {
                    e.printStackTrace();
                }

            } else {
                throw new ErrorEffectException();
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