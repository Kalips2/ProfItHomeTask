package home3.task2.exceptions;

public class IncorrrectPropertyName extends RuntimeException{
  public IncorrrectPropertyName() {
    super();
  }

  public IncorrrectPropertyName(String message) {
    super(message);
  }
}
