package it.polimi.isw2019.model;

import it.polimi.isw2019.model.ammotile.AmmoTile;
import it.polimi.isw2019.model.exception.AmmoTileUseException;
import it.polimi.isw2019.model.exception.OutOfBoundsException;
import it.polimi.isw2019.model.weaponcard.AbstractWeaponCard;

import java.util.ArrayList;
import java.util.Arrays;

public class Arena {

    private Square[][] squares = new Square[3][4];
    private ArrayList<Room> rooms = new ArrayList<>();
    private String[][] arenaRepresentation;

    Arena() {

    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void chooseArena(int numArena) throws OutOfBoundsException {
        try {
            squares = CreateArena.chooseMap(numArena);
            rooms = CreateArena.chooseRoom(numArena);
        } catch (OutOfBoundsException e) {
            throw new OutOfBoundsException("Number out of possible bounds");
        }

    }

    //I colori indicano i punti di spawn
    public void setWeaponsCardOnSquareSpawn(AbstractWeaponCard[] weaponCardsRed, AbstractWeaponCard[] weaponCardsBlue, AbstractWeaponCard[] weaponCardsYellow) {
        squares[1][0].setWeaponCards(weaponCardsRed);
        squares[0][2].setWeaponCards(weaponCardsBlue);
        squares[2][3].setWeaponCards(weaponCardsYellow);
    }


    public void placeAnotherWeaponCardsOnSquareSpawn(AbstractWeaponCard weaponCard, int x, int y) {
        squares[x][y].putNewWeaponCard(weaponCard);
    }

    public boolean containsWeaponOnSpawnSquare(int x, int y, AbstractWeaponCard weaponCard) {
        return squares[x][y].containsWeapon(weaponCard);
    }

    public void takeWeaponCardsOnSquareSpawn(AbstractWeaponCard weaponCard, int x, int y) {
        squares[x][y].takeWeapon(weaponCard);
    }

    public AbstractWeaponCard[] getWeaponCardsOnSquares(int x, int y) {
        return squares[x][y].getWeaponCards();
    }


    public void setAmmoTilesOnSquare(ArrayList<AmmoTile> ammoTiles) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if ((!((i == 1 && j == 0) || (i == 0 && j == 2) || (i == 2 && j == 3))) && squares[i][j] != null) {
                    squares[i][j].setAmmoTile(ammoTiles.get(0));
                    ammoTiles.remove(0);
                    //cambio di stato
                }
            }

        }
    }

    public void placeAnotherAmmoTileOnSquare(AmmoTile ammoTile, int x, int y) {
        squares[x][y].setAmmoTile(ammoTile);
    }

    public AmmoTile getAmmoTileOnSquare(int x, int y) {
        return squares[x][y].getAmmoTile();
    }

    public AmmoTile takeAmmoTileOnSquare(int x, int y) throws AmmoTileUseException {
        try {
            return squares[x][y].takeAmmoTile();
        } catch (AmmoTileUseException e) {
            throw new AmmoTileUseException();
        }
    }

    public boolean useAmmoTileOnSquare(int x, int y) {
        return squares[x][y].isCanUseAmmo();
    }

    public ArrayList<Player> playersInOneRoom(ColorRoom colorRoom, Player player) {
        ArrayList<Player> playersInRoom = new ArrayList<>();
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getColorRoom() == colorRoom) {
                playersInRoom = rooms.get(i).getPlayers();
            }
        }
        if (playersInRoom.contains(player)) {
            playersInRoom.remove(player);
        }
        return playersInRoom;
    }

    public ArrayList<Player> playersInOneSquareOnArena(int x, int y, Player player) {
        ArrayList<Player> players = squares[x][y].getPlayers();
        if (players.contains(player)) {
            players.remove(player);
        }
        return players;
    }

    /**
     * @param player
     * @return
     */
    public ArrayList<Player> playerWhoSeeOnArena(Player player) {

        int x = player.getX();
        int y = player.getY();
        ArrayList<Player> playersWhoSee = new ArrayList<>();
        ArrayList<Player> playersInTheRoom = new ArrayList<>();
        playersWhoSee.clear();
        playersWhoSee = squares[x][y].getPlayers();

        //Orizzontale verso destra
        for (int i = 0; i < 3; i++) {
            if (y + i + 1 < 4) {
                if (squares[x][y + i].squaresAvailable().contains(squares[x][y + i + 1]))
                    playersWhoSee.addAll(squares[x][y + i + 1].getPlayers());
            }
        }

        //Orizzontale verso sinistra
        for (int i = 0; i < 3; i++) {
            if (y - i - 1 >= 0) {
                if (squares[x][y - i].squaresAvailable().contains(squares[x][y - i - 1]))
                    playersWhoSee.addAll(squares[x][y - i - 1].getPlayers());
            } else break;
        }
        //Verticale verso l'alto
        for (int i = 0; i < 2; i++) {
            if (x - i - 1 > 0) {
                if (squares[x - i][y].squaresAvailable().contains(squares[x - i - 1][y]))
                    playersWhoSee.addAll(squares[x - i - 1][y].getPlayers());
            } else break;
        }
        //Verticale verso il basso
        for (int i = 0; i < 2; i++) {
            if (x + i + 1 < 3) {
                if (squares[x + i][y].squaresAvailable().contains(squares[x + i + 1][y]))
                    playersWhoSee.addAll(squares[x + i + 1][y].getPlayers());
            }
        }

        playersInTheRoom = playerInTheRoomNear(player);
        for (int i = 0; i < playersInTheRoom.size(); i++) {
            if (!playersWhoSee.contains(playersInTheRoom.get(i))) playersWhoSee.add(playersInTheRoom.get(i));
        }

        if (playersWhoSee.contains(player)) {
            playersWhoSee.remove(player);
        }
        return playersWhoSee;
    }


    public ArrayList<Player> playerInTheRoomNear(Player player) {
        ArrayList<Player> playersInTheRoom = new ArrayList<>();

        int x = player.getX();
        int y = player.getY();

        for (int i = 0; i < rooms.size(); i++) {
            if (x - 1 > 0) {
                if (rooms.get(i).containsSquare(squares[x - 1][y]) && squares[x][y].squaresAvailable().contains(squares[x - 1][y]))
                    playersInTheRoom.addAll(rooms.get(i).getPlayers());
            }
            if (x + 1 < 2) {
                if (rooms.get(i).containsSquare(squares[x + 1][y]) && squares[x][y].squaresAvailable().contains(squares[x + 1][y]))
                    playersInTheRoom.addAll(rooms.get(i).getPlayers());
            }
            if (y - 1 > 0) {
                if (rooms.get(i).containsSquare(squares[x][y - 1]) && squares[x][y].squaresAvailable().contains(squares[x][y - 1]))
                    playersInTheRoom.addAll(rooms.get(i).getPlayers());
            }
            if (y + 1 < 4) {
                if (rooms.get(i).containsSquare(squares[x][y + 1]) && squares[x][y].squaresAvailable().contains(squares[x][y + 1]))
                    playersInTheRoom.addAll(rooms.get(i).getPlayers());
            }

        }
        return playersInTheRoom;
    }


    //Controllo sul colore scelto dal giocatore dove spownare
    public void spawnPlayer(ColorRoom colorRoomToSpawn, Player player) {
        switch (colorRoomToSpawn) {
            case RED:
                squares[1][0].addPlayer(player);
                player.changeRoom(ColorRoom.RED);
                player.changeSquare(1, 0);
                break;
            case BLUE:
                squares[0][2].addPlayer(player);
                player.changeRoom(ColorRoom.BLUE);
                player.changeSquare(0, 2);
                break;
            case YELLOW:
                squares[2][3].addPlayer(player);
                player.changeRoom(ColorRoom.YELLOW);
                player.changeSquare(2, 3);
                break;
        }
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getColorRoom() == colorRoomToSpawn) {
                rooms.get(i).addPlayer(player);
            }
        }

    }

    //per spostare di posizione il giocaotre
    public void movePlayer(Player player, int x, int y) {

        if (isSquaresAvailable(player, x, y)) {
            if (isPlayerChangeRoom(player, x, y)) {
                playerChangeRoom(player, x, y);
            }
            squares[player.getX()][player.getY()].removePlayers(player);
            squares[x][y].addPlayer(player);
            player.changeSquare(x, y);
        }
    }

    public void teleporterMove(Player player, int x, int y) {

        if (isPlayerChangeRoom(player, x, y)) {
            playerChangeRoom(player, x, y);
        }

        squares[player.getX()][player.getY()].removePlayers(player);
        squares[x][y].addPlayer(player);
        player.changeSquare(x, y);
    }

    public boolean isSquaresAvailable(Player player, int x, int y) {
        ArrayList<Square> squaresAvailable = squares[player.getX()][player.getY()].squaresAvailable();
        return squaresAvailable.contains(squares[x][y]);
    }

    public boolean isSpawnSquare(int x, int y) {
        return squares[x][y].isSpawnPoint();
    }

    //per vedere se il player cambia stanza
    public boolean isPlayerChangeRoom(Player player, int x, int y) {
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getColorRoom() == player.getColorRoom() && rooms.get(i).containsSquare(squares[x][y]))
                return false;
        }
        return true;
    }

    public void playerChangeRoom(Player player, int x, int y) {
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).containsSquare(squares[player.getX()][player.getY()])) {
                rooms.get(i).removePlayer(player);
            }
            if (rooms.get(i).containsSquare(squares[x][y])) {
                rooms.get(i).addPlayer(player);
                player.changeRoom(rooms.get(i).getColorRoom());
            }
        }
    }

    public Square[][] getSquares() {
        return squares;
    }

    public ArrayList<Square> squaresAvailable(int x, int y){
        return squares[x][y].squaresAvailable();
    }

    public Square getSquare(int x, int y){
        return squares[x][y];
    }

    public int[] coordinateOfSquare(Square square){
        int[] index = new int[2];
        for(int i=0;i<3;i++)
            for(int j=0; i<4; j++)
                if(square.equals(squares[i][j])){
                    index[0] = i;
                    index[1] = j;
                }
        return index;
        }


    public String[][] getArenaRepresentation() {
        return arenaRepresentation;
    }

    //chiedere a sara per il colore
    public void setArenaRepresentation() {
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                //mettere a posto
                //accedere al colore e alla carta
                squares[i][j].setSquareRepresentation("blue",'A',0);
            }
        }
    }

    public void updateArenaRepresentation(int x, int y, int numPlayers){
        squares[x][y].setNumPlayersInSquare(numPlayers);
    }

}


