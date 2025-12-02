package exception;

public class TextException extends Exception{
  public TextException() {
    super();
  }

  public TextException(String message) {
    super(message);
  }

  public TextException(Throwable reason) {
    super(reason);
  }

  public TextException(Throwable reason, String message) {
    super(message, reason);
}
