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

    public void killPlayer (ColorPlayer colorPlayer, int num){
        if (num==1){
            damageToken[lengthDamageToken-numSkull][0]=colorPlayer;
        }
        if (num==2){
            damageToken[lengthDamageToken-numSkull][0]=colorPlayer;
            damageToken[lengthDamageToken-numSkull][1]=colorPlayer;
        }
        numSkull--;
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


}
