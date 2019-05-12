package it.polimi.isw2019.Server.Model.WeaponCard;

import it.polimi.isw2019.Server.Model.ColorCube;
import it.polimi.isw2019.Server.Model.Exception.ErrorEffectException;
import it.polimi.isw2019.Server.Model.Exception.KillShotException;
import it.polimi.isw2019.Server.Model.Exception.NoEffectException;
import it.polimi.isw2019.Server.Model.Exception.OverKillException;
import it.polimi.isw2019.Server.Model.GameBoard;
import it.polimi.isw2019.Server.Model.Player;

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

    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException {

        /*AGGIUNGERE CONTROLLO SE PLAYER VISIBILE*/

        if (secondDefender != null){ //se ha indicato 2 giocatori da attaccare

            if (oneDistanceX(attacker, firstDefender)){ //Se la distanza di 1 è sull'asse delle X allora fai un danno al primo player

                try {

                    firstDefender.sufferDamageOrMark(attacker.getColor(), 1, 0);

                } catch (OverKillException e){
                    e.printStackTrace();
                } catch (KillShotException e) {
                    e.printStackTrace();
                }


                if (oneDistanceX(firstDefender, secondDefender)){ //Se la distanza di 1 è sull'asse delle X allora fai un danno al secondo player altrimenti chiama l'eccezione

                    try {

                        secondDefender.sufferDamageOrMark(attacker.getColor(), 1, 0);

                    } catch (OverKillException e){
                        e.printStackTrace();
                    } catch (KillShotException e) {
                        e.printStackTrace();
                    }

                }

                else {

                    throw new ErrorEffectException();
                }
            }

            else if (oneDistanceY(attacker, firstDefender)){ //Se la distanza di 1 è sull'asse delle Y allora fai un danno al primo player

                try {

                    firstDefender.sufferDamageOrMark(attacker.getColor(), 1, 0);

                } catch (OverKillException e){
                    e.printStackTrace();
                } catch (KillShotException e) {
                    e.printStackTrace();
                }

                if (oneDistanceY(firstDefender, secondDefender)){  //Se la distanza di 1 è sull'asse delle Y allora fai un danno al secondo player altrimenti chiama l'eccezione

                    try {

                        secondDefender.sufferDamageOrMark(attacker.getColor(), 1, 0);

                    } catch (KillShotException e){
                        e.printStackTrace();
                    } catch (OverKillException e) {
                        e.printStackTrace();
                    }

                }

                else { //eccezione  perché il secondo giocatore non è sull'asse

                    throw new ErrorEffectException();

                }

            }

            else { //eccezione  perché il primo giocatore non è sull'asse

                throw new ErrorEffectException();

            }

        }

        else if ((firstDefender != null) && (secondDefender == null)) { //se ha indicato un solo player

            if (oneDistanceX(attacker, firstDefender)) { //Se la distanza di 1 è sull'asse delle X allora fai un danno al primo player

                try {

                    firstDefender.sufferDamageOrMark(attacker.getColor(), 1, 0);

                } catch (OverKillException e){
                    e.printStackTrace();
                } catch (KillShotException e) {
                    e.printStackTrace();
                }

            }
            else if (oneDistanceY(attacker, firstDefender)) { //Se la distanza di 1 è sull'asse delle Y allora fai un danno al primo player

                try {

                    firstDefender.sufferDamageOrMark(attacker.getColor(), 1, 0);

                } catch (OverKillException e){
                    e.printStackTrace();
                } catch (KillShotException e) {
                    e.printStackTrace();
                }


            }
            else { //eccezione  perché il giocatore non è sull'asse

                throw new ErrorEffectException();

            }
        }
        else { //non ha segnato nessun player
            throw new ErrorEffectException();
        }

    }

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException {

        /*AGGIUNGERE CONTROLLO SE LA STANZA È ADIACENTE*/

        ArrayList <Player> playerList = gameBoard.playersInOneSquare(x1, x2, null);

        if (attacker.getX() - x1 == 1){

            for (Player aPlayerList:playerList) {

                try {

                    aPlayerList.sufferDamageOrMark(attacker.getColor(), 2, 1);

                } catch (OverKillException | KillShotException e){
                    e.printStackTrace();
                }


            }

        } else {

            throw new ErrorEffectException();

        }


    }

    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException {

        /*NON C'È L'EFFETTO */

        throw new NoEffectException();

    }


}
