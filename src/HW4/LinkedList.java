package HW4;

public interface LinkedList<E> {

    E removeFirst();

    E removeLast();

    int size();

    void display();

    void insertFirst(E item);

    void insertLast(E item);

    boolean isEmpty();

    boolean contains(E value);

    void remove(E value);

    class Node<E> {
        E value;
        Node<E> next;
        Node<E> previous;

        public Node(E value, Node<E> next, Node<E> previous){
            this.value = value;
            this.next = next;
            this.previous = previous;
        }
    }
}
