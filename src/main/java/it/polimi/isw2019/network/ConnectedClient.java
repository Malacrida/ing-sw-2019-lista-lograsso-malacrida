package it.polimi.isw2019.network;

public class ConnectedClient {

    private int idClient;
    private String nameClient;
    private boolean isInactive;

    public ConnectedClient(int idClient, String nameClient){
        this.idClient = idClient;
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
