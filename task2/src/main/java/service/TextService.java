package service;

import composite.TextComponent;
import composite.TextComposite;
import exception.TextException;

import java.util.List;

public interface TextService {

  TextComponent compositeText(String text) throws TextException;
  void swapWords(TextComposite text);
  List<TextComponent> sortByWordCount(TextComposite text);
}
