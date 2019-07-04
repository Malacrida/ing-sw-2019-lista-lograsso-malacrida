package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.ColorCube;
import it.polimi.isw2019.model.GameBoard;
import it.polimi.isw2019.model.Player;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.ErrorEffectException;
import it.polimi.isw2019.model.exception.NoEffectException;

import java.util.ArrayList;

public class Hellion extends AbstractWeaponCard {

    public Hellion() {
        super(11, "Hellion", ColorCube.RED, 1, 2, 1);
        this.infoEffect[0] = "FIRST EFFECT: Deal 1 damage to 1 target you can see at least 1 move away. Then give 1 mark to that target and everyone else on that square.\n";
        this.infoEffect[1] = "SECOND EFFECT: Deal 1 damage to 1 target you can see at least 1 move away. Then give 2 marks to that target and everyone else on that square.\n";
        this.infoEffect[2] = "THIRD EFFECT: This effect doesn't exist;\n";
        this.infoEffect[3] = "NOTES: You can use only one effect\n";
        this.rechargeCube = new ColorCube[2];
        this.rechargeCube[0] = ColorCube.RED;
        this.rechargeCube[1] = ColorCube.YELLOW;
        this.paySecondEffect = new ColorCube[1];
        this.paySecondEffect[0] = ColorCube.RED;
        setWeaponCardDescription();
    }




    private void hellionEffects (GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int marks) throws ErrorEffectException {
        ArrayList<Player> playerList = gameBoard.playersInOneSquare(defenders.get(0).getX(), defenders.get(0).getY(), null);
        ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

        if(visiblePlayers.contains(defenders.get(0))){ // se defenders.get(0) è contenuto nella lista di player visiili dall'attaccante allora procedo
            try {
                defenders.get(0).sufferDamageOrMark(attacker.getColor(), 1, 0);
                controlPlayersDamages(gameBoard, defenders.get(0));
            } catch (DamageTrackException e) {
                e.getMessage();
            }

            for (Player aPlayerList : playerList) {
                try {
                    aPlayerList.sufferDamageOrMark(attacker.getColor(), 0, marks);
                } catch (DamageTrackException e) {
                    e.getMessage();
                }
            }
        }
        else {
            throw new ErrorEffectException();
        }
    }

    /**
     * Deal 1 damage to 1 target you can see at least 1 move away. Then give 1 mark to that target and everyone else on that square.
     * @param gameBoard is the Gameboard where players play
     * @param attacker is the player who use Weapon card
     * @param defenders are players attacked
     * @param coordinates some coordinates used to move players or to indicate squares to attack players
     * @throws ErrorEffectException there is a problem during effect
     */

    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {

        if(!sameSquare(attacker.getX(), attacker.getY(), defenders.get(0).getX(), defenders.get(0).getY())){ //se ha inserito almeno un difensore e si trova in una cella almeno distante 1

            hellionEffects(gameBoard, attacker, defenders, 1);
        }
        else{
            throw new ErrorEffectException();
        }

    }

    /**
     * Deal 1 damage to 1 target you can see at least 1 move away. Then give 2 marks to that target and everyone else on that square.
     * @param gameBoard is the Gameboard where players play
     * @param attacker is the player who use Weapon card
     * @param defenders are players attacked
     * @param coordinates some coordinates used to move players or to indicate squares to attack players
     * @throws ErrorEffectException there is a problem during effect
     */


    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {
        //PAGA UN ROSSO

        if((!defenders.isEmpty()) && (!sameSquare(attacker.getX(), attacker.getY(), defenders.get(0).getX(), defenders.get(0).getY()))) { //se ha inserito almeno un difensore e si trova in una cella almeno distante 1
            hellionEffects(gameBoard, attacker, defenders, 2);
        }

        else {
            throw new ErrorEffectException();
        }
    }

    /**
     * This effect doesn't exist
     * @throws NoEffectException there isn't this effect
     */


    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws NoEffectException {

        throw new NoEffectException();

    }


}
