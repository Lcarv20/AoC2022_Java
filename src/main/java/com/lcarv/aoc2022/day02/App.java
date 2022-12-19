package com.lcarv.aoc2022.day02;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import com.lcarv.aoc2022.utils.Input;

public class App {
  static String[] playOpts = { "A", "B", "C" };
  static String[] myInputs = { "X", "Y", "Z" };

  static Game game = new Game();
  // static Input input = new
  // Input("src/main/java/com/lcarv/aoc2022/day02/problemInput.txt");
  static String inputPath = "src/main/java/com/lcarv/aoc2022/day02/problemInput.txt";
  // static Input input = new
  // Input("src/main/java/com/lcarv/aoc2022/day02/testIpt.txt");

  static void processInput() throws FileNotFoundException {
    Scanner scanner = Input.fromFile(inputPath);
    while (scanner.hasNext()) {
      String data = scanner.nextLine();
      String[] match = data.split(" ");

      int opponentInput = Arrays.asList(playOpts).indexOf(match[0]);
      int myInput = Arrays.asList(myInputs).indexOf(match[1]);
      // this is the first part of the problem
      game.firstPart(opponentInput, myInput);
      game.secondPart(playOpts, match[0], match[1]);
    }

    scanner.close();

  }

  public static void main(String[] args) throws FileNotFoundException {
    processInput();
    System.out.println(game.firstPartResult); // 12458
    System.out.println(game.secondPartResult); // 12683

  }
}

class Game {
  public int firstPartResult = 0;
  public int secondPartResult = 0;

  public void firstPart(int opponent, int me) {

    // Points for the play
    firstPartResult += me + 1;

    // Points for the result
    // Tie
    if (opponent == me) {
      firstPartResult += 3;
    }

    // Win
    if ((me - opponent + 3) % 3 == 1) {
      firstPartResult += 6;
    }
  }

  public void secondPart(String[] plays, String oponentInput, String myInput) {
    HashMap<String, Integer> gameOutcome = new HashMap<String, Integer>();
    // Lose
    gameOutcome.put("X", 0);
    // Tie
    gameOutcome.put("Y", 3);
    // Win
    gameOutcome.put("Z", 6);

    int opponentPlayIndex = Arrays.asList(plays).indexOf(oponentInput);

    int myOptionPoints = 0;
    if (myInput.toCharArray()[0] == 'X') {
      // If i have to lose I have to play the option before my opponents except if
      // that option is 0
      myOptionPoints += (opponentPlayIndex == 0) ? 3 : opponentPlayIndex;
    } else if (myInput.toCharArray()[0] == 'Z') {
      // If I have to win I play the otion after of my opponents except if that option
      // is 2
      myOptionPoints += (opponentPlayIndex == 2) ? 1 : opponentPlayIndex + 2;
    } else {
      myOptionPoints += opponentPlayIndex + 1;
    }
    secondPartResult += gameOutcome.get(myInput) + myOptionPoints;
  }

}
