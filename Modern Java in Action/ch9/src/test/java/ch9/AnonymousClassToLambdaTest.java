package ch9;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnonymousClassToLambdaTest {
  private String variable = "AnonymousClassToLambdaTest variable";

  @Test
  @DisplayName("From anonymous classes to lambda expressions")
  void testSimpleRefactor() {
    Runnable r1 = () -> System.out.println("是在 Hello");
  }

  @Test
  @DisplayName("Different scope of `this` between anonymous classes and lambda")
  void testKeywordThis() {
    Runnable r1 = () -> System.out.println(this.variable);

    Runnable r2 = new Runnable() {
      String variable = "Runnable class variable"; // remove this cause compile error

      public void run() {
        System.out.println(this.variable);
      }
    };

  }

  @Test
  @DisplayName("Shadow variables from enclosing class")
  void testShadowVariable() {
    int a = 10;
    Runnable r1 = () -> {
//      int a = 2;
      System.out.println(a);
    };

    Runnable r2 = () -> {
      int a1 = 2;
      System.out.println(a1);
    };
  }

  @Test
  @DisplayName("Test code ambiguous in the context of overloading")
  void testCodeAmbiguous() {
    doSomething((Task) () -> System.out.println("Danger danger!!"));

    // Cause compile error
//    doSomething(() -> System.out.println("Danger danger!!"));
  }

  interface Task {
    void execute();
  }

  public static void doSomething(Runnable r) {
    r.run();
  }

  public static void doSomething(Task r) {
    r.execute();
  }
}
