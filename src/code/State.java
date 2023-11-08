package code;

public class State {
    int prosperity;
    int food;
    int materials;
    int energy;
    int moneySpent;
    Boolean hasRequested = false;
    Resource orderedResources;
    int daysTillDelivery;

    public State(int prosperity, int food, int materials, int energy, int moneySpent, Boolean hasRequested,
            Resource orderedResources, int daysTillDelivery) {
        this.prosperity = prosperity;
        this.food = food;
        this.materials = materials;
        this.energy = energy;
        this.moneySpent = moneySpent;
        this.hasRequested = hasRequested;
        this.orderedResources = orderedResources;
        this.daysTillDelivery = daysTillDelivery;
    }
}
