package parser;

import composite.ComponentType;
import composite.TextComponent;
import composite.TextComposite;
import composite.TextLeaf;
import constant.ComponentRegex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AbstractTextParser implements TextParser {
  protected final String COMPONENT_REGEX;
  protected TextParser successor;
  protected ComponentType type;

  public AbstractTextParser(ComponentType type, String componentRegex) {
    COMPONENT_REGEX = componentRegex;
    this.type = type;
  }

  public void setNextParser(AbstractTextParser next){
    successor = next;
  }

  @Override
  public List<TextComponent> parse(String string) {
    List<TextComponent> result = new ArrayList<>();
    Pattern pattern = Pattern.compile(COMPONENT_REGEX);
    Matcher matcher = pattern.matcher(string);
    while (matcher.find()) {
      String fragment = matcher.group();

      if (successor == null) {
        result.addAll(parseSymbols(fragment));
        continue;
      }
      TextComposite composite = new TextComposite(type);
      List<TextComponent> children = successor.parse(fragment);
      for (TextComponent c : children) {
        composite.add(c);
      }
      result.add(composite);
    }
    return result;
  }

  @Override
  public void setNextParser(TextParser next) {
    successor = next;
  }

  private List<TextComponent> parseSymbols(String word) {
    List<TextComponent> symbols = new ArrayList<>();
    for (char c : word.toCharArray()) {
      if (Character.isLetterOrDigit(c)) {
        symbols.add(new TextLeaf(c, ComponentType.LETTER));
      } else {
        symbols.add(new TextLeaf(c, ComponentType.PUNCTUATION));
      }
    }
    return symbols;
  }
}


