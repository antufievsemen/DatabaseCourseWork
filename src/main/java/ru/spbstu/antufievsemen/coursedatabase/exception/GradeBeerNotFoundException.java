package ru.spbstu.antufievsemen.coursedatabase.exception;

public class GradeBeerNotFoundException extends RuntimeException{
  public GradeBeerNotFoundException(String message) {
    super(message);
  }
}
