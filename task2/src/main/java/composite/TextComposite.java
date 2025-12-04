package composite;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.StringJoiner;

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
    switch (type){
      case TEXT -> {
        components.forEach(component -> {
          sb.append(component.restore());
          sb.append("\t\t");
        });
      }
      case PARAGRAPH -> {
        components.forEach(component -> {
          sb.append(component.restore());
          sb.append("\n");
        });
      }
      case WORD, SENTENCE -> {
        components.forEach(component -> {
          sb.append(component.restore());
        });
        return sb.toString();
      }
      case LETTER, PUNCTUATION -> {
        return  "";
      }
    }
    return sb.toString();
  }
}

