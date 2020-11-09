package Greedy;

public class WiggleSort {
  // a1 <= a2 >= a3 ...
  public void solve(int[] array) {
    boolean shouldBeBigger = false;

    for (int i = 0; i < array.length - 1; i++) {
      if(shouldBeBigger) {
        if(array[i] < array[i + 1]) {
          swap(array, i, i + 1);
        }
      } else {
        if(array[i] > array[i + 1]) {
          swap(array, i, i + 1);
        }
      }

      shouldBeBigger = !shouldBeBigger;
    }
  }

  private void swap(int[] array, int i, int j) {
    int aux = array[i];
    array[i] = array[j];
    array[j] = swap;
  }
}
