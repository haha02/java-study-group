package collection_api_enhancement;

import static java.util.Map.entry;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class ListSetMapFactoryTest {

  @Test
  void testListFactory() {
    List<String> members = List.of("Dennis", "Riley");
//    members.set(0, "Jeff"); // throw UnsupportedOperationException
//    members.add("Jeff"); // throw UnsupportedOperationException
  }

  @Test
  void testPrintTypeOfVarargs() {
    varargsMethod();
  }

  @Test
  void testSetFactory() {
    Set<String> members = Set.of("Dennis", "Riley");
    // Set<String> members = Set.of("Dennis", "Riley", "Riley"); // throw IllegalArgumentException
  }

  @Test
  void testMapFactory() {
    Map<String, Integer> ageOfMembers
        = Map.of("Dennis", 25, "Riley", 18);
    System.out.println("ageOfMembers: " + ageOfMembers);

    Map<String, Integer> ageOfMembersOverTen
        = Map.ofEntries(
            entry("Dennis", 25),
            entry("Riley", 18) //, ...
          );
    System.out.println("ageOfMembersOverTen: " + ageOfMembersOverTen);
  }

  private void varargsMethod(String... names) {
    System.out.println("Type of names: " + names.getClass().getName());
  }
}
