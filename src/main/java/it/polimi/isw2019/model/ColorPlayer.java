package it.polimi.isw2019.model;

public enum ColorPlayer {
    YELLOW("yellow"), GREEN("green"), BLUE("blue"), VIOLET("violet"), GREY("grey");

    private String colorPlayerRepresentation;

    ColorPlayer(String colorPlayerRepresentation){
        this.colorPlayerRepresentation = colorPlayerRepresentation;
    }
    public String getColorPlayerRepresentation() {
        return colorPlayerRepresentation;
    }
}
