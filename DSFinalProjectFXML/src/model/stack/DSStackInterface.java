package model.stack;

public interface DSStackInterface<T> 
{
  void push(T element) throws StackOverflowException;
  // Throws StackOverflowException if this stack is full,
  // otherwise places element at top of stack.
 
  void pop() throws StackUnderflowException;
  // Throws StackUnderflowException if this stack is empty,
  // otherwise removed top element from this stack.
  
  T top() throws StackUnderflowException;
  // Throws StackUnderflowException if this stack is empty,
  // otherwise returns top element of stack.
  
  boolean isFull();
  // Returns true if this stack is full, otherwise false.
  
  boolean isEmpty();
  // Returns true if this stack is empty, otherwise false.
}