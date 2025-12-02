package composite;

import java.util.ArrayList;
import java.util.Objects;

public class TextComposite implements TextComponent {
  private ArrayList<TextComponent> components = new ArrayList<>();
  private ComponentType type;

  public TextComposite(ComponentType type) {
    this.type = type;
  }

  public TextComponent getChild(int index){
    return components.get(index);
  }
  @Override
  public void add(TextComponent textComponent) {
    components.add(textComponent);
  }

  @Override
  public void remove(TextComponent textComponent) {
    components.remove(textComponent);
  }
  @Override
  public void operation() {
    components.removeIf(Objects::isNull);
    components.forEach(TextComponent::operation);
  }

  @Override
  public int count() {
    return components.size();
  }

  @Override
  public String restore() {
    StringBuilder sb = new StringBuilder();
    components.forEach(component -> sb.append(component.restore()));
    return sb.toString();
  }
}
