#include <iostream>
using namespace std;

template <class T>
class AVL {
public:
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
        iter = iter->left;
      } else {
        iter = iter->right;
      }
    }

    return false;
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
    assert(!node || (node && node->value != value));

    if (!node) {
      node = new AVLNode(value);
    } else if(value < node->value) {
      add(node->left, value);
    } else {
      add(node->right, value);
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
      
    } else {
      AVLNode* toDelete = node;
      node = node->left ? node->left : node->right;
      delete toDelete;
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

  int height(const AVLNode*& node) {
    return !node ? 0 : node->height;
  }

  bool requiresLeftRotation(const AVLNode*& node) {
    return height(node->right) > height(node->left) > 1;
  }

  bool requiresRightRotation(const AVLNode*& node) {
    return height(node->left) > height(node->right) > 1;
  }

  void rotateLeft(const AVLNode*& node) {
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

  void rotateRight(const AVLNode*& node) {
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

int main() {
  return 1;
}