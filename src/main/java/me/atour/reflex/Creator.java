package me.atour.reflex;

import lombok.NonNull;

/**
 * Creates and modifies class instances.
 */
public class Creator {

  /**
   * Layer over {@link sun.misc.Unsafe}.
   */
  private static final Unsafer unsafe = new Unsafer();

  /**
   * Instantiates a class instance.
   *
   * @param clazz the {@link Class} for which to create the instance
   * @return the created instance
   * @param <T> type parameter for the class type
   * @throws InstantiationException when the class cannot be instantiated
   */
  @NonNull public <T> T instantiate(@NonNull Class<T> clazz) throws InstantiationException {
    return unsafe.allocateInstance(clazz);
  }
}
