package collection_api_enhancement;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class ConcurrentHashMapTest {

  @Test
  void testReduceMethod() {
    ConcurrentHashMap<String, Integer> ageOfMember = genImmutableAgeOfMemberMap();
    int threshold = 1;
    String memBio = ageOfMember.reduce(
        threshold,
        (name, age) -> name + ": " + age + " ",
        String::concat);
    System.out.println(memBio);

    System.out.println("Map size: " + ageOfMember.mappingCount());
  }

  @Test
  void testMapSetViews() {
//    ConcurrentHashMap<String, Integer> ageOfMember = genImmutableAgeOfMemberMap();
    ConcurrentHashMap<String, Integer> ageOfMember = genMutableAgeOfMemberMap();
    System.out.println("Before remove: " + ageOfMember);
    ageOfMember.keySet().remove("Jeff");
    System.out.println("After remove keySet: " + ageOfMember);
    ageOfMember.newKeySet().remove("Dennis");
    System.out.println("After remove newKeySet: " + ageOfMember);
  }

  private ConcurrentHashMap<String, Integer> genImmutableAgeOfMemberMap() {
    return new ConcurrentHashMap<>(
        Map.of(
            "Mark", 15,
            "Dennis", 25,
            "Riley", 18,
            "Joey", 18,
            "Jimmy", 23,
            "Sean", 20,
            "Jeff", 25)
    );
  }

  private ConcurrentHashMap<String, Integer> genMutableAgeOfMemberMap() {
    return new ConcurrentHashMap<>(
        Stream.of(
            new SimpleEntry<>("Mark", 15),
            new SimpleEntry<>("Dennis", 25),
            new SimpleEntry<>("Riley", 18),
            new SimpleEntry<>("Joey", 18),
            new SimpleEntry<>("Jimmy", 23),
            new SimpleEntry<>("Sean", 20),
            new SimpleEntry<>("Jeff", 25)
        ).collect(
            Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)
        )
    );
  }
}
