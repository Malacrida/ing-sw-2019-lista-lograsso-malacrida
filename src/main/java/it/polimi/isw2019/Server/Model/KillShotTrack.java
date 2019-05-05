package it.polimi.isw2019.Server.Model;

public class KillShotTrack {
    private int numSkull;
    private boolean finalFrenzy;
    private ColorPlayer[][] damageToken;


    KillShotTrack (int numSkull){
        this.numSkull=numSkull;
        finalFrenzy=false;
        damageToken=new ColorPlayer[numSkull][2];
        /*due colonne-> si occupa una per il danno normale due per aver infierito*/
    }

    public void killPlayer (ColorPlayer colorPlayer, int num){
        if (num==1){

        }
        if (num==2){

        }
        numSkull--;
    }


}
