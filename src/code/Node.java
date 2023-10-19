package code;
public class Node {
    int prosperity;
    int food;
    int materials;
    int energy;
    int moneySpent = 0;
    Node parent;
    String operator;
    Node(int prosperity, int food, int materials, int energy, Node parent, String operator) {
        this.prosperity = prosperity;
        this.food = food;
        this.materials = materials;
        this.energy = energy;
    }
}
