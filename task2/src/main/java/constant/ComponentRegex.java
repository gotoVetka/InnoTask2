package constant;


public class ComponentRegex{
  public static final String TEXT = "(.+(\\r?\\n|$))";
  public static final String PARAGRAPH = "([^.!?]+[.!?]+)\\s*";
  public static final String SENTENCE = "([\\w'-]+(?:[.,:;!?%]+)?|[^\\w\\s]+)";
  public static final String WORD = "([\\w'-]+(?:[.,:;!?%]+)?|[^\\w\\s]+)";
  public static final String SYMBOL = ".";
}
