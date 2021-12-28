package HW8;

public class Main {

    public static void main(String[] args) {
        HashTable<Product, Integer> hashTable = new HashTableImpl<>(5); //5
//
        hashTable.put(new Product(1, "Orange"), 150); //1
        hashTable.put(new Product(77, "Banana"), 100); //2
        hashTable.put(new Product(67, "Carrot"), 228); //2
        hashTable.put(new Product(60, "Lemon"), 250); //0
        hashTable.put(new Product(51, "Milk"), 120); //1
        hashTable.put(new Product(21, "Potato"), 67); //1
//
        System.out.println("Size is " + hashTable.size());
        hashTable.display();
//
        System.out.println("Cost potato is " + hashTable.get(new Product(21, "Potato")));
        System.out.println("Cost banana is " + hashTable.get(new Product(77, "Banana")));
        System.out.println("Cost carrot is " + hashTable.get(new Product(67, "Carrot")));

        hashTable.put(new Product(21, "Potato"), 165); //1

        hashTable.remove(new Product(21, "Potato"));
        hashTable.remove(new Product(77, "Banana"));

        System.out.println("Cost potato is " + hashTable.get(new Product(21, "Potato")));
        System.out.println("Cost banana is " + hashTable.get(new Product(77, "Banana")));
        System.out.println("Cost carrot is " + hashTable.get(new Product(67, "Carrot")));

        hashTable.put(new Product(43, "Pineapple"), 228);//2
        hashTable.put(new Product(71, "Banana"), 100); //2

        System.out.println(hashTable.size());
        hashTable.display();

        hashTable.remove(new Product(51, "Milk"));
        hashTable.put(new Product(52, "Milk"), 120); //1
        hashTable.remove(new Product(71, "Banana"));

        System.out.println(hashTable.size());
        hashTable.display();
    }
}
