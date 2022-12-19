package com.lcarv.aoc2022.day04;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.lcarv.aoc2022.utils.Input;
import com.lcarv.aoc2022.utils.Log;

public class App {
  static int fullyOverlapingElves = 0;
  static int partialOverlapingElves = 0;

  public static void main(String[] args) throws FileNotFoundException {
    // Input input = new Input("src/main/java/com/lcarv/aoc2022/day04/input.test");
    Scanner scanner = Input.fromFile("src/main/java/com/lcarv/aoc2022/day04/input.prod");

    while (scanner.hasNext()) {
      String elvesPlanStr = scanner.nextLine();
      ArrayList<int[]> processedElvesPlan = processElvesPlan(elvesPlanStr);
      int[] elf1 = processedElvesPlan.get(0);
      int[] elf2 = processedElvesPlan.get(1);
      firstPart(elf1, elf2);
      secondPart(elf1, elf2);
    }
    scanner.close();
    Log.ln(fullyOverlapingElves);
    Log.ln(partialOverlapingElves);
  }

  private static void firstPart(int[] elf1, int[] elf2) {
    if (elf1[0] >= elf2[0] && elf1[1] <= elf2[1]) {
      fullyOverlapingElves++;
    } else if (elf1[0] <= elf2[0] && elf1[1] >= elf2[1]) {
      fullyOverlapingElves++;
    }
  }

  private static void secondPart(int[] elf1, int[] elf2) {
    if (isOverlaping(elf1, elf2)) {
      partialOverlapingElves++;
    }
  }

  private static boolean isOverlaping(int[] elf1, int[] elf2) {
    return (elf2[0] <= elf1[1] && elf1[0] <= elf2[1]);
  }

  private static ArrayList<int[]> processElvesPlan(String elvesPlanStr) {
    String[] elvesPlanArr = elvesPlanStr.split(",");
    int[] elf1 = Arrays.stream(elvesPlanArr[0].split("-"))
        .mapToInt(Integer::parseInt)
        .toArray();
    int[] elf2 = Arrays.stream(elvesPlanArr[1].split("-"))
        .mapToInt(Integer::parseInt)
        .toArray();

    ArrayList<int[]> elvesPlan = new ArrayList<int[]>();
    elvesPlan.add(elf1);
    elvesPlan.add(elf2);

    return elvesPlan;
  }
}
