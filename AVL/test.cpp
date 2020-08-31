#include<iostream>
using namespace std;

template <class T>
struct AVLNode {
  AVLNode* left;
  AVLNode* right;
  T value;

  AVLNode(const T& value) {
    left = NULL;
    right = NULL;
    this->value = value;
  }
};

template<class T>
void rotateLeft(AVLNode<T>*& node) {
  if (!node) {
    return;
  }

  AVLNode<T>* substitute = node->right;
  // Node takes ownership of substitute's left child
  node->right = substitute->left;
  // Substitute takes Node as left child
  substitute->left = node;

  // Swap node with substitute
  node = substitute;
}

template<class T>
void print(AVLNode<T>*& node) {
  if(! node) {
    return;
  }

  print(node->left);
  cout << node->value;
  print(node->right);
}


int main() {
  AVLNode<int>* root = new AVLNode<int>(1);
  root->right = new AVLNode<int>(2);
  root->right->right = new AVLNode<int>(3);

  print<int>(root);
  rotateLeft<int>(root);
  print<int>(root);

  return 1;
}