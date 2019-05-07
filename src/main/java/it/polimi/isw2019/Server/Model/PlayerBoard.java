package it.polimi.isw2019.Server.Model;

public class PlayerBoard extends AbstractPlayerBoard {

    //private HashMap<Integer, ColorPlayer> damagePlayer= new HashMap<>();

    public PlayerBoard(ColorPlayer color) {
        super(color, false);
    }

    @Override
    public ColorPlayer getColor() {
        return super.getColor();
    }

    public PlayerBoard getPlayerBoard(){
        return this;
    }

    /*@Override
    public void scorePlayer() {
        ArrayList<ColorPlayer> colorDamage = listOfColorDamage();

        //Ordine crescente
        for (int i=0; i<colorDamage.size(); i++){
            damagePlayer.put(numOfDamagesOfOneColor(colorDamage.get(i)), colorDamage.get(i));
        }
    }*/

}
