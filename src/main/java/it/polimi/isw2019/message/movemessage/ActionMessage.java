package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.view.VisitorView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActionMessage extends MoveMessage {

    private ArrayList<String> actionYouCanPerform;

    public ActionMessage(String nickName){
        super(nickName);
        actionYouCanPerform = new ArrayList<>();

    }

    public void setPowerUpAction(){
            actionYouCanPerform.add(6,"USE POWER UP CARD");
        }

        public void setReloadWeaponCard(){
            actionYouCanPerform.add(5,"RELOAD");
        }

        public void setNormalAction(){
            actionYouCanPerform.add(0,"RUN TIL 3 SQUARE ");
            actionYouCanPerform.add(1,"RUN TIL 1 SQUARE AND GRAB");
            actionYouCanPerform.add(2,"USE WEAPON CARD");

        }

        public void setFrenzyAction(){
            actionYouCanPerform.add(0,"RUN TIL 4 SQUARE ");
            actionYouCanPerform.add(1,"RUN TIL 2 SQUARE AND GRAB");
            actionYouCanPerform.add(4,"RUN TIL 2 SQUARE ,RELOAD AND USE WEAPON CARD");

        }

        public void setFrenzyFirstPlayerAction(){

            actionYouCanPerform.add(1,"RUN TIL 3 SQUARE AND GRAB");
            actionYouCanPerform.add(4,"RUN TIL 2 SQUARE ,RELOAD AND USE WEAPON CARD");


        }

        public void setFirstPoweredAction(){
            actionYouCanPerform.add(1,"RUN TIL 2 SQUARE AND GRAB");
        }

        public void setSecondPoweredAction(){
            actionYouCanPerform.add(3,"RUN TIL 1 SQUARE  AND USE WEAPON CARD");
        }

        public ArrayList<String> getActionYouCanPerform() {
            return this.actionYouCanPerform;
        }


    @Override
    public void accept(VisitorView visitorview) {
            visitorview.visitActionView(this);
    }
}
