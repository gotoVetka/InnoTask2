package parser;

import composite.TextComponent;

import java.util.List;

public interface TextParser {
  List<TextComponent> parse(String string);
  void setNextParser(TextParser next);
}
