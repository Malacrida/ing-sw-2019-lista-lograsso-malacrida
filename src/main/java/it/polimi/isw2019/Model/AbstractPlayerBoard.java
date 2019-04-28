package it.polimi.isw2019.Model;

import it.polimi.isw2019.Model.Exception.KillShotException;
import it.polimi.isw2019.Model.Exception.OutOfBoundsException;
import it.polimi.isw2019.Model.Exception.OverKillException;

import java.util.ArrayList;

public abstract class AbstractPlayerBoard {

    protected ColorPlayer color;
    private int playerSkulls=0;
    private int playerBoardID; // no caratterizzazione con il colore
    private boolean usePlayerBoard;
    private boolean frenzy;
    protected ArrayList<ColorPlayer> damageTokens= new ArrayList<>();
    private ArrayList<ColorPlayer> markTokens= new ArrayList<>();
    private ArrayList<ColorCube> redCubes= new ArrayList<>();
    private ArrayList<ColorCube> yellowCubes= new ArrayList<>();
    private ArrayList<ColorCube> blueCubes= new ArrayList<>();


    AbstractPlayerBoard(ColorPlayer color, boolean frenzy){
        this.color=color;
       // this.playerBoardID=playerBoardID;
        this.frenzy=frenzy;
        this.playerSkulls=0;
        redCubes.add(ColorCube.RED);
        yellowCubes.add(ColorCube.YELLOW);
        blueCubes.add(ColorCube.BLUE);
    }

    public ColorPlayer getColor() {
        return color;
    }

    public void addRedCubes (int num) throws OutOfBoundsException {
        if (redCubes.size()<3){
            for (int i=0; i<num;i++){
                redCubes.add(ColorCube.RED);
                if (redCubes.size()==3) throw new OutOfBoundsException();
            }
        }
    }

    public void addYellowCubes (int num) throws OutOfBoundsException {
        if (yellowCubes.size()<3){
            for (int i=0; i<num;i++){
                yellowCubes.add(ColorCube.RED);
                if (yellowCubes.size()==3) throw new OutOfBoundsException();
            }
        }
    }

    public void addBlueCubes (int num) throws OutOfBoundsException {
        if (blueCubes.size()<3){
            for (int i=0; i<num;i++){
                blueCubes.add(ColorCube.RED);
                if (blueCubes.size()==3) throw new OutOfBoundsException();
            }
        }
    }

    public int getPlayerSkulls() {
        return playerSkulls;
    }

    public void addPlayerSkulls(){
        playerSkulls++;
    }

    //Numero di danni totali subiti dal giocatore e controllo sulla morte
    public int numOfDamanges () throws KillShotException, OverKillException {
        if (damageTokens.size()==11) throw new KillShotException();
        if (damageTokens.size()>=12) throw new OverKillException(damageTokens.get(11));
        return damageTokens.size();
    }

    //numero di marchi di un tipo di colore posseduti dal player
    public int numOfMarkOfOneColor (ColorPlayer colorPlayer){
        int cont=0;
        for (int i=0; i<markTokens.size(); i++){
            if (markTokens.get(i)==colorPlayer){
                cont++;
            }
        }
        return cont;
    }

    // numero danni di quel colore
    public int numOfDamagesOfOneColor (ColorPlayer colorPlayer){
        int cont=0;
        for (int i=0; i<markTokens.size(); i++){
            if (markTokens.get(i)==colorPlayer){
                cont++;
            }
        }
        return cont;
    }

    public ArrayList<ColorPlayer> listOfColorDamage (){
        ArrayList<ColorPlayer> colorDamage = new ArrayList<>();
        for (int i=0; i<damageTokens.size(); i++){
            if (!colorDamage.contains(damageTokens.get(i))){
                colorDamage.add(damageTokens.get(i));
            }
        }
        return colorDamage;
    }

    //rimzione dei marchi di un colore posseduti dal player
    public void removeMarkOfOneColor (ColorPlayer colorPlayer){
        for (int i=0; i<markTokens.size(); i++){
            if (markTokens.get(i)==colorPlayer){
                markTokens.remove(i);
            }
        }
    }

    //Aggiungere i danni al giocatore
    public void takeDamage (ColorPlayer colorPlayer, int numberOfDamage){
        for (int i=0; i<numberOfDamage; i++){
            damageTokens.add(colorPlayer);
        }
        if (numOfMarkOfOneColor(colorPlayer)>0){
            for (int i=0; i<numOfMarkOfOneColor(colorPlayer); i++){
                damageTokens.add(colorPlayer);
            }
            removeMarkOfOneColor(colorPlayer);
        }
    }

    //Aggiunge marchi ma nel caso supera i tre marchi dello stesso colore non vengono aggiunti
    // ma non si lancia nemmeno un eccezione
    public void addMark (ColorPlayer colorPlayer, int numberOfMark) {
        for (int i=0; i<numberOfMark; i++){
            if (numOfMarkOfOneColor(colorPlayer)<3){
                markTokens.add(colorPlayer);
            }
            else break;
        }
    }

    public void deathPlayer (){
        playerSkulls++;
        damageTokens.clear();
    }

    //crea la mappa con i danni inferti al giocatore e chi li ha inferti
    public abstract void scorePlayer();




}