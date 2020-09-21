#include<string>
#include<iostream>
#include<unordered_map>
#include<map>
using namespace std;

struct Person {
    int age;
    string name;
};

using PersonHash = unordered_map<string, Person*>;
using PersonAVL = map<int, Person*>;

struct System {
public:
    System() {}

    // O(log(N))
    void add(Person* person) {
      if(exists(person->name) || existsAge(person->age)) {
        return;
      }

      peopleByName[person->name] = person;
      peopleByAge[person->age] = person;
    }

    // O(log(N))
    Person* getYoungest() {
      if(peopleByAge.size() == 0) {
        return NULL;
      }

      return peopleByAge.begin()->second;
    }

    // O(log(N))
    Person* getOldest() {
      if(peopleByAge.size() == 0) {
        return NULL;
      }

      return peopleByAge.rbegin()->second;
    }

    // O(log(N))
    void remove(string name) {
      Person* person = peopleByName[name];
      if(!person) {
        return;
      }

      peopleByAge.erase(person->age);
      peopleByName.erase(name);
      delete person;
    }

    // O(1)
    Person* get(string name) {
      return peopleByName[name];
    }

    // O(1)
    bool exists(string name) {
        return peopleByName.find(name) != peopleByName.end();
    }

private:
  bool existsAge(int age) {
    return peopleByAge.find(age) != peopleByAge.end();
  }

  PersonHash peopleByName;
  PersonAVL peopleByAge;
};

int main() {
    return 1;
}