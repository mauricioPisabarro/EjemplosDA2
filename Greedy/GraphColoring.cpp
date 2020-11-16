#include <iostream>
#include <vector>
#include <queue>
#include <unordered_map>
using namespace std;

#define FIRST_COLOR 0
typedef int Color;
typedef int Vertex;

class Graph {
public:
  vector<Vertex> children(Vertex v) {
    return adjacencyLists[v];
  }

  unordered_map<Vertex, vector<Vertex>> adjacencyLists;  
};

// Assignar un color a cada vertice para que dos vertices adyacentes
// no tengan el mismo color
unordered_map<Vertex, Color> colorGraph(Graph& graph) {
  unordered_map<Vertex, Color> coloring;
  queue<int> q;

  q.push(0);
  coloring[0] = FIRST_COLOR;
  Color nextColorToAssign = FIRST_COLOR + 1;
  while(!q.empty()) {
    int levelSize = q.size();

    for (int i = 0; i < levelSize; i++)
    {
      Vertex v = q.front();
      q.pop();

      coloring[v] = nextColorToAssign;
      nextColorToAssign++;

      for(Vertex child : graph.children(v)) {
        // Already has been colored
        if(coloring.find(child) != coloring.end()) {
          continue;
        }

        q.push(child);
      }
    }
    
  }

  return coloring;
}


int main() {
  return 1;
}