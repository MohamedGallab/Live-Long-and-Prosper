package code;

public class Node {
    State state;
    Node parent;
    LLAPSearch.Operator operator;
    int depth;

    Node(int prosperity, int food, int materials, int energy, Node parent, LLAPSearch.Operator operator, int depth,
            Resource orderedResources, int moneySpent, int daysTillDelivery) {
        this.state = new State(prosperity, food, materials, energy, moneySpent, orderedResources,
                daysTillDelivery);
        this.depth = depth;
        this.operator = operator;
        this.parent = parent;
    }

    Node(Node node) {
        this.state = new State(node.state.prosperity, node.state.food, node.state.materials, node.state.energy,
                node.state.moneySpent, node.state.orderedResources,
                node.state.daysTillDelivery);
        this.depth = node.depth;
        this.parent = node;
    }

    Node(State state, Node parent, LLAPSearch.Operator operator, int depth) {
        this.state = state;
        this.depth = depth;
        this.operator = operator;
        this.parent = parent;
    }

    public String toString() {
        return "Prosperity: " + state.prosperity +
                ", Food: " + state.food +
                ", Materials: " + state.materials +
                ", Energy: " + state.energy +
                ", Money Spent: " + state.moneySpent +
                ", Ordered Resources: " + state.orderedResources +
                ", Days Till Delivery: " + state.daysTillDelivery;
    }
}
