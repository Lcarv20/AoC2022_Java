package com.lcarv.aoc2022.day03;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.lcarv.aoc2022.utils.Input;
import com.lcarv.aoc2022.utils.Log;

// -> each rucksack has the same nr of items in its 2 compartments
// -> lower case letters are worth from 1 to 26 and upper case letters from 27 to 52

public class App {
  static int prioritiesCount = 0;
  static int prioritiesCount2 = 0;

  public static void main(String[] args) throws FileNotFoundException {
    // Input input = new Input("src/main/java/com/lcarv/aoc2022/day03/testIpt.txt");
     Scanner scanner =  Input.fromFile("src/main/java/com/lcarv/aoc2022/day03/problemInput.txt");
    

    // Both rucksackAccumulator and accumulation is for the 2nd part;
    // rucksackAccumulator is corresponds to a collection of 3 rucksacks, that
    // will be distributed to a group of 3 elves.
    // Each elf takes care of a single rucksack
    List<String> groupRucksack = new ArrayList<String>();
    int accumulator = 0;

    while (scanner.hasNext()) {
      String rucksack = scanner.nextLine().strip();
      groupRucksack.add(rucksack);
      accumulator++;
      firstPart(rucksack);

      if (accumulator == 3) {
        secondPart(groupRucksack);
        accumulator = 0;
        groupRucksack.clear();
      }
    }
    scanner.close();
    Log.ln(prioritiesCount);
    Log.ln(prioritiesCount2);

  }

  static void firstPart(String rucksack) {
    int rucksackLen = rucksack.length();
    String[] compartment1 = rucksack.substring(0, rucksackLen / 2).split("");
    String compartment2 = rucksack.substring(rucksackLen / 2, rucksackLen);

    for (String i : compartment1) {
      if (compartment2.contains(i)) {
        prioritiesCount += calculatedPriority(i);
        break;
      }
    }

  }

  private static void secondPart(List<String> groupRucksacks) {
    for (int i = 0; i < groupRucksacks.get(0).length(); i++) {
      String itemToSearch = groupRucksacks.get(0).charAt(i) + "";
      boolean rucksack2 = groupRucksacks.get(1).contains(itemToSearch);
      boolean rucksack3 = groupRucksacks.get(2).contains(itemToSearch);
      if (rucksack2 && rucksack3) {
        System.out.println("the item searched is " + itemToSearch);
        prioritiesCount2 += calculatedPriority(itemToSearch);
        break;
      }
    }
  }

  private static int calculatedPriority(String itemType) {
    // convert to ascii and subtract the differene
    int itemPriority = (int) itemType.toCharArray()[0];

    // the points are from 1 to 26 to lower case letters,
    // and 27 to 52 to upper casw letters
    int calculatedPriority = (itemPriority > 96) ? itemPriority - 96 : itemPriority - 38;
    return calculatedPriority;
  }

}
