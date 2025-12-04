package chain;

import composite.ComponentType;
import composite.TextComponent;
import composite.TextComposite;
import parser.TextParser;

import java.util.List;

public class ParserChainRunner {

  private TextParser chain;

  public ParserChainRunner(TextParser chain){
    this.chain = chain;
  }

  public TextComponent runChain(String text){
    TextComposite root = new TextComposite(ComponentType.TEXT);
    List<TextComponent> children = chain.parse(text);
    children.forEach(root::add);
    return root;
  }
}
