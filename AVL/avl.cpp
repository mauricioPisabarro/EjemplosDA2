#include <iostream>
using namespace std;

template <class T>
class AVL {
public:
  AVL() {
    root = NULL;
    elements = 0;
  }

  void add(const T& element) {
    add(root, element);
  }

  void remove(const T& element) {
    remove(root, element);
  }

  bool exists(const T& element) {
    AVLNode* iter = root;

    while(iter) {
      if (iter->value == element) {
        return true;
      } else if(iter->value < element) {
        iter = iter->right;
      } else {
        iter = iter->left;
      }
    }

    return false;
  }

  int size() const {
    return elements;
  }
private:
  struct AVLNode {
    AVLNode* left;
    AVLNode* right;
    int height;
    T value;

    AVLNode(const T& value) {
      left = NULL;
      right = NULL;
      this->value = value;
      height = 1;
    }
  };

  void add(AVLNode*& node, const T& value) {
    // assert(!node || (node && node->value != value));

    if (!node) {
      node = new AVLNode(value);
      elements++;
    } else if(value < node->value) {
      add(node->left, value);
    } else if(value > node->value) {
      add(node->right, value);
    } else {
      // dont add repeated numbers
      return;
    }

    balance(node);
  }

  void remove(AVLNode*& node, const T& element) {
    if(!node) {
      return;
    }

    if(element < node->value) {
      remove(node->right, element);
    } else if(element > node->value) {
      remove(node->left, element);
    } else if(node->left && node->right) {
      AVLNode* smallest = node->right;
      while(smallest && smallest->left) {
        smallest = smallest->left;
      }

      node->value = smallest->value;
      remove(node->right, node->value);
    } else {
      AVLNode* toDelete = node;
      node = node->left ? node->left : node->right;
      delete toDelete;

      elements--;
    }
  }

  void balance(AVLNode*& node) {
    if(!node) {
      return;
    }

    if(requiresRightRotation(node)) {
      rotateRight(node);
    } else if(requiresLeftRotation(node)) {
      rotateLeft(node);
    }

    updateHeight(node);
  }

  void updateHeight(AVLNode* &node) {
    if (!node) {
      return;
    }

    node->height = max(height(node->left), height(node->right)) + 1;
  }

  int height(AVLNode*& node) const {
    return !node ? 0 : node->height;
  }

  bool requiresLeftRotation(AVLNode*& node) const {
    return height(node->right) > height(node->left) > 1;
  }

  bool requiresRightRotation(AVLNode*& node) const {
    return height(node->left) > height(node->right) > 1;
  }

  void rotateLeft(AVLNode*& node) {
    if (!node) {
      return;
    }

    AVLNode* substitute = node->right;
    // Node takes ownership of substitute's left child
    node->right = substitute->left;
    // Substitute takes Node as left child
    substitute->left = node;

    // Swap node with substitute
    node = substitute;
  }

  void rotateRight(AVLNode*& node) {
    if(!node) {
      return;
    }

    AVLNode* substitute = node->left;
    // Node takes ownership of substitute's right child
    node->left = substitute->right;
    // Substitute takes Node as right child
    substitute->right = node;

    // Swap node with substitute
    node = substitute;
  }

  AVLNode* root;
  int elements;
};

template <class T>
class Set {
public:
  Set() {
    tree = new AVL<T>();
  }

  void add(const T& element) {
    if (exists(element)) {
      return;
    }

    tree->add(element);
  }
  
  void remove(const T& element) {
    tree->remove(element);
  }
  
  bool exists(const T& element) {
    return tree->exists(element);
  }

  int size() {
    return tree->size();
  }

private:
  AVL<T>* tree;
};

int main() {
  Set<int>* mySet = new Set<int>();

  for (int i = 0; i < 10; i++)
  {
    cout << (mySet->exists(i) ? "true" : "false") << " ";
    cout << mySet->size()  << "\n";
    for (int j = 0; j < 2; j++)
    {
      mySet->add(i);
    }
    
    cout << (mySet->exists(i) ? "true" : "false") << " ";
    cout << mySet->size() << "\n";
  }

  return 1;
}