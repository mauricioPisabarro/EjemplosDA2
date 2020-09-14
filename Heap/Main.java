import java.util.HashMap;

public class Main {
  public static void main(String[] args) {
    Heap<Integer, Integer> heap = new Heap<>();

    for (int i = 0; i < 16; i++) {
      heap.push(i, i);
    }

    while(!heap.isEmpty()) {
      System.out.print(heap.pop() + " ");
    }
  }

  // With max heap property
  public static class Heap<P extends Comparable<P>, V extends Comparable<V>> {
    class Pair {
      public P p;
      public V v;

      public Pair(P priority, V value) {
        p = priority;
        v = value;
      }
    }

    private HashMap<V, Integer> map;
    private Object[] heap;
    private int last;
    private int size;

    public Heap() {
      heap = new Object[16];
      last = -1;
      size = 0;
    }

    // PRE: Heap !isFull
    public void push(P priority, V value) {
      Pair pair = new Pair(priority, value);

      last++;
      size++;
      heap[last] = pair;
      map.put(value, last);
      floatPair(last);
    }

    // PRE: Heap !isEmpty()
    public V pop() {
      Pair first = (Pair) heap[0];

      heap[0] = heap[last];
      map.remove(last);
      last--;
      size--;
      sink(0);

      return first.v;
    }

    public void changePriority(P priority, V value) {
      if(!exists(value)) {
        return;
      }

      int index = map.get(value);
      // decidir si hundimos
      // decidir si flotamos
    }

    // O(1)
    public boolean exists(V value) {
      return map.containsKey(value);
    }

    public boolean isFull() {
      return size == heap.length;
    }

    public boolean isEmpty() {
      return size == 0;
    }

    private void floatPair(int index) {
      if (index == 0) {
        return;
      }

      Pair current = (Pair) heap[index];
      Pair parent = (Pair) heap[parent(index)];
      while (index != 0 && parent.p.compareTo(current.p) < 0) {
        heap[index] = parent;
        heap[parent(index)] = current;

        map.put(parent.v, index);
        map.put(current.v, parent(index));

        index = parent(index);
        current = (Pair) heap[index];
        parent = (Pair) heap[parent(index)];
      }
    }

    private void sink(int index) {
      int l = leftChild(index);
      int r = rightChild(index);
      int biggest = index;

      if (l <= last) {
        Pair left = (Pair) heap[l];
        Pair current = (Pair) heap[biggest];
        if(left.p.compareTo(current.p) > 0) {
          biggest = l;
        }
      }

      if (r <= last) {
        Pair right = (Pair) heap[r];
        Pair current = (Pair) heap[biggest];
        if(right.p.compareTo(current.p) > 0) {
          biggest = r;
        }
      }

      if(index != biggest) {
        Pair aux = (Pair) heap[index];
        heap[index] = heap[biggest];
        heap[biggest] = aux;

        map.put(aux.v, biggest);
        map.put(((Pair)heap[index]).v, index);

        sink(biggest);
      }
    }

    private int leftChild(int i) {
      return 2 * i + 1;
    }

    private int rightChild(int i) {
      return 2 * i + 2;
    }

    private int parent(int i) {
      return (i - 1) / 2;
    }
  }
}