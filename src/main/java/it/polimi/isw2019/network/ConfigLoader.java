package it.polimi.isw2019.network;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

        /**
         * Relative path of the configuration file
         */
        private static final String FILE_PATH = "/config.txt";

        /**
         * Tag to get the info on the port used for the RMI connection
         */
        private static final String RMI_PORT = "RMI_PORT";

        /**
         * Tag to get the info on the IP address of the server
         */
        private static final String HOST = "HOST";

        /**
         * Tag to get the value of the timer for the connection waiting for the various players
         */
        private static final String TIMER_LOBBY = "TIMER_LOBBY";

        /**
         * Tag to get the timer value for the player to make the move
         */
        private static final String TIMER_ACTION = "TIMER_ACTION";
        
        /**
         * IP address of the server
         */
        private String hostIp;

        /**
         * Port used for the RMI connection
         */
        private int rmiPort;

        /**
         * Value of the timer for the connection waiting for the various players
         */
        private int timerLobby;

        /**
         * Value of the timer for the player's move
         */
        private int timerAction;

        /**
         * Constructor. Launch the load method
         */
        public ConfigLoader(){
            load();
        }

        /**
         * Upload the information extracted from the configuration file into the respective variables
         */
        private void load() {
            Properties config = new Properties();
            try {

                InputStream inputStream = getClass().getResourceAsStream(FILE_PATH);
                config.load(inputStream);
                setRmiPort(config.getProperty(RMI_PORT));
                setHostIp(config.getProperty(HOST));
                setTimerLobby(config.getProperty(TIMER_LOBBY));
                setTimerAction(config.getProperty(TIMER_ACTION));
            } catch (IOException e) {
                System.err.println("Error reading configuration file.");
            }
        }

        /**
         * Setter the hostIP
         * @param s IP address of the server
         */
        private void setHostIp(String s){
            hostIp = s;
        }

        /**
         * Setter of the rmiPort
         * @param s Port used for the RMI
         */
        private void setRmiPort(String s){
            rmiPort = Integer.parseInt(s);
        }

        /**
         * Setter the timerLobby
         * @param s Timer for the connection waiting in lobby
         */
        private void setTimerLobby(String s){
           timerLobby = Integer.parseInt(s);
        }

        /**
         * Setter of the timerAction
         * @param s Timer for the player's move
         */
        private void setTimerAction(String s){
            timerAction = Integer.parseInt(s);
        }

        /**
         * getter of the value of rmiPort
         * @return Port used for the RMI
         */
        public int getRmiPort() {
            return rmiPort;
        }

        /**
         * getter of hostIP
         * @return IP address of the server
         */
        public String getHostIp() {
            return hostIp;
        }

        /**
         * getter of the value of timerLobby
         * @return Timer for the connection waiting for the various players
         */
        public int getTimerLobby() {
            return timerLobby;
        }

        /**
         * getter of timerAction
         * @return Timer for the player's move
         */
        public int getTimerAction() {
            return timerAction;
        }


}