package it.polimi.isw2019.model;



public class KillShotTrack {

    private int numSkull;
    private boolean finalFrenzy;
    private ColorPlayer[][] damageToken;
    private int lengthDamageToken;

    //introdurre exception del frenzy!!

    KillShotTrack (int mod){
        if (mod==1) numSkull=5;
        else if (mod==2) numSkull=8;
        finalFrenzy=false;
        lengthDamageToken=numSkull;
        damageToken=new ColorPlayer[lengthDamageToken][2];
        /*due colonne-> si occupa una per il danno normale due per aver infierito*/

    }

    /**
     * getter of number of skulls on kill shot track
     * @return number of skulls
     */

    public int getNumSkull() {
        return numSkull;
    }

    /**
     * boolean to see if is in frenzy mode
     * @return boolean
     */

    public boolean isFinalFrenzy() {
        return finalFrenzy;
    }

    /**
     *
     * @param colorPlayer
     * @param num
     */

    public void killPlayer (ColorPlayer colorPlayer, int num) {

        if (num==11){
            damageToken[lengthDamageToken-numSkull][0]=colorPlayer;
        }
        else if (num==12){
            damageToken[lengthDamageToken-numSkull][0]=colorPlayer;
            damageToken[lengthDamageToken-numSkull][1]=colorPlayer;
        }

        numSkull--;

        if (numSkull == 0)
            finalFrenzy=true;
    }


    /**
     * method to count how many times one player is dead
     * @param colorPlayer
     * @return
     */

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


    /**
     * method to get how many damage are on kill shot track
     * @return
     */

    public ColorPlayer[][] getDamageToken() {
        return damageToken;
    }
}
