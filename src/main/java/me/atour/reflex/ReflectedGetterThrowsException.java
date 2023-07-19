package me.atour.reflex;

/**
 * Thrown when a reflected getter throws a {@link Throwable}.
 */
public class ReflectedGetterThrowsException extends RuntimeException {

  /**
   * Constructs the {@link RuntimeException} with its cause.
   *
   * @param cause the {@link Throwable} that caused this exception to be thrown
   */
  public ReflectedGetterThrowsException(Throwable cause) {
    super(cause);
  }
}
