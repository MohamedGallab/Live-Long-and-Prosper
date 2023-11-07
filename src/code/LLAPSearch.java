package code;

import java.util.*;

public class LLAPSearch extends GenericSearch {
    int unitPriceFood, unitPriceMaterials, unitPriceEnergy;
    int amountRequestFood, delayRequestFood;
    int amountRequestMaterials, delayRequestMaterials;
    int amountRequestEnergy, delayRequestEnergy;
    int priceBUILD1, foodUseBUILD1, materialsUseBUILD1, energyUseBUILD1, prosperityBUILD1;
    int priceBUILD2, foodUseBUILD2, materialsUseBUILD2, energyUseBUILD2, prosperityBUILD2;

    public String solve(String intialState, String strategy, boolean visualize) {
        GeneralSearch(intialState, strategy);

        return "";
    }

    PriorityQueue<Node> createQueue(String strategy) {
        PriorityQueue<Node> pq = new PriorityQueue<Node>(new Comparator<Node>() {
            public int compare(Node n1, Node n2) {
                switch (strategy) {
                    case "BF":
                        return n1.depth - n2.depth;
                    case "DF":
                        return n2.depth - n1.depth;
                    case "ID":
                        return n2.depth - n1.depth;
                    case "UC":
                        return n1.moneySpent - n2.moneySpent;
                    case "GR":
                        return n2.prosperity - n1.prosperity;
                    case "GR2":
                        return n1.moneySpent - n2.moneySpent;
                    case "AR":
                        return 0;
                    case "AR2":
                        return 0;
                    default:
                        return 0;
                }
            }
        });

        return pq;
    }

    List<Node> expand(Node root) {
        List<Node> children = new ArrayList<>();

        children.add(requestEnergy(root));
        children.add(requestFood(root));
        children.add(requestMaterials(root));
        children.add(build1(root));
        children.add(build2(root));
        children.add(wait(root));

        return children;
    }

    Node requestFood(Node root) {
        if (root.daysTillDelivery > 0)
            return null;
        Node child = new Node(root.prosperity, root.food, root.materials, root.energy, root, Operator.RequestFood,
                root.depth + 1, Resource.Food, root.moneySpent, root.daysTillDelivery);
        child.hasRequested = true;
        child.daysTillDelivery = delayRequestFood;
        child.food--;
        child.materials--;
        child.energy--;
        child.moneySpent += (unitPriceFood + unitPriceMaterials + unitPriceEnergy);
        if (child.food < 0 || child.materials < 0 || child.energy < 0 || child.moneySpent > 100000)
            return null;
        return child;
    }

    Node requestMaterials(Node root) {
        if (root.daysTillDelivery > 0)
            return null;
        Node child = new Node(root.prosperity, root.food, root.materials, root.energy, root, Operator.RequestMaterials,
                root.depth + 1, Resource.Materials, root.moneySpent, root.daysTillDelivery);
        child.hasRequested = true;
        child.daysTillDelivery = delayRequestMaterials;
        child.food--;
        child.materials--;
        child.energy--;
        child.moneySpent += (unitPriceFood + unitPriceMaterials + unitPriceEnergy);
        if (child.food < 0 || child.materials < 0 || child.energy < 0 || child.moneySpent > 100000)
            return null;
        return child;
    }

    Node requestEnergy(Node root) {
        if (root.daysTillDelivery > 0)
            return null;
        Node child = new Node(root.prosperity, root.food, root.materials, root.energy, root, Operator.RequestEnergy,
                root.depth + 1, Resource.Energy, root.moneySpent, root.daysTillDelivery);
        child.hasRequested = true;
        child.daysTillDelivery = delayRequestEnergy;
        child.food--;
        child.materials--;
        child.energy--;
        child.moneySpent += (unitPriceFood + unitPriceMaterials + unitPriceEnergy);
        if (child.food < 0 || child.materials < 0 || child.energy < 0 || child.moneySpent > 100000)
            return null;
        return child;
    }

    void checkDeliveryArrived(Node child) {
        if (child.daysTillDelivery == 0) {
            switch (child.orderedResources) {
                case Resource.Food:
                    child.food += amountRequestFood;
                    break;
                case Resource.Materials:
                    child.materials += amountRequestMaterials;
                    break;
                case Resource.Energy:
                    child.energy += amountRequestEnergy;
                    break;
            }
        }
    }

