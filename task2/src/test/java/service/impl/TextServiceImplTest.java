package service.impl;

import composite.TextComponent;
import composite.TextComposite;
import exception.TextException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reader.TextReader;
import reader.impl.TextReaderImpl;
import service.TextService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class TextServiceImplTest {

  private static final Logger log = LoggerFactory.getLogger(TextServiceImplTest.class);
  private final String TEST_FILE_PATH_STRING = "src/resources/data/test.txt";
  private final Path TEST_FILE_PATH = Paths.get(TEST_FILE_PATH_STRING);
  private static final String SENTENCE = "John loves Cats.";
  private static final String ORIGINAL_TEXT_CONTENT =
          "It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n\n" +
                  "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English.\n\n" +
                  "It is a established fact that a reader will be of a page when looking at its layout.\n\n" +
                  "Bye.";

  private TextService textService;
  private TextComponent rootComposite;
  private TextComponent sentenceComposite;
  private TextReader reader;

  @BeforeEach
  void setUp() throws TextException {
    try {
      Files.createDirectories(TEST_FILE_PATH.getParent());
      Files.writeString(TEST_FILE_PATH, ORIGINAL_TEXT_CONTENT);
      textService = new TextServiceImpl();
      reader = new TextReaderImpl();
      String rootText = reader.readText(TEST_FILE_PATH_STRING);
      rootComposite = textService.compositeText(rootText);
      sentenceComposite = textService.compositeText(SENTENCE);

    } catch (IOException e) {
      fail("Failed to setup test file or read it: " + e.getMessage());
    }
  }

  @AfterEach
  void tearDown() throws IOException {
    Files.deleteIfExists(TEST_FILE_PATH);
  }

  @Test
  void swapWords(){
    textService.swapWords((TextComposite) sentenceComposite);
    String expectedNormalized = SENTENCE.replaceAll("\\s+", " ").trim();
    String actualRestoredText = sentenceComposite.restore();
    String actualNormalized = actualRestoredText.replaceAll("\\s+", " ").trim();
    assertEquals("Cats. loves John", actualNormalized);
  }

  @Test
  void sortByWordCount(){

  }

  @Test
  void restoreTextNullRoot() {
    assertNotNull(rootComposite, "Root composite should not be null after processText");
  }

  @Test
  void restoreText() throws TextException {
    String expectedNormalized = ORIGINAL_TEXT_CONTENT.replaceAll("\\s+", " ").trim();
    String actualRestoredText = rootComposite.restore();
    String actualNormalized = actualRestoredText.replaceAll("\\s+", " ").trim();
    assertEquals(expectedNormalized, actualNormalized);
  }
}