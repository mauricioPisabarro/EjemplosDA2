package imp_java;

public class HeapSort {
  public static void main(String args[]) 
  { 
    int arr[] = {12, 10, 15, 4, 7, 8};
    
    heapSort(arr); 
    
    System.out.println("Sorted array is"); 
    printArray(arr); 
  } 

  static void printArray(int arr[]) 
  { 
    int n = arr.length; 
    for (int i=0; i<n; ++i) 
    System.out.print(arr[i]+" "); 
    System.out.println(); 
  } 

  static void heapSort(int arr[]){
    //armamos el heap
    var n = arr.length;
    for (int i = n / 2 ;i >= 0; i--){
      heapify(arr, n, i);
    }

    //extraer los elementos del heap
    for(int i = n-1; i>0; i--){
      var temp = arr[0];
      arr[0] = arr[i];
      arr[i] = temp;

      //volvemos a llamar a heapify
      heapify(arr, i, 0);
    }
  }

  //n hasta donde llego, i es la raiz
  static void heapify(int arr[], int n, int i){
    int larger = i;
    int left = i*2+1;
    int right = left+1;

    if (left<n && arr[left] > arr[larger])
    larger = left;

    if (right<n && arr[right] > arr[larger])
    larger = right;

    //si larger no es la raiz
    if (larger != i){
      var temp = arr[i];
      arr[i] = arr[larger];
      arr[larger] = temp;

      heapify(arr, n, larger);
    }
  }
}



