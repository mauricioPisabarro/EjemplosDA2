// package HowToDebug;
import java.io.*;
import java.util.*;

class Test {
  public static void main(String[] args) throws Exception{
    Scanner sc = new Scanner(new FileReader(new File("../Files/hash/test10e1.in")));

    int cases = sc.nextInt();
    sc.nextLine();

    while(cases-- > 0) {
      System.out.println(sc.nextLine());
    }
  }
}