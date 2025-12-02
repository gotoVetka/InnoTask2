package reader.impl;

import exception.TextException;
import reader.TextReader;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextReaderImpl implements TextReader {
  public static final Logger logger = LogManager.getLogger();
  @Override
  public String readText(String filepath) throws TextException{
    try{
      Path path = Paths.get(filepath);
      return Files.readString(path);
    } catch (IOException exception){
      logger.error("Error with reading file: {} \n {}", filepath, exception);
      throw new TextException(exception);
    }
  }
}
