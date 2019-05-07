package catchBox;

import java.awt.*;
import java.util.Arrays;
import java.util.ArrayList;
import agentSearch.State;
import agentSearch.Action;

public class CatchState extends State implements Cloneable {

    private final ArrayList<EnvironmentListener> listeners;
    private int posLine, posColumn;
  //  private int goalLine, goalColumn;
    private int stepCount;
    protected int[][] matrix;

    public CatchState(int[][] matrix) {

        this.listeners = new ArrayList<>();

        this.posLine = -1;
        this.posColumn = -1;
        this.stepCount = 0;

        this.matrix = new int[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix.length; j++) {

                this.matrix[i][j] = matrix[i][j];

                if (matrix[i][j] == Properties.CATCH) {

                    posLine = i;
                    posColumn = j;
                }
            }
        }
    }

    public void executeAction(Action action) {

        action.execute(this);

        this.stepCount++;

        fireUpdatedEnvironment();
    }

    public boolean canMoveUp() {

        return this.posLine != 0 && this.matrix[posLine-1][posColumn]!= Properties.WALL;
    }

    public boolean canMoveRight() {

        return this.posColumn != this.matrix.length - 1&& this.matrix[posLine][posColumn+1]!= Properties.WALL;
    }

    public boolean canMoveDown() {
        System.out.println(this.posLine != this.matrix.length - 1);

        return this.posLine != this.matrix.length - 1&& this.matrix[posLine+1][posColumn]!= Properties.WALL;
    }

    public boolean canMoveLeft() {


        return this.posColumn != 0 && this.matrix[posLine][posColumn-1]!= Properties.WALL;


    }

    public void moveUp() {
        this.matrix[posLine][posColumn] = Properties.EMPTY;
        this.matrix[--posLine][posColumn] =  Properties.CATCH;
    }

    public void moveRight() {

        this.matrix[posLine][posColumn] = Properties.EMPTY;
        this.matrix[posLine][++posColumn] =  Properties.CATCH;
    }

    public void moveDown() {

        this.matrix[posLine][posColumn] = Properties.EMPTY;
        this.matrix[++posLine][posColumn] =  Properties.CATCH;
    }

    public void moveLeft() {

        System.out.println(posLine+" "+posColumn);
        this.matrix[posLine][posColumn] = Properties.EMPTY;
        this.matrix[posLine][--posColumn] =  Properties.CATCH;
    }

    public int getNumBox() {

        int num = 0;

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix.length; j++) {

                if (this.matrix[i][j] == Properties.BOX) {

                    num++;
                }
            }
        }

        return num;
    }

    public void setCellCatch(int line, int column) {
        this.matrix[posLine][posColumn] = Properties.EMPTY;
        this.posLine = line;
        this.posColumn = column;
        this.matrix[line][column] = Properties.CATCH;
    }

    public int[][] getMatrix() {

        return matrix;
    }

    public void setGoal(int line, int column) {

        this.matrix[line][column] = Properties.DOOR;
    }

    public int getSteps() {

        return this.stepCount;
    }

    public int getSize() {

        return matrix.length;
    }


    public double compute(int goalLine, int goalColumn){
        int line = Math.abs(goalLine-posLine);
        int column = Math.abs(goalColumn-posColumn);

        return (line+column);
    }
    public Color getCellColor(int line, int column) {

        switch (matrix[line][column]) {

            case Properties.BOX:
                return Properties.COLORBOX;

            case Properties.CATCH:
                return Properties.COLORCATCH;

            case Properties.DOOR:
                return Properties.COLORDOOR;

            case Properties.WALL:
                return Properties.COLORWALL;

            default:
                return Properties.COLOREMPTY;
        }
    }

    public int getPosLine() {
        return posLine;
    }

    public int getPosColumn() {
        return posColumn;
    }

    @Override
    public boolean equals(Object other) {

        if (!(other instanceof CatchState)) {

            return false;
        }


        CatchState o = (CatchState) other;

        if (matrix.length != o.matrix.length) {

            return false;
        }

        return Arrays.deepEquals(matrix, o.matrix);
    }

    @Override
    public int hashCode() {

        return 97 * 7 + Arrays.deepHashCode(this.matrix);
    }

    @Override
    public String toString() {

        StringBuilder buffer = new StringBuilder();
        buffer.append(matrix.length);

        for (int i = 0; i < matrix.length; i++) {

            buffer.append('\n');

            for (int j = 0; j < matrix.length; j++) {

                buffer.append(matrix[i][j]);
                buffer.append(' ');
            }
        }

        return buffer.toString();
    }

    @Override
    public CatchState clone() {

        return new CatchState(this.matrix);
    }

    public synchronized void addEnvironmentListener(EnvironmentListener l) {

        if (!listeners.contains(l)) {

            listeners.add(l);
        }
    }

    public synchronized void removeEnvironmentListener(EnvironmentListener l) {

        listeners.remove(l);
    }

    public void fireUpdatedEnvironment() {

        for (EnvironmentListener listener : listeners) {

            listener.environmentUpdated();
        }
    }
}