package privateaccess;

public class Example {
  private static int x = 99;

  public static void showY(Inner that) {
    System.out.println(that.y);
  }

  static class Inner {
    private int y = 100;
    public void showX() {
      System.out.println( x );
    }
    public void setX(int newX) { x = newX; }
  }
}

class TryIt {
  public static void main(String[] args) {
    Example.Inner ei = new Example.Inner();
    ei.showX();
    ei.setX(1000);
    ei.showX();
    Example.showY(ei);
//    System.out.println(Example.x);
//    System.out.println(ei.y);
  }
}