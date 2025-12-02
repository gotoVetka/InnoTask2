package reader;

import exception.TextException;

public interface TextReader {
  String readText(String filepath) throws TextException;
}
