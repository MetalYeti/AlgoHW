package HW3;

public class DequeueImpl<E> extends QueueImpl<E> implements Deque<E> {

    public DequeueImpl(int maxSize) {
        super(maxSize);
    }

    @Override
    public boolean insertRight(E value) {
        return insert(value);
    }

    @Override
    public E removeLeft() {
        return remove();
    }

    @Override
    public boolean insertLeft(E value) {
        if (isFull()) {
            return false;
        }

        if (head == 0) {
            head = data.length;
        }
        if (tail == -1) {
            tail = data.length - 1;
        }

        data[--head] = value;
        size++;
        return true;
    }

    @Override
    public E removeRight() {
        if (isEmpty()) {
            return null;
        }

        if (tail == -1) {
            tail = data.length - 1;
        }

        E value = data[tail--];
        size--;
        return value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");

        int i = head;
        int end = tail == -1 ? data.length - 1 : tail;

        for (int s = size; s > 0; s--) {
            if (i == data.length) {
                i = 0;
            }
            sb.append(data[i]);
            if (i != end) {
                sb.append(",");
            }
            i++;
        }

        sb.append("]");
        return sb.toString();
    }
}
