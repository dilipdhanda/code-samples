package com.c0.PayPal;

public class ClosestPureColor {

  public static String closestColor(String bits24) {
    if (bits24 == null || bits24.length() != 24 || !bits24.matches("[01]{24}"))
      throw new IllegalArgumentException("Expected 24-bit binary string");

    int r = Integer.parseInt(bits24.substring(0, 8), 2);
    int g = Integer.parseInt(bits24.substring(8, 16), 2);
    int b = Integer.parseInt(bits24.substring(16, 24), 2);

    int[][] colors = {
      {255, 0, 0},   // Red
      {0, 255, 0},   // Green
      {0, 0, 255},   // Blue
      {0, 0, 0},     // Black
      {255, 255, 255} // White
    };
    String[] names = {"Red", "Green", "Blue", "Black", "White"};

    int min = Integer.MAX_VALUE;
    int minIndex = -1;
    boolean tie = false;

    for (int i = 0; i < colors.length; i++) {
      int dr = r - colors[i][0];
      int dg = g - colors[i][1];
      int db = b - colors[i][2];
      int dist2 = dr*dr + dg*dg + db*db; // squared Euclidean distance

      if (dist2 < min) {
        min = dist2;
        minIndex = i;
        tie = false;
      } else if (dist2 == min) {
        tie = true;
      }
    }

    return tie ? "Ambiguous" : names[minIndex];
  }

  // quick demo
  public static void main(String[] args) {
    System.out.println(closestColor("111111110000000000000000")); // near Red -> "Red"
    System.out.println(closestColor("000000000000000000000000")); // Black
    System.out.println(closestColor("111111111111111111111111")); // White
  }
}

