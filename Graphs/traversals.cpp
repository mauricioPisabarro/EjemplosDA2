#include<iostream>
#include<stack>
#include<queue>
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

void stackLeft(TreeNode* node, stack<TreeNode*>& stack) {
  while(node) {
    stack.emplace(node);
    node = node->left;
  }
}

void iterativeInorder(TreeNode* root) {
  stack<TreeNode*> stack;
  
  stackLeft(root, stack);
  while(!stack.empty()) {
    TreeNode* toVisit = stack.top();
    stack.pop();

    cout << toVisit->val << " ";
    if(toVisit->right) {
      stackLeft(toVisit->right, stack);
    }
  }
} 

// BFS
void levelOrderTraversal(TreeNode* root) {
  queue<TreeNode*> q;
  q.push(root);

  while(!q.empty()) {
    int size = q.size();
    for (int i = 0; i < size; i++)
    {
      TreeNode* toVisit = q.front();
      q.pop();

      cout << toVisit->val << " ";
      if(toVisit->left) {
        q.push(toVisit->left);
      }

      if(toVisit->right) {
        q.push(toVisit->right);
      }
    }

    cout << "\n";
  }
}


int main() {
  TreeNode* twelve = new TreeNode(12);
  TreeNode* ten = new TreeNode(10, twelve, NULL);

  TreeNode* two = new TreeNode(2, NULL, ten);

  TreeNode* eight = new TreeNode(8);
  TreeNode* four = new TreeNode(4);
  TreeNode* three = new TreeNode(3, eight, four);


  TreeNode* root = new TreeNode(1, three, two);
  
  levelOrderTraversal(root);
  return 1;
}