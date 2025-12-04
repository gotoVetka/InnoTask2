package exception;

public class TextException extends Exception {
  public TextException() {
    super();
  }

  public TextException(String message) {
    super(message);
  }

  public TextException(Throwable cause) {
    super(cause);
  }

  public TextException(Throwable cause, String message) {
    super(message, cause);
  }
}