    Node wait(Node root) {
        if (root.daysTillDelivery <= 0) {
            return null;
        }
        Node child = new Node(root.prosperity, root.food, root.materials, root.energy, root, Operator.Wait,
                root.depth + 1, root.orderedResources, root.moneySpent, root.daysTillDelivery - 1);
        // checkDeliveryArrived(child);
        child.food--;
        child.materials--;
        child.energy--;
        child.moneySpent += (unitPriceFood + unitPriceMaterials + unitPriceEnergy);
        if (child.food < 0 || child.materials < 0 || child.energy < 0 || child.moneySpent > 100000)
            return null;
        return child;
    }

    Node build1(Node root) {
        Node child = new Node(root.prosperity, root.food, root.materials, root.energy, root, Operator.Build1,
                root.depth + 1, root.orderedResources, root.moneySpent, root.daysTillDelivery - 1);
        checkDeliveryArrived(child);
        child.food -= foodUseBUILD1;
        child.materials -= materialsUseBUILD1;
        child.energy -= energyUseBUILD1;
        child.prosperity += prosperityBUILD1;
        child.moneySpent += (foodUseBUILD1 * unitPriceFood + materialsUseBUILD1 * unitPriceMaterials
                + energyUseBUILD1 * unitPriceEnergy + priceBUILD1);
        if (child.food < 0 || child.materials < 0 || child.energy < 0 || child.moneySpent > 100000)
            return null;
        return child;
    }

    Node build2(Node root) {
        Node child = new Node(root.prosperity, root.food, root.materials, root.energy, root, Operator.Build2,
                root.depth + 1, root.orderedResources, root.moneySpent, root.daysTillDelivery - 1);
        checkDeliveryArrived(child);
        child.food -= foodUseBUILD2;
        child.materials -= materialsUseBUILD2;
        child.energy -= energyUseBUILD2;
        child.prosperity += prosperityBUILD2;
        child.moneySpent += (foodUseBUILD2 * unitPriceFood + materialsUseBUILD2 * unitPriceMaterials
                + energyUseBUILD2 * unitPriceEnergy + priceBUILD2);
        if (child.food < 0 || child.materials < 0 || child.energy < 0 || child.moneySpent > 100000)
            return null;
        return child;
    }

    Node init(String initialState) {
        List<String> semicolonSplit = Arrays.asList(initialState.split(";"));
        List<List<Integer>> commaSplitList = new ArrayList<>();

        for (String semicolonPart : semicolonSplit) {
            // Second, split each part using ','
            List<String> commaSplit = Arrays.asList(semicolonPart.split(","));
            List<Integer> tmp = new ArrayList<>();
            for (String string : commaSplit) {
                tmp.add(Integer.parseInt(string));
            }

            commaSplitList.add(tmp);
        }

        Node root = new Node(commaSplitList.get(0).get(0), commaSplitList.get(1).get(0), commaSplitList.get(1).get(1),
                commaSplitList.get(1).get(2), null, null, 0, null, 0, -1);

        unitPriceFood = commaSplitList.get(2).get(0);
        unitPriceMaterials = commaSplitList.get(2).get(1);
        unitPriceEnergy = commaSplitList.get(2).get(2);
        amountRequestFood = commaSplitList.get(3).get(0);
        delayRequestFood = commaSplitList.get(3).get(1);
        amountRequestMaterials = commaSplitList.get(4).get(0);
        delayRequestMaterials = commaSplitList.get(4).get(1);
        amountRequestEnergy = commaSplitList.get(5).get(0);
        delayRequestEnergy = commaSplitList.get(5).get(1);
        priceBUILD1 = commaSplitList.get(6).get(0);
        foodUseBUILD1 = commaSplitList.get(6).get(1);
        materialsUseBUILD1 = commaSplitList.get(6).get(2);
        energyUseBUILD1 = commaSplitList.get(6).get(3);
        prosperityBUILD1 = commaSplitList.get(6).get(4);
        priceBUILD2 = commaSplitList.get(7).get(0);
        foodUseBUILD2 = commaSplitList.get(7).get(1);
        materialsUseBUILD2 = commaSplitList.get(7).get(2);
        energyUseBUILD2 = commaSplitList.get(7).get(3);
        prosperityBUILD2 = commaSplitList.get(7).get(4);

        return root;
    }

    public static void main(String[] args) throws Exception {
        String init = "50;" +
                "22,22,22;" +
                "50,60,70;" +
                "30,2;19,1;15,1;" +
                "300,5,7,3,20;" +
                "500,8,6,3,40;";
        new LLAPSearch().solve(init, "BF", true);
    }
}
