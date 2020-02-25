package ch9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExecuteAroundTest {

  @Test
  @DisplayName("Reuse the logic dealing with the preparation and cleanup phases")
  void reuseExecuteAround() {
    String oneLine =
        processFile(BufferedReader::readLine);
    String twoLines =
        processFile(b -> String.format("%s, %s", b.readLine(), b.readLine()));
    System.out.println("First line: " + oneLine);
    System.out.println("Two lines: " + twoLines);
  }

  public static String processFile(BufferedReaderProcessor p) {
    try(BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
      return p.process(br);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "";
  }

  public interface BufferedReaderProcessor {
    String process(BufferedReader b) throws IOException;
  }
}
