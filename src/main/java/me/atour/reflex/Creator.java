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
   * @param <T> type parameter for the class type
   * @return the created instance
   * @throws InstantiationException when the class cannot be instantiated
   */
  @NonNull public <T> T instantiate(@NonNull Class<T> clazz) throws InstantiationException {
    return unsafe.allocateInstance(clazz);
  }
}
