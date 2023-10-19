package code;
import java.util.*;

public class LLAPSearch extends GenericSearch{
    static Node root;
    static int unitPriceFood, unitPriceMaterials, unitPriceEnergy;
    static int amountRequestFood, delayRequestFood;
    static int amountRequestMaterials, delayRequestMaterials;
    static int amountRequestEnergy, delayRequestEnergy;
    static int priceBUILD1, foodUseBUILD1, materialsUseBUILD1, energyUseBUILD1, prosperityBUILD1;
    static int priceBUILD2, foodUseBUILD2, materialsUseBUILD2, energyUseBUILD2, prosperityBUILD2;

    // initialP rosperity;
    // initialF ood, initialM aterials, initialEnergy;
    // unitPriceF ood, unitPriceMaterials, unitPriceEnergy;
    // amountRequestF ood, delayRequestF ood;
    // amountRequestM aterials, delayRequestM aterials;
    // amountRequestEnergy, delayRequestEnergy;
    // priceBUILD1, foodUseBUILD1,
    // materialsUseBUILD1, energyUseBUILD1, prosperityBUILD1;
    // priceBUILD2, foodUseBUILD2,
    // materialsUseBUILD2, energyUseBUILD2, prosperityBUILD1;
    public static String search(String intialState, String strategy, boolean visualize) {
        // First, split the string using ';'
        List<String> semicolonSplit = Arrays.asList(intialState.split(";"));
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

        root = new Node(commaSplitList.get(0).get(0), commaSplitList.get(1).get(0), commaSplitList.get(1).get(1),
                commaSplitList.get(1).get(2), null, "");

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


        return "";
    }

    public static void main(String[] args) throws Exception {
        String init = "50;" +
                "22,22,22;" +
                "50,60,70;" +
                "30,2;19,1;15,1;" +
                "300,5,7,3,20;" +
                "500,8,6,3,40;";

        search(init, "BFS", true);
    }
}
