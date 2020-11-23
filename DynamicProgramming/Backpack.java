package DynamicProgramming;

public class Backpack {
  class Item {
    int volume;
    int weight;
    int value;
  }

  // Bottom up
  int dpWithVolume(Item[] items, int massCapacity, int volumeCapacity) {
    int[][][] dp = new int[massCapacity + 1][volumeCapacity + 1][items.length + 1];

    for (int i = 1; i <= massCapacity; i++) {
      for (int j = 1; j <= volumeCapacity; j++) {
        for (int k = 1; k <= dp.length; k++) {
          Item current = items[k - 1];
          if(current.weight > massCapacity || current.volume > volumeCapacity) {
            dp[i][j][k] = dp[i][j][k - 1];
            continue;
          }

          int considering = current.value + dp[i - current.weight][j - current.volume][k - 1];
          int notConsidering = dp[i][j][k - 1];

          dp[i][j][k] = Math.max(considering, notConsidering);
        }
      }
    }

    return dp[massCapacity][volumeCapacity][items.length];
  }

  // Top down
  int memoizationWithVolume(Item[] items, int massCapacity, int volumeCapacity, int currentIndex, Integer[][][] memo) {
    if(currentIndex >= items.length) {
      return 0;
    } else if(memo[massCapacity][volumeCapacity][currentIndex] != null) {
      return memo[massCapacity][volumeCapacity][currentIndex];
    }
    
    Item current = items[currentIndex];
    if(current.weight > massCapacity || current.volume > volumeCapacity) {
      memo[massCapacity][volumeCapacity][currentIndex] = memoizationWithVolume(items, massCapacity, volumeCapacity, currentIndex + 1, memo);
      return memo[massCapacity][volumeCapacity][currentIndex];
    }

    int considering = item.value + memoizationWithVolume(items, massCapacity - item.weight, volumeCapacity - item.volume, currentIndex + 1, memo);
    int notConsidering = memoizationWithVolume(items, massCapacity, volumeCapacity, currentIndex + 1, memo);

    memo[massCapacity][volumeCapacity][currentIndex] = Math.max(considering, notConsidering);

    return memo[massCapacity][volumeCapacity][currentIndex];
  }
  
  int backtrackingWithVolume(Item[] items, int massCapacity, int volumeCapacity, int currentIndex) {
    if(currentIndex >= items.length) {
      return 0;
    } 
    
    Item current = items[currentIndex];
    if(current.weight > massCapacity || current.volume > volumeCapacity) {
      return backtrackingWithVolume(items, massCapacity, volumeCapacity, currentIndex + 1);
    }

    int considering = item.value + backtrackingWithVolume(items, massCapacity - item.weight, volumeCapacity - item.volume, currentIndex + 1);
    int notConsidering = backtrackingWithVolume(items, massCapacity, volumeCapacity, currentIndex + 1);

    return Math.max(considering, notConsidering);
  }
}

// Memorizacion es un approach top down vs Dynamic Programming que un approach bottom up
// Fibonacci (7)
//                     7
//                  5      6
//                3   4   4  5
//              1  2 2 3 2 3 3 4
//                0 1
