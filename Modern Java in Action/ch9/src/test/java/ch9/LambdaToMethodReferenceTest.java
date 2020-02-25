package ch9;

import static ch9.Dish.make;
import static java.util.stream.Collectors.groupingBy;

import ch9.Dish.CaloricLevel;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LambdaToMethodReferenceTest {

  @Test
  @DisplayName("Using method references whenever possible to improve code readability")
  void refactorToTellDontAsk() {
    List<Dish> menu = List.of(make("beef", 900), make("lettuce", 50));
    Map<CaloricLevel, List<Dish>> dishesByCaloricLevel =
        menu.stream()
            .collect(
                groupingBy(Dish::getCaloricLevel));

    dishesByCaloricLevel.forEach((k, v) -> {
      System.out.println("CaloricLevel: " + k);
      v.forEach(d -> System.out.println("Food: " + d.getName()));
    });
  }

}
