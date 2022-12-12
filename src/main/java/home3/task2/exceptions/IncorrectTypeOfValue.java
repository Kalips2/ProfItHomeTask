package home3.task2.exceptions;

public class IncorrectTypeOfValue extends RuntimeException{
  public IncorrectTypeOfValue() {
    super();
  }

  public IncorrectTypeOfValue(String message) {
    super(message);
  }
}
