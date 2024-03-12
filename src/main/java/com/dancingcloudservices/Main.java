package com.dancingcloudservices;

public record Main(String msg) {
  public static void main(String[] args) {
    Object obj = new Main("Hello Java 21 World!");
    System.out.println(
        switch (obj) {
          case Main(String s) -> "message is: " + s;
          default -> "Well, that's odd!";
        }
    );
  }
}