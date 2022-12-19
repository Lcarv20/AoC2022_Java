package com.lcarv.aoc2022.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Input {
  private File file;
  private Scanner scanner_;

  public Input(String uri) {
    try {
      file = new File(uri);
      scanner_ = new Scanner(file);
    } catch (FileNotFoundException e) {
      System.out.println("File not found sorry! \n" + e);
    }
  }

  public Scanner scanner() {
    return scanner_;
  }

  public static Scanner fromFile(String filePath) throws FileNotFoundException {
    File file = new File(filePath);
    Scanner scanner = new Scanner(file);
    return scanner;
  }

}
