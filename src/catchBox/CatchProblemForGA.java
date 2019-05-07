package catchBox;

import ga.Problem;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.LinkedList;

public class CatchProblemForGA implements Problem<CatchIndividual> {
    //TODO this class might require the definition of additional methods and/or attributes
    public LinkedList<Cell> cellsBoxes;
    public LinkedList<Pair> pairs;
    public Cell cellCatch;
    public Cell door;

    public CatchProblemForGA(LinkedList<Cell> cellsBoxes, LinkedList<Pair> pairs, Cell cellCatch, Cell door) {
        this.cellsBoxes = cellsBoxes;
        this.pairs = pairs;
        this.cellCatch = cellCatch;
        this.door = door;
    }

    @Override
    public CatchIndividual getNewIndividual() {
        //TODO
        return new CatchIndividual(this, cellsBoxes.size());
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("# of cells: ");
        sb.append(cellsBoxes.size());
        sb.append("\n");
        sb.append("Pairs:");
        sb.append(pairs.toString());
        return sb.toString();
    }

    public LinkedList<Pair> getPairs() {
        return pairs;
    }
}
