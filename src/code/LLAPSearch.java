package code;

import java.util.*;

public class LLAPSearch extends GenericSearch {

    public static void main(String[] args) throws Exception {
        String init = "32;" +
                "20,16,11;" +
                "76,14,14;" +
                "9,1;9,2;9,1;" +
                "358,14,25,23,39;" +
                "5024,20,17,17,38;";

        System.out.println("ANSWER: ");
        String answer = solve(init, "ID", true);
        System.out.println(answer);
    }

    int unitPriceFood, unitPriceMaterials, unitPriceEnergy;
    int amountRequestFood, delayRequestFood;
    int amountRequestMaterials, delayRequestMaterials;
    int amountRequestEnergy, delayRequestEnergy;
    int priceBUILD1, foodUseBUILD1, materialsUseBUILD1, energyUseBUILD1, prosperityBUILD1;
    int priceBUILD2, foodUseBUILD2, materialsUseBUILD2, energyUseBUILD2, prosperityBUILD2;
    int expandedNodes = 0;

    public enum Operator {
        RequestFood, RequestMaterials, RequestEnergy, Build1, Build2, Wait;
    }

    LLAPSearch(String initialState) {
        this.initialState = init(initialState);
    }

    public static String solve(String initialState, String strategy, boolean visualize) {
        LLAPSearch problem = new LLAPSearch(initialState);

        // Node goalNode = agent.GeneralSearch(strategy);
        Node goalNode = Search.GeneralSearch(problem, strategy);

        return problem.buildAnswer(goalNode, visualize);
    }

