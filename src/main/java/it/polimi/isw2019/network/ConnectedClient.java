package it.polimi.isw2019.network;

public class ConnectedClient {

    private int idClient;
    private String nameClient;
    private boolean isInactive;

    public ConnectedClient(String nameClient){
        this.nameClient = nameClient;
    }

    public int getIdClient(){
        return idClient;
    }

    public String getNameClient(){
        return nameClient;
    }

    public boolean getIsInactive(){
        return isInactive;
    }

    public void setInactive (boolean inactive){
        this.isInactive = inactive;
    }
}
