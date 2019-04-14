package it.polimi.isw2019.Model;

public class SquareAmmo extends Square {

    private boolean useAmmo;
    // Arrey Ammo

    SquareAmmo( Square squareN, Square squareE, Square squareS, Square squareO) {
        super( squareN, squareE, squareO, squareS, false);
    }

    public boolean isUseAmmo (){
        return useAmmo;
    }

    // Metodi per le ammo tile
}
