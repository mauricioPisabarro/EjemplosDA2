#include<string>
#include<iostream>
using namespace std;

struct Person {
    int age;
    string name;
};

struct System {
public:
    // O(log(N))
    void add(Person* person) {

    }

    // O(log(N))
    Person* getYoungest() {
        return NULL;
    }

    // O(log(N))
    Person* getOldest() {
        return NULL;
    }

    void remove(string name) {

    }

    // O(1)
    bool exists(string name) {
        return false;
    }
};

int main() {
    return 1;
}