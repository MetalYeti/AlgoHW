package HW2;

import java.util.Objects;
import java.util.Random;

public class Notebook implements Comparable {
    private Vendors vendor;
    private int memory;
    private int price;

    public enum Vendors {
//        XAMIOU,  ESER, MACNOTE, ASOS, LENUVO;
        LENUVO, ASOS, MACNOTE, ESER, XAMIOU;

        private static Random rnd = new Random();

        public static Vendors getRandomVendor() {
            int idx = rnd.nextInt(Vendors.values().length);
            return Vendors.values()[idx];
        }
    }

    public Notebook(Vendors vendor, int memory, int price) {
        this.vendor = vendor;
        this.memory = memory;
        this.price = price;
    }

    public Vendors getVendor() {
        return vendor;
    }

    public int getMemory() {
        return memory;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public int compareTo(Object o) {
        if (this.equals(o)) return 0;

        Notebook notebook = (Notebook) o;

        if (price != notebook.getPrice()) {
            return price - notebook.getPrice();
        } else if (memory != notebook.getMemory()) {
            return memory - notebook.getMemory();
        } else {
            return vendor.compareTo(notebook.getVendor());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notebook notebook = (Notebook) o;
        return memory == notebook.getMemory() && price == notebook.getPrice() && vendor.equals(notebook.getVendor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(vendor, memory, price);
    }

    @Override
    public String toString() {
        return "Notebook{" +
                "vendor='" + vendor + '\'' +
                ", memory=" + memory +
                ", price=" + price +
                '}';
    }
}
