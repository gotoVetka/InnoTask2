package composite;

public enum ComponentType {

  TEXT("(.+(\\r?\\n|$))", "\t\t"),
  PARAGRAPH("([^.!?]+[.!?]+)\\s*", "\n"),
  SENTENCE("([\\w'-]+(?:[.,:;!?%]+)?|[^\\w\\s]+)", ""),
  WORD("([\\w'-]+(?:[.,:;!?%]+)?|[^\\w\\s]+)", " "),
  LETTER(".", ""),
  PUNCTUATION("[.,!?;:'\"(){}\\[\\]<>\\-—]", "");

  public final String REGEX;
  public final String SEPARATOR;

  ComponentType(String REGEX, String SEPARATOR) {
    this.REGEX = REGEX;
    this.SEPARATOR = SEPARATOR;
  }
}

