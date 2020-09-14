#include <iostream>
using namespace std;

template<class P, class V>
class Heap {
public:
  Heap() {
    this->heap = new pair<V,P>[16];
    this->size = 16;
    this->elements = 0;
    this->last = -1;
  }

  ~Heap() {
    delete[] this->heap;
  }

  void push(P& priority, V& value) {
    heap[++last] = make_pair<P,V>(std::forward<P>(priority), std::forward<V>(value));

    floatValue(last - 1);
    elements++;
  }

  V pop() {
    V value = heap[0].second;

    heap[0] = heap[last--];
    sinkValue(0);

    elements--;

    return value;
  }

  bool isEmpty() {
    return elements == 0;
  }

  void print() {
    for (int i = 0; i <= last; i++) {
      cout << heap[i].second << " ";
    }
  }
private:
  void floatValue(int i) {
    if (i <= 0) {
      return;
    }
    
    auto pair = heap[i];
    auto directParent = heap[parent(i)];
    if (pair.first > directParent.first) {
      heap[i] = directParent;
      heap[parent(i)] = pair;
      floatValue(parent(i));
    }
  }

  void sinkValue(int i) {
    if(i >= last || isLeaf(i)) {
      return;
    }

    int l = leftChild(i);
    int r = rightChild(i);
    int biggest = i;
    
    if(l <= last && heap[l].first > heap[biggest].first) {
      biggest = l;
    }
    
    if(r <= last && heap[r].first > heap[biggest].first) {
      biggest = r;
    }

    if(i != biggest) {
      swap(heap[biggest], heap[i]);
      sinkValue(biggest);
    }
  }

  int parent(int i) {
    return (i - 1)/2;
  }

  int leftChild(int i) {
    return 2 * i + 1;
  }

  int rightChild(int i) {
    return 2 * i + 2;
  }

  bool isLeaf(int i) {
    return leftChild(i) >= last;
  }

  int last;
  int size;
  int elements;
  pair<P,V>* heap;
};

int main() {
  Heap<int,int> heap;

  int zero = 0;
  for(int i = 0; i < 16; i++) {
    int j = i;
    heap.push(zero, j);
    heap.print();
    cout << "\n";
  }

  cout << "\n";cout << "\n";
  while(!heap.isEmpty()) {
    cout << heap.pop() << " ";
  }  

  return 1;
}
