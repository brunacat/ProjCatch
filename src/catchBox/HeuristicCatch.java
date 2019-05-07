package catchBox;

import agentSearch.Heuristic;

public class HeuristicCatch extends Heuristic<CatchProblemSearch, CatchState> {

    @Override
    public double compute(CatchState state) {

        return state.compute(problem.getGoal().getLine(), problem.getGoal().getColumn());
    }

    @Override
    public String toString() {

        return "Distance to the goal";
    }
}