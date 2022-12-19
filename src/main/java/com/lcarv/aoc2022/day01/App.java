package com.lcarv.aoc2022.day01;

import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner; // Import the Scanner class to read text files

public class App {

  static ArrayList<Integer> elvesStash = new ArrayList<Integer>();

  static void processIpt(String filePath) {
    try {
      File file = new File(filePath); Scanner myReader = new Scanner(file);
      int currentElfStash = 0;

      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        // System.out.println(data);

        if (data == "") {
          elvesStash.add(currentElfStash);
          // biggestElfStock = currentElfStash > biggestElfStock ? currentElfStash :
          // biggestElfStock;
          currentElfStash = 0;
        } else {
          try {
            currentElfStash += Integer.parseInt(data);
          } catch (NumberFormatException e) {
            System.out.println("error parsing this data - " + data);
            System.out.println(e);
          }
        }

      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error ocourred while reading the file");
      e.printStackTrace();
    }

  }

  public static void main(String[] args) {
    processIpt("src/main/java/com/lcarv/aoc2022/day01/problemInput.txt");
    Collections.sort(elvesStash, Collections.reverseOrder());
    int top3Elves = (elvesStash.get(0) + elvesStash.get(1) + elvesStash.get(2));
    System.out.println("The elf that has the most calories: " + elvesStash.get(0));
    System.out.println("The 3 elves that have most food have a total of " + top3Elves);
  }
}
