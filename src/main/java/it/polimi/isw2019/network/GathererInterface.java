package it.polimi.isw2019.network;

import it.polimi.isw2019.network.rmi.VirtualView;

import java.util.ArrayList;

public interface GathererInterface extends Runnable{

    public void setLobby(Lobby lobby);

    public void setVirtualViews(ArrayList<VirtualView> virtualViews);

}
