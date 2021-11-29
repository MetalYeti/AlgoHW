package HW2;

import java.util.Random;

public class NotebookFactory {
    private static Random rnd = new Random();

    private NotebookFactory() {
    }

    public static Notebook[] generateArray(int elementCount) {
        Notebook[] result = new Notebook[elementCount];

        for (int i = 0; i < elementCount; i++) {
            Notebook.Vendors tempVendor = generateVendor();
            int tempPrice = generateNumber(500, 3000, 50);
            int tempMemory = generateNumber(4, 24, 4);

            result[i] = new Notebook(tempVendor, tempMemory, tempPrice);
        }

        return result;
    }

    private static Notebook.Vendors generateVendor() {
        return Notebook.Vendors.getRandomVendor();
    }

    private static int generateNumber(int min, int max, int step) {
        int multiplier = (max - min) / step;
        return min + rnd.nextInt(multiplier + 1) * step;
    }
}
