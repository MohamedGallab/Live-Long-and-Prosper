package code;

import java.util.*;

/**
 * Search
 */
public class Search {

    public static boolean isUnique(State state, HashSet<State> visited) {
        return visited.add(state);
    }

    public static Node GeneralSearch(GenericSearch problem, String strategy) {
        if (strategy == "ID") {
            // int maxDepth = 0;
            for (int i = 0;; i++) {
                HashSet<State> visited = new HashSet<>();
                Node root = new Node(problem.initialState, null, null, 0);

                var nodes = problem.createQueue(strategy);

                nodes.add(root);
                isUnique(root.state, visited);

                while (!nodes.isEmpty()) {
                    Node node = nodes.poll();
                    if (problem.isGoal(node)) {
                        return node;
                    }
                    List<Node> children = problem.expand(node);

                    for (Node child : children) {
                        if (child != null && isUnique(child.state, visited) && child.depth <= i) {
                            nodes.offer(child);
                            // maxDepth = child.depth > maxDepth ? child.depth : maxDepth;
                        }
                    }
                }

                // if(maxDepth < i){
                //     break;
                // }
            }
            // return null;
        }

        HashSet<State> visited = new HashSet<>();
        Node root = new Node(problem.initialState, null, null, 0);

        var nodes = problem.createQueue(strategy);

        nodes.add(root);
        isUnique(root.state, visited);

        while (!nodes.isEmpty()) {
            Node node = nodes.poll();
            if (problem.isGoal(node)) {
                return node;
            }
            List<Node> children = problem.expand(node);

            for (Node child : children) {
                if (child != null && isUnique(child.state, visited)) {
                    nodes.offer(child);
                }
            }
        }
        return null;
    }
}