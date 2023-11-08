package code;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

abstract class GenericSearch {
    enum Operator {
    }

    GenericSearch(String initialState) {
        this.initialState = init(initialState);
    }

    State initialState;

    HashSet<State> visited = new HashSet<>();

    abstract State init(String initialState);

    abstract PriorityQueue<Node> createQueue(String strategy);

    abstract List<Node> expand(Node root);

    abstract boolean isGoal(Node node);

    abstract int costFunction(Node node);

    abstract boolean isUnique(State state);

    Node GeneralSearch(String strategy) {
        Node root = new Node(initialState, null, null, 0);

        var nodes = createQueue(strategy);

        nodes.add(root);
        isUnique(root.state);

        while (!nodes.isEmpty()) {
            Node node = nodes.poll();
            if (isGoal(node)) {
                return node;
            }
            List<Node> children = expand(node);
            
            for (Node child : children) {
                if (child != null && isUnique(child.state)) {
                    nodes.offer(child);
                    // System.out.println(child.state);
                }
            }
        }
        return null;
    }
}
