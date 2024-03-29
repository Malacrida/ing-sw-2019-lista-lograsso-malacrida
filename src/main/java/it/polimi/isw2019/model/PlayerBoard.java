package it.polimi.isw2019.model;

import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.NoCubesException;
import it.polimi.isw2019.model.exception.OutOfBoundsException;
import it.polimi.isw2019.model.exception.TooManyCubes;

import java.util.ArrayList;

public class PlayerBoard{

    protected ColorPlayer color;
    private int playerSkulls;
    private int playerBoardID; // no caratterizzazione con il colore
    private boolean usePlayerBoard;
    private boolean frenzy;

    protected ArrayList<ColorPlayer> damageTokens= new ArrayList<>();
    private ArrayList<ColorPlayer> markTokens= new ArrayList<>();

    private ArrayList<ColorCube> redCubes= new ArrayList<>();
    private ArrayList<ColorCube> yellowCubes= new ArrayList<>();
    private ArrayList<ColorCube> blueCubes= new ArrayList<>();

    private String[] skullsRepresentation;
    private String[] markRepresentation;
    private String[] damageRepresentation;
    private String colorRepresentation;
    private String[][] tmp;
    private String playerBoardRepresentation;

    /**
     * method to see how much damage has one player
     * @return
     */

    public int numOfDamages (){
        return damageTokens.size();
    }

    /**
     * if is in frenzy game
     * @return
     */

    public boolean isFrenzy() {
        return frenzy;
    }

    /**
     * set frenzy player board
     * @param frenzy
     */

    public void setFrenzy(boolean frenzy) {
        this.frenzy = frenzy;
    }

    /**
     * inizialize player board
     * @param color
     */

    public PlayerBoard(ColorPlayer color){

        this.color=color;
        this.frenzy=false;
        this.playerSkulls=0;
        redCubes.add(ColorCube.RED);
        yellowCubes.add(ColorCube.YELLOW);
        blueCubes.add(ColorCube.BLUE);
        setPlayerBoardRepresentation();

    }

    /**
     * getter of player board's color
     * @return color
     */

    public ColorPlayer getColor() {
        return color;
    }

    /**
     * number of red cubes on player board
     * @return number of red cubes
     */

    public int numOfRedCubes (){
        return redCubes.size();
    }
    /**
     * number of blue cubes on player board
     * @return number of blue cubes
     */

    public int numOfBlueCubes (){
        return blueCubes.size();
    }
    /**
     * number of yellow cubes on player board
     * @return number of yellow cubes
     */

    public int numOfYellowCubes (){
        return yellowCubes.size();
    }


    /**
     * add red cube in player board
     * @throws OutOfBoundsException
     */

    public void addRedCubes () throws OutOfBoundsException {
        if (redCubes.size()<3){
            redCubes.add(ColorCube.RED);
        }
        else throw new OutOfBoundsException("Cannot add red cubes");
    }

    /**
     * add yellow cube in player board
     * @throws OutOfBoundsException
     */

    public void addYellowCubes () throws OutOfBoundsException {
        if (yellowCubes.size()<3){
            yellowCubes.add(ColorCube.YELLOW);

        }
        else throw new OutOfBoundsException("Cannot add yellow cubes");
    }

    /**
     * add blue cube in player board
     * @throws OutOfBoundsException
     */

    public void addBlueCubes () throws OutOfBoundsException {
        if (blueCubes.size()<3){
                blueCubes.add(ColorCube.BLUE);
        }
        else throw new OutOfBoundsException("Cannot add blue cubes");
    }

    /**
     * remove red cubes
     * @param num of cubes to be removed
     * @throws OutOfBoundsException exception
     */
    public void removeRedCubes (int num) throws OutOfBoundsException {
        if (redCubes.size()-num>=0){
            if (redCubes.size()-num>=0){
                if(num == 1)
                    redCubes.remove(0);
                else if(num == 2){
                    redCubes.remove(0);
                    redCubes.remove(0);
                }
                else
                    redCubes.clear();
            }
        }
        else throw new OutOfBoundsException("Non hai abbastanza cubi rossi");
    }

