// package Heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class SelectKBiggest {
  public static void main(String[] args) {
    PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();sc.nextLine();
    int[] array = getArray(N, sc);

    int K = sc.nextInt();sc.nextLine();
    for(Integer c : array) {
      pq.add(c);

      if(pq.size() > K) {
        pq.poll();
      }
    }

    while(!pq.isEmpty()) {
      System.out.print(pq.poll() + " ");
    }
  }

  private static int[] getArray(int n, Scanner sc) {
    int[] arr = new int[n];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = sc.nextInt();
    }
    return arr;
  }
}