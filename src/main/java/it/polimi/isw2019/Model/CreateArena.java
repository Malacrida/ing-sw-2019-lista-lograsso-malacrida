package it.polimi.isw2019.Model;

import it.polimi.isw2019.Model.Exception.OutOfBoundsException;

import java.util.ArrayList;

public class CreateArena {

    private static Square squares [][]= new Square[3][4];
    private static ArrayList<Room> rooms= new ArrayList<>();

    public static Square[][] chooseMap (int numArena) throws OutOfBoundsException {
        setSquares();
        switch (numArena){
            case 1:
                setMap1();
                break;
            case 2:
                setMap2();
                break;
            case 3:
                setMap3();
                break;
            case 4:
                setMap4();
                break;
            default:
                throw new OutOfBoundsException();
        }
        return squares;
    }

    public static ArrayList<Room> chooseRoom (int numArena) throws OutOfBoundsException{
        switch (numArena){
            case 1:
                setRooms1();
                break;
            case 2:
                setRooms2();
                break;
            case 3:
                setRooms3();
                break;
            case 4:
                setRooms4();
                break;
            default:
                throw new OutOfBoundsException();
        }
        return rooms;
    }

    private static void setSquares(){
        squares[0][0]= new SquareAmmo();
        squares[0][1]= new SquareAmmo();
        squares[0][2]= new SquareSpawn();
        squares[0][3]= new SquareAmmo();

        squares[1][0]= new SquareSpawn();
        squares[1][1]= new SquareAmmo();
        squares[1][2]= new SquareAmmo();
        squares[1][3]= new SquareAmmo();

        squares[2][0]= new SquareAmmo();
        squares[2][1]= new SquareAmmo();
        squares[2][2]= new SquareAmmo();
        squares[2][3]= new SquareSpawn();

    }

    private static void setMap1 (){
        squares[0][0].setSquareAdjacent(null, squares[0][1], squares[1][0],null);
        squares[0][1].setSquareAdjacent(null, squares[0][2], null, squares[0][0]);
        squares[0][2].setSquareAdjacent(null, squares[0][3], squares[1][2], squares[0][1]);
        squares[0][3].setSquareAdjacent(null,null,squares[1][3], squares[0][2]);

        squares[1][0].setSquareAdjacent(squares[0][0], squares[1][1], null,null);
        squares[1][1].setSquareAdjacent(null, null, squares[2][1], squares[1][0]);
        squares[1][2].setSquareAdjacent(squares[0][2], squares[1][3], squares[2][2], null);
        squares[1][3].setSquareAdjacent(squares[0][3],null,squares[2][3], squares[1][2]);

        squares[2][0]= null; //stanza non disponibile
        squares[2][1].setSquareAdjacent(squares[1][1], squares[2][2], null, null);
        squares[2][2].setSquareAdjacent(squares[1][2], squares[2][3], null, squares[2][1]);
        squares[2][3].setSquareAdjacent(squares[1][3],null,null, squares[2][2]);

    }

    private static void setRooms1 (){
        Room bluRooms = new Room(ColorRoom.BLUE);
        bluRooms.addSquare(squares[0][0]);
        bluRooms.addSquare(squares[0][1]);
        bluRooms.addSquare(squares[0][2]);

        Room greenRooms = new Room(ColorRoom.GREEN);
        greenRooms.addSquare(squares[0][3]);

        Room redRooms = new Room(ColorRoom.RED);
        redRooms.addSquare(squares[1][0]);
        redRooms.addSquare(squares[1][1]);

        Room greyRoom = new Room(ColorRoom.GREY);
        greyRoom.addSquare(squares[2][1]);

        Room yellowRoom = new Room(ColorRoom.YELLOW);
        yellowRoom.addSquare(squares[1][2]);
        yellowRoom.addSquare(squares[1][3]);
        yellowRoom.addSquare(squares[2][2]);
        yellowRoom.addSquare(squares[2][3]);

        rooms.add(bluRooms);
        rooms.add(greenRooms);
        rooms.add(redRooms);
        rooms.add(greyRoom);
        rooms.add(yellowRoom);
    }

    private static void setMap2 (){
        squares[0][0].setSquareAdjacent(null, squares[0][1], squares[1][0],null);
        squares[0][1].setSquareAdjacent(null, squares[0][2], squares[1][1], squares[0][0]);
        squares[0][2].setSquareAdjacent(null, null, squares[1][2], squares[0][1]);
        squares[0][3]= null;//stanza non disponibile

        squares[1][0].setSquareAdjacent(squares[0][0], null, squares[0][1],null);
        squares[1][1].setSquareAdjacent(squares[0][1], squares[1][2], squares[2][1], null);
        squares[1][2].setSquareAdjacent(squares[0][2], squares[1][3], null, squares[1][1]);
        squares[1][3].setSquareAdjacent(null,null,squares[2][3], squares[1][2]);

        squares[2][0].setSquareAdjacent(squares[1][0], squares[2][1], null,null);
        squares[2][1].setSquareAdjacent(squares[1][1], squares[2][2], null, squares[2][0]);
        squares[2][2].setSquareAdjacent(null, squares[2][3], null, squares[2][1]);
        squares[2][3].setSquareAdjacent(squares[1][3],null,null, squares[2][2]);

    }

