package it.polimi.isw2019.model;

import java.util.concurrent.TimeUnit;

public class TimerPlayer implements Runnable {

    Model model;
    int time;
    boolean answer= false;

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
        }

    }

    public void startTimer (){
        Thread thread = new Thread(this);
        thread.start();
    }

    public void stopTimer (){
        answer=true;

    }


}
