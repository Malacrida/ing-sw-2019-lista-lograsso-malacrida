package it.polimi.isw2019.Model;

import java.util.ArrayList;

public class Player {
   private String name;
   private ColorPlayer color;
   private ArrayList position;
   private  boolean selected;// a cosa serve ??
   private  boolean useFigure;
   private PlayerBoard playerBoard;

   //la figura può essere sostituita dal colore

   public Player(String name, ColorPlayer color, PlayerBoard playerBoard) {
      this.name = name;
      //il colore deve essere tolto dagli altri possibili colori
      this.color = color;
      this.playerBoard = playerBoard;
      //dovrebbe avere una dimensione fissa. La prima indica la riga la seconda indica la colonna
      position = new ArrayList(2);
   }

   //assumerei un metodo del model che assegna al player la sua figura, actionTile, playerBoard in base al COLORE che ha scelto.
   //la scelta dei colori deve essere SINCRONIZED : due persone NON possono scegliere lo stesso colore
   public Player getPlayer() {
      return this;
   }

   public String getName() {
      return this.name;
   }

   public ColorPlayer getColor(){
      return this.color;
   }

   public boolean getSelected() {
      return this.selected;
   }

   public boolean getUseFigure() {
      return this.useFigure;
   }

   public ArrayList getPosition() {
      return this.position;
   }
}
