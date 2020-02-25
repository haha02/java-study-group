package ch9;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LambdaTest {

  @Test
  @DisplayName("Testing the behavior of a visible lambda")
  void testLambdaBehavior() {
    Point p1 = new Point(10, 15);
    Point p2 = new Point(10, 20);
    int result = compareByXAndThenY.compare(p1 , p2);
    assertTrue(result < 0);
  }

  @Test
  @DisplayName("Focusing on the behavior of the method using a lambda")
  void testMethodBehavior() {
    List<Point> points = List.of(new Point(5, 5), new Point(10, 5));
    List<Point> expectedPoints = List.of(new Point(15, 5), new Point(20, 5));
    List<Point> newPoints = moveAllPointsRightBy(points, 10);
    assertEquals(expectedPoints, newPoints);
  }

  @Test
  @DisplayName("Testing high-order functions")
  void testHigherOrderMethod() {
    List<Integer> numbers = List.of(1, 2, 3, 4);
    List<Integer> even = filter(numbers, i -> i % 2 == 0);
    List<Integer> smallerThanThree = filter(numbers, i -> i < 3);
    assertEquals(List.of(2, 4), even);
    assertEquals(List.of(1, 2), smallerThanThree);
  }

  private static final Comparator<Point> compareByXAndThenY =
      comparing(Point::getX).thenComparing(Point::getY);

  private static List<Point> moveAllPointsRightBy(List<Point> points, int x) {
    return points.stream()
        .map(p -> new Point(p.getX() + x, p.getY()))
        .collect(toList());
  }

  private <T> List<T> filter(List<T> inputList, Predicate<T> filter) {
    return inputList.stream().filter(filter).collect(toList());
  }
}
