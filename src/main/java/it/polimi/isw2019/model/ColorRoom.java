package it.polimi.isw2019.model;

public enum ColorRoom {
    RED("red"), YELLOW("yellow"), GREEN("green"), BLUE("blue"), VIOLET("violet"), GREY("grey");

    private String colorRoomRepresentation;

    ColorRoom(String colorRoomRepresentation){
        this.colorRoomRepresentation = colorRoomRepresentation;
    }
    public String getColorRoomRepresentation() {
        return colorRoomRepresentation;
    }
}
