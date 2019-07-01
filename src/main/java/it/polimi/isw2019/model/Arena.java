package it.polimi.isw2019.model;

import it.polimi.isw2019.model.ammotile.AmmoTile;
import it.polimi.isw2019.model.exception.AmmoTileUseException;
import it.polimi.isw2019.model.exception.OutOfBoundsException;
import it.polimi.isw2019.model.weaponcard.AbstractWeaponCard;

import java.util.ArrayList;

public class Arena {

    private Square[][] squares = new Square[3][4];
    private ArrayList<Room> rooms = new ArrayList<>();
    private String arenaRepresentation = new String();
    private String[][] tmpArena = new String[7][24];

    Arena() {

    }


    public ArrayList<Room> getRooms() {
        return rooms;
    }

    /**
     * This method is used to choose on which arena do you want play
     * @param numArena is an identifier of type arena
     * @throws OutOfBoundsException
     */

    public void chooseArena(int numArena) throws OutOfBoundsException {
        try {
            squares = CreateArena.chooseMap(numArena);
            rooms = CreateArena.chooseRoom(numArena);
            //setArenaRepresentation();
            setTmpArena();
        } catch (OutOfBoundsException e) {
            throw new OutOfBoundsException("Number out of possible bounds");
        }

    }

    /**
     * setter weapon card on arena
     * @param weaponCardsRed weapon cards on red spawn
     * @param weaponCardsBlue weapon cards on blue spawn
     * @param weaponCardsYellow weapon cards on yellow spawn
     */

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

    /**
     * setter ammo tiles on arena
     * @param ammoTiles ammo tiles
     */
    public void setAmmoTilesOnSquare(ArrayList<AmmoTile> ammoTiles) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if ((!((i == 1 && j == 0) || (i == 0 && j == 2) || (i == 2 && j == 3))) && squares[i][j] != null) {
                    squares[i][j].setAmmoTile(ammoTiles.get(0));
                    ammoTiles.remove(0);
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

    public AmmoTile takeAmmoTileOnSquare(int x, int y) throws AmmoTileUseException{
            System.out.println(squares[x][y].getSquareRepresentation());
            return squares[x][y].takeAmmoTile();
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

    /**
     * list of player in a square
     * @param x coordinate of square
     * @param y coordinate of square
     * @param player you use to remove the attacker from the list of player in one square
     * @return arraylist of players
     */

    public ArrayList<Player> playersInOneSquareOnArena(int x, int y, Player player) {
        ArrayList<Player> players = squares[x][y].getPlayers();
        if (players.contains(player)) {
            //remove or get ?
            players.remove(player);
        }
        return players;
    }

    /** list of players that one player can see
     * @param player who want know which players see
     * @return arraylist of players
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

    /** list of players in the square near player
     * @param player who want know which players are in the room
     * @return arraylist of players
     */

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

    /**
     * this method spawn player on arena
     * @param colorRoomToSpawn color of spawn
     * @param player who spawn
     */

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

    /**
     * change player's position
     * @param player who has to move
     * @param x first coordinate
     * @param y second coordinate
     */

    //per spostare di posizione il giocaotre
    public void movePlayer(Player player, int x, int y) {

        if(player.getX() == -1 && player.getY() == -1){
            squares[x][y].addPlayer(player);
            player.changeSquare(x, y);
        }
        else if (isSquaresAvailable(player, x, y)) {
            if (isPlayerChangeRoom(player, x, y)) {
                playerChangeRoom(player, x, y);
            }
            squares[player.getX()][player.getY()].removePlayers(player);
            squares[x][y].addPlayer(player);
            player.changeSquare(x,y);
        }

    }

    public void movePlayerRespawnSquare(Player player, ColorRoom colorRoom){
        if(colorRoom.equals(ColorRoom.YELLOW)){
            player.changeRoom(ColorRoom.YELLOW);
            movePlayer(player,2,3);
        }
        else if(colorRoom.equals(ColorRoom.BLUE)){
            player.changeRoom(ColorRoom.BLUE);
            movePlayer(player,0,2);
        }
        else if(colorRoom.equals(ColorRoom.RED)){
            player.changeRoom(ColorRoom.RED);
            movePlayer(player,1,0);
        }
    }

    public boolean isRespawnSquare(int x, int y){
        if((x == 2 && y == 3) || (x == 0 && y == 2) ||(x ==0 && y == 1))
            return true;
        else
            return false;
    }

    /**
     * teleport player who use this method
     * @param player who use teleporter card
     * @param x first coordinate
     * @param y second coordiante
     */
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

    /**
     * change player's room
     * @param player who change room
     * @param x first coordinate
     * @param y second coordinate
     */
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

    public ArrayList<Square> squaresAvailable(int x, int y) {
        return squares[x][y].squaresAvailable();
    }

    public Square getSquare(int x, int y) {
        return squares[x][y];
    }

    public ColorRoom getColorRoom(Square square) {
        ColorRoom colorRoom = null;
        for (Room room : rooms) {
            if (room.containsSquare(square)) {
                colorRoom = room.getColorRoom();
                break;
            }

        }
        return colorRoom;
    }

    public int[] coordinateOfSquare(Square square) {
        int[] index = new int[2];
        for (int i = 0; i < 3; i++)
            for (int j = 0; i < 4; j++)
                if (square.equals(squares[i][j])) {
                    index[0] = i;
                    index[1] = j;
                }
        return index;
    }



    public void setArenaRepresentation() {

        arenaRepresentation = " ";

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if(squares[i][j]!= null)
                        arenaRepresentation += " " +squares[i][j].getSquareRepresentation() ;
                else
                    arenaRepresentation +=  "  "+"X" + " " + "X" + " ";
            }
            arenaRepresentation+= "\n";
            arenaRepresentation += " ";
        }

    }

