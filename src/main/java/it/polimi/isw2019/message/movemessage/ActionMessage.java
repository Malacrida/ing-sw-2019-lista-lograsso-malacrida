package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.view.VisitorView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActionMessage extends MoveMessage {

    private String[] actionYouCanPerform;

    public ActionMessage(String nickName){
        super(nickName);
        actionYouCanPerform = new String[7];

    }

    public void setPowerUpAction(){

            actionYouCanPerform[6] = "USE POWER UP CARD";
        }

    public void setReloadWeaponCard(){
            actionYouCanPerform[5] = "RELOAD";
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


    @Override
    public void accept(VisitorView visitorview) {
            visitorview.visitActionView(this);
    }
}
