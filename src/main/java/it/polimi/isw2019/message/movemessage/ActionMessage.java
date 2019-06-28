package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.network.rmi.VirtualViewVisitorInterface;
import it.polimi.isw2019.view.VisitorView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActionMessage extends MoveMessage {

    private String[] actionYouCanPerform;
    private ArrayList<String> actionPlayerCanPerform;
    private ArrayList<Integer> intIdAction;


    /**
     *
     * @param nickName
     */
    public ActionMessage(String nickName){

        super(nickName);
        actionYouCanPerform = new String[7];

        actionPlayerCanPerform = new ArrayList<>();
        intIdAction = new ArrayList<>();
    }

    /**
     *
     */
    public void setPowerUpAction(){
            actionYouCanPerform[6] = "USE POWER UP CARD";

            actionPlayerCanPerform.add("USE POWER UP CARD");
            intIdAction.add(6);

    }

    /**
     * Setter of Weapon cards which are reloaded
     */
    public void setReloadWeaponCard(){

        actionYouCanPerform[5] = "RELOAD";

        actionPlayerCanPerform.add("RELOAD");
        intIdAction.add(5);
    }

    /**
     * Setter of action Run
     * @param num number of change position that he can do
     */

    public void setRunAction(int num){
        actionYouCanPerform[0] = "RUN TIL " + num + "SQUARE";

        actionPlayerCanPerform.add("RUN TIL " + num + "SQUARE");
        intIdAction.add(0);
    }

    /**
     * Setter of action Run and Grab
     * @param num number of change position that he can do
     */
    public void setRunAndGrab(int num){
        actionYouCanPerform[1] = "RUN TIL " + num +" SQUARE AND GRAB";

        actionPlayerCanPerform.add("RUN TIL " + num + "SQUARE AND GRAB");
        intIdAction.add(1);


    }

    /**
     * Setter of action shot (use a Weapon Card)
     */
    public void setUseWeaponCard(){
        actionYouCanPerform[2] = "USE WEAPON CARD";

        actionPlayerCanPerform.add("USE WEAPON CARD");
        intIdAction.add(2);
    }

    /**
     * Setter of run and shot (use a Weapon Card)
     */
    public void setRunUseWeaponCardAction(){
        actionYouCanPerform[3] = "RUN TIL 1 SQUARE  AND USE WEAPON CARD";

        actionPlayerCanPerform.add("RUN TIL 1 SQUARE  AND USE WEAPON CARD");
        intIdAction.add(3);
    }

    /**
     * Setter of frenzy action, the player can run, realod and shot
     * @param num number of change position that he can do
     */
    public void setRunReloadAndUseWeaponCard(int num){
        actionYouCanPerform[4] = "RUN TIL " + num + " SQUARE ,RELOAD AND USE WEAPON CARD";

        actionPlayerCanPerform.add("RUN TIL " + num + "SQUARE ,RELOAD AND USE WEAPON CARD");
        intIdAction.add(4);
    }

    /**
     * setter of not frenzy action
     */

    public void setNormalAction(){
            actionYouCanPerform[0] = "RUN TIL 3 SQUARE ";
            actionYouCanPerform[1] = "RUN TIL 1 SQUARE AND GRAB";
            actionYouCanPerform[2] = "USE WEAPON CARD";

        }

    /**
     * Setter of frenzy action
     */

    public void setFrenzyAction(){
            actionYouCanPerform[0] = "RUN TIL 4 SQUARE ";
            actionYouCanPerform[1] = "RUN TIL 2 SQUARE AND GRAB";
            actionYouCanPerform[4] = "RUN TIL 2 SQUARE ,RELOAD AND USE WEAPON CARD";

        }

    /**
     * setter frenzy action of first player
     */


    public void setFrenzyFirstPlayerAction(){

            actionYouCanPerform[1] = "RUN TIL 3 SQUARE AND GRAB";
            actionYouCanPerform[4] = "RUN TIL 2 SQUARE ,RELOAD AND USE WEAPON CARD";


        }

    /**
     * Setter first powered action
     */

    public void setFirstPoweredAction(){
            actionYouCanPerform[1] = "RUN TIL 2 SQUARE AND GRAB";
        }

    /**
     * Setter second powered action
     */

    public void setSecondPoweredAction(){
            actionYouCanPerform[3] = "RUN TIL 1 SQUARE  AND USE WEAPON CARD";
        }

        public String[] getActionYouCanPerform() {
            return this.actionYouCanPerform;
        }

    /**
     * list of actions that player can do
     * @return array list of these action
     */
    public ArrayList<String> getActionPlayerCanPerform() {
        return actionPlayerCanPerform;
    }

    /**
     * every action are identefied by id
     * @return id of actions
     */

    public ArrayList<Integer> getIntIdAction() {
        return intIdAction;
    }

    @Override
    public int[] getFeaturesAvailable() {
        return new int[0];
    }

    @Override
    public void setFeaturesAvailable(int[] featuresAvailable) {

    }

    @Override
    public void accept(VisitorView visitorview) {
            visitorview.visitActionView(this);
    }

    @Override
    public void accept(VirtualViewVisitorInterface virtualView) {
        virtualView.sendActionView(this);
    }
}
