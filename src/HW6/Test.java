package HW6;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Test {
    private static Random rnd = new Random();

    public static void main(String[] args) {

        final int MAX_DEPTH = 4;
        List<Tree<Integer>> array = new LinkedList<>();


        for (int i = 0; i < 20; i++) {
            Tree<Integer> tree = new TreeImpl<>();
            while (tree.getDepth() < MAX_DEPTH) {
                tree.add(getRandomIntInRange(-25, 25));
            }
            array.add(tree);
        }

        int balancedCount = 0;
        for (Tree<Integer> t : array) {
            if (t.isBalanced()) {
                balancedCount++;
            }
            t.display();
            System.out.println("isBalanced: " + t.isBalanced());
            System.out.println("Depth: " + t.getDepth() + ", Size: " + t.size());
        }

        System.out.println("Сбалансировано " + (balancedCount * 1.0 / array.size() * 100) + "% деревьев");

    }

    private static int getRandomIntInRange(int min, int max) {
        int multiplier = max - min;
        return min + rnd.nextInt(multiplier + 1);
    }
}
