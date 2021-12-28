package HW8;

public class HashTableImpl<K, V> implements HashTable<K, V> {

    private final Item<K, V>[] data;
    private int size;

    static class Item<K, V> implements Entry<K, V> {
        private final K key;
        private V value;
        private Item<K, V> nextItem;
        private Item<K, V> previousItem;

        public Item(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Item<K, V> getPreviousItem() {
            return previousItem;
        }

        public void setPreviousItem(Item<K, V> previousItem) {
            this.previousItem = previousItem;
        }

        public Item<K, V> getNextItem() {
            return nextItem;
        }

        public void setNextItem(Item<K, V> nextItem) {
            this.nextItem = nextItem;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Item{" + "key=" + key + ", value=" + value + '}';
        }
    }

    public HashTableImpl(int initialCapacity) {
        this.data = new Item[initialCapacity];
    }

    public HashTableImpl() {
        this(16);
    }

    @Override
    public boolean put(K key, V value) {
        int index = hashFunc(key);
        Item<K, V> currentItem = getItemByKey(key);

        if (currentItem != null) {
            if (value.equals(currentItem.getValue())) {
                return false;
            }
            currentItem.setValue(value);
            return true;
        } else {
            currentItem = data[index];
            Item<K, V> previousItem = currentItem;

            while (currentItem != null) {
                previousItem = currentItem;
                currentItem = currentItem.getNextItem();
            }
            Item<K, V> item = new Item<>(key, value);
            if (previousItem != null) {
                item.setPreviousItem(previousItem);
                previousItem.setNextItem(item);
            } else {
                data[index] = item;
            }
        }
        size++;
        return true;
    }

    private boolean isKeysEquals(Item<K, V> item, K key) {
        return (item.getKey() == null) ? (key == null) : item.getKey().equals(key);
    }


    private int hashFunc(K key) {
        return Math.abs(key.hashCode() % data.length);
    }

    @Override
    public V get(K key) {
        Item<K, V> result = getItemByKey(key);
        return result == null ? null : result.getValue();
    }

    @Override
    public V remove(K key) {
        int index = hashFunc(key);
        Item<K, V> removeCandidate = getItemByKey(key);

        if (removeCandidate != null) {
            if (removeCandidate.getPreviousItem() == null) {
                data[index] = removeCandidate.getNextItem();
                if (removeCandidate.getNextItem() != null) {
                    removeCandidate.getNextItem().setPreviousItem(null);
                }
                removeCandidate.setNextItem(null);
            } else {
                if (removeCandidate.getNextItem() == null) {
                    removeCandidate.getPreviousItem().setNextItem(null);
                    removeCandidate.setPreviousItem(null);
                } else {
                    removeCandidate.getPreviousItem().setNextItem(removeCandidate.getNextItem());
                    removeCandidate.getNextItem().setPreviousItem(removeCandidate.getPreviousItem());
                    removeCandidate.setPreviousItem(null);
                    removeCandidate.setNextItem(null);
                }
            }
            size--;
            return removeCandidate.getValue();
        }
        return null;
    }

    private Item<K, V> getItemByKey(K key) {
        int index = hashFunc(key);
        Item<K, V> currentItem = data[index];

        while (currentItem != null) {
            if (isKeysEquals(currentItem, key)) {
                return currentItem;
            }
            currentItem = currentItem.getNextItem();
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size != 0;
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("-----------------------\n");
        for (int i = 0; i < data.length; i++) {
            Item<K, V> currentItem = data[i];
            sb.append(i).append(" = ");
            while (currentItem != null) {
                String next = currentItem.getNextItem() == null ? "" : " => ";
                sb.append(String.format("%s %s ", currentItem, next));
                currentItem = currentItem.getNextItem();
            }
            sb.append("\n");
        }
        sb.append("-----------------------\n");
        return sb.toString();
    }
}
