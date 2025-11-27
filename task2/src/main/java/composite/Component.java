package composite;

public interface Component {
  void add(Component component);
  void remove(Component component);
  void operation();
}
