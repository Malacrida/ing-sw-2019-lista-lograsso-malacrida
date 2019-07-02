package it.polimi.isw2019.network;

import it.polimi.isw2019.network.network_interface.ClientInterface;

public class ConnetedClient {

    String nickname;

    ClientInterface clientInterface;

    TypeConnection typeConnection;

    boolean active;

    public ConnetedClient(String nickname, ClientInterface clientInterface, TypeConnection typeConnection, boolean active){
        this.nickname = nickname;
        this.clientInterface = clientInterface;
        this.typeConnection = typeConnection;
        this.active = active;
    }
}