    String buildAnswer(Node node, boolean visualize) {
        if (node == null) {
            return "NOSOLUTION";
        }

        String answer = ";" + node.state.moneySpent + ";" + expandedNodes;

        while (node.parent != null) {
            if (visualize) {
                System.out.println(node);
            }
            answer = node.operator + ", " + answer;
            node = node.parent;
        }

        return answer;
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
                        return costFunction(n1) - costFunction(n2);
                    case "GR1":
                        return greedyHeuristic1(n1) - greedyHeuristic1(n2);
                    case "GR2":
                        return greedyHeuristic2(n1) - greedyHeuristic2(n2);
                    case "AS1":
                        return (costFunction(n1) + arHeuristic1(n1)) - (costFunction(n2) + arHeuristic1(n2));
                    case "AS2":
                        return (costFunction(n1) + arHeuristic2(n1)) - (costFunction(n2) + arHeuristic2(n2));
                    default:
                        throw new IllegalArgumentException("Invalid strategy: " + strategy);
                }
            }
        });

        return pq;
    }

    boolean isGoal(code.Node node) {
        return node.state.prosperity >= 100;
    }

    int costFunction(Node node) {
        return node.state.moneySpent;
    }

    List<Node> expand(Node root) {
        List<Node> children = new ArrayList<>();

        children.add(build1(root));
        children.add(build2(root));
        children.add(wait(root));
        children.add(requestFood(root));
        children.add(requestMaterials(root));
        children.add(requestEnergy(root));

        expandedNodes++;

        return children;
    }

    Node requestFood(Node root) {
        if (root.state.daysTillDelivery > 0)
            return null;

        Node child = new Node(root);

        child.state.daysTillDelivery = delayRequestFood;
        child.state.orderedResources = Resource.Food;
        child.operator = Operator.RequestFood;
        child.depth++;
        child.state.food--;
        child.state.materials--;
        child.state.energy--;

        child.state.moneySpent += (unitPriceFood + unitPriceMaterials + unitPriceEnergy);

        if (child.state.food < 0 || child.state.materials < 0 || child.state.energy < 0
                || child.state.moneySpent > 100000)
            return null;
        return child;
    }

    Node requestMaterials(Node root) {
        if (root.state.daysTillDelivery > 0)
            return null;

        Node child = new Node(root);

        child.state.daysTillDelivery = delayRequestMaterials;
        child.operator = Operator.RequestMaterials;
        child.state.orderedResources = Resource.Materials;
        child.depth++;
        child.state.food--;
        child.state.materials--;
        child.state.energy--;
        child.state.moneySpent += (unitPriceFood + unitPriceMaterials + unitPriceEnergy);

        if (child.state.food < 0 || child.state.materials < 0 || child.state.energy < 0
                || child.state.moneySpent > 100000)
            return null;
        return child;
    }

    Node requestEnergy(Node root) {
        if (root.state.daysTillDelivery > 0)
            return null;

        Node child = new Node(root);

        child.state.daysTillDelivery = delayRequestEnergy;
        child.operator = Operator.RequestEnergy;
        child.state.orderedResources = Resource.Energy;
        child.depth++;
        child.state.food--;
        child.state.materials--;
        child.state.energy--;
        child.state.moneySpent += (unitPriceFood + unitPriceMaterials + unitPriceEnergy);

        if (child.state.food < 0 || child.state.materials < 0 || child.state.energy < 0
                || child.state.moneySpent > 100000)
            return null;
        return child;
    }

    void checkDeliveryArrived(Node root) {
        if (root.state.orderedResources == null)
            return;
        if (root.state.daysTillDelivery == 0) {
            switch (root.state.orderedResources) {
                case Food:
                    root.state.food += amountRequestFood;
                    root.state.food = root.state.food < 50 ? root.state.food : 50;
                    break;
                case Materials:
                    root.state.materials += amountRequestMaterials;
                    root.state.materials = root.state.materials < 50 ? root.state.materials : 50;
                    break;
                case Energy:
                    root.state.energy += amountRequestEnergy;
                    root.state.energy = root.state.energy < 50 ? root.state.energy : 50;
                    break;
            }
            root.state.orderedResources = null;
        }
        if (root.state.daysTillDelivery < 0)
            root.state.daysTillDelivery = -1;
    }

    Node wait(Node root) {
        Node child = new Node(root);

        if (root.state.daysTillDelivery <= 0) {
            return null;
        }

        child.operator = Operator.Wait;
        child.depth++;
        child.state.daysTillDelivery--;
        checkDeliveryArrived(child);
        child.state.food--;
        child.state.materials--;
        child.state.energy--;
        child.state.moneySpent += (unitPriceFood + unitPriceMaterials + unitPriceEnergy);

        if (child.state.food < 0 || child.state.materials < 0 || child.state.energy < 0
                || child.state.moneySpent > 100000)
            return null;
        return child;
    }

    Node build1(Node root) {
        Node child = new Node(root);

        child.depth++;
        child.operator = Operator.Build1;
        child.state.daysTillDelivery--;
        checkDeliveryArrived(child);
        child.state.food -= foodUseBUILD1;
        child.state.materials -= materialsUseBUILD1;
        child.state.energy -= energyUseBUILD1;
        child.state.prosperity += prosperityBUILD1;
        child.state.moneySpent += (foodUseBUILD1 * unitPriceFood + materialsUseBUILD1 * unitPriceMaterials
                + energyUseBUILD1 * unitPriceEnergy + priceBUILD1);

        if (child.state.food < 0 || child.state.materials < 0 || child.state.energy < 0
                || child.state.moneySpent > 100000)
            return null;
        return child;
    }

    Node build2(Node root) {
        Node child = new Node(root);

        child.depth++;
        child.operator = Operator.Build2;
        child.state.daysTillDelivery--;
        checkDeliveryArrived(child);
        child.state.food -= foodUseBUILD2;
        child.state.materials -= materialsUseBUILD2;
        child.state.energy -= energyUseBUILD2;
        child.state.prosperity += prosperityBUILD2;
        child.state.moneySpent += (foodUseBUILD2 * unitPriceFood + materialsUseBUILD2 * unitPriceMaterials
                + energyUseBUILD2 * unitPriceEnergy + priceBUILD2);
        if (child.state.food < 0 || child.state.materials < 0 || child.state.energy < 0
                || child.state.moneySpent > 100000)
            return null;
        return child;
    }

    State init(String initialState) {
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

        return root.state;
    }

    int greedyHeuristic1(Node node) {
        return 100 - node.state.prosperity;
    }

    int greedyHeuristic2(Node node) {
        int prosperityBuild = prosperityBUILD1 > prosperityBUILD2 ? prosperityBUILD1 : prosperityBUILD2;
        int priceBuild = priceBUILD1 < priceBUILD2 ? priceBUILD1 : priceBUILD2;
        int moneyToSpend = (100 - node.state.prosperity) / prosperityBuild * priceBuild;
        return moneyToSpend;
    }

    int arHeuristic1(Node node) {
        int prosperityBuild = prosperityBUILD1 > prosperityBUILD2 ? prosperityBUILD1 : prosperityBUILD2;
        int priceBuild = priceBUILD1 < priceBUILD2 ? priceBUILD1 : priceBUILD2;
        int moneyToSpend = (100 - node.state.prosperity) / prosperityBuild * priceBuild;
        return moneyToSpend;
    }

    int arHeuristic2(Node node) {
        int prosperityBuild = prosperityBUILD1 > prosperityBUILD2 ? prosperityBUILD1 : prosperityBUILD2;
        int priceBuild = priceBUILD1 < priceBUILD2 ? priceBUILD1 : priceBUILD2;
        int materialBuild = materialsUseBUILD1 < materialsUseBUILD2 ? materialsUseBUILD1 : materialsUseBUILD2;
        int foodBuild = foodUseBUILD1 < foodUseBUILD2 ? foodUseBUILD1 : foodUseBUILD2;
        int energyBuild = energyUseBUILD1 < energyUseBUILD2 ? energyUseBUILD1 : energyUseBUILD2;

        int buildsRequired = (100 - node.state.prosperity) / prosperityBuild;
        int foodCost = (buildsRequired * foodBuild - 50) * unitPriceFood;
        int materialCost = (buildsRequired * materialBuild - 50) * unitPriceMaterials;
        int energyCost = (buildsRequired * energyBuild - 50) * unitPriceEnergy;

        int moneyToSpend = foodCost + materialCost + energyCost + buildsRequired * priceBuild;
        return moneyToSpend;
    }

}
