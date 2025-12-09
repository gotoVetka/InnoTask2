package chain;

import composite.ComponentType;
import composite.TextComponent;
import composite.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parser.TextParser;

import java.util.List;

public class ParserChainRunner {
  private static final Logger logger = LogManager.getLogger();
  private TextParser chain;

  public ParserChainRunner(TextParser chain){
    this.chain = chain;
  }

  public TextComposite runChain(String text){
    TextComposite root = new TextComposite(ComponentType.TEXT);
    List<TextComponent> children = chain.parse(text);
    children.forEach(root::add);
    return root;
  }
}
