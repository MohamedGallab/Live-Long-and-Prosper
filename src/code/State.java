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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        State otherState = (State) obj;
        return prosperity == otherState.prosperity &&
                food == otherState.food &&
                materials == otherState.materials &&
                energy == otherState.energy &&
                moneySpent == otherState.moneySpent &&
                daysTillDelivery == otherState.daysTillDelivery &&
                (orderedResources == null ? otherState.orderedResources == null
                        : orderedResources.equals(otherState.orderedResources));
    }

    @Override
    public int hashCode() {
        int result = prosperity;
        result = 31 * result + food;
        result = 31 * result + materials;
        result = 31 * result + energy;
        result = 31 * result + moneySpent;
        result = 31 * result + (orderedResources == null ? 0 : orderedResources.hashCode());
        result = 31 * result + daysTillDelivery;
        return result;
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
