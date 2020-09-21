#include<iostream>
#include<fstream>
#include<string>
using namespace std;

int main() {
  int n;

  ifstream file;
  file.open("../Files/hash/test10e1.in");
  cout << "Ingrese un numero: ";
  file >> n;

  while(n-- > 0) {
    string line;
    file >> line;    
  }

  file.close();

  return 1;
}