package it.polimi.isw2019.View;
import it.polimi.isw2019.Message.PlayerMove.*;
public abstract class ViewCLIAction {
    private String idAction;
    private String idPlayerMove;
    private char status;
    private int [][] matrix;
    private int maxMovement;

    public ViewCLIAction(String idPlayerMove, String idAction){
        this.idPlayerMove = idPlayerMove;

    }

    public abstract PlayerMove handleAction(ViewCLI view);

    public String getIdAction(){
        return this.idAction;
    }

    public char getStatus(){
        return status;
    }

    public String getIdPlayerMove(){
        return idPlayerMove;
    }

    public void setMatrix(int [][]matrix){
        this.matrix = matrix;
    }
    public int[][] getMatrix(){
        return this.matrix;
    }
    public void setMovement(int rawMatrix, int columnMatrix, int rawGameBoard, int columnGameBoard){
        this.matrix[rawMatrix][columnMatrix] = rawGameBoard;
        this.matrix[rawMatrix][columnGameBoard+1] = columnGameBoard;
    }

    public void setMaxMovement(int maxMovement){
        this.maxMovement = maxMovement;
    }

    public int getMaxMovement(){
        return this.maxMovement;
    }
}
