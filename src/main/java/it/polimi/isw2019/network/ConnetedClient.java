package it.polimi.isw2019.network;

import it.polimi.isw2019.network.network_interface.ClientInterface;

public class ConnetedClient {


    private String nickname;

    private ClientInterface clientInterface;

    private TypeConnection typeConnection;

    private boolean active;

    public ConnetedClient(String nickname, ClientInterface clientInterface, TypeConnection typeConnection, boolean active){
        this.nickname = nickname;
        this.clientInterface = clientInterface;
        this.typeConnection = typeConnection;
        this.active = active;
    }

    public String getNickname() {
        return nickname;
    }

    public ClientInterface getClientInterface() {
        return clientInterface;
    }

    public TypeConnection getTypeConnection() {
        return typeConnection;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active){
        this.active = active;
    }
}
