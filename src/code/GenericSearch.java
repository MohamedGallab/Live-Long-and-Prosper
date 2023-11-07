package code;

import java.util.List;
import java.util.PriorityQueue;

abstract class GenericSearch {
    abstract Node init(String initialState);

    abstract PriorityQueue<Node> createQueue(String strategy);

    abstract List<Node> expand(Node root);

    String GeneralSearch(String initialState, String strategy) {
        Node root = init(initialState);

        var nodes = createQueue(strategy);

        nodes.add(root);

        while (!nodes.isEmpty()) {
            Node node = nodes.poll();

            if (node.prosperity == 100) {
                return "success";
            }

            List<Node> children = expand(node);

            for (Node child : children) {
                if (child != null) {
                    nodes.offer(child);
                }
            }
        }
        return "failure";
    }
}
