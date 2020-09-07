package Hash;

import java.util.function.Function;


// PUEDE SER QUE NO FUNCIONE
// PUEDE SER QUE NO FUNCIONE
// PUEDE SER QUE NO FUNCIONE
// PUEDE SER QUE NO FUNCIONE
// PUEDE SER QUE NO FUNCIONE
// PUEDE SER QUE NO FUNCIONE
class Table {
  public static void main(String[] args) {

  }

  class HashTable<K extends Comparable<K>, V> {
    class Pair<K, V> {
      private K key;
      private V value;
      private boolean wasErased;

      public Pair(K key, V value) {
        this.key = key;
        this.value = value;
      }

      public K getKey() {
        return key;
      }

      public V getValue() {
        return value;
      }

      public boolean wasErased() {
        return wasErased;
      }

      public void setWasErased() {
        wasErased = true;
      }
    }
    
    private Pair<K, V>[] table;
    private int size;
    private Function<K, Integer> hashFunction;

    public HashTable(Function<K, Integer> hashFunction) {
      table = (Pair[]) new Object[16];
      this.hashFunction = hashFunction;
    }

    public void insert(K key, V value) {
      if (isFull()) {
        resize();
      }

      // key.hashCode() % table.length
      int whereToInsert = this.hashFunction.apply(key) % table.length;
      Pair<K, V> pair = (Pair<K, V>) table[whereToInsert];
      while(pair != null && ((!pair.getKey().equals(key) && !pair.wasErased()))) {
        whereToInsert = (whereToInsert + 1) % table.length;
        pair = (Pair<K, V>) table[whereToInsert];
      }

      table[whereToInsert] = new Pair<K, V>(key, value);
      size++;
    }

    public V get(K key) {
      Pair<K, V> pair = privateGet(key);

      return pair == null ? null : pair.getValue();
    }

    public boolean exists(K key) {
      if(isEmpty()) {
        return false;
      }

      return get(key) != null;
    }

    public void remove(K key) {
      Pair<K, V> pair = privateGet(key);

      if(pair == null) {
        return;
      }

      pair.setWasErased();
    }

    private Pair<K, V> privateGet(K key) {
      int whereToGet = this.hashFunction.apply(key) % table.length;
      Pair<K, V> pair = (Pair<K, V>) table[whereToGet];
      int whereIStarted = whereToGet;
      while(pair != null && (pair.wasErased() || !key.equals(pair.getKey()))) {
        whereToGet = (whereToGet + 1) % table.length;
        pair = (Pair<K, V>) table[whereToGet];

        if(whereToGet == whereIStarted) {
          return null;
        }
      }

      if(pair == null) {
        return null;
      }

      return pair;
    }

    public boolean isEmpty() {
      return size == 0;
    }

    public boolean isFull() {
      return size == table.length;
    }

    public int size() {
      return size;
    }

    private void resize() {
      Object[] oldTable = table;
      size = 0;
      table = (Pair<K, V>[]) new Object[table.length * 2];

      for (Object pairObject : oldTable) {
        Pair<K, V> pair = (Pair<K, V>) pairObject;

        insert(pair.getKey(), pair.getValue());
      }
    }

  }
}