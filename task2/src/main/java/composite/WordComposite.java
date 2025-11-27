package composite;

import java.util.ArrayList;
import java.util.Objects;

public class WordComposite implements Component{
  ArrayList<Component> words = new ArrayList<>();
  @Override
  public void add(Component component) {
    words.add(component);
  }

  @Override
  public void remove(Component component) {
    words.remove(component);
  }
  @Override
  public void operation() {
    words.removeIf(Objects::isNull);
    words.forEach(Component::operation);
  }
}
