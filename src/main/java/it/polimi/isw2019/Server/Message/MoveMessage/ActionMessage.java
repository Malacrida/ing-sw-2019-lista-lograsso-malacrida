package it.polimi.isw2019.Server.Message.MoveMessage;

import java.util.ArrayList;
public class ActionMessage extends MoveMessage {

        private ArrayList<SingleAction> actionYouCanPerform;

        public ActionMessage(String idMoveMessage){
            super(idMoveMessage);
            actionYouCanPerform = new ArrayList<SingleAction>();
            actionYouCanPerform.add(new SingleAction("RELOAD",0,true,false,true));
            actionYouCanPerform.add(new SingleAction("USE POWER UP CARD",0,false,true,false));
        }


        public void setNormalAction(){
            actionYouCanPerform.add(new SingleAction("RUN TIL 3 SQUARE ", 3,false,false,false ));
            actionYouCanPerform.add(new SingleAction("RUN TIL 1 SQUARE AND GRAB", 1,false,true,false ));
            actionYouCanPerform.add(new SingleAction("USE WEAPON CARD", 0,true,false,false ));

        }

        public void setFrenzyAction(){
            actionYouCanPerform.add(new SingleAction("RUN TIL 4 SQUARE ", 4,false,false,false ));
            actionYouCanPerform.add(new SingleAction("RUN TIL 2 SQUARE AND GRAB", 2,false,true,false ));
            actionYouCanPerform.add(new SingleAction("RUN TIL 2 SQUARE ,RELOAD AND USE WEAPON CARD", 2,true,false,true ));

        }

        public void setFrenzyFirstPlayerAction(){

            actionYouCanPerform.add(new SingleAction("RUN TIL 3 SQUARE AND GRAB", 3,false,true,false ));
            actionYouCanPerform.add(new SingleAction("RUN TIL 2 SQUARE ,RELOAD AND USE WEAPON CARD", 2,true,false,true ));


        }

        public void setFirstPoweredAction(){
            actionYouCanPerform.add(new SingleAction("RUN TIL 2 SQUARE AND GRAB", 2,false,true,false ));
        }

        public void setSecondPoweredAction(){
            actionYouCanPerform.add(new SingleAction("RUN TIL 1 SQUARE  AND USE WEAPON CARD", 1,true,false,false ));
        }

        public ArrayList<SingleAction> getActionYouCanPerform(){
            return this.actionYouCanPerform;
        }

}