    /**
     * remove yellow cubes
     * @param num of cubes to be removed
     * @throws OutOfBoundsException
     */
    public void removeYellowCubes (int num) throws OutOfBoundsException {
        if (yellowCubes.size()-num>=0){
            if (yellowCubes.size()-num>=0){
                if(num == 1)
                    yellowCubes.remove(0);
                else if(num == 2){
                    yellowCubes.remove(0);
                    yellowCubes.remove(0);
                }
                else
                    yellowCubes.clear();
            }
        }
        else throw new OutOfBoundsException("Non hai abbastanza cubi gialli");
    }
    /**
     * remove yellow cubes
     * @param num of cubes to be removed
     * @throws OutOfBoundsException
     */
    public void removeBlueCubes (int num) throws OutOfBoundsException {
        if (blueCubes.size()-num>=0){
            if(num == 1)
                blueCubes.remove(0);
            else if(num == 2){
                blueCubes.remove(0);
                blueCubes.remove(0);
            }
            else
                blueCubes.clear();
        }
        else throw new OutOfBoundsException("Non hai abbastanza cubi blu!");
    }

    /**
     * remove cube from player board
     * @param colorCube
     */
    public void removeCube(ColorCube colorCube) throws NoCubesException {

        if((colorCube.equals(ColorCube.BLUE))) {
            try {
                this.removeBlueCubes(1);
            } catch (OutOfBoundsException e) {
                throw  new NoCubesException("Blue cubes ended");
            }
        }

        else if (colorCube.equals(ColorCube.RED)) {
            try {
                this.removeRedCubes(1);
            } catch (OutOfBoundsException e) {
                throw new NoCubesException("Red cubes ended");
            }
        }

        else if (colorCube.equals(ColorCube.YELLOW)) {
            try {
                this.removeYellowCubes(1);
            } catch (OutOfBoundsException e) {
                throw new NoCubesException("Yellow cubes ended");
            }
        }
    }

    /**
     * add cubes on player board5
     * @param colorCube
     * @throws TooManyCubes
     */

    public void addCube(ColorCube colorCube) throws TooManyCubes {

        if((colorCube.equals(ColorCube.BLUE))) {
            try {
                this.addBlueCubes();
            } catch (OutOfBoundsException e) {
                throw  new TooManyCubes("Blue cubes full");
            }
        }

        else if (colorCube.equals(ColorCube.RED)) {
            try {
                this.addRedCubes();
            } catch (OutOfBoundsException e) {
                throw  new TooManyCubes("Red cubes full");
            }
        }

        else if (colorCube.equals(ColorCube.YELLOW)) {
            try {
                this.addYellowCubes();
            } catch (OutOfBoundsException e) {
                throw  new TooManyCubes("Yellow cubes full");
            }
        }
    }

    public ArrayList<ColorPlayer> getDamageTokens() {
        return damageTokens;
    }

    public ArrayList<ColorPlayer> getMarkTokens() {
        return markTokens;
    }

    public int getPlayerSkulls() {
        return playerSkulls;
    }

    public void addPlayerSkulls(){
        playerSkulls++;
    }


    /**
     * getter of number of marks of one color
     * @param colorPlayer marks' color
     * @return number of marks
     */

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

    /**
     * getter of number of damages of one color
     * @param colorPlayer damages' color
     * @return number of damage
     */

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

    /**
     * list of type colors of mark on player board
     * @return type colors
     */

    public ArrayList<ColorPlayer> listOfColorMark(){
        ArrayList<ColorPlayer> colorMark = new ArrayList<>();
        for (int i=0; i<markTokens.size(); i++){
            if (!colorMark.contains(damageTokens.get(i))){
                colorMark.add(damageTokens.get(i));
            }
        }
        return colorMark;
    }

