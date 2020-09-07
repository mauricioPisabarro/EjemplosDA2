#include <iostream>
using namespace std;

template<class T>
class HashKey {
public:
  unsigned int operator()(const T& t) const;
};

template<class T, class P, typename HashFunction = HashKey<T>>
class HashTable {
public:
  void insert(const T& key, const P& value) {
    unsigned int index = hashCode(key) % this->size;
  }

  
private:
  HashFunction hashCode;
};

class HashInt {
public:
  unsigned int operator()(const int& t) {
    return t;
  }
};

int main() {
  HashTable<int, int, HashInt> table;

  return 1;
}