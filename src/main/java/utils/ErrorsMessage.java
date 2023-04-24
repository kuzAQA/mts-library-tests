package utils;

public class ErrorsMessage {

  public static String errorMessage(String name) {
    return String.format("Поле %s не соответствует ожидаемому результату.", name);
  }
}
