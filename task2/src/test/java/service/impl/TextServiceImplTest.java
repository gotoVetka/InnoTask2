package service.impl;

import composite.TextComponent;
import exception.TextException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class TextServiceImplTest {

  private final String TEST_FILE_PATH_STRING = "src/resources/data/test.txt";
  private final Path TEST_FILE_PATH = Paths.get(TEST_FILE_PATH_STRING);

  private static final String ORIGINAL_TEXT_CONTENT =
          "It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n\n" +
                  "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English.\n\n" +
                  "It is a established fact that a reader will be of a page when looking at its layout.\n\n" +
                  "Bye.";

  private TextServiceImpl textService;
  private TextComponent rootComposite;

  @BeforeEach
  void setUp() throws TextException {
    try {
      Files.createDirectories(TEST_FILE_PATH.getParent());
      Files.writeString(TEST_FILE_PATH, ORIGINAL_TEXT_CONTENT);

      textService = new TextServiceImpl();
      rootComposite = textService.compositeText(TEST_FILE_PATH_STRING);

    } catch (IOException e) {
      fail("Failed to setup test file or read it: " + e.getMessage());
    }
  }

  @AfterEach
  void tearDown() throws IOException {
    Files.deleteIfExists(TEST_FILE_PATH);
  }

  @Test
  void restoreTextNullRoot() {
    assertNotNull(rootComposite, "Root composite should not be null after processText");
  }

  @Test
  void restoreText() throws TextException {
    assertNotNull(rootComposite, "rootComposite must be available for restoration.");
    String expectedNormalized = ORIGINAL_TEXT_CONTENT.replaceAll("\\s+", " ").trim();
    String actualRestoredText = textService.restoreText(rootComposite);
    String actualNormalized = actualRestoredText.replaceAll("\\s+", " ").trim();
    assertEquals(expectedNormalized, actualNormalized, "Restored text must match the original content (normalized).");
  }
}