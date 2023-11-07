package code;

public class Node {
    int prosperity;
    int food;
    int materials;
    int energy;
    int moneySpent;
    Node parent;
    Operator operator;
    Boolean hasRequested = false;
    int daysTillDelivery;
    Resource orderedResources;
    int depth;

    Node(int prosperity, int food, int materials, int energy, Node parent, Operator operator, int depth,
            Resource orderedResources, int moneySpent, int daysTillDelivery) {
        this.orderedResources = orderedResources;
        this.prosperity = prosperity;
        this.food = food;
        this.materials = materials;
        this.energy = energy;
        this.depth = depth;
        this.operator = operator;
        this.parent = parent;
        this.moneySpent = moneySpent;
        this.daysTillDelivery = daysTillDelivery;
    }
}
