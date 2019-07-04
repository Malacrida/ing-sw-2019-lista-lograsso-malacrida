package it.polimi.isw2019.network;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

        /**
         * Relative path of the configuration file
         */
        private static final String CONFIG_FILE_PATH = "/config.txt";

        /**
         * Tag to get the info on the port used for the RMI connection
         */
        private static final String RMI_PORT = "RMI_PORT";

        /**
         * Tag to get the info on the port used for the Socket connection
         */
        private static final String SOKET_PORT = "SKT_PORT";

        /**
         * Tag to get the info on the IP address of the server
         */
        private static final String HOST = "HOST";

        /**
         * Tag to get the value of the timer for the connection waiting for the various players
         */
        private static final String TIMER_START = "TIMER_START";

        /**
         * Tag to get the timer value for the player to make the move
         */
        private static final String TIMER_USER = "TIMER_USER";

        /**
         * Tag to get the number of attempts to make before considering a disconnected client
         */
        private static final String MAX_RECONNECTION = "MAX_RECONNECTION";

        /**
         * IP address of the server
         */
        private String hostIp;

        /**
         * Port used for the Socket connection
         */
        private int soketPort;

        /**
         * Port used for the RMI connection
         */
        private int rmiPort;

        /**
         * Value of the timer for the connection waiting for the various players
         */
        private int timerStart;

        /**
         * Value of the timer for the player's move
         */
        private int timerUser;

        /**
         * Number of attempts to connect to a customer before considering it disconnected
         */
        private int maxReconnection;

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

                InputStream inputStream = getClass().getResourceAsStream(CONFIG_FILE_PATH);
                config.load(inputStream);
                setRmiPort(config.getProperty(RMI_PORT));
                setHostIp(config.getProperty(HOST));
                //setSktPort(config.getProperty(SOCKET_PORT));
                //setTimerStart(config.getProperty(TIMER_START));
                //setTimerUser(config.getProperty(TIMER_USER));
                //setMaxReconnection(config.getProperty(MAX_RECONNECTION));
            } catch (IOException e) {
                System.err.println("Error reading configuration file.");
            }
        }

        /**
         *
         * Sets the hostIP attribute
         *
         * @param s IP address of the server
         */
        private void setHostIp(String s){
            hostIp = s;
        }

        /**
         * Sets the rmiPort attribute
         *
         * @param s Port used for the RMI connection
         */
        private void setRmiPort(String s){
            rmiPort = Integer.parseInt(s);
        }

        /**
         * Sets the sktPort attribute
         *
         * @param s Port used for the Socket connection
         */
        private void setSktPort(String s){
            soketPort = Integer.parseInt(s);
        }

        /**
         * Sets the timerStart attribute
         *
         * @param s Value of the timer for the connection waiting for the various players
         */
        private void setTimerStart(String s){
            timerStart = Integer.parseInt(s);
        }

        /**
         * Sets the timerUser attribute
         *
         * @param s Value of the timer for the player's move
         */
        private void setTimerUser(String s){
            timerUser = Integer.parseInt(s);
        }

        /**
         * Sets the maxReconnection attribute
         *
         * @param s Number of attempts to connect to a customer before considering it disconnected
         */
        private void setMaxReconnection(String s){
            maxReconnection = Integer.parseInt(s);
        }

        /**
         * Returns the value of sktPort attribute
         *
         * @return Port used for the Socket connection
         */
        public int getSktPort() {
            return soketPort;
        }

        /**
         * Returns the value of rmiPort attribute
         *
         * @return Port used for the RMI connection
         */
        public int getRmiPort() {
            return rmiPort;
        }

        /**
         * Returns of the value of timerStart attribute
         *
         * @return Value of the timer for the connection waiting for the various players
         */
        public int getTimerStart() {
            return timerStart;
        }

        /**
         * Returns the value of timerUser attribute
         *
         * @return Value of the timer for the player's move
         */
        public int getTimerUser() {
            return timerUser;
        }

        /**
         * Returns the value of maxReconnection attribute
         *
         * @return Number of attempts to connect to a customer before considering it disconnected
         */
        public int getMaxReconnection() {
            return maxReconnection;
        }

        /**
         * Returns the value of hostIP attribute
         *
         * @return IP address of the server
         */
        public String getHostIp() {
            return hostIp;
        }
}