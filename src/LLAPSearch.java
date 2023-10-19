import java.util.*;

public class LLAPSearch {
    Node root;
    int unitPriceFood, unitPriceMaterials, unitPriceEnergy;
    int amountRequestFood, delayRequestFood;
    int amountRequestMaterials, delayRequestMaterials;
    int amountRequestEnergy, delayRequestEnergy;
    int priceBUILD1, foodUseBUILD1, materialsUseBUILD1, energyUseBUILD1, prosperityBUILD1;
    int priceBUILD2, foodUseBUILD2, materialsUseBUILD2, energyUseBUILD2, prosperityBUILD2;

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
    void solve(String intialState, String strategy, boolean visualize) {
        List<List<String>> result = new ArrayList<>();

        // First, split the string using ';'
        String[] semicolonSplit = input.split(";");
        for (String semicolonPart : semicolonSplit) {
            List<String> commaSplitList = new ArrayList<>();
            // Second, split each part using ','
            String[] commaSplit = semicolonPart.split(",");
            for (String commaPart : commaSplit) {
                commaSplitList.add(commaPart);
            }
            result.add(commaSplitList);
        }

        root = new Node(semicolonSplit[0], amountRequestFood, amountRequestMaterials, delayRequestEnergy,
                amountRequestEnergy);

    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
