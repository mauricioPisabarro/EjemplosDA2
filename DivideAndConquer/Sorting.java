// package AVL;

import java.util.Random;

public class Sorting {
  private static Random randomGenerator;
  
  static {
    randomGenerator = new Random();
  }

  public static void main(String[] args) {
    int[] example = new int[]{ 7, 1, 180 , 4 , -12, 9 };
    //print(mergeSort(example, 0, 5));

    quickSort(example, 0, example.length - 1);
    print(example);
  }

  static void print(int[] arr) {
    for(int i : arr) {
      System.out.print(i + " ");
    }

    System.out.println();
  }

  // Buscar un pivot
    // Todos los elementos mas chicos a la izquierda del pivot
    // Todos los elementos mas grandes a la derecha del pivot
    // Esto nos deja el pivot ordenado

  // ordenar(left, pivot - 1);
  // ordenar(pivot + 1, right);
  static void quickSort(int[] array, int left, int right) {
    if(left >= right) {
      return;
    }

    int pivot = findPivot(array, left, right);
    // pivot ya ordenado
    // Divide
    quickSort(array, left, pivot - 1);
    quickSort(array, pivot + 1, right);
  }

  static int findPivot(int[] array, int left, int right) {
    // Elegir aleatorio para amortizar el peor caso de O(N^2)
    int random = randomGenerator.nextInt(right - left) + left;
    swap(array, right, random);
    // __________________________________________
    // [7, 1, 180 , 4 , -12, 9]   pivotValue = 9  pivotIndex = 0  i = 0
    // [7, 1, 180 , 4 , -12, 9]   pivotValue = 9  pivotIndex = 1  i = 1
    // [7, 1, 180 , 4 , -12, 9]   pivotValue = 9  pivotIndex = 2  i = 2
    // [7, 1, 180 , 4 , -12, 9]   pivotValue = 9  pivotIndex = 2  i = 3
    // [7, 1, 4 , 180 , -12, 9]   pivotValue = 9  pivotIndex = 3  i = 4
    // [7, 1, 4 , -12 , 180, 9]   pivotValue = 9  pivotIndex = 4  i = 5
    int pivotValue = array[right];
    int pivotIndex = left;
    for(int i = left; i < right; i++) {
      if(array[i] < pivotValue) {
        swap(array, i, pivotIndex);
        pivotIndex++;
      }
    }  
    
    // [7, 1, 4 , -12 , 9, 180]   pivotValue = 9  pivotIndex = 4  i = 5
    swap(array, right, pivotIndex);

    return pivotIndex;
  }

  static void swap(int[] array, int i, int j) {
    int aux = array[i];
    array[i] = array[j];
    array[j] = aux;
  }


  // array = a ordenar
  // indice al principio
  // indice al final
  static int[] mergeSort(int[] array, int left, int right) {
    if(left == right) {
      return new int[] { array[left] };
    }

    int middle = (right - left)/2 + left; // (left + right) / 2_

    // Divide
    int[] l = mergeSort(array, left, middle);
    int[] r = mergeSort(array, middle + 1, right);
  
    // Conquer
    return merge(l, r);
  }

  // O(left) + O(right) == O(N)
  static int[] merge(int [] left, int[] right) {
    int[] merged = new int[left.length + right.length];

    int i = 0;

    int l = 0;
    int r = 0;
    while(l < left.length && r < right.length) {
      if(left[l] <= right[r]) {
        merged[i++] = left[l++];
      } else {
        merged[i++] = right[r++];
      }
    }

    while(l < left.length) {
      merged[i++] = left[l++];
    }

    while(r < right.length) {
      merged[i++] = right[r++];
    }

    return merged;
  }
} 