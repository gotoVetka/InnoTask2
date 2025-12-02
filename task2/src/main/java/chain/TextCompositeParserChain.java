package chain;

import composite.ComponentType;
import composite.TextComponent;
import composite.TextComposite;
import constant.ComponentRegex;
import parser.TextParser;
import parser.impl.TextParserImpl;

import java.util.List;

public class TextCompositeParserChain {
  private TextParser enter;

  public TextCompositeParserChain(){
    TextParser text = new TextParserImpl(ComponentType.TEXT, ComponentRegex.TEXT);
    TextParser paragraph = new TextParserImpl(ComponentType.PARAGRAPH, ComponentRegex.PARAGRAPH);
    TextParser sentence = new TextParserImpl(ComponentType.SENTENCE, ComponentRegex.SENTENCE);
    TextParser word = new TextParserImpl(ComponentType.WORD, ComponentRegex.WORD);
    TextParser symbol = new TextParserImpl(ComponentType.LETTER, ComponentRegex.SYMBOL);
    text.setNextParser(paragraph);
    paragraph.setNextParser(sentence);
    sentence.setNextParser(word);
    word.setNextParser(symbol);

    this.enter = text;
  }
  public TextComponent parse(String text){
    TextComposite root = new TextComposite(ComponentType.TEXT);
    List<TextComponent> children = enter.parse(text);
    children.forEach(root::add);
    return root;
  }
}
