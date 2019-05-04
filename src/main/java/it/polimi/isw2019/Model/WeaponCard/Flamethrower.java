package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.Exception.ErrorEffect;
import it.polimi.isw2019.Model.Exception.NoEffectException;
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
    }

    @Override
    public void firstEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffect {

        /*AGGIUNGERE CONTROLLO SE PLAYER VISIBILE*/

        if ((firstDefender != null) && (secondDefender != null)){ //se ha indicato 2 giocatori da attaccare

            if (oneDistanceX(attacker, firstDefender)){ //Se la distanza di 1 è sull'asse delle X allora fai un danno al primo player

                firstDefender.sufferDamage(attacker.getColor(), 1, 0);

                if (oneDistanceX(firstDefender, secondDefender)){ //Se la distanza di 1 è sull'asse delle X allora fai un danno al secondo player altrimenti chiama l'eccezione

                    secondDefender.sufferDamage(attacker.getColor(), 1, 0);

                }

                else {

                    throw new ErrorEffect();
                }
            }

            else if (oneDistanceY(attacker, firstDefender)){ //Se la distanza di 1 è sull'asse delle Y allora fai un danno al primo player

                firstDefender.sufferDamage(attacker.getColor(), 1, 0);

                if (oneDistanceY(firstDefender, secondDefender)){  //Se la distanza di 1 è sull'asse delle Y allora fai un danno al secondo player altrimenti chiama l'eccezione

                    secondDefender.sufferDamage(attacker.getColor(), 1, 0);

                }

                else { //eccezione  perché il secondo giocatore non è sull'asse

                    throw new ErrorEffect();

                }

            }

            else { //eccezione  perché il primo giocatore non è sull'asse

                throw new ErrorEffect();

            }

        }

        else if ((firstDefender != null) && (secondDefender == null)) { //se ha indicato un solo player

            if (oneDistanceX(attacker, firstDefender)) { //Se la distanza di 1 è sull'asse delle X allora fai un danno al primo player

                firstDefender.sufferDamage(attacker.getColor(), 1, 0);

            }
            else if (oneDistanceY(attacker, firstDefender)) { //Se la distanza di 1 è sull'asse delle Y allora fai un danno al primo player

                firstDefender.sufferDamage(attacker.getColor(), 1, 0);

            }
            else { //eccezione  perché il giocatore non è sull'asse

                throw new ErrorEffect();

            }
        }
        else { //non ha segnato nessun player
            throw new ErrorEffect();
        }

    }

    @Override
    public void secondEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffect {

        /*AGGIUNGERE CONTROLLO SE LA STANZA È ADIACENTE*/
/*
        ArrayList<Player> playerList1 = firstAttackSquare.getPlayers();
        ArrayList<Player> playerList2 = secondAttackSquare.getPlayers();

        try{

            if ((!firstAttackSquare.findPlayer(attacker)) && (!secondAttackSquare.findPlayer(attacker)){ //verifica che l'attacker sia in una stanza diversa da quelle selezionate

                try{

                    for (Player aPlayerList1 : playerList1) { //lo fa se la stanza non è vuota

                        aPlayerList1.sufferDamage(attacker.getColor(), 2, 0);

                    }

                    for (Player aPlayerList2 : playerList2) { //lo fa se la stanza non è vuota

                        aPlayerList2.sufferDamage(attacker.getColor(), 1, 0);

                    }

                } catch (ErrorEffect){ //se la stanza è vuota allora errore

                    throw new ErrorEffect();

                }

            }

        } catch (ErrorEffect){ //se una delle stanze è la stessa dell'attacker allora errore

            throw new ErrorEffect();

        }*/
    }

    @Override
    public void thirdEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException {

        /*NON C'È L'EFFETTO */

        throw new NoEffectException();

    }


}