    public void insertSquareInRepresentation(Square square,int startingPointRaw,int startingPointColumn){
        if(square!= null) {
            square.setSquareRepresentation();
            for (int i = startingPointColumn, j = 0; i < startingPointColumn + 6; i++, j++) {
                tmpArena[startingPointRaw - 1][i] = square.getRaw()[j];
                tmpArena[startingPointRaw][i] = square.getCenter()[j];
                tmpArena[startingPointRaw + 1][i] = square.getCenter()[j];
            }
        }
        else{
            for (int i = startingPointColumn, j = 0; i < startingPointColumn + 6; i++, j++) {
                tmpArena[startingPointRaw - 1][i] = "_";
                tmpArena[startingPointRaw][i] = " ";
                tmpArena[startingPointRaw + 1][i] = "_";

            }

            tmpArena[startingPointRaw - 1][startingPointColumn] = "|";
            tmpArena[startingPointRaw][startingPointColumn] = "|";
            tmpArena[startingPointRaw + 1][startingPointColumn] = "|";

            tmpArena[startingPointRaw - 1][startingPointColumn + 5] = "|";
            tmpArena[startingPointRaw][startingPointColumn+ 5] = "|";
            tmpArena[startingPointRaw + 1][startingPointColumn+ 5] = "|";
        }
    }


    public void setTmpArena() {
        /*int raw = 0, column = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (j == 0) {
                    column = 0;
                } else if (j == 1) {
                    column = 6;
                } else if (j == 2) {
                    column = 12;
                } else if (j == 3) {
                    column = 18;
                }
                //righe
                if (i == 0) {
                    raw = 1;
                } else if (i == 1) {
                    raw = 3;
                } else if (i == 2) {
                    raw = 5;
                }
                insertSquareInRepresentation(squares[i][j], raw, column);
            }



        }

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 24; j++) {
                arenaRepresentation += tmpArena[i][j];
                if (j == 23) {
                    arenaRepresentation += "\n";
                }
            }
         }*/
        arenaRepresentation += "  0    1    2    3  ";
        arenaRepresentation += "\n";
        for(int i = 0; i < 3; i ++) {
            for (int j = 0; j < 4; j++) {
                if(j == 0){
                    arenaRepresentation+= i + " ";
                }
                if(squares[i][j]!= null)
                    arenaRepresentation += squares[i][j].toString();
                else
                    arenaRepresentation +=  "X" + " " + "X" + "   ";
             }
            System.out.println("\n");
            }

        }




    public String getArenaRepresentation() {
        setArenaRepresentation();
        return arenaRepresentation;
    }

    public void setStatusCardOnBoard(){
        for(int i = 0; i < 3 ; i ++){
            for(int j = 0 ; j < 4 ; j ++){
                if(!squares[i][j].isCanUseAmmo())
                    squares[i][j].getAmmoTile().setCheckState(StateCard.ON_BOARD);
                else if(squares[i][j] instanceof SquareSpawn){
                    for(AbstractWeaponCard weaponCards: squares[i][j].getWeaponCards()){
                        if(weaponCards.getStateCard() != StateCard.ON_BOARD){
                            weaponCards.changeState(StateCard.ON_BOARD);
                        }
                    }
                }
            }
        }
    }


    @Override
    public String toString(){
        setArenaRepresentation();
        return getArenaRepresentation();
    }
}

