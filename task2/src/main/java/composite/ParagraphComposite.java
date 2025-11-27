package composite;

import java.util.ArrayList;
import java.util.Objects;

public class ParagraphComposite implements Component{
  ArrayList<Component> paragraphs = new ArrayList<>();
  @Override
  public void add(Component component) {
    paragraphs.add(component);
  }

  @Override
  public void remove(Component component) {
    paragraphs.remove(component);
  }
  @Override
  public void operation() {
    paragraphs.removeIf(Objects::isNull);
    paragraphs.forEach(Component::operation);
  }
}
