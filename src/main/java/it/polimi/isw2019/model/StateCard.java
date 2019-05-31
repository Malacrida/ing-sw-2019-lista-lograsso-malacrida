package it.polimi.isw2019.model;

public enum StateCard {
    DECK("deck"), //-> nel mazzo
    ON_BOARD("on board"), //-> sulla gameBoard
    HOLDING("holding"), //-> carica
    DISCHARGE("discharged"), //-> scarica
    DISCARD("discarded"); //-> scartata

    private String stateCardRepresentation;

    StateCard(String stateCardRepresentation) {
        this.stateCardRepresentation = stateCardRepresentation;
    }

    public String getStateCardRepresentation() {
        return stateCardRepresentation;
    }
}
