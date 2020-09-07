// package AVL;

public class Main {
  public static class AVLTree<T extends Comparable<T>> {
    // template<class T>
    private class AVLNode<T extends Comparable<T>> {
      public int height;
      public T data;
      public AVLNode<T> left;
      public AVLNode<T> right;

      public AVLNode(T data) {
        this.data = data;
        // this->data = data;
        left = null;
        right = null;
        height = 1;
      }
    }

    private AVLNode<T> root;
    private int elements;

    public void add(T data) {
      root = add(root, data);
    }

    public void remove(T data) {
      root = remove(root, data);
    }

    public boolean exists(T data) {
      AVLNode<T> iterator = root;

      while(iterator != null){
        // smaller data < iterator.data
        if(data.compareTo(iterator.data) < 0) {
          iterator = iterator.left;
          // bigger data > iterator.data
        } else if(data.compareTo(iterator.data) > 0) {
          iterator = iterator.right;
        } else {
          return true;
        }
      }

      return false;
    }

    // const T& get(const T& data);
    public T get(T data) {
      return get(root, data);
    }

    public int size() {
      return elements;
    }

    private T get(AVLNode<T> node, T data) {
      if(node == null) {
        return null;
      }

      AVLNode<T> iterator = node;
      while(iterator != null) {
        if(data.compareTo(iterator.data) == 0) {
          return iterator.data;
        } else if(data.compareTo(iterator.data) < 0) {
          iterator = iterator.left;
        } else {
          iterator = iterator.right;
        }
      }

      return null;
    }

    // data no existe en la estructura
    private AVLNode<T> add(AVLNode<T> node, T data) {
      if(node == null) {
        elements++;
        return new AVLNode<>(data);
      }

      if(data.compareTo(node.data) == 0) {
        // hacer nothing
        return node;
      } else if(data.compareTo(node.data) > 0) {
        node.right = add(node.right, data);
      } else {
        node.left = add(node.left, data);
      }

      node = balance(node);

      return node;
    }

    private AVLNode<T> remove(AVLNode<T> node, T data) {
      if(node == null) {
        return null;
      }

      if(data.compareTo(node.data) > 0) {
        node.right = remove(node.right, data);
      } else if(data.compareTo(node.data) < 0) {
        node.left = remove(node.left, data);
      } else if(data.compareTo(node.data) == 0) {
        if(node.left != null && node.right != null) {
          T substitute = findMax(node.left);//
  
          node.data = substitute;
          remove(node.left, substitute);
          elements--;
        } else {
          // AVLNode<T> toDelete = node;
          node = node.left != null ? node.left : node.right;
          // delete toDelete;
          elements--;
        }
      } else {
        // do nothing
      }

      return node;
    }

    private AVLNode<T> balance(AVLNode<T> node) {
      if(needsLeftRotation(node)) {
        node = rotateLeft(node);
      } else if(needsRightRotation(node)) {
        node = rotateRight(node);
      }

      updateHeight(node);

      return node;
    }

    //         1
    //          \
    //           2
    //            \
    //             3
    private AVLNode<T> rotateLeft(AVLNode<T> node) {
      AVLNode<T> substitute = node.right;
      // donde ponemos los hijos a la izquierda del substituto
      node.right = substitute.left;
      substitute.left = node;

      return substitute;
    }

    //                1
    //              /
    //             2
    //            /
    //           3
    private AVLNode<T> rotateRight(AVLNode<T> node) {
      AVLNode<T> substiute = node.left;
      node.left = substiute.right;
      substiute.right = node;

      // node = substitute;

      return substiute;
    }

    private boolean needsLeftRotation(AVLNode<T> node) {
      if (node == null) {
        return false;
      }

      return height(node.right) - height(node.left) > 1;
    }
    
    private boolean needsRightRotation(AVLNode<T> node) {
      if (node == null) {
        return false;
      }
      
      return height(node.left) - height(node.right) > 1;
    }

    private void updateHeight(AVLNode<T> node) {
      node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    private int height(AVLNode<T> node) {
      return node != null ? node.height : 0;
    }

    private T findMax(AVLNode<T> node) {
      if(node == null) {
        return null;
      }

      while(node.right != null) {
        node = node.right;
      }

      return node.data;
    }
  }

  private static class Diccionario<T extends Comparable<T>> {
    private AVLTree<T> tree;

    public Diccionario() {
      tree = new AVLTree();
    }

    // No agrega repetidos
    public void add(T data) {
      tree.add(data);
    }
    
    public void remove(T data) {
      if(!tree.exists(data)) {
        return;
      }

      tree.remove(data);
    }

    public boolean exists(T data) {
      return tree.exists(data);
    }
  }

  private static class Tabla<D extends Comparable<D>, C> {
    private class Pair implements Comparable<Pair> {
      public D domain;
      public C codomain;

      public Pair(D d, C c) {
        domain = d;
        codomain = c;
      }

      @Override
      public int compareTo(Pair other) {
        return domain.compareTo(other.domain);
      }
    }

    private AVLTree<Pair> tree;

    public Tabla() {
      tree = new AVLTree<>();
    }

    public void add(D domain, C element) {
      Pair p = new Pair(domain, element);
      tree.add(p);
    }

    public C get(D domain) {
      Pair p = new Pair(domain, null);

      Pair originalPair = tree.get(p);
      return originalPair != null ? originalPair.codomain : null;
    }

    public void remove(D domain) {
      Pair p = new Pair(domain, null);
      tree.remove(p);
    }

    public boolean exists(D domain) {
      Pair p = new Pair(domain, null);
      return tree.exists(p);
    }
  }
  
  public static void main(String[] args) {
    Tabla<String, Integer> tabla = new Tabla<>();
    tabla.add("clave1", 23);
    tabla.add("clave2", 24);
    System.out.println(tabla.get("clave2"));
    tabla.remove("clave2");
    System.out.println(tabla.get("clave2"));
  }
}