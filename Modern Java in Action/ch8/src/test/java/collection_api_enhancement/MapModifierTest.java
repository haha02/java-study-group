package collection_api_enhancement;

import static java.util.Map.entry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.jupiter.api.Test;

public class MapModifierTest {

  @Test
  void testSorting() {
    Map<String, Integer> memberHeight
        = genMemberHeightMap();
    System.out.println("Sort by name");
    memberHeight
        .entrySet()
        .stream()
        .sorted(Entry.comparingByKey()) // sort by name
        .forEachOrdered(System.out::println);

    System.out.println("Sort by height");
    memberHeight
        .entrySet()
        .parallelStream()
        .sorted(Entry.comparingByValue()) // sort by name
        .forEachOrdered(System.out::println);
  }

  @Test
  void testGetDefault() {
    Map<String, Integer> memberHeight = genMemberHeightMap();
    System.out.println(memberHeight.getOrDefault("Jeff", 180));
    System.out.println(memberHeight.getOrDefault("Riley", 180));
  }

  @Test
  void testComputeIfAbsent() {
    Map<String, List<String>> memberTasks = new HashMap<>();
    memberTasks
        .computeIfAbsent("Jean", name -> new ArrayList<>())
        .add("JavaInAction");
    System.out.println(memberTasks);
  }

  @Test
  void testRemoveIfAbsent() {
    Map<String, String> memberTasks = new HashMap<>();
    memberTasks.put("Jean", "JavaInAction");
    System.out.println(memberTasks);

    memberTasks.remove("Jean", "Java");
    System.out.println(memberTasks);

    memberTasks.remove("Jean", "JavaInAction");
    System.out.println(memberTasks);
  }

  @Test
  void testReplacementPattern() {
    Map<String, String> memberNames = new HashMap<>();
    memberNames.put("Jeff", "Wang");
    memberNames.put("Jean", "Hu");
    memberNames.replaceAll(
        (firstName, lastName) -> lastName.toUpperCase());
    System.out.println(memberNames);
  }

  @Test
  void testMerge() {
    Map<String, String> frontend = Map.ofEntries(
        entry("Sean", "Chou"),
        entry("Jeff", "Wang"));

    Map<String, String> backend = Map.ofEntries(
        entry("Sean", "Chou"),
        entry("Jeff", "Wang"),
        entry("Joey", "Wu"),
        entry("Jimmy", "Liao"));

    Map<String, String> dev = new HashMap<>(frontend);
    backend.forEach((k, v) -> dev.merge(k, v, String::concat));
    System.out.println(dev);
  }

  private Map<String, Integer> genMemberHeightMap() {
    return Map.ofEntries(
        entry("Jeff", 175),
        entry("Sean", 170),
        entry("Jean", 160),
        entry("Joey", 168)
    );
  }
}
