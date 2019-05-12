package it.polimi.isw2019.Server.Model;

public class KillShotTrack {
    private int numSkull;
    private boolean finalFrenzy;
    private ColorPlayer[][] damageToken;
    private int lengthDamageToken;


    KillShotTrack (int numSkull){
        this.numSkull=numSkull;
        finalFrenzy=false;
        damageToken=new ColorPlayer[numSkull][2];
        /*due colonne-> si occupa una per il danno normale due per aver infierito*/
        lengthDamageToken=numSkull;
    }

    public int getNumSkull() {
        return numSkull;
    }

    public boolean isFinalFrenzy() {
        return finalFrenzy;
    }

    public void killPlayer (ColorPlayer colorPlayer, int num) {
        if (num==11){
            damageToken[lengthDamageToken-numSkull][0]=colorPlayer;
        }
        if (num==12){
            damageToken[lengthDamageToken-numSkull][0]=colorPlayer;
            damageToken[lengthDamageToken-numSkull][1]=colorPlayer;
        }
        numSkull--;
        if (numSkull == 0) finalFrenzy=true;
    }




    public int numOfKillShotByOnePlayer (ColorPlayer colorPlayer){
        int cont=0;
        for (int i=0; i<lengthDamageToken; i++){
            for (int j=0; j<2; j++){
                if(damageToken[i][j]==colorPlayer){
                    cont++;
                }
            }
        }
        return cont;
    }


    public ColorPlayer[][] getDamageToken() {
        return damageToken;
    }
}
