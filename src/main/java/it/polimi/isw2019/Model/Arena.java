package it.polimi.isw2019.Model;

import java.util.ArrayList;

import static it.polimi.isw2019.Model.ColorRoom.*;

public class Arena {

    private static Arena istance;
   // private int numOfRoom;
    private Square[][] squares;
    private ArrayList<Room> rooms;

    Arena(){
        squares= new Square[3][4];
    }

    public Arena instanceArena (int numArena){
        if (istance==null) istance= new Arena();
        chooseArena(numArena);
        return istance;
    }

    private void chooseArena (int numArena){
        switch (numArena){
            case 1:
                setMap1();
                setRooms1();
                break;
            case 2:
                setMap2();
                setRooms2();
                break;
            case 3:
                setMap3();
                setRooms3();
                break;
            case 4:
                setMap4();
                setRooms4();
                break;
        }
    }



    private void setMap1 (){
        squares[0][0]= new SquareAmmo(null, squares[0][1], squares[1][0],null);
        squares[0][1]= new SquareAmmo(null, squares[0][2], null, squares[0][0]);
        squares[0][2]= new SquareSpawn(null, squares[0][3], squares[1][2], squares[0][1]);
        squares[0][3]= new SquareAmmo(null,null,squares[1][3], squares[0][2]);

        squares[1][0]= new SquareSpawn(squares[0][0], squares[1][1], null,null);
        squares[1][1]= new SquareAmmo(null, null, squares[2][1], squares[1][0]);
        squares[1][2]= new SquareAmmo(squares[0][2], squares[1][3], squares[2][2], null);
        squares[1][3]= new SquareAmmo(squares[0][3],null,squares[2][3], squares[1][2]);

        squares[2][0]= new SquareAmmo(null, null, null,null); //stanza non disponibile
        squares[2][1]= new SquareAmmo(squares[1][1], squares[2][2], null, null);
        squares[2][2]= new SquareAmmo(squares[1][2], squares[2][3], null, squares[2][1]);
        squares[2][3]= new SquareSpawn(squares[1][3],null,null, squares[2][2]);

    }

    private void setRooms1 (){
        Room bluRooms = new Room(BLUE);
        bluRooms.addSquere(squares[0][0]);
        bluRooms.addSquere(squares[0][1]);
        bluRooms.addSquere(squares[0][2]);

        Room greenRooms = new Room(GREEN);
        greenRooms.addSquere(squares[0][3]);

        Room redRooms = new Room(RED);
        redRooms.addSquere(squares[1][0]);
        redRooms.addSquere(squares[1][1]);

        Room greyRoom = new Room(GREY);
        greyRoom.addSquere(squares[2][1]);

        Room yellowRoom = new Room(YELLOW);
        yellowRoom.addSquere(squares[1][2]);
        yellowRoom.addSquere(squares[1][3]);
        yellowRoom.addSquere(squares[2][2]);
        yellowRoom.addSquere(squares[2][3]);

        rooms.add(bluRooms);
        rooms.add(greenRooms);
        rooms.add(redRooms);
        rooms.add(greyRoom);
        rooms.add(yellowRoom);
    }



    private void setMap2 (){
        squares[0][0]= new SquareAmmo(null, squares[0][1], squares[1][0],null);
        squares[0][1]= new SquareAmmo(null, squares[0][2], squares[1][1], squares[0][0]);
        squares[0][2]= new SquareSpawn(null, null, squares[1][2], squares[0][1]);
        squares[0][3]= new SquareAmmo(null,null,null, null);//stanza non disponibile

        squares[1][0]= new SquareSpawn(squares[0][0], null, squares[0][1],null);
        squares[1][1]= new SquareAmmo(squares[0][1], squares[1][2], squares[2][1], null);
        squares[1][2]= new SquareAmmo(squares[0][2], squares[1][3], null, squares[1][1]);
        squares[1][3]= new SquareAmmo(null,null,squares[2][3], squares[1][2]);

        squares[2][0]= new SquareAmmo(squares[1][0], squares[2][1], null,null);
        squares[2][1]= new SquareAmmo(squares[1][1], squares[2][2], null, squares[2][0]);
        squares[2][2]= new SquareAmmo(null, squares[2][3], null, squares[2][1]);
        squares[2][3]= new SquareSpawn(squares[1][3],null,null, squares[2][2]);

    }

    private void setRooms2 (){
        Room bluRooms = new Room(BLUE);
        bluRooms.addSquere(squares[0][1]);
        bluRooms.addSquere(squares[0][2]);

        Room redRooms = new Room(RED);
        redRooms.addSquere(squares[0][0]);
        redRooms.addSquere(squares[1][0]);

        Room violetRooms = new Room(VIOLET);
        violetRooms.addSquere(squares[1][1]);
        violetRooms.addSquere(squares[1][2]);

        Room greyRoom = new Room(GREY);
        greyRoom.addSquere(squares[2][0]);
        greyRoom.addSquere(squares[2][1]);
        greyRoom.addSquere(squares[2][2]);

        Room yellowRoom = new Room(YELLOW);
        yellowRoom.addSquere(squares[1][3]);
        yellowRoom.addSquere(squares[2][3]);

        rooms.add(bluRooms);
        rooms.add(redRooms);
        rooms.add(violetRooms);
        rooms.add(greyRoom);
        rooms.add(yellowRoom);
    }


