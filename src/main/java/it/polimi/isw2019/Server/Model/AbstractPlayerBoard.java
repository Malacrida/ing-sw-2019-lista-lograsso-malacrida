package it.polimi.isw2019.Server.Model;

import it.polimi.isw2019.Server.Model.Exception.KillShotException;
import it.polimi.isw2019.Server.Model.Exception.OutOfBoundsException;
import it.polimi.isw2019.Server.Model.Exception.OverKillException;

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

    public int numOfRedCubes (){
        return redCubes.size();
    }

    public int numOfBlueCubes (){
        return blueCubes.size();
    }

    public int numOfYellowCubes (){
        return yellowCubes.size();
    }

    public void addRedCubes () throws OutOfBoundsException {
        if (redCubes.size()<3){
            redCubes.add(ColorCube.RED);
        }
        else throw new OutOfBoundsException("Non puoi aggiungere più cubi rossi");
    }

    public void addYellowCubes () throws OutOfBoundsException {
        if (yellowCubes.size()<3){
            yellowCubes.add(ColorCube.RED);

        }
        else throw new OutOfBoundsException("Non puoi aggiungere più cubi gialli");
    }

    public void addBlueCubes () throws OutOfBoundsException {
        if (blueCubes.size()<3){
                blueCubes.add(ColorCube.RED);
        }
        else throw new OutOfBoundsException("Non puoi aggiungere più cubi blu");
    }

    public void removeRedCubes (int num) throws OutOfBoundsException {
        if (redCubes.size()-num>=0){
            for (int i=redCubes.size(); i>0;i--){
                redCubes.remove(ColorCube.RED);
            }
        }
        else throw new OutOfBoundsException("Non hai abbastanza cubi rossi");
    }

    public void removeYellowCubes (int num) throws OutOfBoundsException {
        if (yellowCubes.size()-num>=0){
            for (int i=yellowCubes.size(); i>0;i--){
                yellowCubes.remove(ColorCube.RED);
            }
        }
        else throw new OutOfBoundsException("Non hai abbastanza cubi gialli");
    }

    public void removeBlueCubes (int num) throws OutOfBoundsException {
        if (blueCubes.size()-num>=0){
            for (int i=blueCubes.size(); i>0;i--){
                blueCubes.remove(ColorCube.RED);
            }
        }
        else throw new OutOfBoundsException("Non hai abbastanza cubi blu!");
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
        for (int i=0; i<damageTokens.size(); i++){
            if (damageTokens.get(i)==colorPlayer){
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
        for (int i=0; i<numOfMarkOfOneColor(colorPlayer); i++) {
            damageTokens.add(colorPlayer);
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

    public ColorPlayer firstBlood(){
        return damageTokens.get(0);
    }



}
