package ch9;

import static java.util.stream.Collectors.toList;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LambdaLoggingTest {

  @Test
  @DisplayName("Use Peek to log the information in every stage")
  void logStream() {
    List<Integer> numbers = List.of(1 ,2, 3);
    List<Integer> result =
        numbers.stream()
            .peek(x -> System.out.println("from stream: " + x))
        .map(x -> x + 17)
        .peek(x -> System.out.println("after map: " + x))
        .filter(x -> x % 2 == 0)
        .peek(x -> System.out.println("after filter: " + x))
        .limit(2)
        .peek(x -> System.out.println("after limit: " + x))
        .collect(toList());
    System.out.println("Result: " + result);
  }
}
