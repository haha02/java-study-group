package collection_api_enhancement;

import static collection_api_enhancement.CollectionFactory.*;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import org.junit.jupiter.api.Test;

class CollectionFactoryTest {

  @Test
  public void genListOfMember_PrintMembers() {
    List<String> memList = genListOfMember();
    logAction(memList, l -> l.set(0, "Jeff"));
    logAction(memList, l -> l.add("Jeff"));
  }

  @Test
  public void genListOfMemberUsingCollectionFactory_ModifyMembers() {
    List<String> memList = genListOfMemberUsingCollectionFactory();
    logAction(memList, l -> l.set(0, "Jeff"));
//    logAction(memList, l -> l.add("Jeff")); // throw UnsupportedOperationException
  }

  @Test
  void genSetOfMemberUsingCollectionFactory_ModifyMembers() {
    Set<String> memSet = genSetOfMemberUsingCollectionFactory();
    logAction(memSet, s -> s.add("Jeff"));
  }

  @Test
  void genSetOfMemberUsingStream_ModifyMembers() {
    Set<String> memSet = genSetOfMemberUsingStream(); // Get a mutable Set
    logAction(memSet, s -> s.add("Jeff"));
  }

  public static <T> void logAction(T objUnderTest, Consumer<T> action) {
    System.out.println("Before: " + objUnderTest);
    action.accept(objUnderTest);
    System.out.println("After: " + objUnderTest);

  }
}