    private static void setRooms2 (){
        Room bluRooms = new Room(ColorRoom.BLUE);
        bluRooms.addSquare(squares[0][1]);
        bluRooms.addSquare(squares[0][2]);

        Room redRooms = new Room(ColorRoom.RED);
        redRooms.addSquare(squares[0][0]);
        redRooms.addSquare(squares[1][0]);

        Room violetRooms = new Room(ColorRoom.VIOLET);
        violetRooms.addSquare(squares[1][1]);
        violetRooms.addSquare(squares[1][2]);

        Room greyRoom = new Room(ColorRoom.GREY);
        greyRoom.addSquare(squares[2][0]);
        greyRoom.addSquare(squares[2][1]);
        greyRoom.addSquare(squares[2][2]);

        Room yellowRoom = new Room(ColorRoom.YELLOW);
        yellowRoom.addSquare(squares[1][3]);
        yellowRoom.addSquare(squares[2][3]);

        rooms.add(bluRooms);
        rooms.add(redRooms);
        rooms.add(violetRooms);
        rooms.add(greyRoom);
        rooms.add(yellowRoom);
    }

    private static void setMap3 (){
        squares[0][0].setSquareAdjacent(null, squares[0][1], squares[1][0],null);
        squares[0][1].setSquareAdjacent(null, squares[0][2], null, squares[0][0]);
        squares[0][2].setSquareAdjacent(null, null, squares[1][2], squares[0][1]);
        squares[0][3]=null; // stanza non disponibile

        squares[1][0].setSquareAdjacent(squares[0][0], squares[1][1], null,null);
        squares[1][1].setSquareAdjacent(null, squares[1][2], squares[2][1], squares[1][0]);
        squares[1][2].setSquareAdjacent(squares[0][2], squares[1][3], null, squares[1][1]);
        squares[1][3].setSquareAdjacent(null,null,squares[2][3], squares[1][2]);

        squares[2][0]=null; // stanza non disponibile
        squares[2][1].setSquareAdjacent(squares[1][1], squares[2][2], null, null);
        squares[2][2].setSquareAdjacent(null, squares[2][3], null, squares[2][1]);
        squares[2][3].setSquareAdjacent(squares[1][3],null,null, squares[2][2]);

    }

    private static void setRooms3 (){
        Room bluRooms = new Room(ColorRoom.BLUE);
        bluRooms.addSquare(squares[0][0]);
        bluRooms.addSquare(squares[0][1]);
        bluRooms.addSquare(squares[0][2]);

        Room redRooms = new Room(ColorRoom.RED);
        redRooms.addSquare(squares[1][0]);
        redRooms.addSquare(squares[1][1]);
        redRooms.addSquare(squares[1][2]);

        Room greyRoom = new Room(ColorRoom.GREY);
        greyRoom.addSquare(squares[2][1]);
        greyRoom.addSquare(squares[2][2]);

        Room yellowRoom = new Room(ColorRoom.YELLOW);
        yellowRoom.addSquare(squares[1][3]);
        yellowRoom.addSquare(squares[2][3]);

        rooms.add(bluRooms);
        rooms.add(redRooms);
        rooms.add(greyRoom);
        rooms.add(yellowRoom);
    }

    private static void setMap4 (){
        squares[0][0].setSquareAdjacent(null, squares[0][1], squares[1][0], null);
        squares[0][1].setSquareAdjacent(null, squares[0][2], squares[1][1], squares[0][0]);
        squares[0][2].setSquareAdjacent(null, squares[0][3], squares[1][2], squares[0][1]);
        squares[0][3].setSquareAdjacent(null,null,squares[1][3], squares[0][2]);

        squares[1][0].setSquareAdjacent(squares[0][0], null, squares[2][0],null);
        squares[1][1].setSquareAdjacent(squares[0][1], null, squares[2][1], null);
        squares[1][2].setSquareAdjacent(squares[0][2], squares[1][3], squares[2][2], null);
        squares[1][3].setSquareAdjacent(squares[0][3],null,squares[2][3], squares[1][2]);

        squares[2][0].setSquareAdjacent(squares[1][0], squares[1][1], null,null);
        squares[2][1].setSquareAdjacent(squares[1][1], squares[2][2], null, squares[2][0]);
        squares[2][2].setSquareAdjacent(squares[1][2], squares[2][3], null, squares[2][1]);
        squares[2][3].setSquareAdjacent(squares[1][3],null,null, squares[2][2]);

    }

    private static void setRooms4 (){
        Room bluRooms = new Room(ColorRoom.BLUE);
        bluRooms.addSquare(squares[0][1]);
        bluRooms.addSquare(squares[0][2]);

        Room greenRooms = new Room(ColorRoom.GREEN);
        greenRooms.addSquare(squares[0][3]);

        Room redRooms = new Room(ColorRoom.RED);
        redRooms.addSquare(squares[0][0]);
        redRooms.addSquare(squares[1][0]);

        Room violetRooms = new Room(ColorRoom.VIOLET);
        violetRooms.addSquare(squares[1][1]);

        Room greyRoom = new Room(ColorRoom.GREY);
        greyRoom.addSquare(squares[2][0]);
        greyRoom.addSquare(squares[2][1]);

        Room yellowRoom = new Room(ColorRoom.YELLOW);
        yellowRoom.addSquare(squares[1][2]);
        yellowRoom.addSquare(squares[1][3]);
        yellowRoom.addSquare(squares[2][2]);
        yellowRoom.addSquare(squares[2][3]);

        rooms.add(bluRooms);
        rooms.add(greenRooms);
        rooms.add(redRooms);
        rooms.add(violetRooms);
        rooms.add(greyRoom);
        rooms.add(yellowRoom);
    }


}
