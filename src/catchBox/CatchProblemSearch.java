package catchBox;

import agentSearch.Action;
import agentSearch.Problem;


import java.util.LinkedList;
import java.util.List;

public class CatchProblemSearch<S extends CatchState> extends Problem<S> {


    private Cell goal;
    private List<Action> actions;

    public CatchProblemSearch(S initialCatchState, Cell goalPosition) {
        super(initialCatchState);

       actions = new LinkedList<Action>() {{
           add(new ActionUp());
           add(new ActionDown());
           add(new ActionLeft());
           add(new ActionRight());
       }};
       this.goal = goalPosition;
    }

    public Cell getGoal() {
        return goal;
    }

    @Override
    public List<S> executeActions(S state) {

        List<S> successors = new LinkedList<>();

        for (Action action : actions) {
            if (action.isValid(state)) {
                S successor = (S) state.clone();
                action.execute(successor);
                successors.add(successor);
            }
        }
        return successors;
    }

    public boolean isGoal(S state) {
        return state.getPosLine()==goal.getLine() && state.getPosColumn()==goal.getColumn();
    }
}
