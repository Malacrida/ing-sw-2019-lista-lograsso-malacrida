package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.ColorCube;
import it.polimi.isw2019.model.exception.ErrorEffectException;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.NoEffectException;
import it.polimi.isw2019.model.GameBoard;
import it.polimi.isw2019.model.Player;

import java.util.ArrayList;

public class MachineGun extends AbstractWeaponCard {

    public MachineGun() {
        super(2, "Machine Gun", ColorCube.BLUE, 3, 0, 3);
        this.infoEffect[0] = "FIRST EFFECT: Choose 1 or 2 targets you can see and deal 1 damage to each.\\n";
        this.infoEffect[1] = "SECOND EFFECT: Deal 1 additional damage to one of those targets. You have to pay a YELLOW cube.";
        this.infoEffect[2] = "THIRD EFFECT: Deal 1 additional damage to the other of those targets and/or deal 1 damage to a different target you can see. You have to pay a BLUE cube.\n";
        this.infoEffect[3] = "NOTES: If you deal both additional points of damage, they must be dealt ot 2 different targhets. If you see only 2 targets, you deal 2 to each if you use both optional effects. If you use the basic effect on only 1 target, you can still use the turret tripod to give it 1 additional damage.\n";
        this.rechargeCube = new ColorCube[2];
        this.rechargeCube[0] = ColorCube.BLUE;
        this.rechargeCube[1] = ColorCube.RED;
    }



    private void ifIsVisibleOneDamage(GameBoard gameBoard, Player attacker, Player defender) throws ErrorEffectException {

        ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

        if (visiblePlayers.contains(defender)){ //se non è vuoto e se defenders.get(0) è visibile
            try {
                defender.sufferDamageOrMark(attacker.getColor(), 1, 0);
            } catch ( DamageTrackException e) {
                e.getMessage();
            }
        }
        else {
            throw new ErrorEffectException();
        }
    }

    /**
     *
     * @param gameBoard is the Gameboard where players play
     * @param attacker is the player who use Weapon card
     * @param defenders are players attacked
     * @param coordinates some coordinates used to move players or to indicate squares to attack players
     * @throws ErrorEffectException there is a problem during effect
     */

    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {

        if(defenders.get(0) != null){
            ifIsVisibleOneDamage(gameBoard, attacker, defenders.get(0));
            if(defenders.get(1) != null){ /* Se seleziona il secondo giocatore da attaccare */
                ifIsVisibleOneDamage(gameBoard, attacker, defenders.get(1));
            }
        } else {
            throw new ErrorEffectException();
        }
        this.firstIsValid = true;
    }

    /**
     *
     * @param gameBoard is the Gameboard where players play
     * @param attacker is the player who use Weapon card
     * @param defenders are players attacked
     * @param coordinates some coordinates used to move players or to indicate squares to attack players
     * @throws ErrorEffectException there is a problem during effect
     */

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {

        /*PAGO UN GIALLO
        * Se il primo effetto è valido allora aggiunge un danno al primo giocatore a cui ha sparato
        */

        if (!oneDamageIfFirstIsValid(attacker, defenders.get(0), firstIsValid)){
            throw new ErrorEffectException();
        }
    }


    /**
     *
     * @param gameBoard is the Gameboard where players play
     * @param attacker is the player who use Weapon card
     * @param defenders are players attacked
     * @param coordinates some coordinates used to move players or to indicate squares to attack players
     * @throws ErrorEffectException there is a problem during effect
     */
    /*DA SISTEMARE QUESTO EFFETTO*/
    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws NoEffectException, ErrorEffectException, DamageTrackException {

        /*PAGO UN BLU*/

        if (defenders.get(1) != null){
            if (!oneDamageIfFirstIsValid(attacker, defenders.get(0), firstIsValid)){
                throw new ErrorEffectException();
            }
            if(defenders.get(2) != null){
                try {
                    defenders.get(2).sufferDamageOrMark(attacker.getColor(), 1, 0);
                } catch (DamageTrackException e) {
                    e.getMessage();
                }
            }
        } else{
            throw new ErrorEffectException();
        }
    }
}
