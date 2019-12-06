package collection_api_enhancement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionFactory {
  public static List<String> genListOfMember() {
    List<String> members = new ArrayList<>();
    members.add("Dennis");
    members.add("Riley");
    return members;
  }

  public static List<String> genListOfMemberUsingCollectionFactory() {
    return Arrays.asList("Dennis", "Riley");
  }

  public static Set<String> genSetOfMemberUsingCollectionFactory() {
    return new HashSet<>(Arrays.asList("Dennis", "Riley"));
  }

  public static Set<String> genSetOfMemberUsingStream() {
    return Stream.of("Dennis", "Riley").collect(Collectors.toSet());
  }
}
