package code;

public class State {
    int prosperity;
    int food;
    int materials;
    int energy;
    int moneySpent;
    Resource orderedResources;
    int daysTillDelivery;

    public State(int prosperity, int food, int materials, int energy, int moneySpent,
            Resource orderedResources, int daysTillDelivery) {
        this.prosperity = prosperity;
        this.food = food;
        this.materials = materials;
        this.energy = energy;
        this.moneySpent = moneySpent;
        this.orderedResources = orderedResources;
        this.daysTillDelivery = daysTillDelivery;
    }

    public String toString() {
        return "Prosperity: " + prosperity +
                ", Food: " + food +
                ", Materials: " + materials +
                ", Energy: " + energy +
                ", Money Spent: " + moneySpent +
                ", Ordered Resources: " + orderedResources +
                ", Days Till Delivery: " + daysTillDelivery;
    }
}
