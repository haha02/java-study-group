package collection_api_enhancement;

import static collection_api_enhancement.CollectionFactory.genListOfMember;
import static collection_api_enhancement.CollectionFactoryTest.logAction;

import java.util.List;
import org.junit.jupiter.api.Test;

public class CollectionModifierTest {

  @Test
  void testRemoveIf() {
    List<String> memList = genListOfMember();
    logAction(memList, l -> l.removeIf(name -> "Riley".equals(name)));
  }

  @Test
  void testReplaceAll() {
    List<String> memList = genListOfMember();
    logAction(memList, l -> l.replaceAll(String::toUpperCase));
  }
}
