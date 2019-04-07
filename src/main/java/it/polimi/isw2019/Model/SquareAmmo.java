package it.polimi.isw2019.Model;

public class SquareAmmo extends Square {

    private boolean useAmmo;
    // Arrey Ammo

    SquareAmmo(int squareID, int squareN, int squareE, int squareO, int squareS, boolean sideN, boolean sideE, boolean sideO, boolean sideS, boolean spownpoint) {
        super(squareID, squareN, squareE, squareO, squareS, sideN, sideE, sideO, sideS, false);
    }

    public boolean isUseAmmo (){
        return useAmmo;
    }

    // Metodi per le ammo tile
}
