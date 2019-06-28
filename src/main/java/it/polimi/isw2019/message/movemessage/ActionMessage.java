package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.view.VisitorView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActionMessage extends MoveMessage {

    private String[] actionYouCanPerform;
    private ArrayList<String> actionPlayerCanPerform;
    private ArrayList<Integer> intIdAction;


    public ActionMessage(String nickName){

        super(nickName);
        actionYouCanPerform = new String[7];

        actionPlayerCanPerform = new ArrayList<>();
        intIdAction = new ArrayList<>();
    }

    public void setPowerUpAction(){
            actionYouCanPerform[6] = "USE POWER UP CARD";

            actionPlayerCanPerform.add("USE POWER UP CARD");
            intIdAction.add(6);

        }

    public void setReloadWeaponCard(){

        actionYouCanPerform[5] = "RELOAD";

        actionPlayerCanPerform.add("RELOAD");
        intIdAction.add(5);
    }

    public void setRunAction(int num){
        actionYouCanPerform[0] = "RUN TIL " + num + "SQUARE";

        actionPlayerCanPerform.add("RUN TIL " + num + "SQUARE");
        intIdAction.add(0);
    }

    public void setRunAndGrab(int num){
        actionYouCanPerform[1] = "RUN TIL " + num +" SQUARE AND GRAB";

        actionPlayerCanPerform.add("RUN TIL " + num + "SQUARE AND GRAB");
        intIdAction.add(1);


    }
    public void setUseWeaponCard(){
        actionYouCanPerform[2] = "USE WEAPON CARD";

        actionPlayerCanPerform.add("USE WEAPON CARD");
        intIdAction.add(2);
    }

    public void setRunUseWeaponCardAction(){
        actionYouCanPerform[3] = "RUN TIL 1 SQUARE  AND USE WEAPON CARD";

        actionPlayerCanPerform.add("RUN TIL 1 SQUARE  AND USE WEAPON CARD");
        intIdAction.add(3);
    }

    public void setRunReloadAndUseWeaponCard(int num){
        actionYouCanPerform[4] = "RUN TIL " + num + " SQUARE ,RELOAD AND USE WEAPON CARD";

        actionPlayerCanPerform.add("RUN TIL " + num + "SQUARE ,RELOAD AND USE WEAPON CARD");
        intIdAction.add(4);
    }

    public void setNormalAction(){
            actionYouCanPerform[0] = "RUN TIL 3 SQUARE ";
            actionYouCanPerform[1] = "RUN TIL 1 SQUARE AND GRAB";
            actionYouCanPerform[2] = "USE WEAPON CARD";

        }


        public void setFrenzyAction(){
            actionYouCanPerform[0] = "RUN TIL 4 SQUARE ";
            actionYouCanPerform[1] = "RUN TIL 2 SQUARE AND GRAB";
            actionYouCanPerform[4] = "RUN TIL 2 SQUARE ,RELOAD AND USE WEAPON CARD";

        }



        public void setFrenzyFirstPlayerAction(){

            actionYouCanPerform[1] = "RUN TIL 3 SQUARE AND GRAB";
            actionYouCanPerform[4] = "RUN TIL 2 SQUARE ,RELOAD AND USE WEAPON CARD";


        }

        public void setFirstPoweredAction(){
            actionYouCanPerform[1] = "RUN TIL 2 SQUARE AND GRAB";
        }

        public void setSecondPoweredAction(){
            actionYouCanPerform[3] = "RUN TIL 1 SQUARE  AND USE WEAPON CARD";
        }

        public String[] getActionYouCanPerform() {
            return this.actionYouCanPerform;
        }

    public ArrayList<String> getActionPlayerCanPerform() {
        return actionPlayerCanPerform;
    }

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
}
