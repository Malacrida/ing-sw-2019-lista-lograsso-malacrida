package it.polimi.isw2019.network;

import java.io.PrintStream;
import java.net.Socket;

public class SocketConnection implements Runnable {

    private Socket socket;

    private PrintStream out;

    private Server server;

    public SocketConnection(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {

    }
}
