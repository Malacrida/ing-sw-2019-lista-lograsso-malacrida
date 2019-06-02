package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.view.VisitorView;

import java.util.HashMap;
import java.util.Map;

public class ActionMessage extends MoveMessage {

    private Map<Integer,String> actionYouCanPerform;

    public ActionMessage(String idMoveMessage){
        super(idMoveMessage);
        actionYouCanPerform = new HashMap<>();

    }

    public void setPowerUpAction(){
            actionYouCanPerform.put(6,"USE POWER UP CARD");
        }

        public void setReloadWeaponCard(){
            actionYouCanPerform.put(5,"RELOAD");
        }
        public void setNormalAction(){
            actionYouCanPerform.put(0,"RUN TIL 3 SQUARE ");
            actionYouCanPerform.put(1,"RUN TIL 1 SQUARE AND GRAB");
            actionYouCanPerform.put(2,"USE WEAPON CARD");

        }

        public void setFrenzyAction(){
            actionYouCanPerform.put(0,"RUN TIL 4 SQUARE ");
            actionYouCanPerform.put(1,"RUN TIL 2 SQUARE AND GRAB");
            actionYouCanPerform.put(4,"RUN TIL 2 SQUARE ,RELOAD AND USE WEAPON CARD");

        }

        public void setFrenzyFirstPlayerAction(){

            actionYouCanPerform.put(1,"RUN TIL 3 SQUARE AND GRAB");
            actionYouCanPerform.put(4,"RUN TIL 2 SQUARE ,RELOAD AND USE WEAPON CARD");


        }

        public void setFirstPoweredAction(){
            actionYouCanPerform.put(1,"RUN TIL 2 SQUARE AND GRAB");
        }

        public void setSecondPoweredAction(){
            actionYouCanPerform.put(3,"RUN TIL 1 SQUARE  AND USE WEAPON CARD");
        }

        public Map<Integer,String> getActionYouCanPerform(){
            return this.actionYouCanPerform;
        }


    @Override
    public void accept(VisitorView visitorview) {

    }
}
