package composite;

public interface TextComponent {
  void add(TextComponent textComponent);
  void remove(TextComponent textComponent);
  void operation();
  int count();
  String restore();
}