    private void setMap3 (){
        squares[0][0]= new SquareAmmo(null, squares[0][1], squares[1][0],null);
        squares[0][1]= new SquareAmmo(null, squares[0][2], null, squares[0][0]);
        squares[0][2]= new SquareSpawn(null, null, squares[1][2], squares[0][1]);
        squares[0][3]= new SquareAmmo(null,null,null, null); // stanza non disponibile

        squares[1][0]= new SquareSpawn(squares[0][0], squares[1][1], null,null);
        squares[1][1]= new SquareAmmo(null, squares[1][2], squares[2][1], squares[1][0]);
        squares[1][2]= new SquareAmmo(squares[0][2], squares[1][3], null, squares[1][1]);
        squares[1][3]= new SquareAmmo(null,null,squares[2][3], squares[1][2]);

        squares[2][0]= new SquareAmmo(null, null, null,null); // stanza non disponibile
        squares[2][1]= new SquareAmmo(squares[1][1], squares[2][2], null, null);
        squares[2][2]= new SquareAmmo(null, squares[2][3], null, squares[2][1]);
        squares[2][3]= new SquareSpawn(squares[1][3],null,null, squares[2][2]);

    }

    private void setRooms3 (){
        Room bluRooms = new Room(BLUE);
        bluRooms.addSquere(squares[0][0]);
        bluRooms.addSquere(squares[0][1]);
        bluRooms.addSquere(squares[0][2]);

        Room redRooms = new Room(RED);
        redRooms.addSquere(squares[1][0]);
        redRooms.addSquere(squares[1][1]);
        redRooms.addSquere(squares[1][2]);

        Room greyRoom = new Room(GREY);
        greyRoom.addSquere(squares[2][1]);
        greyRoom.addSquere(squares[2][2]);

        Room yellowRoom = new Room(YELLOW);
        yellowRoom.addSquere(squares[1][3]);
        yellowRoom.addSquere(squares[2][3]);

        rooms.add(bluRooms);
        rooms.add(redRooms);
        rooms.add(greyRoom);
        rooms.add(yellowRoom);
    }

    private void setMap4 (){
        squares[0][0]= new SquareAmmo(null, squares[0][1], squares[1][0],null);
        squares[0][1]= new SquareAmmo(null, squares[0][2], squares[1][1], squares[0][0]);
        squares[0][2]= new SquareSpawn(null, squares[0][3], squares[1][2], squares[0][1]);
        squares[0][3]= new SquareAmmo(null,null,squares[1][3], squares[0][2]);

        squares[1][0]= new SquareSpawn(squares[0][0], null, squares[2][0],null);
        squares[1][1]= new SquareAmmo(squares[0][1], null, squares[2][1], null);
        squares[1][2]= new SquareAmmo(squares[0][2], squares[1][3], squares[2][2], null);
        squares[1][3]= new SquareAmmo(squares[0][3],null,squares[2][3], squares[1][2]);

        squares[2][0]= new SquareAmmo(squares[1][0], squares[1][1], null,null);
        squares[2][1]= new SquareAmmo(squares[1][1], squares[2][2], null, squares[2][0]);
        squares[2][2]= new SquareAmmo(squares[1][2], squares[2][3], null, squares[2][1]);
        squares[2][3]= new SquareSpawn(squares[1][3],null,null, squares[2][2]);

    }

    private void setRooms4 (){
        Room bluRooms = new Room(BLUE);
        bluRooms.addSquere(squares[0][1]);
        bluRooms.addSquere(squares[0][2]);

        Room greenRooms = new Room(GREEN);
        greenRooms.addSquere(squares[0][3]);

        Room redRooms = new Room(RED);
        redRooms.addSquere(squares[0][0]);
        redRooms.addSquere(squares[1][0]);

        Room violetRooms = new Room(VIOLET);
        violetRooms.addSquere(squares[1][1]);

        Room greyRoom = new Room(GREY);
        greyRoom.addSquere(squares[2][0]);
        greyRoom.addSquere(squares[2][1]);

        Room yellowRoom = new Room(YELLOW);
        yellowRoom.addSquere(squares[1][2]);
        yellowRoom.addSquere(squares[1][3]);
        yellowRoom.addSquere(squares[2][2]);
        yellowRoom.addSquere(squares[2][3]);

        rooms.add(bluRooms);
        rooms.add(greenRooms);
        rooms.add(redRooms);
        rooms.add(violetRooms);
        rooms.add(greyRoom);
        rooms.add(yellowRoom);
    }



}
