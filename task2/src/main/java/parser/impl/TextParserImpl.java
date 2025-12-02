package parser.impl;

import composite.ComponentType;
import parser.AbstractTextParser;

public class TextParserImpl extends AbstractTextParser {
  public TextParserImpl(ComponentType type, String regex){
    super(type, regex);
  }
}
