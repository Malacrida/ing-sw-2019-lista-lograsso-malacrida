package it.polimi.isw2019.model;

import java.util.concurrent.TimeUnit;

public class TimerPlayer implements Runnable {

    private Model model;
    private int time;
    private boolean answer= false;
    private Thread thread;

    TimerPlayer(int time){
        this.time= time;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void run (){
        answer=false;
        try {
            for (int i =0; i<time; i++){
                TimeUnit.SECONDS.sleep(1);
                System.out.println("wait " +i);
                if (answer){
                    System.out.println("arrivata la risposta" );
                    i=i+time;
                }


            }
            if (!answer)
                model.playerNotAnswer();
        } catch (InterruptedException e) {
            e.printStackTrace();
            thread.interrupt();
        }

    }

    public void startTimer (){
        thread = new Thread(this);
        thread.start();
    }

    public void stopTimer (){
        answer=true;

    }


}
