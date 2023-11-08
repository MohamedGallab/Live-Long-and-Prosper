package code;

import java.util.List;
import java.util.PriorityQueue;

abstract class GenericSearch {
    enum Operator {
    }

    GenericSearch(String initialState) {
        this.initialState = init(initialState);
    }

    State initialState;

    abstract State init(String initialState);

    abstract PriorityQueue<Node> createQueue(String strategy);

    abstract List<Node> expand(Node root);

    abstract boolean isGoal(Node node);

    abstract int costFunction(Node node);

    Node GeneralSearch(String strategy) {
        Node root = new Node(initialState, null, null, 0);

        var nodes = createQueue(strategy);

        nodes.add(root);

        while (!nodes.isEmpty()) {
            Node node = nodes.poll();

            if (isGoal(node)) {
                return node;
            }

            List<Node> children = expand(node);

            for (Node child : children) {
                if (child != null) {
                    nodes.offer(child);
                }
            }
        }
        return null;
    }
}
