package it.polimi.isw2019.Model;

public class Player {
   private String name;
   private ColorPlayer color;
   private boolean selected;
   private int position;
   private boolean useFigure;

   public Player(String name, ColorPlayer color, boolean selected, int position, boolean useFigure) {
      this.name = name;
      this.color = color;
      this.selected = selected;
      this.position = position;
      this.useFigure = useFigure;
   }

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
   public int getPosition() {
      return this.position;
   }
}
