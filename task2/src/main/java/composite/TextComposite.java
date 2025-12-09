package composite;

import java.util.ArrayList;

public class TextComposite implements TextComponent {
  private ArrayList<TextComponent> components = new ArrayList<>();
  private ComponentType type;

  public TextComposite(ComponentType type) {
    this.type = type;
  }

  public ArrayList<TextComponent> getComponents() {
    return components;
  }

  public TextComponent getChild(int index){
    return components.get(index);
  }

  public ComponentType getType() {
    return this.type;
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
    components.forEach(TextComponent::operation);
  }

  @Override
  public int count() {
    int result = 0;
    for (TextComponent component : components){
      result += component.count();
    }
    return result;
  }

  @Override
  public String restore() {
    StringBuilder sb = new StringBuilder();
    components.forEach(component -> {
      sb.append(component.restore());
      sb.append(component.getType().SEPARATOR);
    });
    return sb.toString();
  }
}

