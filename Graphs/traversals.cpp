#include<iostream>
using namespace std;

struct TreeNode {
  TreeNode* left;
  TreeNode* right;
  int val;

  TreeNode(int val) {
    this->val = val;
    this->left = NULL;
    this->right = NULL;
  }

  TreeNode(int val, TreeNode* l, TreeNode* r) {
    this->val = val;
    this->left = l;
    this->right = r;
  }
};

void inorder(TreeNode* node) {
  if(!node) {
    return;
  }

  inorder(node->left);
  cout << node->val << " ";
  inorder(node->right);
}

void preorder(TreeNode* node) {
  if(!node) {
    return;
  }

  cout << node->val << " ";
  inorder(node->left);
  inorder(node->right);
}

void reversePreorder(TreeNode* node) {
  if(!node) {
    return;
  }

  cout << node->val << " ";
  inorder(node->right);
  inorder(node->left);
}

void postorder(TreeNode* node) {
  if(!node) {
    return;
  }

  inorder(node->left);
  inorder(node->right);
  cout << node->val << " ";
}

void reversePostorder(TreeNode* node) {
  if(!node) {
    return;
  }

  inorder(node->right);
  inorder(node->left);
  cout << node->val << " ";
}

int main() {
  TreeNode* twelve = new TreeNode(12);
  TreeNode* ten = new TreeNode(10, twelve, NULL);

  TreeNode* two = new TreeNode(2, NULL, ten);

  TreeNode* eight = new TreeNode(8);
  TreeNode* four = new TreeNode(4);
  TreeNode* three = new TreeNode(3, eight, four);


  TreeNode* root = new TreeNode(1, three, two);

  return 1;
}