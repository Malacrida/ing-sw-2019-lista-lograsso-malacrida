package it.polimi.isw2019.Server.Message.PlayerMove;

public abstract class Action extends PlayerMove {
    private String idAction;

    public Action (String idPlayerMove,String idAction){
        super(idPlayerMove);
        this.idAction = idAction;
    }
    public String getIdAction(){
        return idAction;
    }
}