    /**
     * list of type colors of damage on player board
     * @return
     */

    public ArrayList<ColorPlayer> listOfColorDamage (){
        ArrayList<ColorPlayer> colorDamage = new ArrayList<>();
        for (int i=0; i<damageTokens.size(); i++){
            if (!colorDamage.contains(damageTokens.get(i))){
                colorDamage.add(damageTokens.get(i));
            }
        }
        return colorDamage;
    }

    /**
     * remove marks of one color
     * @param colorPlayer
     */

    //rimzione dei marchi di un colore posseduti dal player
    public void removeMarkOfOneColor (ColorPlayer colorPlayer){
        markTokens.removeIf(markToken -> markToken==colorPlayer);

    }

    /**
     * add a damage to one player
     * @param colorPlayer Color player of damage
     * @param numberOfDamage number of damnages
     * @throws DamageTrackException
     */

    //Aggiungere i danni al giocatore
    public void takeDamage (ColorPlayer colorPlayer, int numberOfDamage)throws DamageTrackException{

        for (int i=0; i<numberOfDamage; i++){
            damageTokens.add(colorPlayer);
            if (damageTokens.size()>=12) {
                removeMarkOfOneColor(colorPlayer);
                throw new DamageTrackException();
            }
        }
        if(numOfMarkOfOneColor(colorPlayer)>0){
            int n= numOfMarkOfOneColor(colorPlayer);
            for (int i=0; i<n ; i++) {
                damageTokens.add(colorPlayer);
                if (damageTokens.size()>=12) {
                    removeMarkOfOneColor(colorPlayer);
                    throw new DamageTrackException();
                }
            }
            removeMarkOfOneColor(colorPlayer);
        }

        if (damageTokens.size() >= 11) {
            throw new DamageTrackException();
        }


    }
    public boolean handlePaymentAnyCubes(){
        if(redCubes.size()>0) {
            try {
                removeRedCubes(1);
                return true;
            } catch (OutOfBoundsException e) {
                //e.printStackTrace();
            }
        }
        else if(blueCubes.size() > 0)
            try {
                removeBlueCubes(1);
                return true;

        } catch (OutOfBoundsException e) {
               // e.printStackTrace();
            }
        else if(yellowCubes.size() > 0){
            try {
                removeYellowCubes(1);
                return true;
            } catch (OutOfBoundsException e) {

            }
        }
      return false;
    }
    /**
     * add marks of one color
     * @param colorPlayer color of marks
     * @param numberOfMark number of marks
     */

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

    /**
     * reset player board after death and add a skull
     */

    public void resetAfterDeath (){
        playerSkulls++;
        damageTokens.clear();
    }

    /**
     * save first damage to add one score
     * @return
     */

    public ColorPlayer firstBlood(){
        return damageTokens.get(0);
    }

    /**
     *
     * @return
     */

    public ColorPlayer killShot (){
        if (damageTokens.size()>=11)
            return damageTokens.get(10);
        else return null;
    }


    public String getPlayerBoardRepresentation() {
        return playerBoardRepresentation;
    }

    public void setPlayerBoardRepresentation() {
      playerBoardRepresentation = "PlayerBoard \n";
      playerBoardRepresentation += "Color : " + color.getColorPlayerRepresentation() + "\n";
      for(ColorPlayer player: listOfColorDamage()){
          playerBoardRepresentation +=" Damage of color " + player.getColorPlayerRepresentation() +" "+ numOfDamagesOfOneColor(player) + " \n";
      }
      for(ColorPlayer player : listOfColorMark()){
          playerBoardRepresentation +="  Mark of color " + player.getColorPlayerRepresentation() +" "+ numOfMarkOfOneColor(player) + " \n";
      }
      playerBoardRepresentation += "Num Skull : " + playerSkulls + "\n";

    }

    public String toString(){
        setPlayerBoardRepresentation();
        return getPlayerBoardRepresentation();
    }

}
