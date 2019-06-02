package it.polimi.isw2019.model;


public enum ColorCube {
    RED("red"), YELLOW("yellow"), BLUE("blue");

    private String colorCubeRepresentation;

    ColorCube(String colorCubeRepresentation){
        this.colorCubeRepresentation = colorCubeRepresentation;
    }
    public String getColorCubeRepresentation() {
        return colorCubeRepresentation;
    }
}
