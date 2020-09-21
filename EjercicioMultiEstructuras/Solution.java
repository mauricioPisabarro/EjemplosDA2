import java.util.*;

class Solution {
    public static void main(String[] args) {

    }

    static class System {
        private Map<String, Person> peopleByName;
        private TreeMap<Integer, Person> peopleByAge;

        public System() {
          peopleByAge = new TreeMap<>();
          peopleByName = new HashMap<>();
        }

        // O(log(N))
        public void add(Person person) {
          if(peopleByName.containsKey(person.name) || peopleByAge.containsKey(person.age)) {
            return;
          }

          peopleByName.put(person.name, person);
          peopleByAge.put(person.age, person);
        }

        // O(log(N))
        public Person getYoungest() {  
          if(peopleByAge.isEmpty()) {
            return null;
          }

          return peopleByAge.firstEntry().getValue();
        }

        // O(log(N))
        public Person getOldest() {
          if(peopleByAge.isEmpty()) {
            return null;
          }

          return peopleByAge.lastEntry().getValue();
        }

        // O(log(N))
        public void remove(String name) {
          if(!peopleByName.containsKey(name)) {
            return;
          }

          Person person = peopleByName.get(name);
          peopleByAge.remove(person.age);
          peopleByName.remove(person.name);
        }

        // O(1)
        public boolean exists(String name) {
            return peopleByName.containsKey(name);
        }
    }

    static class Person {
        public int age;
        public String name;
    }
}