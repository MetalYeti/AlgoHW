package HW4;

public class Main {
    public static void main(String[] args) {
        TwoSideLinkedList<Integer> linkedList = new TwoSideLinkedList<>();

        linkedList.insertFirst(3);
        linkedList.insertFirst(1);
        linkedList.insertFirst(45);
        linkedList.insertFirst(7);
        linkedList.insertLast(8);
        linkedList.insertFirst(6);
        linkedList.insertFirst(9);
        linkedList.insertLast(18);

        for (Integer i : linkedList) {
            System.out.println(i);
        }
    }
}
