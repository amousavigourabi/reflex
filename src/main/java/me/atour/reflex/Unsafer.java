package me.atour.reflex;

import java.lang.reflect.Field;

/**
 * Deals with the calls to {@link sun.misc.Unsafe}.
 */
class Unsafer {

  /**
   * The {@link sun.misc.Unsafe} instance we use.
   */
  private static final sun.misc.Unsafe unsafe;

  static {
    try {
      Field unsafeField = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
      unsafeField.setAccessible(true);
      unsafe = (sun.misc.Unsafe) unsafeField.get(null);
    } catch (ReflectiveOperationException e) {
      throw new RuntimeException();
    }
  }

  /**
   * Instantiates a class without running the constructor.
   *
   * @see sun.misc.Unsafe#allocateInstance(Class)
   */
  public <T> T allocateInstance(Class<T> clazz) throws InstantiationException {
    return (T) unsafe.allocateInstance(clazz);
  }
}
