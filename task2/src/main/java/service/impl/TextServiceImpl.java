package service.impl;

import chain.ParserChainBuilder;
import chain.ParserChainRunner;
import composite.TextComponent;
import exception.TextException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reader.TextReader;
import reader.impl.TextReaderImpl;
import service.TextService;

public class TextServiceImpl implements TextService {
  private static final Logger logger = LogManager.getLogger();
  private final ParserChainBuilder chainBuilder;
  private final ParserChainRunner chainRunner;
  private final TextReader reader;

  public TextServiceImpl() {
    this.chainBuilder = new ParserChainBuilder();
    this.chainRunner = new ParserChainRunner(chainBuilder.buildChain());
    this.reader = new TextReaderImpl();
  }


  @Override
  public TextComponent compositeText(String filepath) throws TextException {
    String textFromFile = reader.readText(filepath);
    TextComponent root = chainRunner.runChain(textFromFile);
    return root;
  }

  @Override
  public String restoreText(TextComponent root) {
    String restoredText = "";
    if(root!=null){
      restoredText = root.restore();
    }
    return restoredText;
  }
}
