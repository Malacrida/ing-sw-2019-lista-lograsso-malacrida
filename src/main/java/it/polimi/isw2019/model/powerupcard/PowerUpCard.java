package it.polimi.isw2019.model.powerupcard;

import it.polimi.isw2019.model.*;
import it.polimi.isw2019.model.exception.DamageTrackException;

public class PowerUpCard implements PowerUpCardInterface{

    /* Attributes */

    private int id;

    private String name;

    private String infoEffect;

    private ColorCube colorCard;

    private ColorRoom colorRoom;

    private String color;

    private StateCard checkState = StateCard.DECK;

    private String[][] tmp;

    private String powerUpDescription;

    private boolean canBeUsed;

    private boolean terminatorTag;

    private int usageCard;

    private int numMovement;


    /* Costruttore */
    public PowerUpCard (int id, String name, String color, String infoEffect){

        this.id = id;
        this.name = name;
        if(name.equals("Newton") || name.equals("Teleporter")){
            canBeUsed = true;
        }
        else{
            canBeUsed = false;
        }

        if(name.equals("Newton") || name.equals("Tagback Grenade")){
            terminatorTag = true;
        }
        else{
            terminatorTag = false;
        }
        this.color = color;
        setColor(color);
        this.infoEffect = infoEffect;

        if(name.equals("Newton")){
            usageCard = 2;
        }
        else if (name.equals("Teleporter")){
            usageCard = 1;
        }
        else if (name.equals("Tagback Grenade")){
            usageCard = 3;
        }
        else{
            usageCard = 0;
        }

        setPowerUpDescription();


    }

    public int getUsageCard() {
        return usageCard;
    }

    public int getNumMovement() {
        return numMovement;
    }

    public boolean isTerminatorTag() {
        return terminatorTag;
    }

    public void setTerminatorTag(boolean terminatorTag) {
        this.terminatorTag = terminatorTag;
    }
    /* Methods */

    /**
     * getter of power up card's identifier
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * getter of power up card's name
     * @return name
     */

    public String getName() {
        return name;
    }

    /**
     * getter of power up card's color
     * @return ColorCube color
     */
    public ColorCube getColorCard(){
        return colorCard;
    }

    /**
     * getter of power up card's color (string from db)
     * @return color (String)
     */
    @Override
    public String getColor() {
        return color;
    }

    /**
     * getter of power up card's effect
     * @return info effect
     */
    public String getInfoEffect(){
        return infoEffect;
    }

    /**
     * getter of power up card's state
     * @return state
     */
    public StateCard getCheckState() {
        return checkState;
    }

    /**
     * implementation of power up card's effect
     * @param gameBoard gameboard
     * @param attacker player who use power up
     * @param defender player who suffer effect of power up
     * @param x first coordinate
     * @param y second coordinate
     */
    @Override
    public void effect(GameBoard gameBoard,Player attacker, Player defender, int x, int y) throws DamageTrackException {
        switch (name) {
            case "Targeting Scope":

                try {
                    defender.sufferDamageOrMark(attacker.getColor(),1,0);
                } catch (DamageTrackException e) {
                    throw new DamageTrackException();

                }

                break;

            case "Newton":

                if(gameBoard.isSquareAvailableOnArena(attacker, x, y)){
                    gameBoard.getGameArena().teleporterMove(attacker, x, y);
                }

                break;

            case "Tagback Grenade":
                try {
                    defender.sufferDamageOrMark(attacker.getColor(), 0, 1);
                } catch (DamageTrackException  e) {
                    throw new DamageTrackException();
                }
                break;

            case "Teleporter":
                if(gameBoard.isSquareAvailableOnArena(attacker, x, y)){
                    gameBoard.changePositionPlayer(attacker, x, y);
                }
                break;

            default:

        }
    }

    /* setColor assign the color from enumeration ColorCube to card read String color from db.json*/

    /**
     * assign the color from enumeration ColorCube to card read String color from db.json
     * @param color color card of power up
     */

    public void setColor(String color){

       if(color.compareTo("YELLOW")==0) {
           colorCard = ColorCube.YELLOW;
           colorRoom = ColorRoom.YELLOW;
       } else if (color.compareTo("RED")==0) {
           colorCard = ColorCube.RED;
           colorRoom = ColorRoom.RED;
       } else if ( color.compareTo("BLUE")==0) {
           colorCard = ColorCube.BLUE;
           colorRoom = ColorRoom.BLUE;
       }
    }

    /**
     * boolean to check if player can use the power up card
     * @return boolean
     */

    public boolean isCanBeUsed() {
        return canBeUsed;
    }

    /**
     * setter all information of power up cards
     */

    public void setPowerUpDescription(){
        powerUpDescription = name + "\n";
        powerUpDescription += "Id :"+ id + "\n";
        powerUpDescription += "InfoEffect: " +infoEffect + "\n";
        powerUpDescription +="Color Card: "+ colorCard.getColorCubeRepresentation();
    }

    public String getPowerUpCardRepresentation(){
        setPowerUpDescription();
        return powerUpDescription;
    }

    public void changeStateCardRepresentation(StateCard stateCard){
        tmp[4][0] = stateCard.getStateCardRepresentation();
    }

    public void changeStateCard(StateCard stateCard){
      checkState = stateCard;
    }


    public ColorRoom getColorRoom() {
        return colorRoom;
    }
}
