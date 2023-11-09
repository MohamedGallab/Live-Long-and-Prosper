package code;

import java.util.*;

abstract class GenericSearch {
    enum Operator {
    }

    State initialState;

    abstract State init(String initialState);

    abstract PriorityQueue<Node> createQueue(String strategy);

    abstract List<Node> expand(Node root);

    abstract boolean isGoal(Node node);

    abstract int costFunction(Node node);
}
