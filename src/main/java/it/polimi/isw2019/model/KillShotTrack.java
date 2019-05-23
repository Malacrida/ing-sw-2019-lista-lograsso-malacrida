package it.polimi.isw2019.model;

public class KillShotTrack {
    private int numSkull;
    private boolean finalFrenzy;
    private ColorPlayer[][] damageToken;
    private int lengthDamageToken;


    KillShotTrack (int mod){
        if (mod==1) numSkull=5;
        if (mod==2) numSkull=8;
        finalFrenzy=false;
        lengthDamageToken=numSkull;
        damageToken=new ColorPlayer[lengthDamageToken][2];
        /*due colonne-> si occupa una per il danno normale due per aver infierito*/

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
