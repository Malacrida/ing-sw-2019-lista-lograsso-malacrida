package it.polimi.isw2019.Server.Message.MoveMessage;

import java.util.ArrayList;

public class ActionMessage extends MoveMessage {

        private ArrayList<SingleAction> actionYouCanPerform;

        public ActionMessage(String idMoveMessage){
            super(idMoveMessage);
            actionYouCanPerform = new ArrayList<SingleAction>();

        }

        public void setPowerUpAction(){
            actionYouCanPerform.add(new SingleAction(6,"USE POWER UP CARD",0,false,true,false, true, false));
        }

        public void setReloadWeaponCard(){
            actionYouCanPerform.add(new SingleAction(5,"RELOAD",0,true,false,true, false, true));
        }
        public void setNormalAction(){
            actionYouCanPerform.add(new SingleAction(0,"RUN TIL 3 SQUARE ", 3,false,false,false,false,false ));
            actionYouCanPerform.add(new SingleAction(1,"RUN TIL 1 SQUARE AND GRAB", 1,false,true,false,false,false ));
            actionYouCanPerform.add(new SingleAction(2,"USE WEAPON CARD", 0,true,false,false ,true,false));

        }

        public void setFrenzyAction(){
            actionYouCanPerform.add(new SingleAction(0,"RUN TIL 4 SQUARE ", 4,false,false,false, false,false ));
            actionYouCanPerform.add(new SingleAction(1,"RUN TIL 2 SQUARE AND GRAB", 2,false,true,false ,false,false));
            actionYouCanPerform.add(new SingleAction(4,"RUN TIL 2 SQUARE ,RELOAD AND USE WEAPON CARD", 2,true,false,true,true,true ));

        }

        public void setFrenzyFirstPlayerAction(){

            actionYouCanPerform.add(new SingleAction(1,"RUN TIL 3 SQUARE AND GRAB", 3,false,true,false ,false,false));
            actionYouCanPerform.add(new SingleAction(4,"RUN TIL 2 SQUARE ,RELOAD AND USE WEAPON CARD", 2,true,false,true,true,true ));


        }

        public void setFirstPoweredAction(){
            actionYouCanPerform.add(new SingleAction(1,"RUN TIL 2 SQUARE AND GRAB", 2,false,true,false ,false,false));
        }

        public void setSecondPoweredAction(){
            actionYouCanPerform.add(new SingleAction(3,"RUN TIL 1 SQUARE  AND USE WEAPON CARD", 1,true,false,false, true,false ));
        }

        public ArrayList<SingleAction> getActionYouCanPerform(){
            return this.actionYouCanPerform;
        }

        

}
