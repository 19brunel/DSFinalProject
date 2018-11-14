package model.stack;

public class StackUnderflowException extends RuntimeException {

 public StackUnderflowException(String s)
 {
  super(s);
 }
}