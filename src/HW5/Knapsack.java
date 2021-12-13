package HW5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Knapsack {

    private static final Item[] array = {
            new Item("Сапог", 2, 150),
            new Item("Газета", 1, 100),
            new Item("Кастрюля", 3, 200),
            new Item("Утюг", 4, 350),
            new Item("Чашка", 3, 250),
            new Item("Зонтик", 2, 175),
    };

    private final int VOLUME = 6;
    private int maxCost = 0;
    private final List<Item> resultList = new ArrayList<>();
    private Item[] items;

    public static void main(String[] args) {
        System.out.println(new Knapsack().findAllItems(array));
    }

    private List<Item> findAllItems(Item[] array) {
        resultList.clear();

        items = Arrays.copyOf(array, array.length);

        find(items.length);

        return resultList;
    }

    private void find(int length) {
        if (length == 0) {
            return;
        }

        int start = items.length - length;

        for (int i = 0; i < length; i++) {
            int weightSum = 0;
            for (int e = start; e < items.length; e++) {
                weightSum += items[e].weight;
            }

            if (weightSum <= VOLUME) {
                int costSum = 0;
                for (int e = start; e < items.length; e++) {
                    costSum += items[e].cost;
                }

                if (costSum > maxCost) {
                    resultList.clear();
                    resultList.addAll(Arrays.asList(Arrays.copyOfRange(items, start, items.length)));
                    maxCost = costSum;
                }
            }
            find(length - 1);
            rotate(length);
        }
    }

    private void rotate(int length) {
        int first = items.length - length;

        Item temp = items[first];
        for (int i = first + 1; i < items.length; i++) {
            items[i - 1] = items[i];
        }
        items[items.length - 1] = temp;
    }
}
