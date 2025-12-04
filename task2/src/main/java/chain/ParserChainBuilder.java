package chain;

import composite.ComponentType;
import composite.ComponentRegex;
import parser.TextParser;
import parser.impl.TextParserImpl;

public class ParserChainBuilder {
  private TextParser chainInstance;

  public ParserChainBuilder(){}

  public TextParser getChainInstance() {
    return chainInstance;
  }

  public TextParser buildChain(){
    if(chainInstance == null){
      TextParser text = new TextParserImpl(ComponentType.TEXT, ComponentRegex.TEXT);
      TextParser paragraph = new TextParserImpl(ComponentType.PARAGRAPH, ComponentRegex.PARAGRAPH);
      TextParser sentence = new TextParserImpl(ComponentType.SENTENCE, ComponentRegex.SENTENCE);
      TextParser word = new TextParserImpl(ComponentType.WORD, ComponentRegex.WORD);
      TextParser symbol = new TextParserImpl(ComponentType.LETTER, ComponentRegex.SYMBOL);
      text.setNextParser(paragraph);
      paragraph.setNextParser(sentence);
      sentence.setNextParser(word);
      word.setNextParser(symbol);
      chainInstance = text;
    }
    return chainInstance;
  }
}
