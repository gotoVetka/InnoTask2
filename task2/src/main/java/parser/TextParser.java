package parser;

import composite.ComponentType;
import composite.TextComponent;

import java.util.List;

public interface TextParser {
  public List<TextComponent> parse(String string);
  void setNextParser(TextParser next);
}
