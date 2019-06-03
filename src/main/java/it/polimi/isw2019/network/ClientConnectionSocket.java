package it.polimi.isw2019.network;

import java.io.IOException;

public class ClientConnectionSocket {

    static final String host = "localhost";
    static final int portNumber = 12345;

    static boolean isActive = true;

    public static void main (String args[]) throws IOException {
        System.out.println("Creating socket to '" + host + "' on port " + portNumber);

        while (isActive) {
            /*Socket newClientSocket = new Socket(host, portNumber); //da mettere nella classe server
            BufferedReader br = new BufferedReader(new InputStreamReader(newClientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(newClientSocket.getOutputStream(), true);

            System.out.println("Server:" + br.readLine());

            BufferedReader userInputBR = new BufferedReader(new InputStreamReader(System.in));
            String userInput = userInputBR.readLine();

            out.println(userInput);

            System.out.println("Server:" + br.readLine());

            if ("exit".equalsIgnoreCase(userInput)) {
                newClientSocket.close();
                break;*/
            }
        }
    }

    /*private void startNewClient(){
        Thread thread = new Thread();
        thread.start();
    }*/



