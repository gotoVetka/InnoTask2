package service;

import composite.TextComponent;
import exception.TextException;

public interface TextService {
  TextComponent compositeText(String filepath) throws TextException;
  String restoreText(TextComponent root);
}
