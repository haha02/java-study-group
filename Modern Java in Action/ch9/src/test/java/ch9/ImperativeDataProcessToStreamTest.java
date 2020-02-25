package ch9;

import static ch9.Dish.make;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ImperativeDataProcessToStreamTest {

  @Test
  @DisplayName("Convert data processes of a collection with typical data processing patterns with the Streams API")
  void refactorToStream() {
    List<Dish> menu = List.of(
        make("beef", 900),
        make("apple", 100),
        make("lettuce", 50));
    List<String> dishNames = new ArrayList<>();
    for(Dish dish : menu) {
      if(dish.getCalories() >= 100){
        dishNames.add(dish.getName());
      }
    }
    System.out.println(dishNames);

    dishNames = menu.stream()
        .filter(d -> d.getCalories() >= 100)
        .map(Dish::getName)
        .collect(toList());
    System.out.println(dishNames);
  }
}
