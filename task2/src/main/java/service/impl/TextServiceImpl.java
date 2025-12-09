package service.impl;

import chain.ParserChainBuilder;
import chain.ParserChainRunner;
import composite.ComponentType;
import composite.TextComponent;
import composite.TextComposite;
import exception.TextException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.TextService;

import java.util.*;

public class TextServiceImpl implements TextService {
  private static final Logger logger = LogManager.getLogger();
  private final ParserChainBuilder chainBuilder;
  private final ParserChainRunner chainRunner;

  public TextServiceImpl() {
    this.chainBuilder = new ParserChainBuilder();
    this.chainRunner = new ParserChainRunner(chainBuilder.buildChain());

  }

  public int maxSentencesWithWords(TextComposite text) {
    List<TextComponent> sentences = getSentences(text);
    Map<String, Set<Integer>> wordToSentenceMap = new HashMap<>();

    for (int i = 0; i < sentences.size(); i++) {
      Set<String> words = extractWords(sentences.get(i));
      for (String word : words) {
        wordToSentenceMap.computeIfAbsent(word, k -> new HashSet<>()).add(i);
      }
    }

    return wordToSentenceMap.values().stream()
            .mapToInt(Set::size)
            .max()
            .orElse(0);
  }

  @Override
  public List<TextComponent> sortByWordCount(TextComposite text) {
    List<TextComponent> sentences = getSentences(text);
    sentences.sort(Comparator.comparingInt(this::countWords));
    return sentences;
  }

  @Override
  public void swapWords(TextComposite text) {
    List<TextComponent> sentences = getSentences(text);
    for (TextComponent sentence : sentences) {
      if (sentence instanceof TextComposite composite) {
        List<TextComponent> words = composite.getComponents();
        if (words.size() > 1) {
          Collections.swap(words, 0, words.size() - 1);
        }
      }
    }
  }


  @Override
  public TextComponent compositeText(String text) throws TextException {
    TextComponent root = chainRunner.runChain(text);
    return root;
  }

  private List<TextComponent> getSentences(TextComposite text) {
    List<TextComponent> sentences = new ArrayList<>();
    for (TextComponent paragraph : text.getComponents()) {
      if (paragraph instanceof TextComposite paragraphComposite) {
        sentences.addAll(paragraphComposite.getComponents());
      }
    }
    return sentences;
  }
  private Set<String> extractWords(TextComponent sentence) {
    Set<String> words = new HashSet<>();
    if (sentence instanceof TextComposite sentenceComposite) {
      for (TextComponent word : sentenceComposite.getComponents()) {
        if (word instanceof TextComposite wordComposite) {
          for (TextComponent component : wordComposite.getComponents()) {
            if (component.getType() == ComponentType.WORD) {
              words.add(component.toString().toLowerCase());
            }
          }
        }
      }
    }
    return words;
  }
  private int countWords(TextComponent sentence) {
    if (sentence instanceof TextComposite sentenceComposite) {
      return sentenceComposite.getComponents().size();
    }
    return 0;
  }
}
