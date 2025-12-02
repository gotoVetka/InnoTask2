package composite;

public class TextLeaf implements TextComponent {
  private char symbol;
  private ComponentType type;

  public TextLeaf(char symbol, ComponentType type) {
    this.symbol = symbol;
    this.type = type;
  }

  @Override
  public void add(TextComponent textComponent) throws UnsupportedOperationException{
    throw new UnsupportedOperationException();
  }

  @Override
  public void remove(TextComponent textComponent) throws UnsupportedOperationException{
    throw new UnsupportedOperationException();
  }

  @Override
  public void operation() {

  }

  @Override
  public int count() {
    return 1;
  }

  @Override
  public String restore() {
    return String.valueOf(symbol);
  }
}
