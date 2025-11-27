package composite;

import java.util.ArrayList;
import java.util.Objects;

public class SentenceComposite implements Component{
  ArrayList<Component> sentences = new ArrayList<>();
  @Override
  public void add(Component component) {
    sentences.add(component);
  }

  @Override
  public void remove(Component component) {
    sentences.remove(component);
  }
  @Override
  public void operation() {
    sentences.removeIf(Objects::isNull);
    sentences.forEach(Component::operation);
  }
}
